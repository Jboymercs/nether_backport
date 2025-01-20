package com.unseen.nb.common.blocks;

import com.unseen.nb.common.blocks.base.BlockBase;
import com.unseen.nb.init.ModBlocks;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockProperties;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockNetherGrass extends BlockBase implements IGrowable
{
    public BlockNetherGrass(String name, Material material) {
        super(name, material);
    }


    public BlockNetherGrass(String name, Material material, float hardness, float resistance, SoundType soundType) {
        super(name, material, hardness, resistance, soundType);
        this.setTickRandomly(true);
        this.fullBlock = true;
    }

    @Override
    public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, ItemStack stack)
    {
        player.addStat(StatList.getBlockStats(this));
        player.addExhaustion(0.005F);

        if (this.canSilkHarvest(worldIn, pos, state, player) && EnchantmentHelper.getEnchantmentLevel(Enchantments.SILK_TOUCH, stack) > 0)
        {
            java.util.List<ItemStack> items = new java.util.ArrayList<ItemStack>();
            ItemStack itemstack = new ItemStack(Item.getItemFromBlock(this));

            if (!itemstack.isEmpty())
            {
                items.add(itemstack);
            }

            net.minecraftforge.event.ForgeEventFactory.fireBlockHarvesting(items, worldIn, pos, state, 0, 1.0f, true, player);
            for (ItemStack item : items)
            {
                spawnAsEntity(worldIn, pos, item);
            }
        }
        else
        {
            harvesters.set(player);
            int i = EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE, stack);
            Blocks.NETHERRACK.dropBlockAsItem(worldIn, pos, state, i);
            harvesters.set(null);
        }
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getRenderLayer()
    {
        return BlockRenderLayer.CUTOUT_MIPPED;
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        if (!worldIn.isRemote)
        {
            if (!worldIn.isAreaLoaded(pos, 3)) {
                return; // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading
            }
                IBlockState stateAbove = worldIn.getBlockState(pos.up());

            /* Decay only needs to occur if directly above this is a full non-opaque block. */
            if(stateAbove.isFullCube())
            { worldIn.setBlockState(pos, Blocks.NETHERRACK.getDefaultState()); }
        }
    }

    @Override
    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient)
    { return worldIn.isAirBlock(pos.up()); }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state)
    { return worldIn.isAirBlock(pos.up()); }

    @Override
    public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state)
    {
        BlockPos blockpos = pos.up();
        boolean additionalTwistingVines = rand.nextInt(8) == 0;

        for (int i = 0; i < 64; ++i)
        {
            BlockPos blockpos1 = blockpos;
            int j = 0;

            /* Detects what plants to place based on the current position's below one. */
            boolean isWarped = worldIn.getBlockState(blockpos1.down()).getBlock() == ModBlocks.WARPED_GRASS;

            while (true)
            {
                if (j >= i / 16)
                {
                    if (worldIn.isAirBlock(blockpos1))
                    {
                        /* We use a random number between 1 and 100 when deciding what to place. */
                        int dice = rand.nextInt(101);

                        if (isWarped)
                        {
                            if (dice <= 20)
                            { worldIn.setBlockState(blockpos1, ModBlocks.WARPED_SPROUT.getDefaultState(), 3); }
                            else if (dice <= 85)
                            { worldIn.setBlockState(blockpos1, ModBlocks.WARPED_ROOTS.getDefaultState(), 3); }
                            else if (dice <= 98)
                            { worldIn.setBlockState(blockpos1, ModBlocks.WARPED_FUNGUS.getDefaultState(), 3); }
                            else if (dice <= 99)
                            { worldIn.setBlockState(blockpos1, ModBlocks.CRIMSON_ROOTS.getDefaultState(), 3); }
                            else
                            { worldIn.setBlockState(blockpos1, ModBlocks.CRIMSON_FUNGUS.getDefaultState(), 3); }

                            if (additionalTwistingVines)
                            {
                                worldIn.setBlockState(blockpos1, ModBlocks.WARPED_VINES.getDefaultState(), 3);
                                additionalTwistingVines = false;
                            }
                        }
                        else
                        {
                            if (dice <= 87)
                            { worldIn.setBlockState(blockpos1, ModBlocks.CRIMSON_ROOTS.getDefaultState(), 3); }
                            else if (dice <= 98)
                            { worldIn.setBlockState(blockpos1, ModBlocks.CRIMSON_FUNGUS.getDefaultState(), 3); }
                            else
                            { worldIn.setBlockState(blockpos1, ModBlocks.WARPED_FUNGUS.getDefaultState(), 3); }
                        }
                    }
                    break;
                }

                blockpos1 = blockpos1.add(rand.nextInt(3) - 1, (rand.nextInt(3) - 1) * rand.nextInt(3) / 2, rand.nextInt(3) - 1);

                if (worldIn.getBlockState(blockpos1.down()).getBlock() != ModBlocks.CRIMSON_GRASS && worldIn.getBlockState(blockpos1.down()).getBlock() != ModBlocks.WARPED_GRASS || worldIn.getBlockState(blockpos1).isNormalCube())
                { break; }

                ++j;
            }
        }
    }
}
