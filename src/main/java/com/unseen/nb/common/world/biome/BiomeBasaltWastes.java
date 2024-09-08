package com.unseen.nb.common.world.biome;

import com.unseen.nb.client.particles.ParticlePixel;
import com.unseen.nb.common.entity.entities.EntityStrider;
import com.unseen.nb.common.world.terrain.basaltHeights.BasaltDeltas;
import com.unseen.nb.common.world.terrain.basaltHeights.RandomLavaInsertion;
import com.unseen.nb.common.world.terrain.basaltHeights.WorldGenBasaltHeights;
import com.unseen.nb.init.ModBlocks;
import com.unseen.nb.init.ModSoundHandler;
import com.unseen.nb.util.ModRand;
import git.jbredwards.nether_api.api.audio.IMusicType;
import git.jbredwards.nether_api.api.audio.ISoundAmbience;
import git.jbredwards.nether_api.api.audio.impl.SoundAmbience;
import git.jbredwards.nether_api.api.biome.IAmbienceBiome;
import git.jbredwards.nether_api.api.biome.INetherBiome;
import git.jbredwards.nether_api.api.registry.INetherAPIRegistryListener;
import git.jbredwards.nether_api.api.world.INetherAPIChunkGenerator;
import git.jbredwards.nether_api.mod.common.config.NetherAPIConfig;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.init.Blocks;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.Random;
/**WIP*/
public class BiomeBasaltWastes extends Biome implements INetherBiome, INetherAPIRegistryListener, IAmbienceBiome {


    public static BiomeProperties properties = new BiomeProperties("Basalt Deltas");
    private static final IBlockState CRIMSON_FLOOR = ModBlocks.BASALT.getDefaultState();
    private WorldGenBasaltHeights heights = new WorldGenBasaltHeights(ModRand.range(4, 10));
    private BasaltDeltas delta = new BasaltDeltas(ModRand.range(7,9));
    private RandomLavaInsertion lava = new RandomLavaInsertion();
    private Random random;
    /**WIP*/
    public BiomeBasaltWastes() {
        super(properties.setRainDisabled());
        this.spawnableMonsterList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
        this.spawnableMonsterList.add(new SpawnListEntry(EntityMagmaCube.class, 100, 2, 5));
        this.spawnableMonsterList.add(new SpawnListEntry(EntityGhast.class, 40, 1, 2));
        this.spawnableCreatureList.add(new SpawnListEntry(EntityStrider.class, 40, 2, 4));
        this.topBlock = CRIMSON_FLOOR;
        random = new Random();
    }



    @Override
    public void decorate(World world, Random rand, BlockPos pos)
    {

      //  BASALT HEIGHTS
       for(int k2 = 0; k2 < ModRand.range(10, 20);k2++) {
           int l6 = random.nextInt(16) + 8;
            int k10 = random.nextInt(16) + 8;
           int depthSignature = 2;
            for(int y = NetherAPIConfig.tallNether ? 240 : 110; y > 32; y--) {
               IBlockState currentBlock = world.getBlockState(pos.add(l6, y, k10));
                if(depthSignature == 1) {
                       // heights.generate(world, rand, pos.add(l6, y + 1, k10));
                }

                if(currentBlock == ModBlocks.BASALT.getDefaultState()) {
                    depthSignature++;
               } else if (currentBlock == Blocks.AIR.getDefaultState()) {
                    depthSignature = 0;
               }
           }
        }

        //  BASALT DELTAS
        for(int k2 = 0; k2 < ModRand.range(2, 5);k2++) {
            int l6 = random.nextInt(16) + 8;
            int k10 = random.nextInt(16) + 8;
            int depthSignature = 2;
            for(int y = NetherAPIConfig.tallNether ? 240 : 110; y > 32; y--) {
                IBlockState currentBlock = world.getBlockState(pos.add(l6, y, k10));
                if(depthSignature == 1) {
                    if(!world.isAirBlock(pos.add(l6 + 8, y, k10 + 8)) && !world.isAirBlock(pos.add(l6 - 8, y, k10 - 8))) {
                        delta.generate(world, rand, pos.add(l6, y, k10));
                    }
                }

                if(currentBlock == ModBlocks.BASALT.getDefaultState()) {
                    depthSignature++;
                } else if (currentBlock == ModBlocks.SMOOTH_BASALT.getDefaultState()) {
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
                        primer.setBlockState(x, y, z, topBlock);
                        //Builds random
                        for(int i = y; i <= y + chunkGenerator.getRand().nextInt(10); i++) {
                            primer.setBlockState(x, i, z, topBlock);
                        }
                        currDepth = 15 + chunkGenerator.getRand().nextInt(5);
                }
                else if(currDepth > 0) {
                    --currDepth;
                    if(y <= 35 && y >= 15) {
                        fillerBlock = Blocks.GRAVEL.getDefaultState();
                        primer.setBlockState(x,y,z,fillerBlock);
                    } else {
                        if(currDepth < 10) {
                            fillerBlock = ModBlocks.BLACK_STONE.getDefaultState();
                            primer.setBlockState(x, y, z, fillerBlock);
                        } else {
                            fillerBlock = ModBlocks.SMOOTH_BASALT.getDefaultState();
                            primer.setBlockState(x, y, z, fillerBlock);
                        }
                    }

                }
            }
        }
    }

    @Override
    public void populate(@Nonnull INetherAPIChunkGenerator chunkGenerator, int chunkX, int chunkZ) {
        int x = chunkX * 16;
        int z = chunkZ * 16;

        BlockPos pos = new BlockPos(x,0,z);
         // Random Lava
        for(int k2 = 0; k2 < ModRand.range(20, 40);k2++) {
           int l6 = random.nextInt(16) + 8;
            int k10 = random.nextInt(16) + 8;
            int depthSignature = 2;
            for(int y = NetherAPIConfig.tallNether ? 240 : 110; y > 32; y--) {
                IBlockState currentBlock = chunkGenerator.getWorld().getBlockState(pos.add(l6, y, k10));
                if(depthSignature == 1) {
                    lava.generate(chunkGenerator.getWorld(), chunkGenerator.getRand(), pos.add(l6, y + 1, k10));
                }

               if(currentBlock == ModBlocks.BASALT.getDefaultState()) {
                   depthSignature++;
               } else if (currentBlock == Blocks.AIR.getDefaultState() || currentBlock == ModBlocks.SMOOTH_BASALT.getDefaultState()) {
                    depthSignature = 0;
               }
            }
        }

        INetherBiome.super.populate(chunkGenerator, chunkX, chunkZ);
    }

    @Nonnull
    @Override
    public Vec3d getFogColor(float celestialAngle, float partialTicks) {
        return new Vec3d(0.6, 0.604, 0.69);
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

    @Override
    @SideOnly(Side.CLIENT)
    public IParticleFactory[] getAmbientParticles()
    { return new IParticleFactory[] {new ParticlePixel.AshBasaltFactory()} ; }

    @Override
    public ISoundAmbience getRandomAmbientSound() {
        return new SoundAmbience(ModSoundHandler.BASALT_DELTAS_AMBIENT, 0.001);
    }

    @Override
    public SoundEvent getAmbientSound() {
        return ModSoundHandler.BASALT_DELTAS_AMBIENT_CONSTANT;
    }
}
