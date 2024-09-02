package com.unseen.nb.common.world.bastion;

import com.unseen.nb.util.ModRand;
import com.unseen.nb.util.ModReference;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.template.TemplateManager;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

/**
 * The Spawn rules and generation rules for the Bastion Remnants
 */
public class BastionRemnants {
    private List<StructureComponent> components;
    private World world;
    private TemplateManager manager;

    //Make sure that you put the config option for size here too
    private static final int SIZE = 2;

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
        BastionTemplate bastion_template = new BastionTemplate(manager, "bridge_parts/bridge_to_hold_1", pos, rot, 0, true);
        components.add(bastion_template);
        BastionTemplate.resetTemplateCount();
        startHoldEntrance(bastion_template, pos, rot);
        System.out.println("Generated Bastion at" + pos);

    }


    private boolean generateHoldBridge() {
    return false;
    }

    /***
     * Probably the biggest part of the Bastions, the hold
     * @param parent
     * @param pos
     * @param rot
     * @return
     */
    private boolean startHoldEntrance(BastionTemplate parent, BlockPos pos, Rotation rot) {
        //These two are the main pieces that will center and generate IAW with the bridge
    BastionTemplate entrance_b_l = addAdjustedPieceNoCount(parent, BlockPos.ORIGIN.add(0,-2,10),"hold/top_r_layer_entrance", rot);
    BastionTemplate entrance_t_l = addAdjustedPieceNoCount(parent, BlockPos.ORIGIN.add(0,-2,-10), "hold/bot_r_layer_entrance", rot);
    //Outer two pieces
    BastionTemplate entrance_t_r = addAdjustedPieceNoCount(parent, BlockPos.ORIGIN.add(20, -2, 10), "hold/top_l_layer_entrance", rot);
    BastionTemplate entrance_b_r = addAdjustedPieceNoCount(parent, BlockPos.ORIGIN.add(20, -2, -10), "hold/bot_l_layer_entrance", rot);

    components.add(entrance_b_l);
    components.add(entrance_t_l);
    components.add(entrance_t_r);
    components.add(entrance_b_r);
    //We are using the bridges position to spawn this massive structure, makes for easy placement and y-adjustment
    //generates the ceiling
        generateBastionCeiling(parent, pos, rot);
    //generates one layer above the entrance
        generateBastionGenerateUpperLayer(parent, pos, rot);
    //generates layers below, Max 3
        generateBastionLayerOne(parent, pos, rot);
        generateBastionLayerTwo(parent, pos, rot);
        generateBastionLayerThree(parent, pos, rot);
        //Floor of the Hold
        generateBastionFloor(parent, pos, rot);

    return true;
    }

    private boolean generateBastionCeiling(BastionTemplate parent, BlockPos pos, Rotation rot) {
        String[] top_right_types = {"hold/top_r_layer_ceiling"};
        BastionTemplate layer_t_r = addAdjustedPieceNoCount(parent, BlockPos.ORIGIN.add(0, 14, 10), ModRand.choice(top_right_types), rot);
        String[] bottom_right_types = {"hold/bot_r_layer_ceiling"};
        BastionTemplate layer_b_r = addAdjustedPieceNoCount(parent, BlockPos.ORIGIN.add(0, 14, -10), ModRand.choice(bottom_right_types), rot);
        String[] top_left_types = {"hold/top_l_layer_ceiling"};
        BastionTemplate layer_t_l = addAdjustedPieceNoCount(parent, BlockPos.ORIGIN.add(20, 14, 10), ModRand.choice(top_left_types), rot);
        String[] bottom_left_types = {"hold/bot_l_layer_ceiling"};
        BastionTemplate layer_b_l = addAdjustedPieceNoCount(parent, BlockPos.ORIGIN.add(20, 14, -10), ModRand.choice(bottom_left_types), rot);

        components.add(layer_t_r);
        components.add(layer_b_r);
        components.add(layer_t_l);
        components.add(layer_b_l);

        return true;
    }

    private boolean generateBastionGenerateUpperLayer(BastionTemplate parent, BlockPos pos, Rotation rot) {
        String[] top_right_types = {"hold/top_r_layer_1", "hold/top_r_layer_2", "hold/top_r_layer_3", "hold/top_r_layer_4"};
        BastionTemplate layer_t_r = addAdjustedPieceNoCount(parent, BlockPos.ORIGIN.add(0, 6, 10), ModRand.choice(top_right_types), rot);
        String[] bottom_right_types = {"hold/bot_r_layer_1", "hold/bot_r_layer_2", "hold/bot_r_layer_3", "hold/bot_r_layer_4"};
        BastionTemplate layer_b_r = addAdjustedPieceNoCount(parent, BlockPos.ORIGIN.add(0, 6, -10), ModRand.choice(bottom_right_types), rot);
        String[] top_left_types = {"hold/top_l_layer_1", "hold/top_l_layer_2", "hold/top_l_layer_3", "hold/top_l_layer_4"};
        BastionTemplate layer_t_l = addAdjustedPieceNoCount(parent, BlockPos.ORIGIN.add(20, 6, 10), ModRand.choice(top_left_types), rot);
        String[] bottom_left_types = {"hold/bot_l_layer_1", "hold/bot_l_layer_2", "hold/bot_l_layer_3", "hold/bot_l_layer_4"};
        BastionTemplate layer_b_l = addAdjustedPieceNoCount(parent, BlockPos.ORIGIN.add(20, 6, -10), ModRand.choice(bottom_left_types), rot);

        components.add(layer_t_r);
        components.add(layer_b_r);
        components.add(layer_t_l);
        components.add(layer_b_l);

        return true;
    }

    private boolean generateBastionLayerOne(BastionTemplate parent, BlockPos pos, Rotation rot) {
        String[] top_right_types = {"hold/top_r_layer_1", "hold/top_r_layer_2", "hold/top_r_layer_3", "hold/top_r_layer_4"};
        BastionTemplate layer_t_r = addAdjustedPieceNoCount(parent, BlockPos.ORIGIN.add(0, -10, 10), ModRand.choice(top_right_types), rot);
        String[] bottom_right_types = {"hold/bot_r_layer_1", "hold/bot_r_layer_2", "hold/bot_r_layer_3", "hold/bot_r_layer_4"};
        BastionTemplate layer_b_r = addAdjustedPieceNoCount(parent, BlockPos.ORIGIN.add(0, -10, -10), ModRand.choice(bottom_right_types), rot);
        String[] top_left_types = {"hold/top_l_layer_1", "hold/top_l_layer_2", "hold/top_l_layer_3", "hold/top_l_layer_4"};
        BastionTemplate layer_t_l = addAdjustedPieceNoCount(parent, BlockPos.ORIGIN.add(20, -10, 10), ModRand.choice(top_left_types), rot);
        String[] bottom_left_types = {"hold/bot_l_layer_1", "hold/bot_l_layer_2", "hold/bot_l_layer_3", "hold/bot_l_layer_4"};
        BastionTemplate layer_b_l = addAdjustedPieceNoCount(parent, BlockPos.ORIGIN.add(20, -10, -10), ModRand.choice(bottom_left_types), rot);

        components.add(layer_t_r);
        components.add(layer_b_r);
        components.add(layer_t_l);
        components.add(layer_b_l);

        return true;
    }

    private boolean generateBastionLayerTwo(BastionTemplate parent, BlockPos pos, Rotation rot) {
        String[] top_right_types = {"hold/top_r_layer_1", "hold/top_r_layer_2", "hold/top_r_layer_3", "hold/top_r_layer_4"};
        BastionTemplate layer_t_r = addAdjustedPieceNoCount(parent, BlockPos.ORIGIN.add(0, -18, 10), ModRand.choice(top_right_types), rot);
        String[] bottom_right_types = {"hold/bot_r_layer_1", "hold/bot_r_layer_2", "hold/bot_r_layer_3", "hold/bot_r_layer_4"};
        BastionTemplate layer_b_r = addAdjustedPieceNoCount(parent, BlockPos.ORIGIN.add(0, -18, -10), ModRand.choice(bottom_right_types), rot);
        String[] top_left_types = {"hold/top_l_layer_1", "hold/top_l_layer_2", "hold/top_l_layer_3", "hold/top_l_layer_4"};
        BastionTemplate layer_t_l = addAdjustedPieceNoCount(parent, BlockPos.ORIGIN.add(20, -18, 10), ModRand.choice(top_left_types), rot);
        String[] bottom_left_types = {"hold/bot_l_layer_1", "hold/bot_l_layer_2", "hold/bot_l_layer_3", "hold/bot_l_layer_4"};
        BastionTemplate layer_b_l = addAdjustedPieceNoCount(parent, BlockPos.ORIGIN.add(20, -18, -10), ModRand.choice(bottom_left_types), rot);

        components.add(layer_t_r);
        components.add(layer_b_r);
        components.add(layer_t_l);
        components.add(layer_b_l);

        return true;
    }

    private boolean generateBastionLayerThree(BastionTemplate parent, BlockPos pos, Rotation rot) {
        String[] top_right_types = {"hold/top_r_layer_1", "hold/top_r_layer_2", "hold/top_r_layer_3", "hold/top_r_layer_4"};
        BastionTemplate layer_t_r = addAdjustedPieceNoCount(parent, BlockPos.ORIGIN.add(0, -25, 10), ModRand.choice(top_right_types), rot);
        String[] bottom_right_types = {"hold/bot_r_layer_1", "hold/bot_r_layer_2", "hold/bot_r_layer_3", "hold/bot_r_layer_4"};
        BastionTemplate layer_b_r = addAdjustedPieceNoCount(parent, BlockPos.ORIGIN.add(0, -25, -10), ModRand.choice(bottom_right_types), rot);
        String[] top_left_types = {"hold/top_l_layer_1", "hold/top_l_layer_2", "hold/top_l_layer_3", "hold/top_l_layer_4"};
        BastionTemplate layer_t_l = addAdjustedPieceNoCount(parent, BlockPos.ORIGIN.add(20, -25, 10), ModRand.choice(top_left_types), rot);
        String[] bottom_left_types = {"hold/bot_l_layer_1", "hold/bot_l_layer_2", "hold/bot_l_layer_3", "hold/bot_l_layer_4"};
        BastionTemplate layer_b_l = addAdjustedPieceNoCount(parent, BlockPos.ORIGIN.add(20, -25, -10), ModRand.choice(bottom_left_types), rot);

        components.add(layer_t_r);
        components.add(layer_b_r);
        components.add(layer_t_l);
        components.add(layer_b_l);

        return true;
    }

    private boolean generateBastionFloor(BastionTemplate parent, BlockPos pos, Rotation rot) {
        String[] top_right_types = {"hold/plate_2"};
        BastionTemplate layer_t_r = addAdjustedPieceNoCount(parent, BlockPos.ORIGIN.add(0, -39, 10), ModRand.choice(top_right_types), rot);
        String[] bottom_right_types = {"hold/plate_4"};
        BastionTemplate layer_b_r = addAdjustedPieceNoCount(parent, BlockPos.ORIGIN.add(0, -39, -10), ModRand.choice(bottom_right_types), rot);
        String[] top_left_types = {"hold/plate_1"};
        BastionTemplate layer_t_l = addAdjustedPieceNoCount(parent, BlockPos.ORIGIN.add(20, -39, 10), ModRand.choice(top_left_types), rot);
        String[] bottom_left_types = {"hold/plate_3"};
        BastionTemplate layer_b_l = addAdjustedPieceNoCount(parent, BlockPos.ORIGIN.add(20, -39, -10), ModRand.choice(bottom_left_types), rot);

        components.add(layer_t_r);
        components.add(layer_b_r);
        components.add(layer_t_l);
        components.add(layer_b_l);

        return true;
    }
    /**
     * Adds a new piece, with the previous template a reference for position and
     * rotation
     */
    private BastionTemplate addAdjustedPiece(BastionTemplate parent, BlockPos pos, String type, Rotation rot) {
        BastionTemplate newTemplate = new BastionTemplate(manager, type, parent.getTemplatePosition(), rot, parent.getDistance(), true);
        BlockPos blockpos = parent.getTemplate().calculateConnectedPos(parent.getPlacementSettings(), pos, newTemplate.getPlacementSettings(), BlockPos.ORIGIN);
        newTemplate.offset(blockpos.getX(), blockpos.getY(), blockpos.getZ());
        adjustAndCenter(parent, newTemplate, rot);
        return newTemplate;
    }


    private BastionTemplate addAdjustedPieceNoCount(BastionTemplate parent, BlockPos pos, String type, Rotation rot) {
        BastionTemplate newTemplate = new BastionTemplate(manager, type, parent.getTemplatePosition(), rot, parent.getDistance(), true);
        BlockPos blockpos = parent.getTemplate().calculateConnectedPos(parent.getPlacementSettings(), pos, newTemplate.getPlacementSettings(), BlockPos.ORIGIN);
        newTemplate.offset(blockpos.getX(), blockpos.getY(), blockpos.getZ());
        adjustAndCenter(parent, newTemplate, rot);
        System.out.println("GeneratedEntranceSTart");
        return newTemplate;
    }




    /**
     * Centers a template to line up on the x, and in the center with z
     */
    private void adjustAndCenter(BastionTemplate parent, BastionTemplate child, Rotation rot) {
        BlockPos adjustedPos = new BlockPos(parent.getTemplate().getSize().getX(), 0, (parent.getTemplate().getSize().getZ() - child.getTemplate().getSize().getZ()) / 2f)
                .rotate(rot);
        child.offset(adjustedPos.getX(), adjustedPos.getY(), adjustedPos.getZ());
    }
}
