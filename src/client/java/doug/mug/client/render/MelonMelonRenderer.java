package doug.mug.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import doug.mug.client.model.MelonMelonModel;
import doug.mug.client.render.state.BrickBrickRenderState;
import doug.mug.client.render.state.MelonMelonRenderState;
import doug.mug.entity.MelonMelonEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class MelonMelonRenderer extends MobRenderer<MelonMelonEntity, MelonMelonRenderState, MelonMelonModel> {

    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath("dougdoug-mugmug", "textures/entity/melon_melon.png");

    public MelonMelonRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new MelonMelonModel(ctx.bakeLayer(ModModelLayers.MELON_MELON)), 0.7f);
    }

    @Override
    protected void scale(MelonMelonRenderState state, PoseStack poseStack) {
        super.scale(state, poseStack);

        // If the state flags this entity as a baby, apply a uniform 50% scale
        if (state.isBaby) {
            poseStack.scale(0.5F, 0.5F, 0.5F);
        }
    }

    @Override
    public Identifier getTextureLocation(MelonMelonRenderState state) {
        return TEXTURE;
    }

    @Override
    public MelonMelonRenderState createRenderState() {
        return new MelonMelonRenderState();
    }
}