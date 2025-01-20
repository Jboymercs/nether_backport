package com.unseen.nb.common.blocks;

import com.unseen.nb.common.blocks.base.BlockPlantBase;
import com.unseen.nb.common.world.base.WorldGenNB;
import com.unseen.nb.common.world.terrain.trees.WorldGenCrimsonTree;
import com.unseen.nb.common.world.terrain.trees.WorldGenWarpedTree;
import com.unseen.nb.init.ModBlocks;
import com.unseen.nb.util.ModRand;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockFungusTree extends BlockPlantBase implements IGrowable {
    private final WorldGenNB[] small_trees = {new WorldGenWarpedTree("s_w_tree_1", 1), new WorldGenWarpedTree("s_w_tree_2", 1), new WorldGenWarpedTree("s_w_tree_3", 1), new WorldGenWarpedTree("s_w_tree_4", 1), new WorldGenWarpedTree("s_w_tree_5", 1)};
    private final WorldGenNB[] medium_trees = {new WorldGenWarpedTree("m_w_tree_1", 2),new WorldGenWarpedTree("m_w_tree_2", 2), new WorldGenWarpedTree("m_w_tree_3", 2), new WorldGenWarpedTree("m_w_tree_4", 2), new WorldGenWarpedTree("m_w_tree_5", 2) };

    private final WorldGenNB[] large_trees = {new WorldGenWarpedTree("l_w_tree_1", 3), new WorldGenWarpedTree("l_w_tree_2", 3), new WorldGenWarpedTree("l_w_tree_3", 3), new WorldGenWarpedTree("l_w_tree_4", 3), new WorldGenWarpedTree("l_w_tree_5", 3)};
    protected static final AxisAlignedBB SAPLING_AABB = new AxisAlignedBB(0.3D, 0.0D, 0.3D, 0.75D, 0.5D, 0.7D);

    public BlockFungusTree(String name, Material materialIn, SoundType soundType) {
        super(name, materialIn, soundType);

    }

    @Override
    @Nullable
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(this);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return SAPLING_AABB;
    }

    @Override
    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient)
    { return worldIn.getBlockState(pos.down()).getBlock() == ModBlocks.WARPED_GRASS; }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state)
    {
        return worldIn.getBlockState(pos.down()).getBlock() == ModBlocks.WARPED_GRASS && worldIn.rand.nextFloat() < 0.45D;
    }

    @Override
    public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        if(worldIn.isAirBlock(pos.up()) && worldIn.isAirBlock(pos.add(0, 9,0))) {
            this.generateTree(worldIn, pos, rand);
        }

    }

    private void generateTree(World world, BlockPos pos, Random random) {
        int randTreeSize = ModRand.range(1, 4);
        if(!world.isRemote) {
                //Warped
                if(randTreeSize == 1) {
                    WorldGenNB tree = ModRand.choice(small_trees);
                    tree.generate(world, random, pos);
                } else if (randTreeSize == 2) {
                    WorldGenNB tree = ModRand.choice(medium_trees);
                    tree.generate(world, random, pos);
                } else {
                    WorldGenNB large_tree = ModRand.choice(large_trees);
                    large_tree.generate(world, random, pos);
                }
        }

    }
}
