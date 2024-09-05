package com.unseen.nb.common.blocks;

import com.unseen.nb.common.blocks.base.BlockBase;
import com.unseen.nb.init.ModBlocks;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockNetherGrass extends BlockBase {

    public BlockNetherGrass(String name, Material material) {
        super(name, material);
    }

    public BlockNetherGrass(String name, Material material, float hardness, float resistance, SoundType soundType) {
        super(name, material, hardness, resistance, soundType);
        this.setTickRandomly(true);
    }

    @Override
    @Nullable
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return this.getItemDropped(state, rand, fortune);
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        if (!worldIn.isRemote)
        {
            if (!worldIn.isAreaLoaded(pos, 3)) {
                return; // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading
            }
                IBlockState state1 = worldIn.getBlockState(pos.up());
            if(state1 == Blocks.AIR.getDefaultState() && !state1.isFullBlock() || state1 instanceof BlockBush || state1 == ModBlocks.CRIMSON_GRASS.getDefaultState() || state1 == ModBlocks.CRIMSON_ROOTS.getDefaultState()
            || state1 == ModBlocks.CRIMSON_FUNGUS.getDefaultState() || state1 == ModBlocks.WARPED_FUNGUS.getDefaultState() || state1 == ModBlocks.WARPED_GRASS.getDefaultState() || state1 == ModBlocks.WARPED_ROOTS.getDefaultState() ||
            state1 == ModBlocks.WARPED_VINES.getDefaultState() || state1 == ModBlocks.WARPED_SPROUT.getDefaultState() || state1 == ModBlocks.CRIMSON_VINES.getDefaultState()) {
                return; //If the above block is not air
            } else {
                worldIn.setBlockState(pos, Blocks.NETHERRACK.getDefaultState());
            }


        }
    }
}
