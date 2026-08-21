// Made with Blockbench 5.0.6
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports

package doug.mug.client.model;

import doug.mug.client.render.state.BrickBrickRenderState;
import doug.mug.entity.BrickBrickEntity;
import net.minecraft.client.model.*;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class BrickBrickModel extends EntityModel<BrickBrickRenderState> {
    private final ModelPart bb_main;

    public BrickBrickModel(ModelPart root) {
        super(root);
        this.bb_main = root.getChild("bb_main");
    }

    public static LayerDefinition getTexturedModelData() {
        MeshDefinition modelData = new MeshDefinition();
        PartDefinition modelPartData = modelData.getRoot();
        PartDefinition bb_main = modelPartData.addOrReplaceChild("bb_main", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, 0.0F, 0.0F));

        bb_main.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(58, 49).addBox(-1.0F, -25.0F, -11.0F, 2.0F, 16.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-1.0F, -11.0F, -10.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-1.0F, -25.0F, -10.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(2, 7).addBox(-6.0F, -27.0F, 8.0F, 5.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(2, 7).addBox(1.0F, -27.0F, 8.0F, 5.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-8.0F, -33.0F, -8.0F, 16.0F, 32.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, 0.0F, 0.0F, 3.1416F, 0.0F));
        return LayerDefinition.create(modelData, 128, 128);
    }

}