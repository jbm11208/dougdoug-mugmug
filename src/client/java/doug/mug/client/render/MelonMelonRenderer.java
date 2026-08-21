package doug.mug.client.render;

import doug.mug.client.model.MelonMelonModel;
import doug.mug.client.render.state.MelonMelonRenderState;
import doug.mug.entity.MelonMelonEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class MelonMelonRenderer extends MobEntityRenderer<MelonMelonEntity, MelonMelonRenderState, MelonMelonModel> {

    private static final Identifier TEXTURE = Identifier.of("dougdoug-mugmug", "textures/entity/melon_melon.png");

    public MelonMelonRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new MelonMelonModel(ctx.getPart(ModModelLayers.MELON_MELON)), 0.7f);
    }

    @Override
    public Identifier getTexture(MelonMelonRenderState state) {
        return TEXTURE;
    }

    @Override
    public MelonMelonRenderState createRenderState() {
        return new MelonMelonRenderState();
    }
}