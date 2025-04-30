package com.unseen.nb.common.blocks;

import com.unseen.nb.Main;
import com.unseen.nb.common.blocks.tileentity.TilePiglinHead;
import com.unseen.nb.common.items.ItemModSkull;
import com.unseen.nb.init.ModBlocks;
import com.unseen.nb.init.ModItems;
import net.minecraft.block.BlockSkull;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockPiglinHead extends BlockSkull
{
	private static final AxisAlignedBB[] AABBS = {new AxisAlignedBB(0.1875D, 0.0D, 0.1875D, 0.8125D, 0.5D, 0.8125D),
			new AxisAlignedBB(0.1875D, 0.25D, 0.5D, 0.8125D, 0.75D, 1.0D),
			new AxisAlignedBB(0.1875D, 0.25D, 0.0D, 0.8125D, 0.75D, 0.5D),
			new AxisAlignedBB(0.5D, 0.25D, 0.1875D, 1.0D, 0.75D, 0.8125D),
			new AxisAlignedBB(0.0D, 0.25D, 0.1875D, 0.5D, 0.75D, 0.8125D)};

	public BlockPiglinHead(String name)
	{
		setHardness(1.0F);
		setSoundType(SoundType.STONE);
		this.setRegistryName(name);
		this.setTranslationKey(name);

		setDefaultState(blockState.getBaseState().withProperty(BlockSkull.NODROP, false).withProperty(BlockSkull.FACING, EnumFacing.UP));
		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new ItemModSkull(this).setRegistryName(this.getRegistryName()));
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
	{
		TileEntity tileentity = world.getTileEntity(pos);
		if (tileentity instanceof TilePiglinHead) return new ItemStack(ModBlocks.PIGLIN_HEAD, 1, ((TilePiglinHead)tileentity).getSkullType());

		return new ItemStack(ModBlocks.PIGLIN_HEAD);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	{ return AABBS[Math.max(0, state.getValue(FACING).getIndex() - 1)]; }

	@Override
	public TileEntity createNewTileEntity(World world, int meta)
	{ return new TilePiglinHead(); }

	@Override
	public boolean canDispenserPlace(World world, BlockPos pos, ItemStack stack)
	{ return false; }

	@Override
	public void getDrops(net.minecraft.util.NonNullList<ItemStack> drops, IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune)
	{
        if (!(Boolean) state.getValue(NODROP))
        {
            TileEntity tileentity = worldIn.getTileEntity(pos);

            if (tileentity instanceof TilePiglinHead)
            {
				TilePiglinHead tileentityskull = (TilePiglinHead)tileentity;
                ItemStack itemstack = new ItemStack(ModBlocks.PIGLIN_HEAD, 1, tileentityskull.getSkullType());
                drops.add(itemstack);
            }
        }
    }

	public void registerModels()
	{
		Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}
}