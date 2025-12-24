package doug.mug.client.render;

import doug.mug.client.model.CowCowModel;
import doug.mug.client.render.state.CowCowRenderState;
import doug.mug.entity.CowCowEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class CowCowRenderer extends MobEntityRenderer<CowCowEntity, CowCowRenderState, CowCowModel> {

    private static final Identifier TEXTURE = Identifier.of("dougdoug-mugmug", "textures/entity/cow_cow.png");

    private static final float MODEL_SCALE = 1.0f;

    public CowCowRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new CowCowModel(ctx.getPart(ModModelLayers.COW_COW)), 0.7f);
    }

    @Override
    public CowCowRenderState createRenderState() {
        return new CowCowRenderState();
    }

    @Override
    protected void setupTransforms(CowCowRenderState state, MatrixStack matrices, float bodyYaw, float baseHeight) {
        super.setupTransforms(state, matrices, bodyYaw, baseHeight);
        matrices.scale(MODEL_SCALE, MODEL_SCALE, MODEL_SCALE);
    }

    @Override
    public Identifier getTexture(CowCowRenderState state) {
        return TEXTURE;
    }
}