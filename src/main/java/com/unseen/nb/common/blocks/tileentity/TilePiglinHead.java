package com.unseen.nb.common.blocks.tileentity;

import net.minecraft.tileentity.TileEntitySkull;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TilePiglinHead extends TileEntitySkull
{
    private int earsAnimatedTicks;
    private boolean earsAnimated;

    public void update()
    {
        if (this.world.isBlockPowered(this.pos))
        {
            this.earsAnimated = true;
            ++this.earsAnimatedTicks;
        }
        else
        { this.earsAnimated = false; }
    }

    @SideOnly(Side.CLIENT)
    public float getAnimationProgress(float p_184295_1_)
    { return this.earsAnimated ? (float)this.earsAnimatedTicks + p_184295_1_ : (float)this.earsAnimatedTicks; }
}