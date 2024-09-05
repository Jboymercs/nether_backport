package com.unseen.nb.common;


import com.unseen.nb.common.entity.entities.EntityFireProofItem;
import com.unseen.nb.util.ModReference;
import com.unseen.nb.util.ModUtils;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = ModReference.MOD_ID)
public class EntityEvents {

    @SubscribeEvent
    public static void turnItemsFireproof(EntityJoinWorldEvent event) {
        World worldIn = event.getWorld();

        if(event.getEntity().getClass() == EntityItem.class) {
            EntityItem entityItem = (EntityItem)event.getEntity();
            ItemStack stack = entityItem.getItem();

            if(ModUtils.isFireproof(stack.getItem())) {
                if(!stack.isEmpty()) {
                    EntityFireProofItem fireproofItem = new EntityFireProofItem(worldIn, entityItem, stack);
                    worldIn.spawnEntity(fireproofItem);
                    entityItem.setDead();
                }
            }
        }
    }
}
