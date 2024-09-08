package com.unseen.nb.common.world.bastion;

import com.unseen.nb.util.ModRand;
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
    private static final int SIZE = 2;

    public static Rotation savedHoldRotation;

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
        //chooses to generate either a pig fort or regular fort
        BastionTemplate pig_fort = new BastionTemplate(manager, "pig_fort/pig_bridge", pos, rot, 0, true);

        BastionTemplate stable_fort = new BastionTemplate(manager, "pig_fort/tower_l_1", pos, rot, 0, true);

        if(ModRand.range(1, 10) <= 6) {
            components.add(stable_fort);
            BastionTemplate.resetTemplateCount();
            finishFort(stable_fort, pos, rot);

        } else {
            components.add(pig_fort);
            BastionTemplate.resetTemplateCount();

            generatePigFort(pig_fort, pos, rot);
        }
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

    private boolean finishFort(BastionTemplate parent, BlockPos pos, Rotation rot) {
        BastionTemplate right_tower = addAdjustedPieceNoCount(parent, BlockPos.ORIGIN.add(-16, 0, 16), "pig_fort/tower_r_1", rot);
        BastionTemplate lower_part_left = addAdjustedPieceNoCount(parent, BlockPos.ORIGIN.add(-16, -24, 0), "pig_fort/big_pillar_l_2", rot);
        BastionTemplate lower_part_right = addAdjustedPieceNoCount(parent, BlockPos.ORIGIN.add(-16, -24, 16), "pig_fort/big_pillar_r_2", rot);
        //start House unit 1
        BastionTemplate unit_1 = addAdjustedPieceNoCount(parent, BlockPos.ORIGIN.add(0,-14,4), "units/house_unit_1", rot);
        BastionTemplate unit_2 =addAdjustedPieceNoCount(parent, BlockPos.ORIGIN.add(8, -14, 4), "units/house_unit_2", rot);
        BastionTemplate unit_3 = addAdjustedPieceNoCount(parent, BlockPos.ORIGIN.add(16, -14, 4), "units/house_unit_3", rot);
        components.add(right_tower);
        components.add(lower_part_left);
        components.add(lower_part_right);
        components.add(unit_1);
        components.add(unit_2);
        components.add(unit_3);
        return true;

    }



    private boolean generatePigFort(BastionTemplate parent, BlockPos pos, Rotation rot) {
        BastionTemplate pig_face = addAdjustedPieceNoCount(parent, BlockPos.ORIGIN.add(0, 8,0), "pig_fort/pig_face", rot);
        BastionTemplate left_pillar = addAdjustedPieceNoCount(parent, BlockPos.ORIGIN.add(0,-16,-8), "pig_fort/big_pillar_l_1", rot);
        BastionTemplate right_pillar = addAdjustedPieceNoCount(parent, BlockPos.ORIGIN.add(0, -16, 8), "pig_fort/big_pillar_r_1", rot);
        BastionTemplate front_face = addAdjustedPieceNoCount(parent, BlockPos.ORIGIN.add(-5,17,1 ), "pig_fort/front_face", rot);
        BastionTemplate pillar_for_bridge = addAdjustedPieceNoCount(parent, BlockPos.ORIGIN.add(-12, -16,0), "bridge_parts/pillar_1", rot);

        components.add(left_pillar);
        components.add(right_pillar);
        components.add(front_face);
        components.add(pig_face);
        components.add(pillar_for_bridge);

        if(world.rand.nextInt(4) == 0) {
            generateSideTowers(pig_face, pos, rot);
        } else {
            generateBridgeTooHold(pig_face, pos, rot);
        }

        return true;
    }

    private boolean generateSideTowers(BastionTemplate parent, BlockPos pos, Rotation rot) {
        BastionTemplate side_tower_left = addAdjustedPieceNoCount(parent, BlockPos.ORIGIN.add(-16, -24, -24), "pig_fort/big_pillar_l_2", rot);
        BastionTemplate side_tower_right = addAdjustedPieceNoCount(parent, BlockPos.ORIGIN.add(-16, -24, 24), "pig_fort/big_pillar_r_2", rot);
        BastionTemplate tower_left = addAdjustedPieceNoCount(parent, BlockPos.ORIGIN.add(-16, 0, -24), "pig_fort/tower_l_1", rot);
        BastionTemplate tower_right = addAdjustedPieceNoCount(parent, BlockPos.ORIGIN.add(-16, 0, 24), "pig_fort/tower_r_1", rot);

        components.add(side_tower_left);
        components.add(tower_left);
        if(world.rand.nextInt(2) == 0) {
            components.add(side_tower_right);
            components.add(tower_right);
        }

        return true;
    }
    private boolean generateBridgeTooHold(BastionTemplate parent, BlockPos pos, Rotation rot) {
        BastionTemplate bridge = addAdjustedPieceNoCount(parent, BlockPos.ORIGIN.add(0,1,0), "bridge_parts/bridge_to_hold_1", rot);
        components.add(bridge);
        startHoldEntrance(bridge, pos, rot);
        return true;
    }
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
        savedHoldRotation = rot;

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
        String[] top_right_types = {"hold/top_r_layer_1", "hold/top_r_layer_2", "hold/top_r_layer_3", "hold/top_r_layer_4", "hold/top_r_layer_5"};
        BastionTemplate layer_t_r = addAdjustedPieceNoCount(parent, BlockPos.ORIGIN.add(0, 6, 10), ModRand.choice(top_right_types), rot);
        String[] bottom_right_types = {"hold/bot_r_layer_1", "hold/bot_r_layer_2", "hold/bot_r_layer_3", "hold/bot_r_layer_4", "hold/bot_r_layer_5"};
        BastionTemplate layer_b_r = addAdjustedPieceNoCount(parent, BlockPos.ORIGIN.add(0, 6, -10), ModRand.choice(bottom_right_types), rot);
        String[] top_left_types = {"hold/top_l_layer_1", "hold/top_l_layer_2", "hold/top_l_layer_3", "hold/top_l_layer_4", "hold/top_l_layer_5"};
        BastionTemplate layer_t_l = addAdjustedPieceNoCount(parent, BlockPos.ORIGIN.add(20, 6, 10), ModRand.choice(top_left_types), rot);
        String[] bottom_left_types = {"hold/bot_l_layer_1", "hold/bot_l_layer_2", "hold/bot_l_layer_3", "hold/bot_l_layer_4", "hold/bot_l_layer_5"};
        BastionTemplate layer_b_l = addAdjustedPieceNoCount(parent, BlockPos.ORIGIN.add(20, 6, -10), ModRand.choice(bottom_left_types), rot);

        components.add(layer_t_r);
        components.add(layer_b_r);
        components.add(layer_t_l);
        components.add(layer_b_l);

        if(world.rand.nextInt(8) != 0) {
            generateInnerBridges(layer_b_r, pos, rot);
        }

        return true;
    }

    private boolean generateBastionLayerOne(BastionTemplate parent, BlockPos pos, Rotation rot) {
        String[] top_right_types = {"hold/top_r_layer_1", "hold/top_r_layer_2", "hold/top_r_layer_3", "hold/top_r_layer_4", "hold/top_r_layer_5"};
        BastionTemplate layer_t_r = addAdjustedPieceNoCount(parent, BlockPos.ORIGIN.add(0, -10, 10), ModRand.choice(top_right_types), rot);
        String[] bottom_right_types = {"hold/bot_r_layer_1", "hold/bot_r_layer_2", "hold/bot_r_layer_3", "hold/bot_r_layer_4", "hold/bot_r_layer_5"};
        BastionTemplate layer_b_r = addAdjustedPieceNoCount(parent, BlockPos.ORIGIN.add(0, -10, -10), ModRand.choice(bottom_right_types), rot);
        String[] top_left_types = {"hold/top_l_layer_1", "hold/top_l_layer_2", "hold/top_l_layer_3", "hold/top_l_layer_4", "hold/top_l_layer_5"};
        BastionTemplate layer_t_l = addAdjustedPieceNoCount(parent, BlockPos.ORIGIN.add(20, -10, 10), ModRand.choice(top_left_types), rot);
        String[] bottom_left_types = {"hold/bot_l_layer_1", "hold/bot_l_layer_2", "hold/bot_l_layer_3", "hold/bot_l_layer_4", "hold/bot_l_layer_5"};
        BastionTemplate layer_b_l = addAdjustedPieceNoCount(parent, BlockPos.ORIGIN.add(20, -10, -10), ModRand.choice(bottom_left_types), rot);

        components.add(layer_t_r);
        components.add(layer_b_r);
        components.add(layer_t_l);
        components.add(layer_b_l);

        if(world.rand.nextInt(8) != 0) {
            generateInnerBridges(layer_b_r, pos, rot);
        }

        return true;
    }

    private boolean generateBastionLayerTwo(BastionTemplate parent, BlockPos pos, Rotation rot) {
        String[] top_right_types = {"hold/top_r_layer_1", "hold/top_r_layer_2", "hold/top_r_layer_3", "hold/top_r_layer_4", "hold/top_r_layer_5"};
        BastionTemplate layer_t_r = addAdjustedPieceNoCount(parent, BlockPos.ORIGIN.add(0, -18, 10), ModRand.choice(top_right_types), rot);
        String[] bottom_right_types = {"hold/bot_r_layer_1", "hold/bot_r_layer_2", "hold/bot_r_layer_3", "hold/bot_r_layer_4", "hold/bot_r_layer_5"};
        BastionTemplate layer_b_r = addAdjustedPieceNoCount(parent, BlockPos.ORIGIN.add(0, -18, -10), ModRand.choice(bottom_right_types), rot);
        String[] top_left_types = {"hold/top_l_layer_1", "hold/top_l_layer_2", "hold/top_l_layer_3", "hold/top_l_layer_4", "hold/top_l_layer_5"};
        BastionTemplate layer_t_l = addAdjustedPieceNoCount(parent, BlockPos.ORIGIN.add(20, -18, 10), ModRand.choice(top_left_types), rot);
        String[] bottom_left_types = {"hold/bot_l_layer_1", "hold/bot_l_layer_2", "hold/bot_l_layer_3", "hold/bot_l_layer_4", "hold/bot_l_layer_5"};
        BastionTemplate layer_b_l = addAdjustedPieceNoCount(parent, BlockPos.ORIGIN.add(20, -18, -10), ModRand.choice(bottom_left_types), rot);

        components.add(layer_t_r);
        components.add(layer_b_r);
        components.add(layer_t_l);
        components.add(layer_b_l);

        if(world.rand.nextInt(8) != 0) {
            generateInnerBridges(layer_b_r, pos, rot);
        }

        return true;
    }

    private boolean generateBastionLayerThree(BastionTemplate parent, BlockPos pos, Rotation rot) {
        String[] top_right_types = {"hold/top_r_layer_1", "hold/top_r_layer_2", "hold/top_r_layer_3", "hold/top_r_layer_4", "hold/top_r_layer_5"};
        BastionTemplate layer_t_r = addAdjustedPieceNoCount(parent, BlockPos.ORIGIN.add(0, -25, 10), ModRand.choice(top_right_types), rot);
        String[] bottom_right_types = {"hold/bot_r_layer_1", "hold/bot_r_layer_2", "hold/bot_r_layer_3", "hold/bot_r_layer_4", "hold/bot_r_layer_5"};
        BastionTemplate layer_b_r = addAdjustedPieceNoCount(parent, BlockPos.ORIGIN.add(0, -25, -10), ModRand.choice(bottom_right_types), rot);
        String[] top_left_types = {"hold/top_l_layer_1", "hold/top_l_layer_2", "hold/top_l_layer_3", "hold/top_l_layer_4", "hold/top_l_layer_5"};
        BastionTemplate layer_t_l = addAdjustedPieceNoCount(parent, BlockPos.ORIGIN.add(20, -25, 10), ModRand.choice(top_left_types), rot);
        String[] bottom_left_types = {"hold/bot_l_layer_1", "hold/bot_l_layer_2", "hold/bot_l_layer_3", "hold/bot_l_layer_4", "hold/bot_l_layer_5"};
        BastionTemplate layer_b_l = addAdjustedPieceNoCount(parent, BlockPos.ORIGIN.add(20, -25, -10), ModRand.choice(bottom_left_types), rot);

        components.add(layer_t_r);
        components.add(layer_b_r);
        components.add(layer_t_l);
        components.add(layer_b_l);

        if(world.rand.nextInt(8) != 0) {
            generateInnerBridges(layer_b_r, pos, rot);
        }

        return true;
    }

    private boolean generateInnerBridges(BastionTemplate parent, BlockPos pos, Rotation rot) {
        String[] inner_types = {"bridge_parts/hold_bridge_1", "bridge_parts/hold_bridge_2","bridge_parts/hold_bridge_3","bridge_parts/hold_bridge_4","bridge_parts/hold_bridge_5"};
        BastionTemplate bridge = addAdjustedPieceNoCount(parent, BlockPos.ORIGIN.add(-13, 0,10), ModRand.choice(inner_types), rot);
        components.add(bridge);
        return true;
    }

    private boolean generateTreasureRoom(BastionTemplate parent, BlockPos pos, Rotation rot) {
        String[] treasure_rooms = {"treasure/treasure_hold_1","treasure/treasure_hold_2","treasure/treasure_hold_3","treasure/treasure_hold_4"};
        BastionTemplate treasure = addAdjustedPieceNoCount(parent, BlockPos.ORIGIN.add(-4, 1, 10), ModRand.choice(treasure_rooms), rot);
        components.add(treasure);
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

        generateTreasureRoom(layer_b_r, pos, rot);
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
