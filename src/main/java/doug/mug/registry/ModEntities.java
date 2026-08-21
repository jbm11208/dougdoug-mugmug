package doug.mug.registry;

import doug.mug.DougDougMugMug;
import doug.mug.entity.BrickBrickEntity;
import doug.mug.entity.MugMugEntity;
import doug.mug.entity.CowCowEntity;
import doug.mug.entity.MelonMelonEntity;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.biome.Biomes;
import doug.mug.MugMugConfigModel;

public final class ModEntities {

    private static final ResourceKey<EntityType<?>> BRICK_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(DougDougMugMug.MOD_ID, "brick_brick"));
    private static final ResourceKey<EntityType<?>> MUG_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(DougDougMugMug.MOD_ID, "mug_mug"));
    private static final ResourceKey<EntityType<?>> COW_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(DougDougMugMug.MOD_ID, "cow_cow"));
    private static final ResourceKey<EntityType<?>> MELON_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(DougDougMugMug.MOD_ID, "melon_melon"));

    public static final EntityType<BrickBrickEntity> BRICK_BRICK = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(DougDougMugMug.MOD_ID, "brick_brick"),
            EntityType.Builder.of(BrickBrickEntity::new, MobCategory.CREATURE)
                    .sized(1.0f, 2.0f)
                    .build(BRICK_KEY));

    public static final EntityType<MugMugEntity> MUG_MUG = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(DougDougMugMug.MOD_ID, "mug_mug"),
            EntityType.Builder.of(MugMugEntity::new, MobCategory.CREATURE)
                    .sized(2.8f, 2.2f)
                    .build(MUG_KEY)
    );

    public static final EntityType<CowCowEntity> COW_COW = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(DougDougMugMug.MOD_ID, "cow_cow"),
            EntityType.Builder.of(CowCowEntity::new, MobCategory.CREATURE)
                    .sized(2.8f, 2.2f)
                    .build(COW_KEY)
    );

    public static final EntityType<MelonMelonEntity> MELON_MELON = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(DougDougMugMug.MOD_ID, "melon_melon"),
            EntityType.Builder.of(MelonMelonEntity::new, MobCategory.CREATURE)
                    .sized(2.8f, 2.2f)
                    .build(MELON_KEY)
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
        SpawnPlacements.register(BRICK_BRICK, SpawnPlacements.getPlacementType(BRICK_BRICK), Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        SpawnPlacements.register(MUG_MUG, SpawnPlacements.getPlacementType(MUG_MUG), Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        SpawnPlacements.register(COW_COW, SpawnPlacements.getPlacementType(COW_COW), Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        SpawnPlacements.register(MELON_MELON, SpawnPlacements.getPlacementType(MELON_MELON), Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
    }

    private static void registerBiomeSpawns() {
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.STONY_SHORE, Biomes.STONY_PEAKS, Biomes.BADLANDS), MobCategory.CREATURE, BRICK_BRICK, DougDougMugMug.CONFIG.BrickBrickSpawnRate(), DougDougMugMug.CONFIG.BrickMinGroupSize(), DougDougMugMug.CONFIG.BrickMaxGroupSize());
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.OCEAN, Biomes.DEEP_OCEAN, Biomes.RIVER, Biomes.BEACH), MobCategory.CREATURE, MUG_MUG, DougDougMugMug.CONFIG.MugMugSpawnRate(), DougDougMugMug.CONFIG.MugMinGroupSize(), DougDougMugMug.CONFIG.MugMaxGroupSize());
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.PLAINS, Biomes.FOREST, Biomes.SAVANNA, Biomes.TAIGA), MobCategory.CREATURE, COW_COW, DougDougMugMug.CONFIG.CowCowSpawnRate(), DougDougMugMug.CONFIG.CowMinGroupSize(), DougDougMugMug.CONFIG.CowMaxGroupSize());
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.JUNGLE, Biomes.SPARSE_JUNGLE, Biomes.BAMBOO_JUNGLE), MobCategory.CREATURE, MELON_MELON, DougDougMugMug.CONFIG.MelonMelonSpawnRate(), DougDougMugMug.CONFIG.MelonMinGroupSize(), DougDougMugMug.CONFIG.MelonMaxGroupSize());
    }

    private ModEntities() {
    }
}