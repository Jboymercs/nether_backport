package com.unseen.nb.config;

import com.unseen.nb.util.ModReference;
import net.minecraftforge.common.config.Config;

@Config(modid = ModReference.MOD_ID, name = "Nether Backport/compat_config")
public class ModCompatConfig {

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
}
