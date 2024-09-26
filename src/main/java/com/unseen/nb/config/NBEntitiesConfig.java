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
}
