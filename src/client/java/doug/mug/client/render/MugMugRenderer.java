package doug.mug.client.render;

import doug.mug.client.model.MugMugModel;
import doug.mug.entity.MugMugEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class MugMugRenderer extends MobEntityRenderer<MugMugEntity, MugMugModel<MugMugEntity>> {

    private static final Identifier TEXTURE = Identifier.of("dougdoug-mugmug", "textures/entity/mug_mug.png");

    public MugMugRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new MugMugModel<>(ctx.getPart(ModModelLayers.MUG_MUG)), 0.2f);
    }

    @Override
    public Identifier getTexture(MugMugEntity entity) {
        return TEXTURE;
    }
    @Override
    public void render(MugMugEntity livingEntity, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i) {
        if(livingEntity.isBaby()) {
            matrixStack.scale(0.5f, 0.5f, 0.5f);
        } else {
            matrixStack.scale(1f, 1f, 1f);
        }

        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}