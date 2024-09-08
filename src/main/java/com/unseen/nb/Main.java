package com.unseen.nb;

import com.unseen.nb.common.capabilities.CapabilityRespawnAnchor;
import com.unseen.nb.common.world.WorldGenNetherStructures;
import com.unseen.nb.common.world.ore.NBOreGen;
import com.unseen.nb.handler.StructureHandler;
import com.unseen.nb.init.*;
import com.unseen.nb.proxy.CommonProxy;
import com.unseen.nb.util.LootTableExtendedFunc;
import com.unseen.nb.util.ModReference;
import com.unseen.nb.util.NBLogger;
import com.unseen.nb.util.integration.ModIntegration;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.storage.loot.functions.LootFunctionManager;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.GameRegistry;
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
        NBLogger.clearLog();
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
        //Mod Integration for Crossbows
        ModIntegration.init();
        //Register World Gen
        GameRegistry.registerWorldGenerator(new NBOreGen(), 1);
        //Register Bastion Spawning
        GameRegistry.registerWorldGenerator(new WorldGenNetherStructures(), 1);
        //Loot Table Functions Extension
        LootFunctionManager.registerFunction(new LootTableExtendedFunc.Serializer());
    }

    @EventHandler
    public void init(FMLInitializationEvent e) {
        //Biome Init
        BiomeRegister.registerBiomes();
        ModNetworkPackets.registerNetworkPackets();
        CapabilityManager.INSTANCE.register(CapabilityRespawnAnchor.ICapabilityRespawnAnchor.class, new CapabilityRespawnAnchor.Storage(), CapabilityRespawnAnchor.RespawnAnchorMethods::new);
        //Furnace Anvil Recipes
        ModRecipes.init();
        //Registers the Structures and Templates
        StructureHandler.handleStructureRegistries();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent e) {

    }



    public static <MSG extends IMessage> void sendMSGToAll(MSG message) {

        //  for(EntityPlayerMP playerMP : Minecraft.getMinecraft().) {
        //  sendNonLocal(message, playerMP);
        //  }
        //network.sendToAll(message);
    }


    public static <MSG extends IMessage> void sendNonLocal(MSG message, EntityPlayerMP playerMP) {
        network.sendTo(message, playerMP);
    }
}
