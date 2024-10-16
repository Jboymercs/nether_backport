package com.unseen.nb.common.blocks;

import com.unseen.nb.common.blocks.base.BlockBase;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockSoulLantern extends BlockBase {
    public static final PropertyBool IS_HANGING = PropertyBool.create("hanging");
    protected static final AxisAlignedBB CRYSTAL_AABB = new AxisAlignedBB(0.3D, 0.0D, 0.3D, 0.7D, 0.6D, 0.7D);
    public BlockSoulLantern(String name, Material material) {
        super(name, material);
    }

    public BlockSoulLantern(String name, Material material, float hardness, float resistance, SoundType soundType) {
        super(name, material, hardness, resistance, soundType);

    }

    @Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    { return isValidBlock(worldIn, pos, worldIn.getBlockState(pos.up()), true) || isValidBlock(worldIn, pos, worldIn.getBlockState(pos.down()), false); }

    @Deprecated
    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
    { return BlockFaceShape.UNDEFINED; }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return CRYSTAL_AABB;
    }


    @Deprecated
    public boolean isTopSolid(IBlockState state)
    {
        return false;
    }

    /**
     * If a Lantern can be placed against this block.
     *
     * `hanging` bool defines which faces to check when it comes to shape
     * */
    public boolean isValidBlock(World worldIn, BlockPos pos, IBlockState state, boolean hanging)
    {
        if (state.getBlock() instanceof BlockShulkerBox) return true;
        if (state.getBlock() instanceof BlockAnvil) return true;
        if (state.getBlock().canPlaceTorchOnTop(state, worldIn, pos)) return true;
        BlockFaceShape shape = state.getBlockFaceShape(worldIn, pos, hanging ? EnumFacing.DOWN : EnumFacing.UP);
        if (shape != BlockFaceShape.BOWL && shape != BlockFaceShape.UNDEFINED) return true;
        return false;
    }

    @Deprecated
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {   if(facing == EnumFacing.UP) {
        return getDefaultState().withProperty(IS_HANGING, false);
    }
        if(facing == EnumFacing.DOWN) {
            return getDefaultState().withProperty(IS_HANGING, true);
        }
        return this.getStateFromMeta(meta);
    }

    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
        super.neighborChanged(state, worldIn, pos, blockIn, fromPos);
        this.checkAndDropBlock(worldIn, pos, state);
    }

    protected void checkAndDropBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        if (!this.canBlockStay(worldIn, pos, state))
        {
            this.dropBlockAsItem(worldIn, pos, state, 0);
            worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
        }
    }

    public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state)
    { return state.getValue(IS_HANGING) ? isValidBlock(worldIn, pos, worldIn.getBlockState(pos.up()), true) : isValidBlock(worldIn, pos, worldIn.getBlockState(pos.down()), false); }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[]{IS_HANGING});
    }


    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getRenderLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(IS_HANGING) ? 1 : 0;
    }


    @Deprecated
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(IS_HANGING, meta == 1);
    }

}
