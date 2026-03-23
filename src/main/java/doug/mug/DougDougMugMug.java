package doug.mug;

import doug.mug.block.ModBlocks;
import doug.mug.registry.ModEntities;
import doug.mug.registry.ModItems;
import net.fabricmc.api.ModInitializer;
import doug.mug.sound.ModSounds;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DougDougMugMug implements ModInitializer {
    public static final String MOD_ID = "dougdoug-mugmug";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModEntities.registerAll();
        ModItems.registerAll();
        ModSounds.registerSounds();
        ModBlocks.registerModBlocks();
        LOGGER.info("DougDoug MugMug initialized: registered entities, spawn eggs, sounds, and blocks.");
    }
}