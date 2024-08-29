package com.unseen.nb.common.world.biome;

import com.unseen.nb.common.entity.entities.EntityStrider;
import com.unseen.nb.common.world.base.WorldGenNB;
import com.unseen.nb.common.world.terrain.fire.WorldGenSoulFire;
import com.unseen.nb.common.world.terrain.fossils.WorldGenFossils;
import com.unseen.nb.common.world.terrain.fossils.WorldGenReplaceOnRand;
import com.unseen.nb.common.world.terrain.plants.WorldGenCrimsonVines;
import com.unseen.nb.common.world.terrain.plants.WorldGenWarpedPlant;
import com.unseen.nb.common.world.terrain.plants.WorldGenWarpedVines;
import com.unseen.nb.common.world.terrain.trees.WorldGenWarpedTree;
import com.unseen.nb.init.ModBlocks;
import com.unseen.nb.util.ModRand;
import com.unseen.nb.util.ModUtils;
import git.jbredwards.nether_api.api.audio.IMusicType;
import git.jbredwards.nether_api.api.biome.INetherBiome;
import git.jbredwards.nether_api.api.registry.INetherAPIRegistryListener;
import git.jbredwards.nether_api.api.world.INetherAPIChunkGenerator;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

import javax.annotation.Nonnull;
import java.util.Random;

public class BiomeSoulValley extends Biome implements INetherBiome, INetherAPIRegistryListener {
    public static BiomeProperties properties = new BiomeProperties("Soul Sand Valley");
    private static final IBlockState CRIMSON_FLOOR = ModBlocks.SOUL_SOIL.getDefaultState();

    private final WorldGenNB[] fossils = {new WorldGenFossils("fossil_1"), new WorldGenFossils("fossil_2"), new WorldGenFossils("fossil_3"), new WorldGenFossils("fossil_4"),
            new WorldGenFossils("fossil_5"), new WorldGenFossils("fossil_6"), new WorldGenFossils("fossil_7"), new WorldGenFossils("fossil_8")};
    private Random random;

    private final WorldGenSoulFire soulFire = new WorldGenSoulFire(ModBlocks.SOUL_FIRE.getDefaultState());

    private final WorldGenReplaceOnRand soul_sand_replacer = new WorldGenReplaceOnRand();
    public BiomeSoulValley() {
        super(properties.setRainDisabled());
        this.spawnableMonsterList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
        this.spawnableMonsterList.add(new SpawnListEntry(EntityEnderman.class, 1, 1, 1));
        this.spawnableMonsterList.add(new SpawnListEntry(EntitySkeleton.class, 10, 1, 2));
        this.spawnableMonsterList.add(new SpawnListEntry(EntityGhast.class, 8, 1, 2));
        this.spawnableCreatureList.add(new SpawnListEntry(EntityStrider.class, 10, 2, 4));
        //Add Strider weight 10, min 2, max 4
        this.topBlock = CRIMSON_FLOOR;
        random = new Random();
    }

    @Override
    public void decorate(World world, Random rand, BlockPos pos) {
        //Fossils
        if (rand.nextInt(5) == 0) {
            for (int k2 = 0; k2 < ModRand.range(1, 2); k2++) {
                int l6 = random.nextInt(16) + 8;
                int k10 = random.nextInt(16) + 8;
                int depthSignature = 2;
                for (int y = 110; y > 32; y--) {
                    IBlockState currentBlock = world.getBlockState(pos.add(l6, y, k10));
                    if (depthSignature == 1) {
                        WorldGenNB fossils_from = ModRand.choice(fossils);
                        fossils_from.generate(world, rand, pos.add(l6 - 2, y + 1, k10 - 2));
                    }

                    if (currentBlock == ModBlocks.SOUL_SOIL.getDefaultState() || currentBlock == Blocks.SOUL_SAND.getDefaultState()) {
                        depthSignature++;
                    } else if (currentBlock == Blocks.AIR.getDefaultState()) {
                        depthSignature = 0;
                    }
                }
            }
        }


        //FIRE
        if(world.rand.nextInt(8) == 0) {
            for (int k2 = 0; k2 < ModRand.range(2, 5); k2++) {
                int l6 = random.nextInt(16) + 8;
                int k10 = random.nextInt(16) + 8;
                int depthSignature = 2;
                for (int y = 110; y > 32; y--) {
                    IBlockState currentBlock = world.getBlockState(pos.add(l6, y, k10));
                    if (depthSignature == 1) {
                        soulFire.generate(world, rand, pos.add(l6, y + 1, k10));
                    }
                    if (currentBlock == ModBlocks.SOUL_SOIL.getDefaultState()) {
                        depthSignature++;
                    } else if (currentBlock == Blocks.AIR.getDefaultState()) {
                        depthSignature = 0;
                    }
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
                    currDepth = 2 + chunkGenerator.getRand().nextInt(2);
                    if(random.nextInt(4) == 0) {
                        primer.setBlockState(x,y,z, Blocks.SOUL_SAND.getDefaultState());
                    } else {
                        primer.setBlockState(x, y, z, topBlock);
                    }
                }
                else if(currDepth > 0) {
                    --currDepth;
                    fillerBlock = Blocks.SOUL_SAND.getDefaultState();
                    primer.setBlockState(x, y, z, fillerBlock);
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
