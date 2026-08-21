package doug.mug.client.render;

import doug.mug.client.model.BrickBrickModel;
import doug.mug.client.render.state.BrickBrickRenderState;
import doug.mug.entity.BrickBrickEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class BrickBrickRenderer extends MobEntityRenderer<BrickBrickEntity, BrickBrickRenderState, BrickBrickModel> {

    private static final Identifier TEXTURE = Identifier.of("dougdoug-mugmug", "textures/entity/brick_brick.png");

    public BrickBrickRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new BrickBrickModel(ctx.getPart(ModModelLayers.BRICK_BRICK)), 0.2f);
    }

    @Override
    public Identifier getTexture(BrickBrickRenderState state) {
        return TEXTURE;
    }

    @Override
    public BrickBrickRenderState createRenderState() {
        return new BrickBrickRenderState();
    }
}