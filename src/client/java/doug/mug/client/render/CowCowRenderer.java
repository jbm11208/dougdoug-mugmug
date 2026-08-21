package doug.mug.client.render;

import doug.mug.client.model.CowCowModel;
import doug.mug.client.render.state.CowCowRenderState;
import doug.mug.entity.CowCowEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;


public class CowCowRenderer extends MobEntityRenderer<CowCowEntity, CowCowRenderState, CowCowModel> {

    private static final Identifier TEXTURE = Identifier.of("dougdoug-mugmug", "textures/entity/cow_cow.png");

    public CowCowRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new CowCowModel(ctx.getPart(ModModelLayers.COW_COW)), 0.7f);
    }

    @Override
    public Identifier getTexture(CowCowRenderState state) {
        return TEXTURE;
    }

    @Override
    public CowCowRenderState createRenderState() {
        return new CowCowRenderState();
    }
}