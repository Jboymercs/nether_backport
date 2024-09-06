package com.unseen.nb.util;

import net.minecraft.util.ResourceLocation;

public class ModReference {
    public static final String MOD_ID ="nb";
    public static final String NAME = "Unseens Nether Backport";
    public static final String CHANNEL_NETWORK_NAME = "Unseens Nether Backport";

    public static final String VERSION = "0.0.1";

    public static final String CLIENT_PROXY_CLASS = "com.unseen.nb.proxy.ClientProxy";
    public static final String COMMON_PROXY_CLASS = "com.unseen.nb.proxy.CommonProxy";


    public static ResourceLocation loc(String name) {
        return new ResourceLocation(MOD_ID, name.toLowerCase());
    }
}
