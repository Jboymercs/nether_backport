package com.unseen.nb.common.world.terrain.plants;

import com.unseen.nb.common.blocks.BlockNetherVines;
import com.unseen.nb.init.ModBlocks;
import com.unseen.nb.util.ModRand;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenWarpedVines extends WorldGenerator {
    private final IBlockState plantState;
    private final BlockNetherVines plantBlock;

    public WorldGenWarpedVines(IBlockState plant)
    {
        this.plantState = plant;
        this.plantBlock = (BlockNetherVines) plant.getBlock();
    }

    public boolean generate(World world, Random rand, BlockPos pos)
    {
        if(plantBlock.canBlockStay(world, pos.up(), plantState) && world.getBlockState(pos) == ModBlocks.WARPED_GRASS.getDefaultState()) {
            world.setBlockState(pos.up(), plantState);
            int randInterval = ModRand.range(1, 7);
            for(int i = pos.getY() + 1; i < pos.getY() + randInterval; i++) {
                BlockPos posToo = new BlockPos(pos.getX(), i, pos.getZ());
                if(world.isAirBlock(posToo)) {
                    world.setBlockState(posToo, plantState);
                }
            }
        }
        return true;
    }
}
