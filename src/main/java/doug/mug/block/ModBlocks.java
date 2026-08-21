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
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModBlocks {

    public static final Block BRICK_TROPHY = registerBlock("brickbricktrophy",
            properties -> new BrickTrophyBlock(properties.nonOpaque()));

    public static final Block MUG_TROPHY = registerBlock("mugmugtrophy",
            properties -> new MugTrophyBlock(properties.nonOpaque()));

    public static final Block COW_TROPHY = registerBlock("cowcowtrophy",
            properties -> new CowTrophyBlock(properties.nonOpaque()));
    public static final Block MELON_TROPHY = registerBlock("melonmelontrophy",
            properties -> new MelonTrophyBlock(properties.nonOpaque()));


    private static Block registerBlock(String name, Function<AbstractBlock.Settings, Block> function) {
        Block toRegister = function.apply(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(DougDougMugMug.MOD_ID, name))));
        registerBlockItem(name, toRegister);
        return Registry.register(Registries.BLOCK, Identifier.of(DougDougMugMug.MOD_ID, name), toRegister);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(DougDougMugMug.MOD_ID, name),
                new BlockItem(block, new Item.Settings().useBlockPrefixedTranslationKey()
                        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(DougDougMugMug.MOD_ID, name)))));
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