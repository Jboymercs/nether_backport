package com.unseen.nb.common.world.ore;

import com.unseen.nb.init.ModBlocks;
import com.unseen.nb.init.ModBlocksCompat;
import com.unseen.nb.util.ModRand;
import com.unseen.nb.util.integration.ModIntegration;
import git.jbredwards.nether_api.mod.common.config.NetherAPIConfig;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class NBOreGen implements IWorldGenerator {
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
    if(world.provider.getDimension() == -1) {
        genOre(ModBlocks.NETHER_GOLD_ORE.getDefaultState(), ModRand.range(1, 16), 10, 10, NetherAPIConfig.tallNether ? 240 : 120, world, random, chunkX, chunkZ);
        genOre(ModBlocks.BLACK_STONE.getDefaultState(), ModRand.range(6, 24), 10, 10, NetherAPIConfig.tallNether ? 240 : 120, world, random, chunkX, chunkZ);
        if(!ModIntegration.FUTURE_MC_LOADED) {
            genOre(ModBlocksCompat.NETHERITE_ORE.getDefaultState(), ModRand.range(1, 5), 5, 3, 24, world, random, chunkX, chunkZ);
        }
    }

    }


    private void genOre(IBlockState block, int size, int spawnChances, int minHeight, int maxHeight, World world, Random rand, int chunkX, int chunkZ){
        WorldGenNetherOre generator = new WorldGenNetherOre(size, block);
        int dy =  maxHeight - minHeight +1;
        if (dy < 1) return;
        for (int i = 0; i < spawnChances; i++){
            int x = chunkX * 16 + rand.nextInt(16);
            int y = minHeight + rand.nextInt(dy);
            int z = chunkZ * 16 + rand.nextInt(16);
            generator.generate(world, rand, new BlockPos(x, y, z));
        }
    }
}
