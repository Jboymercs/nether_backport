package com.unseen.nb.common.world.terrain.basaltHeights;

import com.google.common.collect.Lists;
import com.unseen.nb.init.ModBlocks;
import com.unseen.nb.util.ModRand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.List;
import java.util.Random;

public class WorldGenBasaltHeights extends WorldGenerator {

    private int radius;

    public WorldGenBasaltHeights( int radius) {
    this.radius = radius;
    }

    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        intereperetCircle(position.getX(), position.getY(), position.getZ(), worldIn);
        return true;
    }

    public void intereperetCircle(double x, double y, double z, World world) {
        List<BlockPos> affectedConversionPositions = Lists.newArrayList();
        int radius_int_conversion = (int) Math.ceil(radius);
        for (int dx = -radius_int_conversion; dx < radius_int_conversion + 1; dx++) {
            int y_lim = (int) Math.sqrt(radius_int_conversion*radius_int_conversion-dx*dx);
        for (int dy = -y_lim; dy < y_lim + 1; dy++) {
            int z_lim = (int) Math.sqrt(radius_int_conversion * radius_int_conversion - dx * dx - dy * dy);
            for (int dz = -z_lim; dz < z_lim + 1; dz++) {
                BlockPos blockPos = new BlockPos(x + dx, y + dy, z + dz);
                double power = power(Math.sqrt(dx * dx + dy * dy + dz * dz), radius);
                if ((power > 1) || (power > new Random().nextDouble())) {
                    affectedConversionPositions.add(blockPos);
                }
            }
        }
        }

        for(BlockPos blockPos : affectedConversionPositions) {
            int findSurface = getNetherSurfaceHeight(world, blockPos, blockPos.getY() - 2, blockPos.getY() + 5);
            BlockPos relPos = new BlockPos(blockPos.getX(), findSurface, blockPos.getZ());
            if(!world.isAirBlock(relPos.down()) && findSurface != 0 && world.getBlockState(relPos).getBlock() == ModBlocks.BASALT && world.isAirBlock(relPos.up())) {

                for(int t = 0; t < ModRand.range(1, 5); t++) {
                    world.setBlockState(relPos.add(0,t,0), ModBlocks.BASALT.getDefaultState());
                }

            }
        }
    }


    public static double power(double dist, double radius){
        double decay_rd = radius * 0.95;
        if(dist < decay_rd){
            return 1.1d;
        }
        else {
            return -(1/(radius-decay_rd))*(dist-decay_rd) + 1;
        }
    }

    private int getNetherSurfaceHeight(World world, BlockPos pos, int min, int max)
    {
        int maxY = max;
        int minY = min;
        int currentY = maxY;

        while(currentY >= minY)
        {
            if(!world.isAirBlock(pos.add(0, currentY, 0)))
                return currentY;
            currentY--;
        }
        return 0;
    }

}
