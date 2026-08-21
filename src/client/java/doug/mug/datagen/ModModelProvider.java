package doug.mug.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import doug.mug.block.ModBlocks;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
        blockStateModelGenerator.createNonTemplateHorizontalBlock(ModBlocks.BRICK_TROPHY);
        blockStateModelGenerator.createNonTemplateHorizontalBlock(ModBlocks.MUG_TROPHY);
        blockStateModelGenerator.createNonTemplateHorizontalBlock(ModBlocks.COW_TROPHY);
        blockStateModelGenerator.createNonTemplateHorizontalBlock(ModBlocks.MELON_TROPHY);
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {

    }

}