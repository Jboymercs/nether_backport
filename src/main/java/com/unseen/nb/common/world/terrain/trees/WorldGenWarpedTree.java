package com.unseen.nb.common.world.terrain.trees;

import com.unseen.nb.common.world.base.WorldGenNB;
import com.unseen.nb.init.ModBlocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class WorldGenWarpedTree extends WorldGenNB {
    int size;




    public WorldGenWarpedTree(String structureName, int size) {
        super("trees/warped/" + structureName);
        this.size = size;
    }


    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        if(size == 1) {
            //small trees
            if(worldIn.isAirBlock(position.add(0, 7, 0)) && worldIn.getBlockState(position.down()) == ModBlocks.WARPED_GRASS.getDefaultState()) {
                if(worldIn.getBlockState(position) == ModBlocks.WARPED_FUNGUS.getDefaultState()) {
                    worldIn.setBlockToAir(position);
                }
                return super.generate(worldIn, rand, position);
            }
        } else if (size == 2) {
            //medium trees
            if(worldIn.isAirBlock(position.add(0, 10, 0)) && worldIn.getBlockState(position.down()) == ModBlocks.WARPED_GRASS.getDefaultState()) {
                if(worldIn.getBlockState(position) == ModBlocks.WARPED_FUNGUS.getDefaultState()) {
                    worldIn.setBlockToAir(position);
                }
                return super.generate(worldIn, rand, position);
            }
        } else {
            //large trees
            if(worldIn.isAirBlock(position.add(0, 14, 0)) && worldIn.getBlockState(position.down()) == ModBlocks.WARPED_GRASS.getDefaultState()) {
                if(worldIn.getBlockState(position) == ModBlocks.WARPED_FUNGUS.getDefaultState()) {
                    worldIn.setBlockToAir(position);
                }
                return super.generate(worldIn, rand, position);
            }
        }
        return false;
    }
}
