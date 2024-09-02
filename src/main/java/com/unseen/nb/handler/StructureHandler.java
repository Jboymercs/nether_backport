package com.unseen.nb.handler;

import com.unseen.nb.common.world.bastion.BastionTemplate;
import com.unseen.nb.common.world.bastion.WorldGenBastion;
import net.minecraft.world.gen.structure.MapGenStructureIO;

public class StructureHandler {


    public static void handleStructureRegistries(){
        MapGenStructureIO.registerStructure(WorldGenBastion.Start.class, "BastionRemnants");
        MapGenStructureIO.registerStructureComponent(BastionTemplate.class, "BRP");
    }
}
