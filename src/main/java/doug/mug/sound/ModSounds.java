package doug.mug.sound;

import doug.mug.DougDougMugMug;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {
    public static final SoundEvent MUG_DEATH = registerSoundEvent("mug_death");
    public static final SoundEvent MUG_HURT = registerSoundEvent("mug_hurt");


    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = Identifier.of(DougDougMugMug.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerSounds() {
        DougDougMugMug.LOGGER.info("Registering Mod Sounds for " + DougDougMugMug.MOD_ID);
    }
}