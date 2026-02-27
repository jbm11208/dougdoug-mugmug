// Made with Blockbench 5.0.6
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports

package doug.mug.client.model;

import doug.mug.entity.BrickBrickEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;

public class BrickBrickModel<T extends BrickBrickEntity> extends SinglePartEntityModel<T> {
    private final ModelPart bb_main;

    public BrickBrickModel(ModelPart root) {
        this.bb_main = root.getChild("bb_main");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData bb_main = modelPartData.addChild("bb_main", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        bb_main.addChild("cube_r1", ModelPartBuilder.create().uv(58, 49).cuboid(-1.0F, -25.0F, -11.0F, 2.0F, 16.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(-1.0F, -11.0F, -10.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(-1.0F, -25.0F, -10.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(2, 7).cuboid(-6.0F, -27.0F, 8.0F, 5.0F, 5.0F, 2.0F, new Dilation(0.0F))
                .uv(2, 7).cuboid(1.0F, -27.0F, 8.0F, 5.0F, 5.0F, 2.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(-8.0F, -33.0F, -8.0F, 16.0F, 32.0F, 16.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 1.0F, 0.0F, 0.0F, 3.1416F, 0.0F));
        return TexturedModelData.of(modelData, 128, 128);
    }

    @Override
    public void setAngles(BrickBrickEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
        bb_main.render(matrices, vertexConsumer, light, overlay, color);
    }

    @Override
    public ModelPart getPart() {
        return bb_main;
    }
}