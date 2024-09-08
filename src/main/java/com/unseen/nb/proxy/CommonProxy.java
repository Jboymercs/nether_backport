package com.unseen.nb.proxy;

import com.unseen.nb.common.event.EventOnSoulFire;
import com.unseen.nb.common.network.ParticleMessage;
import com.unseen.nb.init.ModNetworkPackets;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class CommonProxy {

    public void init() {
        MinecraftForge.EVENT_BUS.register(new EventOnSoulFire());
    }



    public void registerItemRenderer(Item item, int meta, String id) {
    }

    public void handleAnimationPacket(int entityId, int index) {

    }

    /** Handles spawning of Particles */
    public void spawnParticle(int particleId, World world, double posX, double posY, double posZ, double speedX, double speedY, double speedZ, int... parameters)
    {
        if (world.isRemote)
        { spawnParticle(particleId, posX, posY, posZ, speedX, speedY, speedZ, parameters); }
        else
        { ModNetworkPackets.network.sendToAllTracking( new ParticleMessage(particleId, posX, posY, posZ, speedX, speedY, speedZ, parameters), new NetworkRegistry.TargetPoint(world.provider.getDimension(), posX, posY, posZ, 0.0D)); }
    }

    /** This exists to be overridden in the ClientProxy! */
    public void spawnParticle(int particleId, double posX, double posY, double posZ, double speedX, double speedY, double speedZ, int... parameters) {}

    public void spawnSoulParticle(World worldIn, double x, double y, double z, double motX, double motY, double motZ) {
    }
}
