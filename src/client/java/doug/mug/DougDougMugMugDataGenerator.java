package doug.mug;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import doug.mug.datagen.*;
import net.minecraft.core.RegistrySetBuilder;

public class DougDougMugMugDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(ModModelProvider::new);
    }

    @Override
    public void buildRegistry(RegistrySetBuilder registryBuilder) {
    }
}