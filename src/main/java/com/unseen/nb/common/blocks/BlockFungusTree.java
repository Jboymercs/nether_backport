package com.unseen.nb.common.blocks;

import com.unseen.nb.common.blocks.base.BlockPlantBase;
import com.unseen.nb.common.world.base.WorldGenNB;
import com.unseen.nb.common.world.terrain.trees.WorldGenCrimsonTree;
import com.unseen.nb.common.world.terrain.trees.WorldGenWarpedTree;
import com.unseen.nb.init.ModBlocks;
import com.unseen.nb.util.ModRand;
import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class BlockFungusTree extends BlockPlantBase implements IGrowable
{
    private final WorldGenNB[] crimson_small_trees = {new WorldGenCrimsonTree("s_c_tree_1", 1), new WorldGenCrimsonTree("s_c_tree_2", 1), new WorldGenCrimsonTree("s_c_tree_3", 1), new WorldGenCrimsonTree("s_c_tree_4", 1), new WorldGenCrimsonTree("s_c_tree_5", 1)};
    private final WorldGenNB[] crimson_medium_trees = {new WorldGenCrimsonTree("m_c_tree_1", 2),new WorldGenCrimsonTree("m_c_tree_2", 2), new WorldGenCrimsonTree("m_c_tree_3", 2), new WorldGenCrimsonTree("m_c_tree_4", 2), new WorldGenCrimsonTree("m_c_tree_5", 2) };
    private final WorldGenNB[] crimson_large_trees = {new WorldGenCrimsonTree("l_c_tree_1", 3), new WorldGenCrimsonTree("l_c_tree_2", 3), new WorldGenCrimsonTree("l_c_tree_3", 3), new WorldGenCrimsonTree("l_c_tree_4", 3), new WorldGenCrimsonTree("l_c_tree_5", 3)};

    private final WorldGenNB[] warped_small_trees = {new WorldGenWarpedTree("s_w_tree_1", 1), new WorldGenWarpedTree("s_w_tree_2", 1), new WorldGenWarpedTree("s_w_tree_3", 1), new WorldGenWarpedTree("s_w_tree_4", 1), new WorldGenWarpedTree("s_w_tree_5", 1)};
    private final WorldGenNB[] warped_medium_trees = {new WorldGenWarpedTree("m_w_tree_1", 2),new WorldGenWarpedTree("m_w_tree_2", 2), new WorldGenWarpedTree("m_w_tree_3", 2), new WorldGenWarpedTree("m_w_tree_4", 2), new WorldGenWarpedTree("m_w_tree_5", 2) };
    private final WorldGenNB[] warped_large_trees = {new WorldGenWarpedTree("l_w_tree_1", 3), new WorldGenWarpedTree("l_w_tree_2", 3), new WorldGenWarpedTree("l_w_tree_3", 3), new WorldGenWarpedTree("l_w_tree_4", 3), new WorldGenWarpedTree("l_w_tree_5", 3)};
    protected static final AxisAlignedBB FUNGUS_AABB = new AxisAlignedBB(0.3D, 0.0D, 0.3D, 0.75D, 0.5D, 0.7D);

    /** If this is a Warped Fungi. Changes which Generators to use, and which block to check for below. */
    boolean isWarped;

    public BlockFungusTree(String name, Material materialIn, SoundType soundType, boolean isWarpedIn)
    {
        super(name, materialIn, soundType);
        isWarped = isWarpedIn;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) { return FUNGUS_AABB; }

    @Override
    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient)
    {
        Block blockBelow = worldIn.getBlockState(pos.down()).getBlock();
        return isWarped ? blockBelow == ModBlocks.WARPED_GRASS : blockBelow == ModBlocks.CRIMSON_GRASS;
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state)
    {
        Block blockBelow = worldIn.getBlockState(pos.down()).getBlock();
        return (isWarped ? blockBelow == ModBlocks.WARPED_GRASS : blockBelow == ModBlocks.CRIMSON_GRASS) && worldIn.rand.nextFloat() < 0.45D;
    }

    @Override
    public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state)
    {
        if(worldIn.isAirBlock(pos.up()) && worldIn.isAirBlock(pos.add(0, 9,0)))
        { this.generateTree(worldIn, pos, rand); }
    }

    private void generateTree(World world, BlockPos pos, Random random)
    {
        int randTreeSize = ModRand.range(1, 4);
        if(!world.isRemote)
        {
            WorldGenNB tree = null;

            switch (randTreeSize)
            {
                case 1:
                    tree = ModRand.choice(isWarped ? warped_small_trees : crimson_small_trees);
                    break;
                case 2:
                    tree = ModRand.choice(isWarped ? warped_medium_trees : crimson_medium_trees);
                    break;
                default:
                    tree = ModRand.choice(isWarped ? warped_large_trees : crimson_large_trees);
                    break;
            }
            if (tree != null) tree.generate(world, random, pos);
        }

    }
}
