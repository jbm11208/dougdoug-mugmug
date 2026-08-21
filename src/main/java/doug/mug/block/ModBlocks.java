package doug.mug.block;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import doug.mug.DougDougMugMug;
import doug.mug.block.custom.*;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Function;

public class ModBlocks {

    public static final Block BRICK_TROPHY = registerBlock("brickbricktrophy",
            properties -> new BrickTrophyBlock(properties.noOcclusion()));

    public static final Block MUG_TROPHY = registerBlock("mugmugtrophy",
            properties -> new MugTrophyBlock(properties.noOcclusion()));

    public static final Block COW_TROPHY = registerBlock("cowcowtrophy",
            properties -> new CowTrophyBlock(properties.noOcclusion()));
    public static final Block MELON_TROPHY = registerBlock("melonmelontrophy",
            properties -> new MelonTrophyBlock(properties.noOcclusion()));


    private static Block registerBlock(String name, Function<BlockBehaviour.Properties, Block> function) {
        Block toRegister = function.apply(BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(DougDougMugMug.MOD_ID, name))));
        registerBlockItem(name, toRegister);
        return Registry.register(BuiltInRegistries.BLOCK, Identifier.fromNamespaceAndPath(DougDougMugMug.MOD_ID, name), toRegister);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(DougDougMugMug.MOD_ID, name),
                new BlockItem(block, new net.minecraft.world.item.Item.Properties().useBlockDescriptionPrefix()
                        .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(DougDougMugMug.MOD_ID, name)))));
    }

    public static void registerModBlocks() {
        DougDougMugMug.LOGGER.info("Registering Mod Blocks for " + DougDougMugMug.MOD_ID);

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.BUILDING_BLOCKS).register(entries -> {
            entries.accept(ModBlocks.MUG_TROPHY);
            entries.accept(ModBlocks.BRICK_TROPHY);
            entries.accept(ModBlocks.COW_TROPHY);
            entries.accept(ModBlocks.MELON_TROPHY);
        });
    }
}