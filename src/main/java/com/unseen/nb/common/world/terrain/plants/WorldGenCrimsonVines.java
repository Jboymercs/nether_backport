package com.unseen.nb.common.world.terrain.plants;

import com.unseen.nb.common.blocks.BlockNetherVines;
import com.unseen.nb.util.ModRand;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenCrimsonVines extends WorldGenerator {

    private final IBlockState plantState;
    private final BlockNetherVines plantBlock;

    public WorldGenCrimsonVines(IBlockState plant)
    {
        this.plantState = plant;
        this.plantBlock = (BlockNetherVines) plant.getBlock();
    }


    public boolean generate(World world, Random rand, BlockPos pos)
    {
        if(plantBlock.canBlockStay(world, pos, plantState)) {
            world.setBlockState(pos, plantState);

            for(int t = pos.getY(); t > pos.getY() - ModRand.range(-6, -1); t--) {
                BlockPos posToo = new BlockPos(pos.getX(), t, pos.getZ());
                if(world.isAirBlock(posToo)) {
                    world.setBlockState(posToo, plantState);
                }
            }
        }
        return true;
    }
}
