package doug.mug.registry;

import doug.mug.DougDougMugMug;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.TypedEntityData;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public final class ModItems {

    public static final Item BRICK_BRICK_SPAWN_EGG = registerSpawnEgg("brick_brick_spawn_egg", ModEntities.BRICK_BRICK);
    public static final Item MUG_MUG_SPAWN_EGG     = registerSpawnEgg("mug_mug_spawn_egg",     ModEntities.MUG_MUG);
    public static final Item COW_COW_SPAWN_EGG     = registerSpawnEgg("cow_cow_spawn_egg",     ModEntities.COW_COW);
    public static final Item MELON_MELON_SPAWN_EGG = registerSpawnEgg("melon_melon_spawn_egg", ModEntities.MELON_MELON);

    public static void registerAll() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(entries -> {
            entries.add(BRICK_BRICK_SPAWN_EGG);
            entries.add(MUG_MUG_SPAWN_EGG);
            entries.add(COW_COW_SPAWN_EGG);
            entries.add(MELON_MELON_SPAWN_EGG);
        });
    }

    private static Item registerSpawnEgg(String path, EntityType<?> entityType) {
        Identifier id = Identifier.of(DougDougMugMug.MOD_ID, path);
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, id);
        NbtCompound extraNbt = new NbtCompound();

        Item.Settings settings = new Item.Settings()
                .registryKey(key)
                .component(DataComponentTypes.ENTITY_DATA, TypedEntityData.create(entityType, extraNbt));

        Item egg = new SpawnEggItem(settings);
        return Registry.register(Registries.ITEM, id, egg);
    }

    private ModItems() {}
}
