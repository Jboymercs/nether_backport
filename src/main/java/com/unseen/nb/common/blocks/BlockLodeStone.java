package com.unseen.nb.common.blocks;

import com.unseen.nb.common.blocks.base.BlockBase;
import com.unseen.nb.common.capabilities.WorldDataLodestone;
import com.unseen.nb.init.ModItems;
import com.unseen.nb.init.ModSoundHandler;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class BlockLodeStone extends BlockBase
{
    public BlockLodeStone(String name, Material material) {
        super(name, material);
    }

    public BlockLodeStone(String name, Material material, float hardness, float resistance, SoundType soundType)
    { super(name, material, hardness, resistance, soundType); }

    @Override
    public void onBlockAdded(World world, BlockPos pos, IBlockState state)
    { if (world instanceof WorldServer) WorldDataLodestone.get((WorldServer) world).add(pos); }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state)
    { if (world instanceof WorldServer) WorldDataLodestone.get((WorldServer) world).remove(pos); }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack stack = playerIn.getHeldItem(hand);

        if(!playerIn.isSneaking() && (stack.getItem() == Items.COMPASS || stack.getItem() == ModItems.LODE_STONE_COMPASS))
        {
            /* This is to make sure the activated Lodestone is saved to the world file, as some might have been placed before the system was added! */
            if (worldIn instanceof WorldServer)
            { if (!WorldDataLodestone.get((WorldServer) worldIn).checkPosition(pos)) WorldDataLodestone.get((WorldServer) worldIn).add(pos); }

            ItemStack lodestoneCompass = new ItemStack(ModItems.LODE_STONE_COMPASS);
            lodestoneCompass.setTagCompound(this.setupLodestoneCompass(pos, worldIn));

            if(!playerIn.capabilities.isCreativeMode) stack.shrink(1);
            playerIn.addItemStackToInventory(lodestoneCompass);
            worldIn.playSound(playerIn, pos, ModSoundHandler.LODES_STONE_LOCK, SoundCategory.BLOCKS, 1.0F, 1.0F);
            return true;
        }

        return false;
    }

    private NBTTagCompound setupLodestoneCompass(BlockPos pos, World worldIn)
    {
        NBTTagCompound lodestoneNBT = new NBTTagCompound();
        lodestoneNBT.setIntArray("LodestonePos", new int[] {pos.getX(), pos.getY(), pos.getZ()});
        lodestoneNBT.setInteger("LodestoneDimension", worldIn.provider.getDimension());
        lodestoneNBT.setBoolean("DetectedLoadstone", true);
        return lodestoneNBT;
    }
}
