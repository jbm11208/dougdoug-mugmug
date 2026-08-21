package doug.mug.registry;

import doug.mug.DougDougMugMug;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;

import java.util.function.Function;

public final class ModItems {

    public static final Item BRICK_BRICK_SPAWN_EGG = registerSpawnEgg("brick_brick_spawn_egg", settings -> new SpawnEggItem(settings.spawnEgg(ModEntities.BRICK_BRICK)));
    public static final Item MUG_MUG_SPAWN_EGG = registerSpawnEgg("mug_mug_spawn_egg", settings -> new SpawnEggItem(settings.spawnEgg(ModEntities.MUG_MUG)));
    public static final Item COW_COW_SPAWN_EGG = registerSpawnEgg("cow_cow_spawn_egg", settings -> new SpawnEggItem(settings.spawnEgg(ModEntities.COW_COW)));
    public static final Item MELON_MELON_SPAWN_EGG = registerSpawnEgg("melon_melon_spawn_egg", settings -> new SpawnEggItem(settings.spawnEgg(ModEntities.MELON_MELON)));

    public static void registerAll() {
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.SPAWN_EGGS).register(entries -> {
            entries.accept(BRICK_BRICK_SPAWN_EGG);
            entries.accept(MUG_MUG_SPAWN_EGG);
            entries.accept(COW_COW_SPAWN_EGG);
            entries.accept(MELON_MELON_SPAWN_EGG);
        });
    }

    private static Item registerSpawnEgg(String path, Function<Item.Properties, Item> function) {
        Identifier id = Identifier.fromNamespaceAndPath(DougDougMugMug.MOD_ID, path);
        return Registry.register(BuiltInRegistries.ITEM, id, function.apply(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, id))));
    }

    private ModItems() {
    }
}
