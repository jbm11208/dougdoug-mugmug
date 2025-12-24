package doug.mug.client.render;

import doug.mug.client.model.MugMugModel;
import doug.mug.client.render.state.MugMugRenderState;
import doug.mug.entity.MugMugEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class MugMugRenderer extends MobEntityRenderer<MugMugEntity, MugMugRenderState, MugMugModel> {

    private static final Identifier TEXTURE = Identifier.of("dougdoug-mugmug", "textures/entity/mug_mug.png");

    private static final float MODEL_SCALE = 1.0f;

    public MugMugRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new MugMugModel(ctx.getPart(ModModelLayers.MUG_MUG)), 0.2f);
    }

    @Override
    public MugMugRenderState createRenderState() {
        return new MugMugRenderState();
    }

    @Override
    protected void setupTransforms(MugMugRenderState state, MatrixStack matrices, float bodyYaw, float baseHeight) {
        super.setupTransforms(state, matrices, bodyYaw, baseHeight);
        matrices.scale(MODEL_SCALE, MODEL_SCALE, MODEL_SCALE);
    }

    @Override
    public Identifier getTexture(MugMugRenderState state) {
        return TEXTURE;
    }
}