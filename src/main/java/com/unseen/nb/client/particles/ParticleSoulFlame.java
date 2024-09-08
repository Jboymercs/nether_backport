package com.unseen.nb.client.particles;

import com.unseen.nb.util.ModReference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * A bare-bones copy of ParticleFlame, stripped down to only what is needed.
 * */
@SideOnly(Side.CLIENT)
public class ParticleSoulFlame extends ParticleBase
{
    private static final ResourceLocation FLAME_SOUL_TEXTURE = new ResourceLocation(ModReference.MOD_ID, "textures/particles/flame_soul.png");
    private final float flameScale;

    protected ParticleSoulFlame(TextureManager textureManager, World worldIn, double x, double y, double z, double movementX, double movementY, double movementZ)
    {
        super(textureManager, worldIn, x, y, z, movementX, movementY, movementZ, FLAME_SOUL_TEXTURE, 0);
        this.motionX = this.motionX * 0.009999999776482582D + movementX;
        this.motionY = this.motionY * 0.009999999776482582D + movementY;
        this.motionZ = this.motionZ * 0.009999999776482582D + movementZ;
        this.posX += (this.rand.nextFloat() - this.rand.nextFloat()) * 0.05F;
        this.posY += (this.rand.nextFloat() - this.rand.nextFloat()) * 0.05F;
        this.posZ += (this.rand.nextFloat() - this.rand.nextFloat()) * 0.05F;
        this.flameScale = 1.5F;

        this.particleMaxAge = (int)(8.0D / (Math.random() * 0.8D + 0.2D)) + 4;
    }

    public void onUpdate()
    {
        super.onUpdate();
        float f = ((float)this.particleAge ) / (float)this.particleMaxAge;
        this.particleScale = this.flameScale * (1.0F - f * f * 0.5F);
    }

    @Override
    public int getBrightnessForRender(float partialTicks)
    { return brightnessIncreaseToFull(partialTicks); }

    @SideOnly(Side.CLIENT)
    public static class Factory implements IParticleFactory
    {
        @Override
        public Particle createParticle(int particleId, World world, double posX, double posY, double posZ, double speedX, double speedY, double speedZ, int... parameters)
        { return new ParticleSoulFlame(Minecraft.getMinecraft().getTextureManager(), world, posX, posY, posZ, speedX, speedY, speedZ); }
    }
}