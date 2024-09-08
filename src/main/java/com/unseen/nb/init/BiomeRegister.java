package com.unseen.nb.init;

import com.unseen.nb.common.world.biome.BiomeBasaltWastes;
import com.unseen.nb.common.world.biome.BiomeCrimsonForest;
import com.unseen.nb.common.world.biome.BiomeSoulValley;
import com.unseen.nb.common.world.biome.BiomeWarpedForest;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class BiomeRegister {

    public static final Biome CRIMSON_FOREST = new BiomeCrimsonForest();
    public static final Biome WARPED_FOREST = new BiomeWarpedForest();

    public static final Biome SOUL_SAND_VALLEY = new BiomeSoulValley();
    public static final Biome BASALT_DELTAS = new BiomeBasaltWastes();
    public static void registerBiomes() {
        initBiome(CRIMSON_FOREST, "crimson_forest", Type.NETHER);
        initBiome(WARPED_FOREST, "warped_forest", Type.NETHER);
        initBiome(SOUL_SAND_VALLEY, "soul_sand_valley", Type.NETHER);
        initBiome(BASALT_DELTAS, "basalt_deltas", Type.NETHER);
    }

    private static void initBiome(Biome biome, String name, BiomeDictionary.Type... types)
    {
        biome.setRegistryName(name);
        ForgeRegistries.BIOMES.register(biome);
        BiomeDictionary.addTypes(biome, types);
    }
}
