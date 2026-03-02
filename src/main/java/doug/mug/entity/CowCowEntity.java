package doug.mug.entity;

import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.AnimalMateGoal;
import net.minecraft.entity.ai.goal.EscapeDangerGoal;
import net.minecraft.entity.ai.goal.FollowParentGoal;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.passive.AbstractHorseEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import static doug.mug.registry.ModEntities.COW_COW;

public class CowCowEntity extends AbstractHorseEntity {

    public CowCowEntity(EntityType<? extends AbstractHorseEntity> type, World world) {
        super(type, world);
    }
    private boolean fullOfMilk = false;
    public static DefaultAttributeContainer.Builder createAttributes() {
        return AnimalEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 20.0D)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25D);
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
                if (!this.getWorld().isClient && i == 0 && this.canEat()) {
                    this.eat(player, hand, held);
                    this.lovePlayer(player);
                    return ActionResult.SUCCESS;
                }
                if (this.isBaby()) {
                    this.eat(player, hand, held);
                    this.growUp(CowCowEntity.toGrowUpAge(-i), true);
                    return ActionResult.success(this.getWorld().isClient);
                }
                if (this.getWorld().isClient) {
                    return ActionResult.CONSUME;
                }
            }
            if (!this.fullOfMilk && held.isOf(Items.MILK_BUCKET) && !this.isBaby()) {
                this.fullOfMilk = true;
                held.decrementUnlessCreative(1, player);
                player.playSound(SoundEvents.BLOCK_MUD_STEP, 1.0f, 1.0f);
                player.getInventory().offerOrDrop(new ItemStack(Items.BUCKET));
                return ActionResult.SUCCESS;
            }
            if (this.fullOfMilk && held.isOf(Items.BUCKET) && !this.isBaby()) {
                this.fullOfMilk = false;
                held.decrementUnlessCreative(1, player);
                player.playSound(SoundEvents.ITEM_BUCKET_FILL, 1.0f, 1.0f);
                player.getInventory().offerOrDrop(new ItemStack(Items.MILK_BUCKET));
                return ActionResult.SUCCESS;
            }
        }
        // 1) Taming with MILK_BUCKET
        if (!this.isTame()) {
            if (held.isOf(Items.MILK_BUCKET)) {
                if (!this.getEntityWorld().isClient()) {
                    held.decrementUnlessCreative(1, player);
                    this.bondWithPlayer(player);
                }
                return ActionResult.SUCCESS;
            }

            // IMPORTANT: do NOT call super here, or vanilla will mount them anyway.
            return ActionResult.PASS;
        }

        // 2) “Saddling” with OAK_LOG (internally equips a real saddle)
        if (this.saddleNotEquipped()) {
            if (held.isOf(Blocks.OAK_LOG.asItem())) {
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
        return other instanceof CowCowEntity && this.isInLove() && other.isInLove();
    }

    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return COW_COW.create(world);
    }
}
