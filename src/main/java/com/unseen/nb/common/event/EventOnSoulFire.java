package com.unseen.nb.common.event;


import com.unseen.nb.init.ModBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Random;

public class EventOnSoulFire {


    @SubscribeEvent
    public void onStandOnSoulFire(LivingEvent.LivingUpdateEvent event) {
        EntityLivingBase base = event.getEntityLiving();
        if(!base.world.isRemote) {
            if(base.world.getBlockState(base.getPosition()).getBlock() == ModBlocks.SOUL_FIRE) {
                if(!(base instanceof EntityWitherSkeleton) && !(base instanceof EntityBlaze) && !(base instanceof EntityGhast) &&
                !base.isImmuneToFire()) {
                    if(base instanceof EntityPlayer) {
                        if(!((EntityPlayer) base).isCreative() && !((EntityPlayer) base).isSpectator()) {
                            base.setFire(10);
                        }
                    }else {
                        base.setFire(10);
                    }
                }
            }
        }
    }


    @SubscribeEvent
    public static void onBonemealForNylium(BonemealEvent event) {
        if(event.getBlock() == Blocks.NETHERRACK.getDefaultState()) {
            BlockPos pos = event.getPos();
            World worldIn = event.getWorld();
            Random rand = worldIn.rand;

            if(!worldIn.getBlockState(pos.up()).isSideSolid(worldIn, pos, EnumFacing.UP)) {
                for(BlockPos blockPos : BlockPos.getAllInBoxMutable(pos.add(-1, -1, -1), pos.add(1, 1, 1))) {
                    if (worldIn.getBlockState(blockPos).getBlock() == ModBlocks.CRIMSON_GRASS || worldIn.getBlockState(blockPos).getBlock() == ModBlocks.WARPED_GRASS) {
                        boolean crimsonFlag = false;
                        boolean warpedFlag = false;

                        IBlockState blockState = worldIn.getBlockState(blockPos);
                        if(blockState.getBlock() == ModBlocks.CRIMSON_GRASS) {
                            crimsonFlag = true;
                        }

                        if(blockState.getBlock() == ModBlocks.WARPED_GRASS) {
                            warpedFlag = true;
                        }

                        if(crimsonFlag && warpedFlag) {
                            break;
                        }

                        if (crimsonFlag && warpedFlag) {
                            worldIn.setBlockState(pos, rand.nextBoolean() ? ModBlocks.CRIMSON_GRASS.getDefaultState() : ModBlocks.WARPED_GRASS.getDefaultState());
                        } else if (crimsonFlag) {
                            worldIn.setBlockState(pos, ModBlocks.CRIMSON_GRASS.getDefaultState());
                        } else if (warpedFlag) {
                            worldIn.setBlockState(pos, ModBlocks.WARPED_GRASS.getDefaultState());
                        }
                        event.setResult(Event.Result.ALLOW);
                    }
                }
            }
        }
    }

}
