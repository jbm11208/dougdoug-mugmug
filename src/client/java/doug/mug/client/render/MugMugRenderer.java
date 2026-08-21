package doug.mug.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import doug.mug.client.model.MugMugModel;
import doug.mug.client.render.state.BrickBrickRenderState;
import doug.mug.client.render.state.MugMugRenderState;
import doug.mug.entity.MugMugEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class MugMugRenderer extends MobRenderer<MugMugEntity, MugMugRenderState, MugMugModel> {

    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath("dougdoug-mugmug", "textures/entity/mug_mug.png");

    public MugMugRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new MugMugModel(ctx.bakeLayer(ModModelLayers.MUG_MUG)), 0.2f);
    }

    @Override
    protected void scale(MugMugRenderState state, PoseStack poseStack) {
        super.scale(state, poseStack);

        // If the state flags this entity as a baby, apply a uniform 50% scale
        if (state.isBaby) {
            poseStack.scale(0.5F, 0.5F, 0.5F);
        }
    }

    @Override
    public Identifier getTextureLocation(MugMugRenderState state) {
        return TEXTURE;
    }

    @Override
    public MugMugRenderState createRenderState() {
        return new MugMugRenderState();
    }
}