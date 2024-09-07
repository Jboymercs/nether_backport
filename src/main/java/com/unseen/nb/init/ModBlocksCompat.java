package com.unseen.nb.init;

import com.unseen.nb.common.blocks.BlockBaseCompat;
import com.unseen.nb.common.blocks.BlockSoulTorch;
import com.unseen.nb.common.blocks.base.BlockBase;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

import java.util.ArrayList;
import java.util.List;

public class ModBlocksCompat {

    public static final float STONE_HARDNESS = 1.7f;
    public static final float STONE_RESISTANCE = 10f;

    public static final float WOOD_HARDNESS = 1.5f;
    public static final float WOOD_RESISTANCE = 5.0f;

    public static final float OBSIDIAN_HARDNESS = 50;
    public static final float OBSIDIAN_RESISTANCE = 2000;
    public static final List<Block> BLOCKS = new ArrayList<Block>();

    public static final Block NETHERITE_BLOCK = new BlockBaseCompat("netherite_block", Material.ROCK, STONE_HARDNESS, STONE_RESISTANCE, NBSoundTypes.NETHERITE).setCreativeTab(CreativeTabs.BUILDING_BLOCKS);

    public static final Block NETHERITE_ORE = new BlockBaseCompat("netherite_ore", Material.ROCK, OBSIDIAN_HARDNESS, OBSIDIAN_RESISTANCE, NBSoundTypes.ANCIENT_DEBRIS).setCreativeTab(CreativeTabs.BUILDING_BLOCKS);


}
