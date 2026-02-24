package doug.mug.entity;

import doug.mug.registry.ModEntities;
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
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class MugMugEntity extends AbstractHorseEntity {

    public MugMugEntity(EntityType<? extends AbstractHorseEntity> type, World world) {
        super(type, world);
    }

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
        this.goalSelector.add(3, new TemptGoal(this, 1.0D, stack -> stack.isOf(Items.SUSPICIOUS_STEW), false));
        this.goalSelector.add(4, new FollowParentGoal(this, 1.0D));
        this.goalSelector.add(5, new WanderAroundFarGoal(this, 1.0D));
        this.goalSelector.add(6, new LookAroundGoal(this));
    }

    /**
     * Hard-lock steering. Even if something mounts you via commands/mods,
     * player control won’t work unless tame + saddle equipped.
     *
     * NOTE: AbstractHorseEntity already does a similar check in its own
     * getControllingPassenger(), but we keep this to match your intent.
     */
    public boolean hasSaddleEquipped() {
        return this.hasStackEquipped(EquipmentSlot.BODY);
    }

    @Override
    @Nullable
    public LivingEntity getControllingPassenger() {
        if (!this.isTame() || !this.hasSaddleEquipped()) {
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

        // 1) Taming with SUSPICIOUS_STEW
        if (!this.isTame()) {
            if (held.isOf(Items.SUSPICIOUS_STEW)) {
                if (!this.getEntityWorld().isClient()) {
                    held.decrementUnlessCreative(1, player);
                    this.bondWithPlayer(player); // exists in your pasted AbstractHorseEntity
                }
                return ActionResult.SUCCESS;
            }

            // IMPORTANT: do NOT call super here, or vanilla will mount them anyway.
            return ActionResult.PASS;
        }

        // 2) “Saddling” with BOWL (internally equips a real saddle)
        if (!this.hasSaddleEquipped()) {
            if (held.isOf(Items.BOWL.asItem())) {
                if (!this.getEntityWorld().isClient()) {
                    held.decrementUnlessCreative(1, player);

                    // Equip a real saddle so vanilla GUI/control logic works
                    this.equipStack(EquipmentSlot.BODY, new ItemStack(Items.SADDLE));
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
        return stack.isOf(Items.SUSPICIOUS_STEW);
    }

    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity mate) {
        return ModEntities.createMugMugChild(world);
    }
}
