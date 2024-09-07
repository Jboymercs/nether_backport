package com.unseen.nb.common.world;

import com.google.common.collect.Lists;
import com.unseen.nb.common.world.bastion.WorldGenBastion;
import com.unseen.nb.common.world.structures.WorldGenNetherPortal;
import com.unseen.nb.common.world.structures.WorldGenRuinedPortals;
import com.unseen.nb.common.world.structures.WorldGenRuinedPortalsGiant;
import com.unseen.nb.config.ModConfig;
import com.unseen.nb.init.BiomeRegister;
import com.unseen.nb.util.ModRand;
import com.unseen.nb.util.NBLogger;
import git.jbredwards.nether_api.mod.common.config.NetherAPIConfig;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.List;
import java.util.Random;

public class WorldGenNetherStructures implements IWorldGenerator {

    private static List<Biome> spawnBiomesRemnants;
    private static List<Biome> spawnBiomesRuinedPortals;
    private static final WorldGenBastion bastion = new WorldGenBastion();

    private static final WorldGenRuinedPortals[] list_Of_Portals = {new WorldGenRuinedPortals("ruined_portal_1"), new WorldGenRuinedPortals("ruined_portal_2"),
            new WorldGenRuinedPortals("ruined_portal_3"),new WorldGenRuinedPortals("ruined_portal_4"),new WorldGenRuinedPortals("ruined_portal_5"),
            new WorldGenRuinedPortals("ruined_portal_6"),new WorldGenRuinedPortals("ruined_portal_7"),new WorldGenRuinedPortals("ruined_portal_8")};

    private static final WorldGenRuinedPortalsGiant[] list_of_Giant_portals = {new WorldGenRuinedPortalsGiant("giant_portal_1"), new WorldGenRuinedPortalsGiant("giant_portal_2"),
    new WorldGenRuinedPortalsGiant("giant_portal_3")};

    private static final WorldGenNetherPortal[] list_of_nether_portals = {new WorldGenNetherPortal("nether_portal_1"),new WorldGenNetherPortal("nether_portal_2"),
            new WorldGenNetherPortal("nether_portal_3"),new WorldGenNetherPortal("nether_portal_4"),new WorldGenNetherPortal("nether_portal_5"),
            new WorldGenNetherPortal("nether_portal_6"),new WorldGenNetherPortal("nether_portal_7")};
    private int portalSpacing = 0;
    private int netherPortalSpacing = 0;

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        int x = chunkX * 16;
        int z = chunkZ * 16;
        BlockPos pos = new BlockPos(x + 8, 0, z + 8);

        //using the overworld for testing, change this ID later
        if(world.provider.getDimension() == -1) {
            //This is the connect between the bastion spawn rules and a signal to tell it to try it here
            //WorldGenBastion handles the actual spawn rules

            if(world.provider.getBiomeForCoords(pos) != getSpawnBiomesRemnarts().iterator()) {
                //Bastion Remnants
                bastion.generate(world, random, pos);


                //Nether Portal Ruins
                if(netherPortalSpacing/8 > ModConfig.nether_ruins_rate) {
                    int y = getNetherSurfaceHeight(world, pos, 35, NetherAPIConfig.tallNether ? 240 : 110);
                    BlockPos modifiedPos = new BlockPos(pos.getX() - 2, y, pos.getZ() - 2);
                    if(!world.isAirBlock(modifiedPos) && !world.isAirBlock(modifiedPos.add(3, 0, 3)) && world.isAirBlock(modifiedPos.add(0, 10, 0))) {
                        WorldGenNetherPortal portal = ModRand.choice(list_of_nether_portals);
                        portal.generate(world, random, pos.add(0, y, 0));
                        netherPortalSpacing = 0;
                    }
                } else {
                    netherPortalSpacing++;
                }
            }
        }

        if (world.provider.getDimension() == 0) {
            if(world.provider.getBiomeForCoords(pos) != getSpawnBiomesRuinedPortals().iterator()) {
                int y = getGroundFromAbove(world, pos.getX(), pos.getZ());
                //generates regular ruined portals
                if (y != 0) {
                    BlockPos posModified = new BlockPos(pos.getX(), y, pos.getZ());
                    if (portalSpacing / 12 > ModConfig.ruined_portal_rate) {
                        //Giant Portal Ruins 5% chance of spawning
                        if (!world.isAirBlock(posModified.down()) && !world.isAirBlock(posModified.add(7, -1, 7)) && world.rand.nextInt(ModConfig.portal_big_chance) == 0) {
                            WorldGenRuinedPortalsGiant portal = ModRand.choice(list_of_Giant_portals);
                            portal.generate(world, random, pos.add(0, y, 0));
                            portalSpacing = 0;

                            //Small Portal Ruins
                        } else if (!world.isAirBlock(posModified.down()) && !world.isAirBlock(posModified.add(3, -1, 3))) {
                            WorldGenRuinedPortals portal = ModRand.choice(list_Of_Portals);
                            portal.generate(world, random, pos.add(0, y, 0));
                            portalSpacing = 0;
                        }
                    } else {
                        portalSpacing++;
                    }
                }
            }
        }
    }


    /**
     * Credit goes to SmileyCorps for Biomes read from a config
     * @return
     */
    public static List<Biome> getSpawnBiomesRemnarts() {
        if (spawnBiomesRemnants == null) {
            spawnBiomesRemnants = Lists.newArrayList();
            for (String str : ModConfig.remnantsBiomesNotAllowed) {
                try {
                    Biome biome = Biome.REGISTRY.getObject(new ResourceLocation(str));
                    if (biome != null) spawnBiomesRemnants.add(biome);
                    else NBLogger.logError("Biome " + str + " is not registered", new NullPointerException());
                } catch (Exception e) {
                    NBLogger.logError(str + " is not a valid registry name", e);
                }
            }
        }
        return spawnBiomesRemnants;
    }

    public static List<Biome> getSpawnBiomesRuinedPortals() {
        if (spawnBiomesRuinedPortals == null) {
            spawnBiomesRuinedPortals = Lists.newArrayList();
            for (String str : ModConfig.ruinsBiomesNotAllowed) {
                try {
                    Biome biome = Biome.REGISTRY.getObject(new ResourceLocation(str));
                    if (biome != null) spawnBiomesRuinedPortals.add(biome);
                    else NBLogger.logError("Biome " + str + " is not registered", new NullPointerException());
                } catch (Exception e) {
                    NBLogger.logError(str + " is not a valid registry name", e);
                }
            }
        }
        return spawnBiomesRuinedPortals;
    }

    public static int getGroundFromAbove(World world, int x, int z)
    {
        int y = 255;
        boolean foundGround = false;
        while(!foundGround && y-- >= 31)
        {
            Block blockAt = world.getBlockState(new BlockPos(x,y,z)).getBlock();
            foundGround =  blockAt != Blocks.AIR && blockAt != Blocks.LEAVES && blockAt != Blocks.LEAVES2 && !(blockAt instanceof BlockLiquid);
        }

        return y;
    }


    private int getNetherSurfaceHeight(World world, BlockPos pos, int min, int max)
    {
        int maxY = max;
        int minY = min;
        int currentY = maxY;

        while(currentY >= minY)
        {
            if(world.isAirBlock(pos.add(0, currentY, 0)) && !world.isAirBlock(pos.add(0, currentY - 1, 0)))
                return currentY - 1;
            currentY--;
        }
        return 0;
    }
}
