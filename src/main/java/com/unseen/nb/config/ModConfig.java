package com.unseen.nb.config;


import com.unseen.nb.util.ModReference;
import net.minecraftforge.common.config.Config;

<<<<<<< Updated upstream
@Config(modid = ModReference.MOD_ID, name = ModReference.NAME)
=======
import java.util.ArrayList;

@Config(modid = ModReference.MOD_ID, name = "Nether Backport/general_config")
>>>>>>> Stashed changes
public class ModConfig {
    @Config.Name("Bastion Remnants Spawn Frequency")
    @Config.Comment("Change the spacing between Bastion Remnants, lower means more frequent, higher means less")
    @Config.RequiresMcRestart
    public static int bastionFrequency = 100;

    @Config.Name("Bastion Remnants Y-level")
    @Config.Comment("Change the Y-level the Bastion Remnants spawn at, NOTE: this is the level that bridges for will spawn at")
    @Config.RequiresMcRestart
    public static double bastionYLevel = 41;

    @Config.Name("Bastion Remnants & Nether Portal Ruins Biomes Blacklist")
    @Config.Comment("This list acts as a black list for the remnants & nether portal ruins to NOT spawn in")
    @Config.RequiresMcRestart
    public static String[] remnantsBiomesNotAllowed = {"nb:basalt_deltas"};


    @Config.Name("Portal Ruins Overworld Spawn Rate")
    @Config.Comment("Change the rate that ruined portals in the overworld spawn at, lower means more frequent, higher means less")
    @Config.RequiresMcRestart
    public static int ruined_portal_rate = 125;

    @Config.Name("Portal Ruins Big Portal Chance")
    @Config.Comment("Change the out of x chance a big portal ruin will spawn instead of a small one, think of it like 1 out of x")
    @Config.RangeInt(min = 2, max = 20)
    @Config.RequiresMcRestart
    public static int portal_big_chance = 15;

    @Config.Name("Portal Ruins Overworld Biomes Blacklist")
    @Config.Comment("This list acts as a black list for Portal Ruins to NOT spawn in")
    @Config.RequiresMcRestart
    public static String[] ruinsBiomesNotAllowed = {"minecraft:ice_mountains", "minecraft:frozen_ocean"};

    @Config.Name("Portal Ruins NETHER Spawn Rate")
    @Config.Comment("Change the rate that ruined portals in the nether spawn at, lower means more frequent, higher means less")
    @Config.RequiresMcRestart
    public static int nether_ruins_rate = 80;

    @Config.Name("Bastion Remnants Mob Spawns")
    @Config.Comment("Change the spawns allotted in the Bastion Remnants, lower means more mobs, higher means less")
    @Config.RangeInt(min = 1, max = 10)
    @Config.RequiresMcRestart
    public static int bastionSpawnRate = 5;

    @Config.Name("Warped Forest Biome Frequency")
    @Config.Comment("Change the Frequency of how common the Warped Forest is")
    @Config.RequiresMcRestart
    public static int warpedForestRate = 5;

    @Config.Name("Crimson Forest Biome Frequency")
    @Config.Comment("Change the Frequency of how common the Crimson Forest is")
    @Config.RequiresMcRestart
    public static int crimsonForestRate = 5;

    @Config.Name("Soul Sand Valley Biome Frequency")
    @Config.Comment("Change the Frequency of how common the Soul Sand Valley is")
    @Config.RequiresMcRestart
    public static int soulSandValleyRate = 2;

    @Config.Name("Basalt Deltas Biome Frequency")
    @Config.Comment("Change the Frequency of how common the Basalt Deltas is")
    @Config.RequiresMcRestart
    public static int basaltDeltasRate = 2;

    @Config.Name("Hoglin/Piglin Zombie Conversion Time")
    @Config.Comment("Change how long it takes for a Piglin or Hoglin to Zombify when entering the overworld")
    @Config.RequiresMcRestart
    public static int zombification_time = 15;

    @Config.Name("Piglin Trade Delay")
    @Config.Comment("Change the delay of Piglins when bartering, take not this timer starts once a Piglin accepts a gold ingot. In Seconds")
    @Config.RequiresMcRestart
    public static int piglins_trade_cooldown = 7;

    @Config.Name("Enable/Disable Piglin Hostility in Bastions")
    @Config.Comment("Only affecting Piglins, this option when disabled makes Piglins not aggroed automatically and follows normal rules. However, Piglin Brutes can still allow Piglins to aggro default: true")
    @Config.RequiresMcRestart
    public static boolean piglins_are_aggro = true;

    @Config.Name("Nether Backport Global Health Modifier")
    @Config.Comment("Modify Health globally of all mobs added in this mod")
    @Config.RequiresMcRestart
    public static double healthScale = 1;

    @Config.Name("Nether Backport Global Attack Damage Modifier")
    @Config.Comment("Modify Attack Damage globally of all mobs added in this mod, only affects Piglin, Pigling Brute, Hoglin, Zoglin, Zombified Piglin")
    @Config.RequiresMcRestart
    public static double attackDamageScale = 1;

    @Config.RequiresMcRestart
    @Config.Comment(value = "Which items will be affected by fire resistance. To add your own do 'modID:itemName', currently not supporting metaData")
    public static String[] fireproofItemList = new String[] {
            ModReference.MOD_ID + ":netherite_ore",
            ModReference.MOD_ID + ":nether_scrap",
            ModReference.MOD_ID + ":netherite_ingot",
            ModReference.MOD_ID + ":netherite_block",
             ModReference.MOD_ID + ":netherite_helmet",
             ModReference.MOD_ID + ":netherite_chestplate",
             ModReference.MOD_ID + ":netherite_leggings",
             ModReference.MOD_ID + ":netherite_boots",
            ModReference.MOD_ID + ":netherite_sword",
            ModReference.MOD_ID + ":netherite_shovel",
            ModReference.MOD_ID + ":netherite_pickaxe",
            ModReference.MOD_ID + ":netherite_axe",
            ModReference.MOD_ID + ":netherite_hoe",
            ModReference.MOD_ID + ":netherite_horse_armor"
    };

    @Config.RequiresMcRestart
    @Config.Comment(value = "Enable this to turn the whitelist for fire resistant items into the opposite. Meaning when set to true, items in the list will act without fire resistance")
    public static boolean fireproofItemBlacklist = false;


}
