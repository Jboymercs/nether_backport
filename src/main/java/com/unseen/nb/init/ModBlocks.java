package com.unseen.nb.init;

import com.unseen.nb.common.blocks.*;
import com.unseen.nb.common.blocks.base.*;

import com.unseen.nb.common.blocks.base.slab.BlockDoubleSlab;
import com.unseen.nb.common.blocks.base.slab.BlockHalfSlab;
import com.unseen.nb.common.blocks.base.slab_wood.BlockDoubleSlabWood;
import com.unseen.nb.common.blocks.base.slab_wood.BlockHalfSlabWood;
import net.minecraft.block.*;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

import java.util.ArrayList;
import java.util.List;

public class ModBlocks {

    public static final float BASALT_HARDNESS = 1.25f;
    public static final float BASALT_RESISTANCE = 7.0F;

    public static final float STONE_HARDNESS = 1.7f;
    public static final float STONE_RESISTANCE = 10f;

    public static final float WOOD_HARDNESS = 1.5f;
    public static final float WOOD_RESISTANCE = 5.0f;

    public static final float OBSIDIAN_HARDNESS = 50;
    public static final float OBSIDIAN_RESISTANCE = 2000;
    public static final List<Block> BLOCKS = new ArrayList<Block>();
    public static final Block CRIMSON_GRASS = new BlockNetherGrass("crimson_grass", Material.ROCK, 0.4F, 0.4F, NBSoundTypes.NYLIUM).setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
    public static final Block WARPED_GRASS = new BlockNetherGrass("warped_grass", Material.ROCK, 0.4F, 0.4F, NBSoundTypes.NYLIUM).setCreativeTab(CreativeTabs.BUILDING_BLOCKS);

    public static final Block CRIMSON_HYPHAE = new BlockNetherWood("crimson_hyphae", Material.GROUND, STONE_HARDNESS, STONE_RESISTANCE, NBSoundTypes.HYPHAE).setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
    public static final Block WARPED_HYPHAE = new BlockNetherWood("warped_hyphae", Material.GROUND, STONE_HARDNESS, STONE_RESISTANCE, NBSoundTypes.HYPHAE).setCreativeTab(CreativeTabs.BUILDING_BLOCKS);

    public static final Block WARPED_STEM = new BlockLogBase("warped_stem",  WOOD_HARDNESS, WOOD_RESISTANCE, NBSoundTypes.STEM_TYPE).setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
    public static final Block CRIMSON_STEM = new BlockLogBase("crimson_stem", WOOD_HARDNESS, WOOD_RESISTANCE, NBSoundTypes.STEM_TYPE).setCreativeTab(CreativeTabs.BUILDING_BLOCKS);

    public static final Block CRIMSON_WART = new BlockBase("crimson_wart", Material.GRASS, 1.0F, 1.0F, NBSoundTypes.WART).setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
    public static final Block WARPED_WART = new BlockBase("warped_wart", Material.GRASS, 1.0F, 1.0F, NBSoundTypes.WART).setCreativeTab(CreativeTabs.BUILDING_BLOCKS);

    public static final Block CRIMSON_PLANKS = new BlockNetherWood("crimson_planks", Material.GROUND, WOOD_HARDNESS, WOOD_RESISTANCE, SoundType.WOOD).setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
    public static final Block WARPED_PLANKS = new BlockNetherWood("warped_planks", Material.GROUND, WOOD_HARDNESS, WOOD_RESISTANCE, SoundType.WOOD).setCreativeTab(CreativeTabs.BUILDING_BLOCKS);

    public static final Block CRIMSON_STAIRS = new BlockStairBaseWood("crimson_stairs", CRIMSON_PLANKS.getDefaultState(), WOOD_HARDNESS, WOOD_RESISTANCE, SoundType.WOOD).setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
    public static final Block WARPED_STAIRS = new BlockStairBaseWood("warped_stairs", WARPED_PLANKS.getDefaultState(), WOOD_HARDNESS, WOOD_RESISTANCE, SoundType.WOOD).setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
    public static final Block CRIMSON_TRAPDOOR = new BlockNetherTrapDoor("crimson_trapdoor", WOOD_RESISTANCE, WOOD_HARDNESS, CreativeTabs.REDSTONE, SoundType.WOOD);
    public static final Block WARPED_TRAPDOOR = new BlockNetherTrapDoor("warped_trapdoor", WOOD_RESISTANCE, WOOD_HARDNESS, CreativeTabs.REDSTONE, SoundType.WOOD);
    public static final Block WARPED_DOOR = new BlockNetherDoor("warped_door", WOOD_HARDNESS, WOOD_RESISTANCE, CreativeTabs.REDSTONE, SoundType.WOOD);
    public static final Block CRIMSON_DOOR = new BlockNetherDoor("crimson_door", WOOD_HARDNESS, WOOD_RESISTANCE, CreativeTabs.REDSTONE, SoundType.WOOD);

    public static final Block CRIMSON_FENCE = new BlockFenceBase("crimson_fence", Material.GROUND, MapColor.RED_STAINED_HARDENED_CLAY, WOOD_HARDNESS, WOOD_RESISTANCE);
    public static final Block WARPED_FENCE = new BlockFenceBase("warped_fence", Material.GROUND, MapColor.CYAN_STAINED_HARDENED_CLAY, WOOD_HARDNESS, WOOD_RESISTANCE);
    public static final Block CRIMSON_GATE = new BlockFenceGateBase("crimson_gate", BlockPlanks.EnumType.OAK, WOOD_HARDNESS, WOOD_RESISTANCE);
    public static final Block WARPED_GATE = new BlockFenceGateBase("warped_gate", BlockPlanks.EnumType.OAK, WOOD_HARDNESS, WOOD_RESISTANCE);

    public static final BlockSlab CRIMSON_SLAB_DOUBLE = new BlockDoubleSlabWood("crimson_slab_double", Material.GROUND, CreativeTabs.SEARCH, ModBlocks.CRIMSON_SLAB_HALF, WOOD_HARDNESS, WOOD_RESISTANCE, SoundType.WOOD);

    public static final BlockSlab CRIMSON_SLAB_HALF = new BlockHalfSlabWood("crimson_slab_half", Material.GROUND, CreativeTabs.BUILDING_BLOCKS, ModBlocks.CRIMSON_SLAB_HALF, ModBlocks.CRIMSON_SLAB_DOUBLE, WOOD_HARDNESS, WOOD_RESISTANCE, SoundType.WOOD);

    public static final BlockSlab WARPED_SLAB_DOUBLE = new BlockDoubleSlabWood("warped_slab_double", Material.GROUND, CreativeTabs.SEARCH, ModBlocks.WARPED_SLAB_HALF, WOOD_HARDNESS, WOOD_RESISTANCE, SoundType.WOOD);

    public static final BlockSlab WARPED_SLAB_HALF = new BlockHalfSlabWood("warped_slab_half", Material.GROUND, CreativeTabs.BUILDING_BLOCKS, ModBlocks.WARPED_SLAB_HALF, ModBlocks.WARPED_SLAB_DOUBLE, WOOD_HARDNESS, WOOD_RESISTANCE, SoundType.WOOD);

    public static final BlockSlab BLACK_STONE_SLAB_DOUBLE = new BlockDoubleSlab("black_stone_slab_double", Material.ROCK, CreativeTabs.SEARCH, ModBlocks.BLACK_STONE_SLAB_HALF, STONE_HARDNESS, STONE_RESISTANCE, SoundType.STONE);

    public static final BlockSlab BLACK_STONE_SLAB_HALF = new BlockHalfSlab("black_stone_slab_half", Material.ROCK, CreativeTabs.BUILDING_BLOCKS, ModBlocks.BLACK_STONE_SLAB_HALF, ModBlocks.BLACK_STONE_SLAB_DOUBLE, STONE_HARDNESS, STONE_RESISTANCE, SoundType.STONE);

    public static final BlockSlab POLISHED_BLACK_STONE_SLAB_DOUBLE = new BlockDoubleSlab("polished_black_stone_slab_double", Material.ROCK, CreativeTabs.SEARCH, ModBlocks.POLISHED_BLACK_STONE_SLAB_HALF, STONE_HARDNESS, STONE_RESISTANCE, SoundType.STONE);

    public static final BlockSlab POLISHED_BLACK_STONE_SLAB_HALF = new BlockHalfSlab("polished_black_stone_slab_half", Material.ROCK, CreativeTabs.BUILDING_BLOCKS, ModBlocks.POLISHED_BLACK_STONE_SLAB_HALF, ModBlocks.POLISHED_BLACK_STONE_SLAB_DOUBLE, STONE_HARDNESS, STONE_RESISTANCE, SoundType.STONE);

    public static final BlockSlab BLACK_BRICKS_SLAB_DOUBLE = new BlockDoubleSlab("black_bricks_slab_double", Material.ROCK, CreativeTabs.SEARCH, ModBlocks.BLACK_BRICKS_SLAB_HALF, STONE_HARDNESS, STONE_RESISTANCE, SoundType.STONE);

    public static final BlockSlab BLACK_BRICKS_SLAB_HALF = new BlockHalfSlab("black_bricks_slab_half", Material.ROCK, CreativeTabs.BUILDING_BLOCKS, ModBlocks.BLACK_BRICKS_SLAB_HALF, ModBlocks.BLACK_BRICKS_SLAB_DOUBLE, STONE_HARDNESS, STONE_RESISTANCE, SoundType.STONE);

    public static final Block BLACK_STONE_WALL = new BlockNetherWall("black_stone_wall", Material.ROCK, STONE_HARDNESS, STONE_RESISTANCE, SoundType.STONE, CreativeTabs.DECORATIONS);
    public static final Block POLISHED_BLACK_STONE_WALL = new BlockNetherWall("polished_stone_wall", Material.ROCK, STONE_HARDNESS, STONE_RESISTANCE, SoundType.STONE, CreativeTabs.DECORATIONS);
    public static final Block POLISHED_BRICKS_STONE_WALL = new BlockNetherWall("bricks_stone_wall", Material.ROCK, STONE_HARDNESS, STONE_RESISTANCE, SoundType.STONE, CreativeTabs.DECORATIONS);


    public static final Block SOUL_SOIL = new BlockSoulSoil("soul_soil", Material.SAND, 0.5F, 0.5F, NBSoundTypes.SOUL_SOIL).setHarvestInfo("shovel", 0).setCreativeTab(CreativeTabs.BUILDING_BLOCKS);

    public static final Block BASALT = new BlockPillarBase("basalt", Material.ROCK, BASALT_HARDNESS, BASALT_RESISTANCE, NBSoundTypes.BASALT).setCreativeTab(CreativeTabs.BUILDING_BLOCKS);

    public static final Block POLISHED_BASALT = new BlockPillarBase("polished_basalt", Material.ROCK, BASALT_HARDNESS, BASALT_RESISTANCE, NBSoundTypes.BASALT).setCreativeTab(CreativeTabs.BUILDING_BLOCKS);

    public static final Block SMOOTH_BASALT = new BlockBase("smooth_basalt", Material.ROCK, BASALT_HARDNESS, BASALT_RESISTANCE, NBSoundTypes.BASALT).setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
    public static final Block CRYING_OBSIDIAN = new BlockCryingObsidian("cry_obi", Material.ROCK, OBSIDIAN_HARDNESS, OBSIDIAN_RESISTANCE, SoundType.STONE).setHarvestInfo("pickaxe", 3).setCreativeTab(CreativeTabs.BUILDING_BLOCKS).setLightLevel(0.7F);


    public static final Block GILDED_BLACKSTONE = new BlockGildedBlackstone("gilded_blackstone", Material.ROCK, STONE_HARDNESS, STONE_RESISTANCE, NBSoundTypes.NETHER_ORE).setCreativeTab(CreativeTabs.BUILDING_BLOCKS);

    public static Block NETHER_GOLD_ORE = new BlockNetherOre("nether_gold_ore", Material.ROCK, 3.0F, 5.0F, NBSoundTypes.NETHER_ORE).setCreativeTab(CreativeTabs.BUILDING_BLOCKS);

    public static Block BLACK_STONE = new BlockBase("black_stone", Material.ROCK, STONE_HARDNESS, STONE_RESISTANCE, SoundType.STONE).setCreativeTab(CreativeTabs.BUILDING_BLOCKS);

    public static Block POLISHED_BLACK_STONE = new BlockBase("polished_black_stone", Material.ROCK, STONE_HARDNESS, STONE_RESISTANCE, SoundType.STONE).setCreativeTab(CreativeTabs.BUILDING_BLOCKS);

    public static Block CHISELED_POLISHED_BLACK_STONE = new BlockBase("chisled_polished_black_stone", Material.ROCK, STONE_HARDNESS, STONE_RESISTANCE, SoundType.STONE).setCreativeTab(CreativeTabs.BUILDING_BLOCKS);

    public static Block BLACK_STONE_BRICKS = new BlockBase("black_stone_bricks", Material.ROCK, STONE_HARDNESS, STONE_RESISTANCE, SoundType.STONE).setCreativeTab(CreativeTabs.BUILDING_BLOCKS);

    public static Block CRACKED_STONE_BRICKS = new BlockBase("cracked_stone_bricks", Material.ROCK, STONE_HARDNESS, STONE_RESISTANCE, SoundType.STONE).setCreativeTab(CreativeTabs.BUILDING_BLOCKS);

    public static Block BLACK_STONE_STAIRS = new BlockStairBase("black_stone_stairs", ModBlocks.BLACK_STONE.getDefaultState(), STONE_HARDNESS, STONE_RESISTANCE, SoundType.STONE).setCreativeTab(CreativeTabs.BUILDING_BLOCKS);

    public static Block POLISHED_BLACK_STONE_STAIRS = new BlockStairBase("polished_black_stone_stairs", ModBlocks.POLISHED_BLACK_STONE.getDefaultState(), STONE_HARDNESS, STONE_RESISTANCE, SoundType.STONE).setCreativeTab(CreativeTabs.BUILDING_BLOCKS);

    public static Block BLACK_STONE_BRICK_STAIRS = new BlockStairBase("black_stone_brick_stairs", ModBlocks.BLACK_STONE_BRICKS.getDefaultState(), STONE_HARDNESS, STONE_RESISTANCE, SoundType.STONE).setCreativeTab(CreativeTabs.BUILDING_BLOCKS);

    public static Block LODE_STONE = new BlockLodeStone("lode_stone", Material.ROCK, 3.5F, 3.5F, NBSoundTypes.LODE_STONE).setCreativeTab(CreativeTabs.DECORATIONS);

    public static Block PIGLIN_HEAD = new BlockPiglinHead("piglin_head");

    public static Block RESPAWN_ANCHOR = new BlockRespawnAnchor("respawn_anchor", Material.ROCK, OBSIDIAN_HARDNESS, OBSIDIAN_RESISTANCE, SoundType.STONE).setHarvestInfo("pickaxe", 3).setCreativeTab(CreativeTabs.DECORATIONS);

    public static Block SHROOMLIGHT = new BlockBase("shroom_light", Material.GRASS, 1.0F, 1.0F, NBSoundTypes.SHROOM_LIGHT).setCreativeTab(CreativeTabs.DECORATIONS).setLightLevel(1.0F);

    public static Block QUARTZ_BRICKS = new BlockBase("quartz_bricks", Material.ROCK, STONE_HARDNESS, STONE_RESISTANCE, SoundType.STONE).setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
    public static Block CRIMSON_FUNGUS = new BlockCrimsonFungusTree("crimson_fungus", Material.PLANTS, NBSoundTypes.HYPHAE);

    public static Block WARPED_FUNGUS = new BlockFungusTree("warped_fungus", Material.PLANTS, NBSoundTypes.HYPHAE);

    public static Block CRIMSON_ROOTS = new BlockPlantBase("crimson_roots", Material.PLANTS, NBSoundTypes.ROOTS);

    public static Block WARPED_ROOTS = new BlockPlantBase("warped_roots", Material.PLANTS, NBSoundTypes.ROOTS);

    public static Block WARPED_SPROUT = new BlockSmallPlantBase("warped_sprout", Material.PLANTS, NBSoundTypes.SPROUT);

    public static Block CRIMSON_VINES = new BlockVineBase("crimson_vine", Material.PLANTS, NBSoundTypes.ROOTS);

    public static Block WARPED_VINES = new BlockVineUpBase("warped_vine", Material.PLANTS, NBSoundTypes.ROOTS);

    public static Block SOUL_FIRE = new BlockSoulFire(CreativeTabs.DECORATIONS, "soul_fire").setLightLevel(0.7F);

    public static Block CHAINS = new BlockChainNew("chain_block", Material.ROCK, STONE_HARDNESS, STONE_RESISTANCE, NBSoundTypes.CHAIN).setCreativeTab(CreativeTabs.DECORATIONS);

    public static Block SOUL_LANTERN = new BlockSoulLantern("soul_lantern", Material.ROCK, 3.5F, 3.5F, NBSoundTypes.LANTERN).setCreativeTab(CreativeTabs.DECORATIONS).setLightLevel(0.7F);
    public static Block SOUL_TORCH = new BlockSoulTorch("soul_torch").setLightLevel(0.7F);


}
