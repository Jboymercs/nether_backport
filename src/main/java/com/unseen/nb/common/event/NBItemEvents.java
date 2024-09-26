package com.unseen.nb.common.event;

import com.unseen.nb.util.ModReference;
import com.unseen.nb.util.ModUtils;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


@Mod.EventBusSubscriber(modid = ModReference.MOD_ID)
public class NBItemEvents {

    @SubscribeEvent
    public static void addHoeHarvesting(PlayerEvent.BreakSpeed event) {
        EntityPlayer playerIn = event.getEntityPlayer();
        ItemStack stack = playerIn.getHeldItem(EnumHand.MAIN_HAND);

        if(stack.getItem() instanceof ItemHoe) {
            IBlockState state = event.getState();
            if(ModUtils.isHoeWhitelisted(state.getBlock())) {
                ItemHoe hoe = (ItemHoe)playerIn.getHeldItem(EnumHand.MAIN_HAND).getItem();
                Item.ToolMaterial material = Item.ToolMaterial.valueOf(hoe.getMaterialName());
                event.setNewSpeed(material.getEfficiency());
            }
        }
    }
}
