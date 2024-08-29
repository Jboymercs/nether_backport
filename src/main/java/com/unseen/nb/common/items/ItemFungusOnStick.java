package com.unseen.nb.common.items;

import com.unseen.nb.common.entity.entities.EntityStrider;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemFungusOnStick extends ItemBase{

    public ItemFungusOnStick(String name, CreativeTabs tab) {
        super(name, tab);
        this.setMaxStackSize(1);
        this.setMaxDamage(100);
    }

    @SideOnly(Side.CLIENT)
    public boolean isFull3D() {
        return true;
    }

    @SideOnly(Side.CLIENT)
    public boolean shouldRotateAroundWhenRendering() {
        return true;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);

        if (worldIn.isRemote) {
            return new ActionResult<ItemStack>(EnumActionResult.PASS, itemstack);
        } else {
            if (playerIn.isRiding() && playerIn.getRidingEntity() instanceof EntityStrider) {
                EntityStrider strider = (EntityStrider)playerIn.getRidingEntity();

                if (itemstack.getMaxDamage() - itemstack.getMetadata() >= 1 && strider.boost()) {
                    itemstack.damageItem(1, playerIn);

                    if (itemstack.isEmpty()) {
                        ItemStack itemstack1 = new ItemStack(Items.FISHING_ROD);
                        itemstack1.setTagCompound(itemstack.getTagCompound());
                        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack1);
                    }
                    return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
                }
            }

            playerIn.addStat(StatList.getObjectUseStats(this));
            return new ActionResult<ItemStack>(EnumActionResult.PASS, itemstack);
        }
    }
}
