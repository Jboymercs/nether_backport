package com.unseen.nb.util;

import com.google.common.collect.Lists;
import com.unseen.nb.init.ModBlocks;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Random;

public class ModUtils {
    public static int getAverageGroundHeight(World world, int x, int z, int sizeX, int sizeZ, int maxVariation) {
        sizeX = x + sizeX;
        sizeZ = z + sizeZ;
        int corner1 = calculateGenerationHeight(world, x, z);
        int corner2 = calculateGenerationHeight(world, sizeX, z);
        int corner3 = calculateGenerationHeight(world, x, sizeZ);
        int corner4 = calculateGenerationHeight(world, sizeX, sizeZ);

        int max = Math.max(Math.max(corner3, corner4), Math.max(corner1, corner2));
        int min = Math.min(Math.min(corner3, corner4), Math.min(corner1, corner2));
        if (max - min > maxVariation) {
            return -1;
        }
        return min;
    }

    public static int calculateGenerationHeight(World world, int x, int z) {
        return world.getTopSolidOrLiquidBlock(new BlockPos(x, 0, z)).getY();
    }

    public static void replaceBlocksInRandCircle(World world, int radius, double x, double y, double z) {

        //This is for the Conversion to Ash Wastelands blocks
        List<BlockPos> affectedConversionPositions = Lists.newArrayList();
        int radius_int_conversion = (int) Math.ceil(radius);

        for (int dx = -radius_int_conversion; dx < radius_int_conversion + 1; dx++) {
            // fast calculate affected blocks
            int y_lim = (int) Math.sqrt(radius_int_conversion*radius_int_conversion-dx*dx);
            for (int dy = -y_lim; dy < y_lim + 1; dy++) {
                int z_lim = (int) Math.sqrt(radius_int_conversion*radius_int_conversion-dx*dx-dy*dy);
                for (int dz = -z_lim; dz < z_lim + 1; dz++) {
                    BlockPos blockPos = new BlockPos(x + dx, y + dy, z + dz);
                    double power = interperetVar(Math.sqrt(dx*dx+dy*dy+dz*dz), radius);
                    if ((power>1) ||(power > new Random().nextDouble())){
                        affectedConversionPositions.add(blockPos);
                    }
                }
            }
        }

        for(BlockPos blockPos : affectedConversionPositions) {
            if(world.getBlockState(blockPos).getBlock() == ModBlocks.SOUL_SOIL) {
                world.setBlockState(blockPos, Blocks.SOUL_SAND.getDefaultState());
            }
        }
    }

    public static double interperetVar(double dist, double radius){
        double decay_rd = radius * 0.95;
        if(dist < decay_rd){
            return 1.1d;
        }
        else {
            return -(1/(radius-decay_rd))*(dist-decay_rd) + 1;
        }
    }
}
