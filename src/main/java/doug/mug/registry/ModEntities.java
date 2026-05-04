package doug.mug.registry;

import doug.mug.DougDougMugMug;
import doug.mug.entity.BrickBrickEntity;
import doug.mug.entity.MugMugEntity;
import doug.mug.entity.CowCowEntity;
import doug.mug.entity.MelonMelonEntity;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.BiomeKeys;
import doug.mug.MugMugConfigModel;

public final class ModEntities {

    public static final EntityType<BrickBrickEntity> BRICK_BRICK = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(DougDougMugMug.MOD_ID, "brick_brick"),
            EntityType.Builder.create(BrickBrickEntity::new, SpawnGroup.CREATURE)
                    .dimensions(1.0f, 2.0f)
                    .build());

    public static final EntityType<MugMugEntity> MUG_MUG = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(DougDougMugMug.MOD_ID, "mug_mug"),
            EntityType.Builder.create(MugMugEntity::new, SpawnGroup.CREATURE)
                    .dimensions(2.8f, 2.2f)
                    .build()
    );

    public static final EntityType<CowCowEntity> COW_COW = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(DougDougMugMug.MOD_ID, "cow_cow"),
            EntityType.Builder.create(CowCowEntity::new, SpawnGroup.CREATURE)
                    .dimensions(2.8f, 2.2f)
                    .build()
    );

    public static final EntityType<MelonMelonEntity> MELON_MELON = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(DougDougMugMug.MOD_ID, "melon_melon"),
            EntityType.Builder.create(MelonMelonEntity::new, SpawnGroup.CREATURE)
                    .dimensions(2.8f, 2.2f)
                    .build()
    );

    public static void registerAll() {
        registerAttributes();
        registerSpawnRestrictions();
        registerBiomeSpawns();
    }

    private static void registerAttributes() {
        FabricDefaultAttributeRegistry.register(BRICK_BRICK, BrickBrickEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(MUG_MUG, MugMugEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(COW_COW, CowCowEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(MELON_MELON, MelonMelonEntity.createAttributes());
    }

    private static void registerSpawnRestrictions() {
        SpawnRestriction.register(BRICK_BRICK, SpawnRestriction.getLocation(BRICK_BRICK), Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::isValidNaturalSpawn);
        SpawnRestriction.register(MUG_MUG, SpawnRestriction.getLocation(MUG_MUG), Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::isValidNaturalSpawn);
        SpawnRestriction.register(COW_COW, SpawnRestriction.getLocation(COW_COW), Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::isValidNaturalSpawn);
        SpawnRestriction.register(MELON_MELON, SpawnRestriction.getLocation(MELON_MELON), Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::isValidNaturalSpawn);
    }

    private static void registerBiomeSpawns() {
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.STONY_SHORE, BiomeKeys.STONY_PEAKS, BiomeKeys.BADLANDS), SpawnGroup.CREATURE, BRICK_BRICK, DougDougMugMug.CONFIG.BrickBrickSpawnRate(), DougDougMugMug.CONFIG.BrickMinGroupSize(), DougDougMugMug.CONFIG.BrickMaxGroupSize());
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.OCEAN, BiomeKeys.DEEP_OCEAN, BiomeKeys.RIVER, BiomeKeys.BEACH), SpawnGroup.CREATURE, MUG_MUG, DougDougMugMug.CONFIG.MugMugSpawnRate(), DougDougMugMug.CONFIG.MugMinGroupSize(), DougDougMugMug.CONFIG.MugMaxGroupSize());
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.PLAINS, BiomeKeys.FOREST, BiomeKeys.SAVANNA, BiomeKeys.TAIGA), SpawnGroup.CREATURE, COW_COW, DougDougMugMug.CONFIG.CowCowSpawnRate(), DougDougMugMug.CONFIG.CowMinGroupSize(), DougDougMugMug.CONFIG.CowMaxGroupSize());
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.JUNGLE, BiomeKeys.SPARSE_JUNGLE, BiomeKeys.BAMBOO_JUNGLE), SpawnGroup.CREATURE, MELON_MELON, DougDougMugMug.CONFIG.MelonMelonSpawnRate(), DougDougMugMug.CONFIG.MelonMinGroupSize(), DougDougMugMug.CONFIG.MelonMaxGroupSize());
    }

    private ModEntities() {
    }
}