package com.unseen.nb.common.world;

import com.unseen.nb.common.world.bastion.WorldGenBastion;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class WorldGenNetherStructures implements IWorldGenerator {


    private static final WorldGenBastion bastion = new WorldGenBastion();

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        int x = chunkX * 16;
        int z = chunkZ * 16;
        BlockPos pos = new BlockPos(x + 8, 0, z + 8);

        //using the overworld for testing, change this ID later
        if(world.provider.getDimension() == -1) {
            //This is the connect between the bastion spawn rules and a signal to tell it to try it here
            //WorldGenBastion handles the actual spawn rules
            bastion.generate(world, random, pos);
        }
    }
}
