package com.unseen.nb.config;

import com.unseen.nb.util.ModReference;
import net.minecraftforge.common.config.Config;

@Config(modid = ModReference.MOD_ID, name = "Nether Backport/blocks_config")
public class ModBlocksConfig {


    @Config.RequiresMcRestart
    @Config.Comment(value = "Which blocks soul speed.Add new blocks with 'modID:blockName'.")
    public static String[] blocksForEnchant = new String[] {
            "minecraft:soul_sand",
            ModReference.MOD_ID + ":soul_soil"
    };

}
