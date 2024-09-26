package com.unseen.nb.config;


import com.unseen.nb.util.ModReference;
import com.unseen.nb.util.ModUtils;
import net.minecraftforge.common.config.Config;

import java.util.ArrayList;

@Config(modid = ModReference.MOD_ID, name = "Unseens Nether Backport/general_config")
public class ModConfig {

    @Config.Name("FutureMC Compat")
    @Config.Comment("This setting allows for easy integration with FutureMC, removing copied blocks & items from here and replacing them with FutureMC, default: true")
    @Config.RequiresMcRestart
    public static boolean futureMCCompat = true;

    @Config.Name("Enable/Disable Piglins use Spartan Weaponry over Crossbows")
    @Config.Comment("Affects Piglins only, makes Piglins carry Spartan Weaponry items. THIS REQUIRES SPARTAN WEAPONRY TO BE LOADED default: false")
    @Config.RequiresMcRestart
    public static boolean useSpartanWeapons = false;

    @Config.Name("Enable/Disable Piglins melee variant use Spartan Weaponry")
    @Config.Comment("Affects Piglins only, makes Piglins carry Spartan melee items. THIS REQUIRES SPARTAN WEAPONRY TO BE LOADED default:false")
    @Config.RequiresMcRestart
    public static boolean useMeleeSpartanWeapons = false;

    @Config.Name("Nether Backport Global Health Modifier")
    @Config.Comment("Modify Health globally of all mobs added in this mod")
    @Config.RequiresMcRestart
    public static double healthScale = 1;

    @Config.Name("Nether Backport Global Attack Damage Modifier")
    @Config.Comment("Modify Attack Damage globally of all mobs added in this mod, only affects Piglin, Piglin Brute, Hoglin, Zoglin, Zombified Piglin")
    @Config.RequiresMcRestart
    public static double attackDamageScale = 1;

    @Config.Name("Disable Basalt Deltas")
    @Config.Comment("The Basalt Deltas in the future will get a rework to help performance, you may disable them if they are tanking performance. default: false")
    @Config.RequiresMcRestart
    public static boolean disableBasaltDeltas = false;

}
