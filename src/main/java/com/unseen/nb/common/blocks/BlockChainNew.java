package com.unseen.nb.common.blocks;


import com.unseen.nb.common.blocks.base.BlockPillarBase;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockChainNew extends BlockPillarBase {
    protected static final AxisAlignedBB CHAIN_AABB_Y = new AxisAlignedBB(0.6D, 0.0D, 0.6D, 0.4D, 1.0D, 0.4D);
    protected static final AxisAlignedBB CHAIN_AABB_X = new AxisAlignedBB(0.0D, 0.4D, 0.6D, 1.0D, 0.6D, 0.4D);
    protected static final AxisAlignedBB CHAIN_AABB_Z = new AxisAlignedBB(0.6D, 0.4D, 0.0D, 0.4D, 0.6D, 1.0D);

    public BlockChainNew(String name, Material materialIn, float hardness, float resistance, SoundType soundType) {
        super(name, materialIn, hardness, resistance, soundType);
    }


    @Override
    public boolean isTopSolid(IBlockState state) {
        return false;
    }
    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        switch (state.getValue(AXIS)) {
            case X:
                return CHAIN_AABB_X;
            case Y:
                return CHAIN_AABB_Y;
            case Z:
                return CHAIN_AABB_Z;
            default:
                return CHAIN_AABB_Y;
        }
    }



    @Override
    public IBlockState getStateFromMeta(int meta) {
        IBlockState state = this.getDefaultState();
        switch (meta) {
            case 0:
                state = state.withProperty(AXIS, EnumFacing.Axis.Y);
                break;
            case 1:
                state = state.withProperty(AXIS, EnumFacing.Axis.X);
                break;
            case 2:
                state = state.withProperty(AXIS, EnumFacing.Axis.Z);
                break;
            default:
                state = state.withProperty(AXIS, EnumFacing.Axis.Y);
        }
        return state;
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        switch (state.getValue(AXIS)) {
            case X:
                return 1;
            case Y:
                return 0;
            case Z:
                return 2;
            default:
                return 3;
        }
    }
    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    /** Same shape as a fence if it is oriented straight up */
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
    { return state.getValue(AXIS) == EnumFacing.Axis.Y ? BlockFaceShape.MIDDLE_POLE : BlockFaceShape.UNDEFINED; }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getRenderLayer()
    {
        return BlockRenderLayer.TRANSLUCENT;
    }

    @Override
    public boolean doesSideBlockRendering(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face) {
        return false;
    }
    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }
}
