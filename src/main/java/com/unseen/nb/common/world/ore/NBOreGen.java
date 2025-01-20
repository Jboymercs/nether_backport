package com.unseen.nb.common.world.ore;

import com.unseen.nb.config.ModConfig;
import com.unseen.nb.config.NBWorldConfig;
import com.unseen.nb.init.ModBlocks;
import com.unseen.nb.init.ModBlocksCompat;
import com.unseen.nb.util.ModRand;
import com.unseen.nb.util.integration.ModIntegration;
import git.jbredwards.nether_api.mod.common.config.NetherAPIConfig;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import org.lwjgl.Sys;
import thedarkcolour.futuremc.FutureMC;
import thedarkcolour.futuremc.config.FConfig;
import thedarkcolour.futuremc.registry.FBlocks;

import java.util.Objects;
import java.util.Random;

public class NBOreGen implements IWorldGenerator {
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
    if(world.provider.getDimension() == -1) {
        genOre(ModBlocks.NETHER_GOLD_ORE.getDefaultState(), ModRand.range(1, 16), NBWorldConfig.nether_gold_spawn_chances, NBWorldConfig.nether_gold_min_y, NetherAPIConfig.tallNether && NBWorldConfig.enabled_double_height ? NBWorldConfig.nether_gold_max_y * 2 : NBWorldConfig.nether_gold_max_y, world, random, chunkX, chunkZ);
        genOre(ModBlocks.BLACK_STONE.getDefaultState(), ModRand.range(32, 64), NBWorldConfig.blacstone_spawn_chances, NBWorldConfig.blackstone_min_y, NetherAPIConfig.tallNether && NBWorldConfig.enabled_double_height ? NBWorldConfig.blackstone_max_y * 2 : NBWorldConfig.blackstone_max_y, world, random, chunkX, chunkZ);
        if(!ModIntegration.FUTURE_MC_LOADED) {
            genOre(ModBlocksCompat.NETHERITE_ORE.getDefaultState(), ModRand.range(1, 5), NBWorldConfig.ancient_debris_spawn_chances, NBWorldConfig.ancient_debris_min_y, NBWorldConfig.ancient_debris_max_y, world, random, chunkX, chunkZ);
        } else if (ModConfig.use_backport_generation) {
            if(ModIntegration.FUTURE_MC_LOADED) {
                genOre(Objects.requireNonNull(ForgeRegistries.BLOCKS.getValue(new ResourceLocation(FutureMC.ID + ":ancient_debris"))).getDefaultState(), ModRand.range(1, 5), NBWorldConfig.ancient_debris_spawn_chances, NBWorldConfig.ancient_debris_min_y, NBWorldConfig.ancient_debris_max_y, world, random, chunkX, chunkZ);
            }
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
