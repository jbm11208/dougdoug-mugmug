package doug.mug.registry;

import doug.mug.DougDougMugMug;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public final class ModItems {

    public static final Item BRICK_BRICK_SPAWN_EGG = registerSpawnEgg("brick_brick_spawn_egg", settings -> new SpawnEggItem(settings.spawnEgg(ModEntities.BRICK_BRICK)));
    public static final Item MUG_MUG_SPAWN_EGG = registerSpawnEgg("mug_mug_spawn_egg", settings -> new SpawnEggItem(settings.spawnEgg(ModEntities.MUG_MUG)));
    public static final Item COW_COW_SPAWN_EGG = registerSpawnEgg("cow_cow_spawn_egg", settings -> new SpawnEggItem(settings.spawnEgg(ModEntities.COW_COW)));
    public static final Item MELON_MELON_SPAWN_EGG = registerSpawnEgg("melon_melon_spawn_egg", settings -> new SpawnEggItem(settings.spawnEgg(ModEntities.MELON_MELON)));

    public static void registerAll() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(entries -> {
            entries.add(BRICK_BRICK_SPAWN_EGG);
            entries.add(MUG_MUG_SPAWN_EGG);
            entries.add(COW_COW_SPAWN_EGG);
            entries.add(MELON_MELON_SPAWN_EGG);
        });
    }

    private static Item registerSpawnEgg(String path, Function<Item.Settings, Item> function) {
        Identifier id = Identifier.of(DougDougMugMug.MOD_ID, path);
        return Registry.register(Registries.ITEM, id, function.apply(new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, id))));
    }

    private ModItems() {
    }
}
