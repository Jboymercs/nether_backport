package com.unseen.nb.common.blocks.base;

import com.unseen.nb.Main;
import com.unseen.nb.common.items.ItemNetherDoor;
import com.unseen.nb.handler.IHasModel;
import com.unseen.nb.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;
/**
 * Credit goes to SmileyCorps from DeeperDepths
 */
public class BlockNetherDoor extends BlockDoor implements IHasModel, IBlockProperties {

    private final ItemNetherDoor item;

    public BlockNetherDoor(String name, float hardness, float resistance, CreativeTabs tab, SoundType soundType) {
        super(Material.WOOD);
        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(tab);
        setSoundType(soundType);
        setHardness(hardness);
        setResistance(resistance);
        useNeighborBrightness = true;

        // Add both an item as a block and the block itself
        ModBlocks.BLOCKS.add(this);
        item = new ItemNetherDoor(this);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return state.getValue(HALF) == BlockDoor.EnumDoorHalf.UPPER ? Items.AIR : this.getItem();
    }



    public Item getItem() {
        return item;
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        BlockPos blockpos = state.getValue(HALF) == BlockDoor.EnumDoorHalf.LOWER ? pos : pos.down();
        IBlockState iblockstate = pos.equals(blockpos) ? state : world.getBlockState(blockpos);
        if (iblockstate.getBlock() != this) return false;
        state = iblockstate.cycleProperty(OPEN);
        world.setBlockState(blockpos, state, 10);
        world.markBlockRangeForRenderUpdate(blockpos, pos);
        world.playSound(null, pos.getX() + 0.5f, pos.getY() + 0.5f, pos.getZ() + 0.5f,
                state.getValue(OPEN) ? SoundEvents.BLOCK_WOODEN_DOOR_OPEN : SoundEvents.BLOCK_WOODEN_DOOR_CLOSE,
                SoundCategory.BLOCKS, 1, 1);
        return true;
    }

    @Override
    public void toggleDoor(World world, BlockPos pos, boolean open) {
        IBlockState state = world.getBlockState(pos);
        if (state.getBlock() != this) return;
        BlockPos blockpos = state.getValue(HALF) == BlockDoor.EnumDoorHalf.LOWER ? pos : pos.down();
        IBlockState other = pos == blockpos ? state : world.getBlockState(blockpos);
        if (other.getBlock() != this || other.getValue(OPEN) == open) return;
        world.setBlockState(blockpos, other.withProperty(OPEN, open), 10);
        world.markBlockRangeForRenderUpdate(blockpos, pos);
        world.playSound(null, pos.getX() + 0.5f, pos.getY() + 0.5f, pos.getZ() + 0.5f,
                state.getValue(OPEN) ? SoundEvents.BLOCK_WOODEN_DOOR_OPEN : SoundEvents.BLOCK_WOODEN_DOOR_CLOSE,
                SoundCategory.BLOCKS, 1, 1);
    }

    @Override
    public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos fromPos) {
        if (state.getValue(HALF) == BlockDoor.EnumDoorHalf.UPPER) {
            BlockPos blockpos = pos.down();
            IBlockState iblockstate = world.getBlockState(blockpos);
            if (!(iblockstate.getBlock() instanceof BlockNetherDoor)) world.setBlockToAir(pos);
            else if (!(block instanceof BlockNetherDoor)) iblockstate.neighborChanged(world, blockpos, block, fromPos);
            return;
        }
        BlockPos otherpos = pos.up();
        IBlockState other = world.getBlockState(otherpos);
        if (!(other.getBlock() instanceof BlockNetherDoor)) {
            world.setBlockToAir(pos);
            if (!world.isRemote) dropBlockAsItem(world, pos, state, 0);
            return;
        }
        if (!world.getBlockState(pos.down()).isSideSolid(world, pos.down(), EnumFacing.UP)) {
            world.setBlockToAir(pos);
            if (!(other.getBlock() instanceof BlockNetherDoor)) world.setBlockToAir(otherpos);
            if (!world.isRemote) dropBlockAsItem(world, pos, state, 0);
            return;
        }
        boolean power = world.isBlockPowered(pos) || world.isBlockPowered(otherpos);
        if (block instanceof BlockNetherDoor |! (power && block.getDefaultState().canProvidePower())
                || power == other.getValue(POWERED)) return;
        world.setBlockState(otherpos, other.withProperty(POWERED, power), 2);
        if (power == state.getValue(OPEN)) return;
        world.setBlockState(pos, state.withProperty(OPEN, power), 2);
        world.markBlockRangeForRenderUpdate(pos, pos);
        world.playSound(null, pos.getX() + 0.5f, pos.getY() + 0.5f, pos.getZ() + 0.5f,
                state.getValue(OPEN) ? SoundEvents.BLOCK_WOODEN_DOOR_OPEN : SoundEvents.BLOCK_WOODEN_DOOR_CLOSE,
                SoundCategory.BLOCKS, 1, 1);
    }


    private IBlockState copyProperties(IBlockState oldState, IBlockState newState) {
        return newState.withProperty(FACING, oldState.getValue(FACING)).withProperty(HALF, oldState.getValue(HALF))
                .withProperty(OPEN, oldState.getValue(OPEN)).withProperty(HINGE, oldState.getValue(HINGE));
    }



    @Override
    public void registerModels() {
        Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }
}
