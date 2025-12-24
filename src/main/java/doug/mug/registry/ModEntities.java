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
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.world.Heightmap;

public final class ModEntities {

	public static final EntityType<BrickBrickEntity> BRICK_BRICK = Registry.register(
			Registries.ENTITY_TYPE,
			id("brick_brick"),
			EntityType.Builder.<BrickBrickEntity>create(BrickBrickEntity::new, SpawnGroup.CREATURE)
					.dimensions(1.0f, 2.0f)
					.build(RegistryKey.of(Registries.ENTITY_TYPE.getKey(), id("brick_brick")))
	);

	public static final EntityType<MugMugEntity> MUG_MUG = Registry.register(
			Registries.ENTITY_TYPE,
			id("mug_mug"),
			EntityType.Builder.<MugMugEntity>create(MugMugEntity::new, SpawnGroup.CREATURE)
					.dimensions(3.5f, 2.25f)
					.build(RegistryKey.of(Registries.ENTITY_TYPE.getKey(), id("mug_mug")))
	);

	public static final EntityType<CowCowEntity> COW_COW = Registry.register(
			Registries.ENTITY_TYPE,
			id("cow_cow"),
			EntityType.Builder.<CowCowEntity>create(CowCowEntity::new, SpawnGroup.CREATURE)
					.dimensions(0.9f, 1.4f)
					.build(RegistryKey.of(Registries.ENTITY_TYPE.getKey(), id("cow_cow")))
	);

	public static final EntityType<MelonMelonEntity> MELON_MELON = Registry.register(
			Registries.ENTITY_TYPE,
			id("melon_melon"),
			EntityType.Builder.<MelonMelonEntity>create(MelonMelonEntity::new, SpawnGroup.CREATURE)
					.dimensions(0.9f, 1.3f)
					.build(RegistryKey.of(Registries.ENTITY_TYPE.getKey(), id("melon_melon")))
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
		BiomeModifications.addSpawn(BiomeSelectors.foundInOverworld(), SpawnGroup.CREATURE, BRICK_BRICK, 8, 2, 4);
		BiomeModifications.addSpawn(BiomeSelectors.foundInOverworld(), SpawnGroup.CREATURE, MUG_MUG, 10, 2, 4);
		BiomeModifications.addSpawn(BiomeSelectors.foundInOverworld(), SpawnGroup.CREATURE, COW_COW, 6, 1, 3);
		BiomeModifications.addSpawn(BiomeSelectors.foundInOverworld(), SpawnGroup.CREATURE, MELON_MELON, 9, 2, 4);
	}

	public static Identifier id(String path) {
		return Identifier.of(DougDougMugMug.MOD_ID, path);
	}

	public static BrickBrickEntity createBrickBrickChild(ServerWorld world) { return BRICK_BRICK.create(world, SpawnReason.NATURAL); }
	public static MugMugEntity createMugMugChild(ServerWorld world) { return MUG_MUG.create(world, SpawnReason.NATURAL); }
	public static CowCowEntity createCowCowChild(ServerWorld world) { return COW_COW.create(world, SpawnReason.NATURAL); }
	public static MelonMelonEntity createMelonMelonChild(ServerWorld world) { return MELON_MELON.create(world, SpawnReason.NATURAL); }

	private ModEntities() {}
}