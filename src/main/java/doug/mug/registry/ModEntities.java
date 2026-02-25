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
					.dimensions(3.5f, 2.25f)
					.build()
	);

	public static final EntityType<CowCowEntity> COW_COW = Registry.register(
			Registries.ENTITY_TYPE,
			Identifier.of(DougDougMugMug.MOD_ID, "cow_cow"),
			EntityType.Builder.create(CowCowEntity::new, SpawnGroup.CREATURE)
					.dimensions(0.9f, 1.4f)
					.build()
	);

	public static final EntityType<MelonMelonEntity> MELON_MELON = Registry.register(
			Registries.ENTITY_TYPE,
			Identifier.of(DougDougMugMug.MOD_ID, "melon_melon"),
			EntityType.Builder.create(MelonMelonEntity::new, SpawnGroup.CREATURE)
					.dimensions(0.9f, 1.3f)
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
		BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.STONY_SHORE, BiomeKeys.STONY_PEAKS, BiomeKeys.BADLANDS), SpawnGroup.CREATURE, BRICK_BRICK, 8, 2, 4);
		BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.OCEAN, BiomeKeys.DEEP_OCEAN, BiomeKeys.RIVER), SpawnGroup.CREATURE, MUG_MUG, 10, 2, 4);
		BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.PLAINS, BiomeKeys.FOREST, BiomeKeys.SAVANNA, BiomeKeys.TAIGA), SpawnGroup.CREATURE, COW_COW, 6, 1, 3);
		BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.JUNGLE, BiomeKeys.SPARSE_JUNGLE, BiomeKeys.BAMBOO_JUNGLE), SpawnGroup.CREATURE, MELON_MELON, 9, 2, 4);
	}

	private ModEntities() {}
}