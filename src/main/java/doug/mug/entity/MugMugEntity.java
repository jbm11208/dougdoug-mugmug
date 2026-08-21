package doug.mug.entity;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.AnimalMateGoal;
import net.minecraft.entity.ai.goal.EscapeDangerGoal;
import net.minecraft.entity.ai.goal.FollowParentGoal;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.AbstractHorseEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import doug.mug.sound.ModSounds;

import static doug.mug.registry.ModEntities.MUG_MUG;

public class MugMugEntity extends AbstractHorseEntity {

    public MugMugEntity(EntityType<? extends AbstractHorseEntity> type, World world) {
        super(type, world);
    }
    private boolean fullOfWater = false;
    public static DefaultAttributeContainer.Builder createAttributes() {
        return AnimalEntity.createMobAttributes()
                .add(EntityAttributes.MAX_HEALTH, 20.0D)
                .add(EntityAttributes.MOVEMENT_SPEED, 0.25D)
                .add(EntityAttributes.TEMPT_RANGE, 64.0D);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new EscapeDangerGoal(this, 1.2D));
        this.goalSelector.add(2, new AnimalMateGoal(this, 1.0D));
        this.goalSelector.add(3, new TemptGoal(this, 1.0D, stack -> stack.isOf(Items.CLAY_BALL), false));
        this.goalSelector.add(4, new FollowParentGoal(this, 1.0D));
        this.goalSelector.add(5, new WanderAroundFarGoal(this, 1.0D));
        this.goalSelector.add(6, new LookAroundGoal(this));
    }

    public boolean saddleNotEquipped() {
        return !this.hasStackEquipped(EquipmentSlot.CHEST);
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return ModSounds.MUG_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.MUG_DEATH;
    }

    @Override
    @Nullable
    public LivingEntity getControllingPassenger() {
        if (!this.isTame() || this.saddleNotEquipped()) {
            return null;
        }
        Entity first = this.getFirstPassenger();
        return first instanceof PlayerEntity p ? p : null;
    }

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        // vanilla early exit
        if (this.hasPassengers() || this.isBaby()) {
            return super.interactMob(player, hand);
        }

        // Sneak-right click opens the horse inventory ONLY if tamed (vanilla behavior)
        if (this.isTame() && player.shouldCancelInteraction()) {
            this.openInventory(player);
            return ActionResult.SUCCESS;
        }

        ItemStack held = player.getStackInHand(hand);

        if (!held.isEmpty()) {
            if (this.isBreedingItem(held)) {
                int i = this.getBreedingAge();
                if (!this.getEntityWorld().isClient() && i == 0 && this.canEat()) {
                    this.eat(player, hand, held);
                    this.lovePlayer(player);
                    return ActionResult.SUCCESS;
                }
                if (this.isBaby()) {
                    this.eat(player, hand, held);
                    this.growUp(MugMugEntity.toGrowUpAge(-i), true);
                    return ActionResult.SUCCESS;
                }
                if (this.getEntityWorld().isClient()) {
                    return ActionResult.CONSUME;
                }
            }
            if (!this.fullOfWater && held.isOf(Items.WATER_BUCKET) && !this.isBaby()) {
                this.fullOfWater = true;
                held.decrementUnlessCreative(1, player);
                player.playSound(SoundEvents.ITEM_BUCKET_EMPTY, 1.0f, 1.0f);
                player.getInventory().offerOrDrop(new ItemStack(Items.BUCKET));
                return ActionResult.SUCCESS;
            }
            if (this.fullOfWater && held.isOf(Items.BUCKET) && !this.isBaby()) {
                this.fullOfWater = false;
                held.decrementUnlessCreative(1, player);
                player.playSound(SoundEvents.ITEM_BUCKET_FILL, 1.0f, 1.0f);
                player.getInventory().offerOrDrop(new ItemStack(Items.WATER_BUCKET));
                return ActionResult.SUCCESS;
            }
        }

        // 1) Taming with SUSPICIOUS_STEW
        if (!this.isTame()) {
            if (held.isOf(Items.SUSPICIOUS_STEW)) {
                if (!this.getEntityWorld().isClient()) {
                    held.decrementUnlessCreative(1, player);
                    this.bondWithPlayer(player);
                }
                return ActionResult.SUCCESS;
            }

            // IMPORTANT: do NOT call super here, or vanilla will mount them anyway.
            return ActionResult.PASS;
        }

        // 2) “Saddling” with BOWL (internally equips a real saddle)
        if (this.saddleNotEquipped()) {
            if (held.isOf(Items.BOWL.asItem())) {
                if (!this.getEntityWorld().isClient()) {
                    held.decrementUnlessCreative(1, player);

                    // Equip a real saddle so vanilla GUI/control logic works
                    this.equipStack(EquipmentSlot.CHEST, new ItemStack(Items.SADDLE));
                }
                return ActionResult.SUCCESS;
            }

            // Not saddled yet: no mounting, no super()
            return ActionResult.PASS;
        }

        // 3) Mounting only after tame + saddle equipped
        this.putPlayerOnBack(player);
        return ActionResult.SUCCESS;
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.isOf(Items.CLAY_BALL);
    }

    @Override
    public boolean canBreedWith(AnimalEntity other) {
        if (other == this) return false;
        return other instanceof MugMugEntity && this.isInLove() && other.isInLove();
    }

    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity mate) {
        return MUG_MUG.create(world, SpawnReason.BREEDING);
    }
}
