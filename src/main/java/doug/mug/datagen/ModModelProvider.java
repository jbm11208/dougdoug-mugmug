package doug.mug.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import doug.mug.block.ModBlocks;
import net.minecraft.data.client.*;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.BRICK_TROPHY);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.MUG_TROPHY);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.COW_TROPHY);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.MELON_TROPHY);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {

    }

}