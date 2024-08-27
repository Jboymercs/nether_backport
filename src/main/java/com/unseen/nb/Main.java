package com.unseen.nb;

import com.unseen.nb.init.BiomeRegister;
import com.unseen.nb.init.ModEntities;
import com.unseen.nb.init.ModSoundHandler;
import com.unseen.nb.proxy.CommonProxy;
import com.unseen.nb.util.ModReference;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = ModReference.MOD_ID, name = ModReference.NAME, version = ModReference.VERSION)
public class Main {

    @SidedProxy(clientSide = ModReference.CLIENT_PROXY_CLASS, serverSide = ModReference.COMMON_PROXY_CLASS)
    public static CommonProxy proxy;
    public static SimpleNetworkWrapper network;
    public static Logger LOGGER = LogManager.getLogger(ModReference.MOD_ID);

    @Mod.Instance
    public static Main instance;


    public Main() {

    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    //Proxy Init
    proxy.init();
        //Registers Sounds
        ModSoundHandler.registerSounds();
        //Registers Entities
        ModEntities.registerEntities();
        //Registers Entity Spawns
        ModEntities.RegisterEntitySpawns();
    }

    @EventHandler
    public void init(FMLInitializationEvent e) {
        //Biome Init
        BiomeRegister.registerBiomes();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent e) {

    }
}
