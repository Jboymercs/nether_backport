package com.unseen.nb.common.event;

import com.unseen.nb.init.ModBlocks;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

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
}
