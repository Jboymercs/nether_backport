package com.unseen.nb.common.blocks;

import com.unseen.nb.common.blocks.base.BlockBase;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockNetherOre extends BlockBase {

    public BlockNetherOre(String name, Material material, float hardness, float resistance, SoundType soundType)
    {
        super(name, material);
        this.setHardness(hardness);
        this.setResistance(resistance);
        this.setSoundType(soundType);
    }

    @Override
    @Nullable
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    { return new ItemStack(Items.GOLD_NUGGET).getItem(); }

    /** 2-6 Gold Nuggets are dropped */
    public int quantityDropped(Random random)
    { return 2 + random.nextInt(5); }

    /** Multiplies all the drops based on level of Fortune */
    public int quantityDroppedWithBonus(int fortune, Random random)
    {
        if (fortune > 0 && Item.getItemFromBlock(this) != this.getItemDropped((IBlockState)this.getBlockState().getValidStates().iterator().next(), random, fortune))
        {
            int i = random.nextInt(fortune + 2) - 1;

            return this.quantityDropped(random) * Math.max(i + 1, 1);
        }
        else
        { return this.quantityDropped(random); }
    }
}
