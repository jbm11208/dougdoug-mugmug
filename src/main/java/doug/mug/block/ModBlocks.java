package doug.mug.block;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import doug.mug.DougDougMugMug;
import doug.mug.block.custom.*;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {

    public static final Block BRICK_TROPHY = registerBlock("brickbricktrophy",
            new BrickTrophyBlock(AbstractBlock.Settings.create().nonOpaque()));

    public static final Block MUG_TROPHY = registerBlock("mugmugtrophy",
            new MugTrophyBlock(AbstractBlock.Settings.create().nonOpaque()));

    public static final Block COW_TROPHY = registerBlock("cowcowtrophy",
            new CowTrophyBlock(AbstractBlock.Settings.create().nonOpaque()));
    public static final Block MELON_TROPHY = registerBlock("melonmelontrophy",
            new MelonTrophyBlock(AbstractBlock.Settings.create().nonOpaque()));

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(DougDougMugMug.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(DougDougMugMug.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks() {
        DougDougMugMug.LOGGER.info("Registering Mod Blocks for " + DougDougMugMug.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            entries.add(ModBlocks.MUG_TROPHY);
            entries.add(ModBlocks.BRICK_TROPHY);
            entries.add(ModBlocks.COW_TROPHY);
            entries.add(ModBlocks.MELON_TROPHY);
        });
    }
}