package com.unseen.nb.common.world.terrain.basaltHeights;

import com.google.common.collect.Lists;
import com.unseen.nb.init.ModBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.List;
import java.util.Random;

public class BasaltDeltas extends WorldGenerator {

    private int radius;

    public BasaltDeltas(int radius) {
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

            for(int c = (int) y + 9; c >= y - 10; c--) {
                BlockPos findSmooth = new BlockPos(blockPos.getX(), c, blockPos.getZ());
                if(world.getBlockState(findSmooth) == ModBlocks.SMOOTH_BASALT.getDefaultState()&& world.getBlockState(findSmooth.add(0,1,0)) == ModBlocks.BASALT.getDefaultState()) {
                    y = c + 1;
                }
            }

            BlockPos relPos = new BlockPos(blockPos.getX(), y, blockPos.getZ());
            //Make sure theres air above
            if(world.getBlockState(relPos) != ModBlocks.BLACK_STONE.getDefaultState() && !world.isAirBlock(relPos) && world.getBlockState(relPos) != Blocks.LAVA.getDefaultState()) {

                if (world.isAirBlock(relPos.add(1, 0, 0)) || world.isAirBlock(relPos.add(-1, 0, 0)) || world.isAirBlock(relPos.add(0, 0, -1)) || world.isAirBlock(relPos.add(0, 0, 1)) ||
                        world.isAirBlock(relPos.add(0, -1, 0))) {
                    world.setBlockState(relPos, ModBlocks.BLACK_STONE.getDefaultState());
                } else {
                    if (world.rand.nextInt(4) == 0) {
                        world.setBlockState(relPos, Blocks.MAGMA.getDefaultState());
                        world.setBlockState(relPos.add(0,-1,0), ModBlocks.BLACK_STONE.getDefaultState());
                        for (int b = relPos.getY() + 1; b <= relPos.getY() + 19; b++) {
                            BlockPos adjustY = new BlockPos(relPos.getX(), b, relPos.getZ());

                            if(world.getBlockState(adjustY) != ModBlocks.BASALT.getDefaultState()) {
                                break;
                            }

                            if (world.getBlockState(adjustY) == ModBlocks.BASALT.getDefaultState()) {
                                world.setBlockToAir(adjustY);
                            }
                        }
                    } else {
                        world.setBlockState(relPos, Blocks.LAVA.getDefaultState());
                        world.setBlockState(relPos.add(0,-1,0), ModBlocks.BLACK_STONE.getDefaultState());
                        for (int b = relPos.getY() + 1; b <= relPos.getY() + 19; b++) {
                            BlockPos adjustY = new BlockPos(relPos.getX(), b, relPos.getZ());

                            if(world.getBlockState(adjustY) != ModBlocks.BASALT.getDefaultState()) {
                                break;
                            }
                            if (world.getBlockState(adjustY) == ModBlocks.BASALT.getDefaultState()) {
                                world.setBlockToAir(adjustY);
                            }
                        }
                    }
                }
            }

        }
    }


           // }






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
