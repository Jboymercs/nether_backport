package com.unseen.nb.config;

import com.unseen.nb.util.ModReference;
import net.minecraftforge.common.config.Config;

@Config(modid = ModReference.MOD_ID, name = "Unseens Nether Backport/world_config")
public class NBWorldConfig {

    @Config.Name("Bastion Remnants Spawn Frequency")
    @Config.Comment("Change the spacing between Bastion Remnants, lower means more frequent, higher means less (Block spacing / 6 > frequency)")
    @Config.RequiresMcRestart
    public static int bastionFrequency = 100;

    @Config.Name("Bastion Remnants Enable/Disable")
    @Config.Comment("When set to false, this will disable Bastion Remnants from spawning")
    @Config.RequiresMcRestart
    public static boolean bastion_enabled = true;

    @Config.Name("Bastion Remnants Y-level")
    @Config.Comment("Change the Y-level the Bastion Remnants spawn at, NOTE: this is the level that bridges for will spawn at")
    @Config.RequiresMcRestart
    public static double bastionYLevel = 41;

    @Config.Name("Bastion Remnants Mob Spawns")
    @Config.Comment("Change the spawns alloted in the Bastion Remnants, lower means more mobs, higher means less")
    @Config.RangeInt(min = 1, max = 10)
    @Config.RequiresMcRestart
    public static int bastionSpawnRate = 5;

    @Config.Name("Bastion Remnants Regular Chest Spawn Chance")
    @Config.Comment("Change the chest spawn chance through general Bastion areas, lower means more likely chance")
    @Config.RangeInt(min = 1, max = 10)
    @Config.RequiresMcRestart
    public static int bastionregularChestChance = 7;

    @Config.Name("Bastion Remnants Hold Chest Spawn Chance")
    @Config.Comment("Change the chest spawn chance through the stronghold parts of the Bastion, lower means more likely chance")
    @Config.RangeInt(min = 1, max = 10)
    @Config.RequiresMcRestart
    public static int bastionHoldChestChance = 7;

    @Config.Name("Bastion Remnants & Nether Portal Ruins Biomes Blacklist")
    @Config.Comment("This list acts as a black list for the remnants & nether portal ruins to NOT spawn in, and custom lava lake spawns for Striders")
    @Config.RequiresMcRestart
    public static String[] remnantsBiomesNotAllowed = {"nb:basalt_deltas"};

    @Config.Name("Portal Ruins Overworld Spawn Rate")
    @Config.Comment("Change the rate that ruined portals in the overworld spawn at, lower means more frequent, higher means less (Block spacing > frequency)")
    @Config.RequiresMcRestart
    public static int ruined_portal_rate = 1400;

    @Config.Name("Portal Ruins Overworld Enable/Disable")
    @Config.Comment("When set to false, this will disable Ruined Portals from spawning in the Overworld")
    @Config.RequiresMcRestart
    public static boolean ruined_portal_enabled = true;

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
    @Config.Comment("Change the rate that ruined portals in the nether spawn at, lower means more frequent, higher means less (Block spacing > frequency)")
    @Config.RequiresMcRestart
    public static int nether_ruins_rate = 840;

    @Config.Name("Portal Ruins NETHER Enable/Disable")
    @Config.Comment("When set to false, this will disable Ruined Portals in the Nether from Spawning")
    @Config.RequiresMcRestart
    public static boolean nether_ruined_portal_enabled = true;

    @Config.Name("Warped Forest Biome Frequency")
    @Config.Comment("Change the Frequency of how common the Warped Forest is")
    @Config.RequiresMcRestart
    public static int warpedForestRate = 5;

    @Config.Name("Warped Forest Particle Rate")
    @Config.Comment("Change the Particle Rate of the Warped Forest to help with performance, higher number means less particles")
    @Config.RequiresMcRestart
    public static int warpedForestparticleRate = 150;

    @Config.Name("Crimson Forest Biome Frequency")
    @Config.Comment("Change the Frequency of how common the Crimson Forest is")
    @Config.RequiresMcRestart
    public static int crimsonForestRate = 5;

    @Config.Name("Crimson Forest Particle Rate")
    @Config.Comment("Change the Particle Rate of the Crimson Forest to help with performance, higher number means less particles")
    @Config.RequiresMcRestart
    public static int crimsonForestparticleRate = 150;

    @Config.Name("Soul Sand Valley Biome Frequency")
    @Config.Comment("Change the Frequency of how common the Soul Sand Valley is")
    @Config.RequiresMcRestart
    public static int soulSandValleyRate = 2;

    @Config.Name("Soul Sand Valley Particle Rate")
    @Config.Comment("Change the Particle Rate of the Soul Sand Valley to help with performance, higher number means less particles")
    @Config.RequiresMcRestart
    public static int soulSandValleyparticleRate = 325;

    @Config.Name("Basalt Deltas Biome Frequency")
    @Config.Comment("Change the Frequency of how common the Basalt Deltas is")
    @Config.RequiresMcRestart
    public static int basaltDeltasRate = 2;

    @Config.Name("Basalt Deltas Particle Rate")
    @Config.Comment("Change the Particle Rate of the Basalt Deltas to help with performance, higher number means less particles")
    @Config.RequiresMcRestart
    public static int basaltDeltasparticleRate = 150;

    @Config.Name("Ancient Debris Max Height Generation")
    @Config.Comment("Change the max height generation of Ancient Debris")
    @Config.RequiresMcRestart
    public static int ancient_debris_max_y = 24;

    @Config.Name("Ancient Debris Min Height Generation")
    @Config.Comment("Change the min height generation of Ancient Debris")
    @Config.RequiresMcRestart
    public static int ancient_debris_min_y = 3;

    @Config.Name("Ancient Debris Spawn Chances")
    @Config.Comment("Change the chance of Ancient Debris spawning per chunk")
    @Config.RequiresMcRestart
    public static int ancient_debris_spawn_chances = 5;

    @Config.Name("Ore generation Height Nether API")
    @Config.Comment("If you have tall nether enabled in the nether-api config, does the backport mod double max spawn heights for Blackstone and Nether Gold Ore? default: true")
    @Config.RequiresMcRestart
    public static boolean enabled_double_height = true;

    @Config.Name("Blackstone Max Height Generation")
    @Config.Comment("Change the max height generation of Blackstone")
    @Config.RequiresMcRestart
    public static int blackstone_max_y = 120;

    @Config.Name("Blackstone Min Height Generation")
    @Config.Comment("Change the min height generation of Blackstone")
    @Config.RequiresMcRestart
    public static int blackstone_min_y = 20;

    @Config.Name("Blackstone Spawn Chances")
    @Config.Comment("Change the chance of Blackstone spawning per chunk")
    @Config.RequiresMcRestart
    public static int blacstone_spawn_chances = 10;

    @Config.Name("Nether Gold Ore Height Generation")
    @Config.Comment("Change the max height generation of Nether Gold Ore")
    @Config.RequiresMcRestart
    public static int nether_gold_max_y = 120;

    @Config.Name("Nether Gold Ore Min Height Generation")
    @Config.Comment("Change the min height generation of Nether Gold Ore")
    @Config.RequiresMcRestart
    public static int nether_gold_min_y = 10;

    @Config.Name("Nether Gold Ore Spawn Chances")
    @Config.Comment("Change the chance of Nether Gold Ore spawning per chunk")
    @Config.RequiresMcRestart
    public static int nether_gold_spawn_chances = 10;

}
