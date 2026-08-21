package doug.mug.sound;

import doug.mug.DougDougMugMug;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Registry;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.Identifier;

public class ModSounds {
    public static final SoundEvent MUG_DEATH = registerSoundEvent("mug_death");
    public static final SoundEvent MUG_HURT = registerSoundEvent("mug_hurt");


    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = Identifier.fromNamespaceAndPath(DougDougMugMug.MOD_ID, name);
        return Registry.register(BuiltInRegistries.SOUND_EVENT, id, SoundEvent.createVariableRangeEvent(id));
    }

    public static void registerSounds() {
        DougDougMugMug.LOGGER.info("Registering Mod Sounds for " + DougDougMugMug.MOD_ID);
    }
}