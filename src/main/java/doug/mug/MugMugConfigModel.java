package doug.mug;

import io.wispforest.owo.config.annotation.Config;
import io.wispforest.owo.config.annotation.Modmenu;

@Modmenu(modId = "dougdoug-mugmug")
@Config(name = "mugmug-config", wrapperName = "MugMugConfig")
public class MugMugConfigModel {
    // int value, higher the value, more likely the mob is to spawn (also makes other mobs less likely to spawn)
    public int BrickBrickSpawnRate = 5;
    public int MugMugSpawnRate = 8;
    public int CowCowSpawnRate = 6;
    public int MelonMelonSpawnRate = 7;

    // int value, sets the minimum size of the spawn groups for each entity (1 means the mugs can spawn alone)
    public int BrickMinGroupSize = 1;
    public int MugMinGroupSize = 1;
    public int CowMinGroupSize = 1;
    public int MelonMinGroupSize = 1;

    // int value, sets the maximum size of the spawn groups for each entity
    public int BrickMaxGroupSize = 3;
    public int MugMaxGroupSize = 3;
    public int CowMaxGroupSize = 3;
    public int MelonMaxGroupSize = 3;
}