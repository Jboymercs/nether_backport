package com.unseen.nb.common.blocks;

import com.unseen.nb.common.blocks.base.BlockBase;
import com.unseen.nb.util.ModRand;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockGildedBlackstone extends BlockBase {

    public BlockGildedBlackstone(String name, Material material, float hardness, float resistance, SoundType soundType) {
        super(name, material);
        this.setHardness(hardness);
        this.setResistance(resistance);
        this.setSoundType(soundType);
    }



    @Override
    @Nullable
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return new ItemStack(Items.GOLD_NUGGET).getItem();
    }

    @Override
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
        Random rand = world instanceof World ? ((World)world).rand : RANDOM;

        int count = quantityDropped(state, fortune, rand);
        for (int i = 0; i < count; i++) {
            Item item = this.getItemDropped(state, rand, fortune);
            if (rand.nextInt(10) == 0) {
                if (item != Items.AIR) {
                    drops.add(new ItemStack(item, ModRand.range(2, 6), this.damageDropped(state)));
                }
            } else {
                drops.add(new ItemStack(this, 1, this.damageDropped(state)));
            }
        }
    }
}
