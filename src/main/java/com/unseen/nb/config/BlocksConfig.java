package com.unseen.nb.config;


import com.unseen.nb.util.ModReference;
import net.minecraftforge.common.config.Config;

@Config(modid = ModReference.MOD_ID, name = "Unseens Nether Backport/blocks_items_config")
public class BlocksConfig {

    @Config.RequiresMcRestart
    @Config.Comment(value = "Which blocks soul fire will work on. Works with 'modID:blockName'.")
    public static String[] soulBlocks = new String[] {
            "minecraft:soul_sand",
            ModReference.MOD_ID + ":soul_soil"
    };

    @Config.RequiresMcRestart
    @Config.Comment(value = "Which blocks soul speed works on. Add new blocks with 'modID:blockName'.")
    public static String[] blocksForEnchant = new String[] {
            "minecraft:soul_sand",
            ModReference.MOD_ID + ":soul_soil"
    };

    @Config.RequiresMcRestart
    @Config.Comment(value = "Which blocks can be harvested with a hoe. Works with 'modID:blockName'. Every leaf block in the game is already in the list, and cannot be removed.")
    public static String[] hoeWhitelistedBlocks = new String[] {
            "minecraft:hay_block",
            "minecraft:nether_wart_block",
            "minecraft:sponge",
            ModReference.MOD_ID + ":crimson_wart",
            ModReference.MOD_ID + ":warped_wart",
            ModReference.MOD_ID + ":shroom_light"
    };


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

    @Config.RequiresMcRestart
    @Config.Comment(value = "Which dimensions are authorized for the Respawn Anchor to work in, by dimension ID #")
    public static int[] allowedDimensions = {
            1,
            -1
    };

    @Config.Name("Respawn Anchor Explodes")
    @Config.Comment("Change if the Respawn Anchor explodes upon using it in the wrong dimension, default : true")
    @Config.RequiresMcRestart
    public static boolean doesAnchorExplode = true;
}
