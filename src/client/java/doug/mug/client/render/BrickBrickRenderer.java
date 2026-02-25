package doug.mug.client.render;

import doug.mug.client.model.BrickBrickModel;
import doug.mug.entity.BrickBrickEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class BrickBrickRenderer extends MobEntityRenderer<BrickBrickEntity, BrickBrickModel<BrickBrickEntity>> {

    private static final Identifier TEXTURE = Identifier.of("dougdoug-mugmug", "textures/entity/brick_brick.png");

    public BrickBrickRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new BrickBrickModel<>(ctx.getPart(ModModelLayers.BRICK_BRICK)), 0.2f);
    }

    @Override
    public Identifier getTexture(BrickBrickEntity entity) {
        return TEXTURE;
    }
    @Override
    public void render(BrickBrickEntity livingEntity, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i) {
        if(livingEntity.isBaby()) {
            matrixStack.scale(0.5f, 0.5f, 0.5f);
        } else {
            matrixStack.scale(1f, 1f, 1f);
        }

        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}