package doug.mug.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import doug.mug.client.model.BrickBrickModel;
import doug.mug.client.render.state.BrickBrickRenderState;
import doug.mug.entity.BrickBrickEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class BrickBrickRenderer extends MobRenderer<BrickBrickEntity, BrickBrickRenderState, BrickBrickModel> {

    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath("dougdoug-mugmug", "textures/entity/brick_brick.png");

    public BrickBrickRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new BrickBrickModel(ctx.bakeLayer(ModModelLayers.BRICK_BRICK)), 0.2f);
    }

    @Override
    protected void scale(BrickBrickRenderState state, PoseStack poseStack) {
        super.scale(state, poseStack);

        // If the state flags this entity as a baby, apply a uniform 50% scale
        if (state.isBaby) {
            poseStack.scale(0.5F, 0.5F, 0.5F);
        }
    }

    @Override
    public Identifier getTextureLocation(BrickBrickRenderState state) {
        return TEXTURE;
    }

    @Override
    public BrickBrickRenderState createRenderState() {
        return new BrickBrickRenderState();
    }
}