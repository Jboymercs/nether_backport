package com.unseen.nb.common.blocks;

import com.unseen.nb.common.blocks.base.BlockBase;
import com.unseen.nb.init.ModBlocks;
<<<<<<< Updated upstream
import net.minecraft.block.BlockBush;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
=======
import com.unseen.nb.util.ModUtils;
import com.unseen.nb.util.states.EnumNBForestTypes;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
>>>>>>> Stashed changes
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class BlockNetherGrass extends BlockBase implements IGrowable {

    private final EnumNBForestTypes forestType;

    public BlockNetherGrass(String name, Material material, EnumNBForestTypes forestType) {
        super(name, material);
        this.forestType = forestType;
    }

    public BlockNetherGrass(String name, Material material, float hardness, float resistance, SoundType soundType, EnumNBForestTypes forestType) {
        super(name, material, hardness, resistance, soundType);
        this.setTickRandomly(true);
        this.forestType = forestType;
    }

    @Override
<<<<<<< Updated upstream
=======
    @Nullable
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return this.getItemDropped(state, rand, fortune);
    }

    @Override
    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
        return true;
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        return true;
    }

    @Override
    public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        ModUtils.growNetherVegetation(worldIn, rand, pos.up(), this.forestType);
    }

    @Override
>>>>>>> Stashed changes
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
            } else if(worldIn.rand.nextInt(16) == 0){
                worldIn.setBlockState(pos, Blocks.NETHERRACK.getDefaultState());
            }


        }
    }
}
