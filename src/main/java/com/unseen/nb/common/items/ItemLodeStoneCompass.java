package com.unseen.nb.common.items;

import com.unseen.nb.Main;
import com.unseen.nb.common.capabilities.WorldDataLodestone;
import com.unseen.nb.handler.IHasModel;
import com.unseen.nb.init.ModBlocks;
import com.unseen.nb.init.ModItems;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemCompass;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class ItemLodeStoneCompass extends ItemCompass implements IHasModel
{
    public ItemLodeStoneCompass(String name) {
        this.setTranslationKey(name);
        this.setRegistryName(name);
        ModItems.ITEMS.add(this);
        this.setMaxStackSize(1);

        this.addPropertyOverride(new ResourceLocation("angle"), new IItemPropertyGetter()
        {
            @SideOnly(Side.CLIENT)
            double rotation;
            @SideOnly(Side.CLIENT)
            double rota;
            @SideOnly(Side.CLIENT)
            long lastUpdateTick;

            @Override
            @SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
            {
                if (entityIn == null && !stack.isOnItemFrame())
                { return 0.0F; }
                else
                {
                    boolean isEntityHere = entityIn != null;
                    Entity entity = isEntityHere ? entityIn : stack.getItemFrame();
                    if (worldIn == null) worldIn = entity.world;
                    double d0 = Math.random();

                    if(hasLodestone(stack) && getLodestoneDimension(stack) == worldIn.provider.getDimensionType().getId())
                    {
                        if (getLoadstoneDetection(stack))
                        {
                            double d1 = isEntityHere ? (double)entity.rotationYaw : this.getFrameRotation((EntityItemFrame)entity);
                            d1 = MathHelper.positiveModulo(d1 / 360.0D, 1.0D);
                            double d2 = this.getCompassAngle(worldIn, entity, stack) / (Math.PI * 2D);
                            d0 = 0.5D - (d1 - 0.25D - d2);
                        }
                    }
                    return (float) MathHelper.positiveModulo(isEntityHere ? this.wobble(worldIn, d0) : d0, 1.0F);
                }
            }

            @SideOnly(Side.CLIENT)
            private double wobble(World worldIn, double d0)
            {
                if (worldIn.getTotalWorldTime() != this.lastUpdateTick)
                {
                    this.lastUpdateTick = worldIn.getTotalWorldTime();
                    double d1 = d0 - this.rotation;
                    d1 = MathHelper.positiveModulo(d1 + 0.5D, 1.0D) - 0.5D;
                    this.rota += d1 * 0.1D;
                    this.rota *= 0.8D;
                    this.rotation = MathHelper.positiveModulo(this.rotation + this.rota, 1.0D);
                }
                return this.rotation;
            }

            @SideOnly(Side.CLIENT)
            private double getFrameRotation(EntityItemFrame entity)
            { return (double)MathHelper.wrapDegrees(180 + entity.facingDirection.getHorizontalIndex() * 90); }

            @SideOnly(Side.CLIENT)
            private double getCompassAngle(World worldIn, Entity entity, ItemStack stack)
            {
                BlockPos blockPos = null;

                if(hasLodestone(stack) && getLodestoneDimension(stack) == worldIn.provider.getDimensionType().getId())
                { blockPos = getPos(stack); }

                /* +0.5 to the X and Z when rendering the compass angle, so the compass points to the center of the Lodestone Block */
                return Math.atan2(((double)blockPos.getZ() + 0.5) - entity.posZ, ((double)blockPos.getX() + 0.5) - entity.posX);
            }
        });
    }

    /** Preforms detection logic for the Lodestone. */
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
    {
        if (!worldIn.isRemote && hasLodestone(stack))
        {
            boolean flag = WorldDataLodestone.get((WorldServer) worldIn).checkPosition(getPos(stack));

            if (flag != getLoadstoneDetection(stack))
            { setLoadstoneDetection(stack, flag); }
        }
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if(worldIn.getBlockState(pos).getBlock() == ModBlocks.LODE_STONE && playerIn.isSneaking()) {
            ItemStack stack = playerIn.getHeldItem(hand);

            ItemStack compass = new ItemStack(Items.COMPASS);
            stack.shrink(1);
            playerIn.addItemStackToInventory(compass);
            worldIn.playSound(playerIn, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.NEUTRAL, 1.0F, 1.0F);
            return EnumActionResult.SUCCESS;
        }

        return super.onItemUse(playerIn, worldIn, pos, hand, facing, hitX, hitY, hitZ);
    }

    /** Sets the NBT if the compass has detected the Lodestone properly. Uses NBT so the detection is synced between the Client and Server. */
    public static void setLoadstoneDetection(ItemStack stack, boolean detectionIn)
    {
        NBTTagCompound nbt;
        if (stack.hasTagCompound())
        { nbt = stack.getTagCompound(); }
        else
        { nbt = new NBTTagCompound(); }

        nbt.setBoolean("DetectedLoadstone", detectionIn);
        stack.setTagCompound(nbt);
    }

    /** If the compass has successfully detected a Lodestone. */
    private boolean getLoadstoneDetection(ItemStack stack)
    { return stack.hasTagCompound() && stack.getTagCompound().getBoolean("DetectedLoadstone"); }

    private static boolean hasLodestone(ItemStack stack)
    { return stack.hasTagCompound() && stack.getTagCompound().hasKey("LodestonePos") && stack.getTagCompound().hasKey("LodestoneDimension"); }

    private BlockPos getPos(ItemStack stack)
    {
        int[] tagArray = stack.getTagCompound().getIntArray("LodestonePos");
        return new BlockPos(tagArray[0], tagArray[1], tagArray[2]);
    }

    private int getLodestoneDimension(ItemStack stack)
    { return stack.getTagCompound().getInteger("LodestoneDimension"); }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack)
    { return hasLodestone(stack); }

    @Override
    public void registerModels()
    { Main.proxy.registerItemRenderer(this, 0, "inventory"); }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        NBTTagCompound NBT = stack.getTagCompound();

        /* TODO: Debug info, add a config setting for it? */
        if (NBT != null && false)
        {
            if (NBT.hasKey("LodestonePos"))
            {
                BlockPos pos = getPos(stack);
                tooltip.add(TextFormatting.GRAY + net.minecraft.util.text.translation.I18n.translateToLocalFormatted("description.nb.lodestone.position.name", pos.getX(), pos.getY(), pos.getZ()));
            }

            if (NBT.hasKey("LodestoneDimension"))
            { tooltip.add(TextFormatting.GRAY + net.minecraft.util.text.translation.I18n.translateToLocalFormatted("description.nb.lodestone.dimension.name", getLodestoneDimension(stack))); }
        }
    }
}
