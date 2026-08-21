package doug.mug.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import doug.mug.client.model.CowCowModel;
import doug.mug.client.render.state.BrickBrickRenderState;
import doug.mug.client.render.state.CowCowRenderState;
import doug.mug.entity.CowCowEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;


public class CowCowRenderer extends MobRenderer<CowCowEntity, CowCowRenderState, CowCowModel> {

    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath("dougdoug-mugmug", "textures/entity/cow_cow.png");

    public CowCowRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new CowCowModel(ctx.bakeLayer(ModModelLayers.COW_COW)), 0.7f);
    }

    @Override
    protected void scale(CowCowRenderState state, PoseStack poseStack) {
        super.scale(state, poseStack);

        // If the state flags this entity as a baby, apply a uniform 50% scale
        if (state.isBaby) {
            poseStack.scale(0.5F, 0.5F, 0.5F);
        }
    }

    @Override
    public Identifier getTextureLocation(CowCowRenderState state) {
        return TEXTURE;
    }

    @Override
    public CowCowRenderState createRenderState() {
        return new CowCowRenderState();
    }
}