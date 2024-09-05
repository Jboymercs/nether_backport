package com.unseen.nb.config;


import com.unseen.nb.util.ModReference;
import net.minecraftforge.common.config.Config;

@Config(modid = ModReference.MOD_ID, name = ModReference.NAME)
public class ModConfig {


    @Config.Name("Bastion Remnants Spawn Frequency")
    @Config.Comment("Change the spacing between Bastion Remnants, lower means more frequent, higher means less")
    @Config.RequiresMcRestart
    public static int bastionFrequency = 90;

    @Config.Name("Portal Ruins Overworld Spawn Rate")
    @Config.Comment("Change the rate that ruined portals in the overworld spawn at, lower means more frequent, higher means less")
    @Config.RequiresMcRestart
    public static int ruined_portal_rate = 125;

    @Config.Name("Portal Ruins Big Portal Chance")
    @Config.Comment("Change the out of x chance a big portal ruin will spawn instead of a small one, think of it like 1 out of x")
    @Config.RangeInt(min = 2, max = 20)
    @Config.RequiresMcRestart
    public static int portal_big_chance = 15;

    @Config.Name("Portal Ruins NETHER Spawn Rate")
    @Config.Comment("Change the rate that ruined portals in the nether spawn at, lower means more frequent, higher means less")
    @Config.RequiresMcRestart
    public static int nether_ruins_rate = 80;

    @Config.Name("Bastion Remnants Mob Spawns")
    @Config.Comment("Change the spawns alloted in the Bastion Remnants, lower means more mobs, higher means less")
    @Config.RangeInt(min = 1, max = 10)
    @Config.RequiresMcRestart
    public static int bastionSpawnRate = 5;

    @Config.Name("Warped Forest Biome Frequency")
    @Config.Comment("Change the Frequency of how common the Warped Forest is")
    @Config.RequiresMcRestart
    public static int warpedForestRate = 70;

    @Config.Name("Crimson Forest Biome Frequency")
    @Config.Comment("Change the Frequency of how common the Crimson Forest is")
    @Config.RequiresMcRestart
    public static int crimsonForestRate = 70;

    @Config.Name("Soul Sand Valley Biome Frequency")
    @Config.Comment("Change the Frequency of how common the Soul Sand Valley is")
    @Config.RequiresMcRestart
    public static int soulSandValleyRate = 70;

    @Config.Name("Hoglin/Piglin Zombie Conversion Time")
    @Config.Comment("Change how long it takes for a Piglin or Hoglin to Zombify when entering the overworld")
    @Config.RequiresMcRestart
    public static int zombification_time = 15;


    @Config.RequiresMcRestart
    @Config.Comment(value = "Which items will be affected by fire resistance. To add your own do 'modID:itemName', currently not supporting metaData")
    public static String[] fireproofItemList = new String[] {
            ModReference.MOD_ID + ":netherite_ore",
            ModReference.MOD_ID + ":nether_scrap",
            ModReference.MOD_ID + ":netherite_ingot",
            ModReference.MOD_ID + ":netherite_block"
            //ModReference.MOD_ID + ":netherite_sword",
            //ModReference.MOD_ID + ":netherite_shovel",
           // ModReference.MOD_ID + ":netherite_pickaxe",
            //ModReference.MOD_ID + ":netherite_axe",
           // ModReference.MOD_ID + ":netherite_hoe",
           // ModReference.MOD_ID + ":netherite_helmet",
           // ModReference.MOD_ID + ":netherite_chestplate",
           // ModReference.MOD_ID + ":netherite_leggings",
           // ModReference.MOD_ID + ":netherite_boots",
           // ModReference.MOD_ID + ":netherite_horse_armor",
           // ModReference.MOD_ID + ":netherite_block"
    };

    @Config.RequiresMcRestart
    @Config.Comment(value = "Enable this to turn the whitelist for fire resistant items into the opposite. Meaning when set to true, items in the list will act without fire resistance")
    public static boolean fireproofItemBlacklist = false;
}
