package com.unseen.nb.client.particles;

import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticlePortal;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * The Portal particle... but it goes up instead of down
 */
@SideOnly(Side.CLIENT)
public class ParticleInversePortal extends ParticlePortal
{
    protected ParticleInversePortal(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn)
    { super(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn); }

    public void onUpdate()
    {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        if (this.particleAge++ >= this.particleMaxAge)
        { this.setExpired(); }

        float ageFactor = (float)this.particleAge / (float)this.particleMaxAge;
        this.motionX *= (1.0 + ageFactor * 0.05);
        this.motionY *= (1.0 + ageFactor * 0.05);
        this.motionZ *= (1.0 + ageFactor * 0.05);
        this.move(this.motionX, this.motionY, this.motionZ);
    }

    @SideOnly(Side.CLIENT)
    public static class Factory implements IParticleFactory
    {
        public Particle createParticle(int particleID, World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, int... p_178902_15_)
        {
            return new ParticleInversePortal(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
        }
    }
}