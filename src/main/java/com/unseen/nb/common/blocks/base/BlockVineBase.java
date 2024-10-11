package com.unseen.nb.common.blocks.base;

import com.unseen.nb.Main;
import com.unseen.nb.handler.IHasModel;
import com.unseen.nb.handler.RegistryHandler;
import com.unseen.nb.init.ModBlocks;
import com.unseen.nb.init.ModItems;
import com.unseen.nb.util.mapper.AdvancedStateMap;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockVineBase extends BlockBush implements IHasModel, RegistryHandler.IStateMappedBlock, IPlantable {
    protected static final AxisAlignedBB CRYSTAL_AABB = new AxisAlignedBB(0.1D, 0.0D, 0.1D, 0.9D, 1.0D, 0.9D);

    public static final PropertyBool IS_TOP = PropertyBool.create("is_top");
    public static final PropertyBool IS_BOTTOM = PropertyBool.create("is_bottom");

    public static final PropertyBool CAN_GROW = PropertyBool.create("can_grow");


    public BlockVineBase(String name, Material materialIn, SoundType soundType) {
        super(materialIn);
        setTranslationKey(name);
        setRegistryName(name);
        setSoundType(soundType);
        this.setTickRandomly(true);
        // Add both an item as a block and the block itself
        ModBlocks.BLOCKS.add(this);
        ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
        this.setDefaultState(this.blockState.getBaseState().withProperty(IS_TOP, true).withProperty(IS_BOTTOM, false).withProperty(CAN_GROW, true));
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[]{IS_TOP, IS_BOTTOM, CAN_GROW});
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        boolean isTop = worldIn.getBlockState(pos.up()).getBlock() != this;
        boolean isBottom = worldIn.getBlockState(pos.down()).getBlock() != this;
        return state.withProperty(IS_TOP, isTop).withProperty(IS_BOTTOM, isBottom);
    }

    @Override
    protected void checkAndDropBlock(World worldIn, BlockPos pos, IBlockState state) {
        if (!this.canBlockStay(worldIn, pos, state)) {
            this.dropBlockAsItem(worldIn, pos, state, 0);
            worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
        }
    }

    @Override
    @Nullable
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return null;
    }

    protected boolean isValidBlock(World world, BlockPos pos, IBlockState blockState) {
        return blockState.isSideSolid(world, pos, EnumFacing.DOWN) || blockState.getBlock() == this || blockState.getBlock() == ModBlocks.CRIMSON_WART || blockState.getBlock() == ModBlocks.WARPED_WART ||
                blockState.getBlock() == Blocks.NETHERRACK;
    }


    @Override
    public boolean canPlaceBlockAt(World world, BlockPos pos) {
        return isValidBlock(world, pos.up(), world.getBlockState(pos.up())) && canBlockStay(world, pos, world.getBlockState(pos));
    }

    @Override
    public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state) {
        return isValidBlock(worldIn, pos.up(), worldIn.getBlockState(pos.up()));
    }

    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
        if(!isValidBlock(worldIn, pos.up(), worldIn.getBlockState(pos.up()))) {
            worldIn.setBlockToAir(pos);
        }
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if(rand.nextInt(16) == 0 && worldIn.isAirBlock(pos.down()) && this.canGrowAt(worldIn, pos, state)) {
            worldIn.setBlockState(pos.down(), this.getDefaultState());
        }
    }

    protected boolean canGrowAt(World world, BlockPos pos, IBlockState state) {
        return state.getValue(CAN_GROW);
    }

    @Override
    protected boolean canSustainBush(IBlockState state) {
        return state.getBlock() == ModBlocks.WARPED_WART || state.getBlock() == ModBlocks.CRIMSON_WART || blockState.getBlock() == Blocks.NETHERRACK;
    }

    // TODO: Test in Multiplayer/over a Server!
    @Override
    public void onEntityCollision(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
    {
        if (entityIn instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) entityIn;

            if (Minecraft.getMinecraft().gameSettings.keyBindJump.isKeyDown())
            {
                player.motionY = 0.2D;
                player.fallDistance = 0.0F;
            }
        }
    }

    @Override public boolean isLadder(IBlockState state, IBlockAccess world, BlockPos pos, EntityLivingBase entity) { return true; }


    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {

        return CRYSTAL_AABB;
    }

    @SideOnly(Side.CLIENT)
    public Block.EnumOffsetType getOffsetType() {
        return Block.EnumOffsetType.NONE;
    }

    @Override
    public void registerModels() {
        Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(CAN_GROW) ? 1 : 0;
    }

    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
        return this.getDefaultState().withProperty(CAN_GROW, true);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(CAN_GROW, meta == 1);
    }

    @Override
    public void setStateMapper(AdvancedStateMap.Builder builder) {
        builder.ignore(CAN_GROW);
    }
}
