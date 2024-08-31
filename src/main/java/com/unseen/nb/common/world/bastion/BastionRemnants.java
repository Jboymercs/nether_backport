package com.unseen.nb.common.world.bastion;

import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.template.TemplateManager;

import java.util.List;

/**
 * The Spawn rules and generation rules for the Bastion Remnants
 */
public class BastionRemnants {
    private List<StructureComponent> components;
    private World world;
    private TemplateManager manager;

    //Make sure that you put the config option for size here too
    private static final int SIZE = 6;

    //this is just for a second floor used by my structures, you can realistically go as infinite with floors and levels as you'd like
    private static final int SECOND_SIZE = SIZE * 2;
    protected BlockPos posIdentified;

    public BastionRemnants(World worldIn, TemplateManager template, List<StructureComponent> components) {
        this.world = worldIn;
        this.manager = template;
        this.components = components;
    }


    public void startBastion(BlockPos pos, Rotation rot) {
        this.posIdentified = pos;

    }
}
