package doug.mug.entity;

import doug.mug.sound.ModSounds;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.FollowParentGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.animal.equine.AbstractHorse;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import static doug.mug.registry.ModEntities.MELON_MELON;

public class MelonMelonEntity extends AbstractHorse {
    private boolean fullOfWater = false;
    public MelonMelonEntity(EntityType<? extends AbstractHorse> type, Level world) {
        super(type, world);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.TEMPT_RANGE, 64.0D);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.2D));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.0D, stack -> stack.is(Items.CLAY_BALL), false));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.0D));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
    }

    public boolean saddleNotEquipped() {
        return !this.hasItemInSlot(EquipmentSlot.CHEST);
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
        if (!this.isTamed() || this.saddleNotEquipped()) {
            return null;
        }
        Entity first = this.getFirstPassenger();
        return first instanceof Player p ? p : null;
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        // vanilla early exit
        if (this.isVehicle() || this.isBaby()) {
            return super.mobInteract(player, hand);
        }

        // Sneak-right click opens the horse inventory ONLY if tamed (vanilla behavior)
        if (this.isTamed() && player.isSecondaryUseActive()) {
            this.openCustomInventoryScreen(player);
            return InteractionResult.SUCCESS;
        }

        ItemStack held = player.getItemInHand(hand);

        if (!held.isEmpty()) {
            if (this.isFood(held)) {
                int i = this.getAge();
                if (!this.level().isClientSide() && i == 0 && this.canFallInLove()) {
                    this.usePlayerItem(player, hand, held);
                    this.setInLove(player);
                    return InteractionResult.SUCCESS;
                }
                if (this.isBaby()) {
                    this.usePlayerItem(player, hand, held);
                    this.ageUp(MelonMelonEntity.getSpeedUpSecondsWhenFeeding(-i), true);
                    return InteractionResult.SUCCESS;
                }
                if (this.level().isClientSide()) {
                    return InteractionResult.CONSUME;
                }
            }
            if (!this.fullOfWater && held.is(Items.WATER_BUCKET) && !this.isBaby()) {
                this.fullOfWater = true;
                held.consume(1, player);
                player.playSound(SoundEvents.BUCKET_EMPTY, 1.0f, 1.0f);
                player.getInventory().placeItemBackInInventory(new ItemStack(Items.BUCKET));
                return InteractionResult.SUCCESS;
            }
            if (this.fullOfWater && held.is(Items.BUCKET) && !this.isBaby()) {
                this.fullOfWater = false;
                held.consume(1, player);
                player.playSound(SoundEvents.BUCKET_FILL, 1.0f, 1.0f);
                player.getInventory().placeItemBackInInventory(new ItemStack(Items.WATER_BUCKET));
                return InteractionResult.SUCCESS;
            }
        }
        // 1) Taming with MELON_SLICE
        if (!this.isTamed()) {
            if (held.is(Items.MELON_SLICE)) {
                if (!this.level().isClientSide()) {
                    held.consume(1, player);
                    this.tameWithName(player);
                }
                return InteractionResult.SUCCESS;
            }

            // IMPORTANT: do NOT call super here, or vanilla will mount them anyway.
            return InteractionResult.PASS;
        }

        // 2) “Saddling” with MELON (internally equips a real saddle)
        if (this.saddleNotEquipped()) {
            if (held.is(Blocks.MELON.asItem())) {
                if (!this.level().isClientSide()) {
                    held.consume(1, player);

                    // Equip a real saddle so vanilla GUI/control logic works
                    this.setItemSlot(EquipmentSlot.CHEST, new ItemStack(Items.SADDLE));
                }
                return InteractionResult.SUCCESS;
            }

            // Not saddled yet: no mounting, no super()
            return InteractionResult.PASS;
        }

        // 3) Mounting only after tame + saddle equipped
        this.doPlayerRide(player);
        return InteractionResult.SUCCESS;
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return stack.is(Items.CLAY_BALL);
    }

    @Override
    public boolean canMate(Animal other) {
        if (other == this) return false;
        return other instanceof MelonMelonEntity && this.isInLove() && other.isInLove();
    }

    @Override
    public AgeableMob getBreedOffspring(ServerLevel world, AgeableMob mate) {
        return MELON_MELON.create(world, EntitySpawnReason.BREEDING);
    }
}
