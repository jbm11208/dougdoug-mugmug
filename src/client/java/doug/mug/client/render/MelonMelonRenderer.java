package doug.mug.client.render;

import doug.mug.client.model.MelonMelonModel;
import doug.mug.entity.MelonMelonEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class MelonMelonRenderer extends MobEntityRenderer<MelonMelonEntity, MelonMelonModel<MelonMelonEntity>> {

    private static final Identifier TEXTURE = Identifier.of("dougdoug-mugmug", "textures/entity/melon_melon.png");

    public MelonMelonRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new MelonMelonModel<>(ctx.getPart(ModModelLayers.MELON_MELON)), 0.7f);
    }

    @Override
    public Identifier getTexture(MelonMelonEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(MelonMelonEntity livingEntity, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i) {
        if (livingEntity.isBaby()) {
            matrixStack.scale(0.5f, 0.5f, 0.5f);
        } else {
            matrixStack.scale(1f, 1f, 1f);
        }

        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}