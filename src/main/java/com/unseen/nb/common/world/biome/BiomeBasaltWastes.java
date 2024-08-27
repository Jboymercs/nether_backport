package com.unseen.nb.common.world.biome;

import com.unseen.nb.common.world.base.WorldGenNB;
import com.unseen.nb.common.world.terrain.basaltHeights.WorldGenBasaltHeights;
import com.unseen.nb.common.world.terrain.plants.WorldGenCrimsonPlant;
import com.unseen.nb.common.world.terrain.plants.WorldGenCrimsonVines;
import com.unseen.nb.common.world.terrain.trees.WorldGenCrimsonTree;
import com.unseen.nb.init.ModBlocks;
import com.unseen.nb.util.ModRand;
import git.jbredwards.nether_api.api.audio.IMusicType;
import git.jbredwards.nether_api.api.biome.INetherBiome;
import git.jbredwards.nether_api.api.registry.INetherAPIRegistryListener;
import git.jbredwards.nether_api.api.world.INetherAPIChunkGenerator;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

import javax.annotation.Nonnull;
import java.util.Random;
/**WIP*/
public class BiomeBasaltWastes extends Biome implements INetherBiome, INetherAPIRegistryListener {


    public static BiomeProperties properties = new BiomeProperties("Basalt Deltas");
    private static final IBlockState CRIMSON_FLOOR = ModBlocks.BASALT.getDefaultState();
    private WorldGenBasaltHeights heights = new WorldGenBasaltHeights(ModRand.range(4, 10));
    private Random random;
    /**WIP*/
    public BiomeBasaltWastes() {
        super(properties.setRainDisabled());
        this.spawnableMonsterList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
        this.topBlock = CRIMSON_FLOOR;
        random = new Random();
    }

    @Override
    public void decorate(World world, Random rand, BlockPos pos)
    {

        //BASALT HEIGHTS
        for(int k2 = 0; k2 < ModRand.range(20, 30);k2++) {
            int l6 = random.nextInt(16) + 8;
            int k10 = random.nextInt(16) + 8;
            int depthSignature = 2;
            for(int y = 110; y > 32; y--) {
                IBlockState currentBlock = world.getBlockState(pos.add(l6, y, k10));
                if(depthSignature == 1) {
                        heights.generate(world, rand, pos.add(l6, y + 1, k10));
                }

                if(currentBlock == ModBlocks.BASALT.getDefaultState()) {
                    depthSignature++;
                } else if (currentBlock == Blocks.AIR.getDefaultState()) {
                    depthSignature = 0;
                }
            }
        }
    }
    @Override
    public void buildSurface(@Nonnull INetherAPIChunkGenerator chunkGenerator, int chunkX, int chunkZ, @Nonnull ChunkPrimer primer, int x, int z, double[] soulSandNoise, double[] gravelNoise, double[] depthBuffer, double terrainNoise) {
        int currDepth = -1;
        for(int y = chunkGenerator.getWorld().getActualHeight() - 1; y >= 0; --y) {
            final IBlockState here = primer.getBlockState(x, y, z);
            if(here.getMaterial() == Material.AIR) currDepth = -1;
            else if(here.getBlock() == Blocks.NETHERRACK) {
                if(currDepth == -1) {
                    if(random.nextInt(5) == 0) {
                        primer.setBlockState(x, y, z, Blocks.LAVA.getDefaultState());
                    } else {
                        primer.setBlockState(x, y, z, topBlock);
                    }
                        currDepth = 20 + chunkGenerator.getRand().nextInt(5);
                }
                else if(currDepth > 0) {
                    --currDepth;
                    if(y <= 35 && y >= 25) {
                        fillerBlock = Blocks.GRAVEL.getDefaultState();
                        primer.setBlockState(x,y,z,fillerBlock);
                    } else {
                        fillerBlock = ModBlocks.BASALT.getDefaultState();
                        primer.setBlockState(x, y, z, fillerBlock);
                    }

                }
            }
        }
    }

    @Override
    public void populate(@Nonnull INetherAPIChunkGenerator chunkGenerator, int chunkX, int chunkZ) {
        INetherBiome.super.populate(chunkGenerator, chunkX, chunkZ);
    }

    @Nonnull
    @Override
    public Vec3d getFogColor(float celestialAngle, float partialTicks) {
        return INetherBiome.super.getFogColor(celestialAngle, partialTicks);
    }

    @Nonnull
    @Override
    public IMusicType getMusicType() {
        return INetherBiome.super.getMusicType();
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
