package com.unseen.nb.init;

import com.unseen.nb.common.network.ParticleMessage;
import com.unseen.nb.util.ModReference;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class ModNetworkPackets
{
    public static SimpleNetworkWrapper network;

    public static void registerNetworkPackets()
    {
        int packetId = 0;
        network = NetworkRegistry.INSTANCE.newSimpleChannel(ModReference.MOD_ID);
        network.registerMessage(ParticleMessage.Handler.class, ParticleMessage.class, packetId++, Side.CLIENT);
    }

}
