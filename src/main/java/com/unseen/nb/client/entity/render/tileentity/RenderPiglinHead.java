package com.unseen.nb.client.entity.render.tileentity;

import com.unseen.nb.client.entity.model.tileentity.ModelPiglinHead;
import com.unseen.nb.common.blocks.tileentity.TilePiglinHead;
import com.unseen.nb.util.ModReference;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySkullRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderPiglinHead extends TileEntitySkullRenderer
{
    public static final RenderPiglinHead INSTANCE = new RenderPiglinHead();
    private ModelPiglinHead model = new ModelPiglinHead();
    public static final ResourceLocation PIGLIN_TEXTURE = new ResourceLocation(ModReference.MOD_ID + ":textures/entity/piglin.png");
    public static final ResourceLocation PIGLIN_BRUTE_TEXTURE = new ResourceLocation(ModReference.MOD_ID + ":textures/entity/piglin_brute.png");
    public static final ResourceLocation PIGLIN_ZOMBIE_TEXTURE = new ResourceLocation(ModReference.MOD_ID + ":textures/entity/piglin_zombie.png");

    @Override
    public void render(TileEntitySkull te, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
    {
        if (!(te instanceof TilePiglinHead)) return;

        EnumFacing enumfacing = EnumFacing.byIndex(te.getBlockMetadata() & 7);
        float animation = te.getAnimationProgress(partialTicks);

        GlStateManager.pushMatrix();
        this.renderPiglinHead((float)x, (float)y, (float)z, enumfacing, (float)(te.getSkullRotation() * 360) / 16.0F, te.getSkullType(), destroyStage, animation);
        GlStateManager.popMatrix();
    }

    public void renderPiglinHead(float x, float y, float z, EnumFacing facing, float rotationIn, int skullType, int destroyStage, float animateTicks)
    {
        ModelPiglinHead modelbase = this.model;

        if(destroyStage >= 0)
        {
            this.bindTexture(DESTROY_STAGES[destroyStage]);
            GlStateManager.matrixMode(5890);
            GlStateManager.pushMatrix();
            GlStateManager.scale(4.0F, 2.0F, 1.0F);
            GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
            GlStateManager.matrixMode(5888);
        }
        else
        {
            switch (skullType)
            {
                case 0:
                default:
                    this.bindTexture(PIGLIN_TEXTURE);
                    break;
                case 1:
                    this.bindTexture(PIGLIN_BRUTE_TEXTURE);
                    break;
                case 2:
                    this.bindTexture(PIGLIN_ZOMBIE_TEXTURE);
                    break;
            }
        }

        GlStateManager.pushMatrix();
        GlStateManager.disableCull();

        if (facing == EnumFacing.UP)
        {
            GlStateManager.translate(x + 0.5F, y, z + 0.5F);
        }
        else
        {
            switch (facing)
            {
                case NORTH:
                    GlStateManager.translate(x + 0.5F, y + 0.25F, z + 0.75F);
                    break;
                case SOUTH:
                    GlStateManager.translate(x + 0.5F, y + 0.25F, z + 0.25F);
                    rotationIn = 180.0F;
                    break;
                case WEST:
                    GlStateManager.translate(x + 0.75F, y + 0.25F, z + 0.5F);
                    rotationIn = 270.0F;
                    break;
                case EAST:
                default:
                    GlStateManager.translate(x + 0.25F, y + 0.25F, z + 0.5F);
                    rotationIn = 90.0F;
            }
        }

        GlStateManager.enableRescaleNormal();
        GlStateManager.scale(-1.0F, -1.0F, 1.0F);
        GlStateManager.enableAlpha();

        modelbase.render((Entity)null, animateTicks, 0.0F, 0.0F, rotationIn, 0.0F, 0.0625F);

        GlStateManager.popMatrix();

        if (destroyStage >= 0)
        {
            GlStateManager.matrixMode(5890);
            GlStateManager.popMatrix();
            GlStateManager.matrixMode(5888);
        }
    }
}