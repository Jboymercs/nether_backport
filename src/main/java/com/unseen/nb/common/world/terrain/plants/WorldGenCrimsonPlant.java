package com.unseen.nb.common.world.terrain.plants;

import com.unseen.nb.init.ModBlocks;
import net.minecraft.block.BlockBush;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenCrimsonPlant extends WorldGenerator {

    private final IBlockState plantState;
    private final BlockBush plantBlock;

    public WorldGenCrimsonPlant(IBlockState plant)
    {
        this.plantState = plant;
        this.plantBlock = (BlockBush) plant.getBlock();
    }

    public boolean generate(World world, Random rand, BlockPos pos)
    {
        if(plantBlock.canBlockStay(world, pos.up(), plantState) && world.getBlockState(pos) == ModBlocks.CRIMSON_GRASS.getDefaultState()) {
            world.setBlockState(pos.up(), plantState);
        }
        return true;
    }
}
