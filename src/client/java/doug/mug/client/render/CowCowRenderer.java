package doug.mug.client.render;

import doug.mug.client.model.CowCowModel;
import doug.mug.entity.CowCowEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class CowCowRenderer extends MobEntityRenderer<CowCowEntity, CowCowModel<CowCowEntity>> {

    private static final Identifier TEXTURE = Identifier.of("dougdoug-mugmug", "textures/entity/cow_cow.png");

    public CowCowRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new CowCowModel<>(ctx.getPart(ModModelLayers.COW_COW)), 0.7f);
    }

    @Override
    public Identifier getTexture(CowCowEntity entity) {
        return TEXTURE;
    }
    @Override
    public void render(CowCowEntity livingEntity, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i) {
        if(livingEntity.isBaby()) {
            matrixStack.scale(0.5f, 0.5f, 0.5f);
        } else {
            matrixStack.scale(1f, 1f, 1f);
        }

        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}