package doug.mug.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import doug.mug.block.ModBlocks;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerNorthDefaultHorizontalRotatable(ModBlocks.BRICK_TROPHY);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotatable(ModBlocks.MUG_TROPHY);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotatable(ModBlocks.COW_TROPHY);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotatable(ModBlocks.MELON_TROPHY);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {

    }

}