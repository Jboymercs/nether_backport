package com.unseen.nb.config;


import com.unseen.nb.util.ModReference;
import net.minecraftforge.common.config.Config;

@Config(modid = ModReference.MOD_ID, name = "Unseens Nether Backport/entities_config")
public class NBEntitiesConfig {

    @Config.Name("Hoglin/Piglin Zombie Conversion Time")
    @Config.Comment("Change how long it takes for a Piglin or Hoglin to Zombify when entering the overworld")
    @Config.RequiresMcRestart
    public static int zombification_time = 15;

    @Config.RequiresMcRestart
    @Config.Comment(value = "Which Items can the Piglins accept bartering for. Worths with 'modID:Itemname ")
    public static String[] itemsForBarter = new String[] {
            "minecraft:gold_ingot"
    };

    @Config.Name("Piglin Trade Delay")
    @Config.Comment("Change the delay of Piglins when bartering, take not this timer starts once a Piglin accepts a gold ingot. In Seconds")
    @Config.RequiresMcRestart
    public static int piglins_trade_cooldown = 7;

    @Config.Name("Enable/Disable Piglin Hostility in Bastions")
    @Config.Comment("Only affecting Piglins, this option when disabled makes Piglins not aggroed automatically and follows normal rules. However, Piglin Brutes can still allow Piglins to aggro default: true")
    @Config.RequiresMcRestart
    public static boolean piglins_are_aggro = true;

    @Config.Name("Hoglin Health")
    @Config.Comment("Change the Health of the Hoglin")
    @Config.RequiresMcRestart
    public static double hoglin_health = 40;

    @Config.Name("Hoglin Armor")
    @Config.Comment("Change the Armor of the Hoglin")
    @Config.RequiresMcRestart
    public static double hoglin_armor = 0;

    @Config.Name("Hoglin Armor Toughness")
    @Config.Comment("Change the Armor Toughness of the Hoglin")
    @Config.RequiresMcRestart
    public static double hoglin_armor_toughness = 0;

    @Config.Name("Hoglin Attack Damage")
    @Config.Comment("Change the Attack Damage of the Hoglin")
    @Config.RequiresMcRestart
    public static float hoglin_attack_damange = 8;

    @Config.Name("Piglin Health")
    @Config.Comment("Change the Health of the Piglin")
    @Config.RequiresMcRestart
    public static double piglin_health = 18;

    @Config.Name("Piglin Armor")
    @Config.Comment("Change the Armor of the Piglin")
    @Config.RequiresMcRestart
    public static double piglin_armor = 0;

    @Config.Name("Piglin Armor Toughness")
    @Config.Comment("Change the Armor Toughness of the Piglin")
    @Config.RequiresMcRestart
    public static double piglin_armor_toughness = 0;

    @Config.Name("Piglin Attack Damage")
    @Config.Comment("Change the Attack Damage of the Piglin")
    @Config.RequiresMcRestart
    public static float piglin_attack_damange = 8;

    @Config.Name("Piglin Attack Damage Ranged")
    @Config.Comment("Change the Attack Damage of the Piglin using a crossbow")
    @Config.RequiresMcRestart
    public static float piglin_attack_damage_ranged = 10;

    @Config.Name("Piglin Brute Health")
    @Config.Comment("Change the Health of the Piglin Brute")
    @Config.RequiresMcRestart
    public static double piglin_brute_health = 50;

    @Config.Name("Piglin Brute Armor")
    @Config.Comment("Change the Armor of the Piglin Brute")
    @Config.RequiresMcRestart
    public static double piglin_brute_armor = 0;

    @Config.Name("Piglin Brute Armor Toughness")
    @Config.Comment("Change the Armor Toughness of the Piglin Brute")
    @Config.RequiresMcRestart
    public static double piglin_brute_armor_toughness = 0;

    @Config.Name("Piglin Brute Attack Damage")
    @Config.Comment("Change the Attack Damage of the Piglin Brute")
    @Config.RequiresMcRestart
    public static float piglin_brute_attack_damange = 10;

    @Config.Name("Zombified Piglin Health")
    @Config.Comment("Change the Health of the Zombified Piglin")
    @Config.RequiresMcRestart
    public static double zombie_piglin_health = 20;

    @Config.Name("Zombified Piglin Armor")
    @Config.Comment("Change the Armor of the Zombified Piglin")
    @Config.RequiresMcRestart
    public static double zombie_piglin_armor = 0;

    @Config.Name("Zombified Piglin Armor Toughness")
    @Config.Comment("Change the Armor Toughness of the Zombified Piglin")
    @Config.RequiresMcRestart
    public static double zombie_piglin_armor_toughness = 0;

    @Config.Name("Zombified Piglin Attack Damage")
    @Config.Comment("Change the Attack Damage of the Zombified Piglin")
    @Config.RequiresMcRestart
    public static float zombie_piglin_attack_damange = 8;

    @Config.Name("Zoglin Health")
    @Config.Comment("Change the Health of the Zoglin")
    @Config.RequiresMcRestart
    public static double zoglin_health = 40;

    @Config.Name("Zoglin Armor")
    @Config.Comment("Change the Armor of the Zoglin")
    @Config.RequiresMcRestart
    public static double zoglin_armor = 0;

    @Config.Name("Zoglin Armor Toughness")
    @Config.Comment("Change the Armor Toughness of the Zoglin")
    @Config.RequiresMcRestart
    public static double zoglin_armor_toughness = 0;

    @Config.Name("Zoglin Attack Damage")
    @Config.Comment("Change the Attack Damage of the Zoglin")
    @Config.RequiresMcRestart
    public static float zoglin_attack_damange = 8;

    @Config.Name("Strider Health")
    @Config.Comment("Change the Health of the Strider")
    @Config.RequiresMcRestart
    public static double strider_health = 16;

    @Config.Name("Strider Armor")
    @Config.Comment("Change the Armor of the Strider")
    @Config.RequiresMcRestart
    public static double strider_armor = 0;

    @Config.Name("Strider Armor Toughness")
    @Config.Comment("Change the Armor Toughness of the Strider")
    @Config.RequiresMcRestart
    public static double strider_armor_toughness = 0;

    @Config.Name("Strider Custom Spawnrate")
    @Config.Comment("This is too help striders spawn in the middle of lava lakes, this is the rate at which they spawn, natural spawns still occur they'll just be on shores, Lower is more frequent, Higher is less")
    @Config.RequiresMcRestart
    public static int strider_lava_spawn_rate = 200;

    @Config.Name("Strider Custom Spawnrate Enable/Disable")
    @Config.Comment("Disable the custom spawnrate in lava lakes")
    @Config.RequiresMcRestart
    public static boolean disabledCustomSpawns = false;

    @Config.Name("Piglin Spawnrate HELL")
    @Config.Comment("Change spawn rate of Piglins in default nether biome")
    @Config.RequiresMcRestart
    public static int hell_piglin_spawnrate = 15;

    @Config.Name("Piglin Spawnrate Crimson Forest")
    @Config.Comment("Change spawn rate of Piglins in Crimson Forest")
    @Config.RequiresMcRestart
    public static int cf_piglin_spawnrate = 30;

    @Config.Name("Strider Spawnrate HELL")
    @Config.Comment("Change spawn rate of Striders in default nether biome")
    @Config.RequiresMcRestart
    public static int hell_strider_spawnrate = 60;

    @Config.Name("Strider Spawnrate Basalt Deltas")
    @Config.Comment("Change spawn rate of Striders in Basalt Deltas")
    @Config.RequiresMcRestart
    public static int bd_strider_spawnrate = 60;

    @Config.Name("Strider Spawnrate Crimson Forest")
    @Config.Comment("Change spawn rate of Striders in Crimson Forest")
    @Config.RequiresMcRestart
    public static int cf_strider_spawnrate = 20;

    @Config.Name("Strider Spawnrate Soulsand Valley")
    @Config.Comment("Change spawn rate of Striders in Soulsand Valley")
    @Config.RequiresMcRestart
    public static int sv_strider_spawnrate = 60;

    @Config.Name("Strider Spawnrate Warped Forest")
    @Config.Comment("Change spawn rate of Striders in Warped Forest")
    @Config.RequiresMcRestart
    public static int wf_strider_spawnrate = 60;

    @Config.Name("Zombified Piglin Spawnrate HELL")
    @Config.Comment("Change spawn rate of Zombified Piglins in default nether biome")
    @Config.RequiresMcRestart
    public static int hell_z_piglin_spawnrate = 50;

    @Config.Name("Zombified Piglin Spawnrate Crimson Forest")
    @Config.Comment("Change spawn rate of Zombified Piglins in Crimson Forest")
    @Config.RequiresMcRestart
    public static int cf_z_piglin_spawnrate = 8;

    @Config.Name("Hoglin Spawnrate Crimson Forest")
    @Config.Comment("Change spawn rate of Hoglins in Crimson Forest")
    @Config.RequiresMcRestart
    public static int cf_hoglin_spawnrate = 40;

    @Config.Name("Magma Cubes Spawnrate Basalt Deltas")
    @Config.Comment("Change spawn rate of Magma cubes in Basalt Deltas")
    @Config.RequiresMcRestart
    public static int bd_magma_cubes_spawnrate = 100;

    @Config.Name("Ghast Spawnrate Basalt Deltas")
    @Config.Comment("Change spawn rate of Ghasts in Basalt Deltas")
    @Config.RequiresMcRestart
    public static int bd_ghast_spawnrate = 40;

    @Config.Name("Ghast Spawnrate Soulsand Valley")
    @Config.Comment("Change spawn rate of Ghasts in Soulsand Valley")
    @Config.RequiresMcRestart
    public static int sv_ghast_spawnrate = 36;

    @Config.Name("Endermen Spawnrate Soulsand Valley")
    @Config.Comment("Change spawn rate of Endermen in Soulsand Valley")
    @Config.RequiresMcRestart
    public static int sv_endermen_spawnrate = 4;

    @Config.Name("Endermen Spawnrate Warped Forest")
    @Config.Comment("Change spawn rate of Endermen in Warped Forest")
    @Config.RequiresMcRestart
    public static int wf_endermen_spawnrate = 20;

    @Config.Name("Skeleton Spawnrate Soulsand Valley")
    @Config.Comment("Change spawn rate of Skeltons in Soulsand Valley")
    @Config.RequiresMcRestart
    public static int sv_skelton_spawnrate = 40;
}
