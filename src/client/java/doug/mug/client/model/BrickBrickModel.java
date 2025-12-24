// Made with Blockbench 5.0.6
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports

package doug.mug.client.model;

import doug.mug.client.render.state.BrickBrickRenderState;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.EntityModel;

public class BrickBrickModel extends EntityModel<BrickBrickRenderState> {

    public BrickBrickModel(ModelPart root) {
        super(root);
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData bb_main = modelPartData.addChild("bb_main", ModelPartBuilder.create(), ModelTransform.of(0.0F, 24.0F, 0.0F, 0.0F, 0.0F, 0.0F));

        bb_main.addChild("cube_r1", ModelPartBuilder.create().uv(58, 49).cuboid(-1.0F, -25.0F, -11.0F, 2.0F, 16.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(-1.0F, -11.0F, -10.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(-1.0F, -25.0F, -10.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(2, 7).cuboid(-6.0F, -27.0F, 8.0F, 5.0F, 5.0F, 2.0F, new Dilation(0.0F))
                .uv(2, 7).cuboid(1.0F, -27.0F, 8.0F, 5.0F, 5.0F, 2.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(-8.0F, -33.0F, -8.0F, 16.0F, 32.0F, 16.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 1.0F, 0.0F, 0.0F, 3.1416F, 0.0F));
        return TexturedModelData.of(modelData, 128, 128);
    }
}