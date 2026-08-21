package doug.mug.client.render;

import doug.mug.DougDougMugMug;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.Identifier;

public final class ModModelLayers {
    public static final ModelLayerLocation MUG_MUG =
            new ModelLayerLocation(Identifier.fromNamespaceAndPath(DougDougMugMug.MOD_ID, "mug_mug"), "main");
    public static final ModelLayerLocation BRICK_BRICK =
            new ModelLayerLocation(Identifier.fromNamespaceAndPath(DougDougMugMug.MOD_ID, "brick_brick"), "main");
    public static final ModelLayerLocation COW_COW =
            new ModelLayerLocation(Identifier.fromNamespaceAndPath(DougDougMugMug.MOD_ID, "cow_cow"), "main");
    public static final ModelLayerLocation MELON_MELON =
            new ModelLayerLocation(Identifier.fromNamespaceAndPath(DougDougMugMug.MOD_ID, "melon_melon"), "main");

    private ModModelLayers() {
    }
}