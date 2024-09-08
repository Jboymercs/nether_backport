package com.unseen.nb.common.world.terrain.fire;

import com.unseen.nb.init.ModBlocks;
import net.minecraft.block.BlockFire;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenSoulFire extends WorldGenerator {
    private final IBlockState plantState;
    private final BlockFire plantBlock;

    public WorldGenSoulFire(IBlockState plant)
    {
        this.plantState = plant;
        this.plantBlock = (BlockFire) plant.getBlock();
    }

    public boolean generate(World world, Random rand, BlockPos pos)
    {
        if(world.getBlockState(pos) == ModBlocks.SOUL_SOIL.getDefaultState()) {
            world.setBlockState(pos.up(), plantState);
        }
        return true;
    }
}
