package com.unseen.nb.common.world.terrain.basaltHeights;

import com.unseen.nb.init.ModBlocks;
import com.unseen.nb.util.ModRand;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class RandomLavaInsertion extends WorldGenerator {

    public RandomLavaInsertion() {

    }

    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        if(!worldIn.isAirBlock(position.add(1,0,0)) && !worldIn.isAirBlock(position.add(-1,0,0)) && !worldIn.isAirBlock(position.add(0,0,1)) && !worldIn.isAirBlock(position.add(0,0,-1))
        && worldIn.getBlockState(position) != Blocks.LAVA.getDefaultState() && worldIn.getBlockState(position.add(0,1,0)) != Blocks.LAVA.getDefaultState()) {
            worldIn.setBlockState(position, Blocks.LAVA.getDefaultState());
            for(int t = position.getY() - 1; t >= position.getY() - ModRand.range(2, 7); t--) {
                BlockPos posModified = new BlockPos(position.getX(), t, position.getZ());
                if(!worldIn.isAirBlock(posModified)) {
                    worldIn.setBlockState(posModified, ModBlocks.BLACK_STONE.getDefaultState());
                }
            }
            return true;
        }
        return false;
    }
}
