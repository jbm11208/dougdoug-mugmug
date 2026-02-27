package doug.mug.client.render;

import doug.mug.DougDougMugMug;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public final class ModModelLayers {
    public static final EntityModelLayer MUG_MUG =
            new EntityModelLayer(Identifier.of(DougDougMugMug.MOD_ID, "mug_mug"), "main");
    public static final EntityModelLayer BRICK_BRICK =
            new EntityModelLayer(Identifier.of(DougDougMugMug.MOD_ID, "brick_brick"), "main");
    public static final EntityModelLayer COW_COW =
            new EntityModelLayer(Identifier.of(DougDougMugMug.MOD_ID, "cow_cow"), "main");
    public static final EntityModelLayer MELON_MELON =
            new EntityModelLayer(Identifier.of(DougDougMugMug.MOD_ID, "melon_melon"), "main");

    private ModModelLayers() {
    }
}