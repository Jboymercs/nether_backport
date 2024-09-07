package com.unseen.nb.handler;

import com.unseen.nb.config.ModConfig;
import com.unseen.nb.init.BiomeRegister;
import com.unseen.nb.util.ModReference;
import git.jbredwards.nether_api.api.event.NetherAPIRegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;

@Mod.EventBusSubscriber(modid = ModReference.MOD_ID)
public class ApiEventHandler {


    @SubscribeEvent(priority = EventPriority.HIGHEST)
    static void onNetherAPIRegistry(@Nonnull final NetherAPIRegistryEvent.Nether event)
    {
        event.registry.registerBiome(BiomeRegister.CRIMSON_FOREST, ModConfig.crimsonForestRate);
        event.registry.registerBiome(BiomeRegister.WARPED_FOREST, ModConfig.warpedForestRate);
        event.registry.registerBiome(BiomeRegister.SOUL_SAND_VALLEY, ModConfig.soulSandValleyRate);
        event.registry.registerBiome(BiomeRegister.BASALT_DELTAS, ModConfig.basaltDeltasRate);
    }
}
