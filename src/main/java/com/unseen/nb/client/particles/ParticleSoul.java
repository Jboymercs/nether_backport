package com.unseen.nb.client.particles;

import com.unseen.nb.util.ModReference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ParticleSoul extends Particle {
	private static final VertexFormat VERTEX_FORMAT = new VertexFormat().addElement(DefaultVertexFormats.POSITION_3F).addElement(DefaultVertexFormats.TEX_2F).addElement(DefaultVertexFormats.COLOR_4UB).addElement(DefaultVertexFormats.TEX_2S).addElement(DefaultVertexFormats.NORMAL_3B).addElement(DefaultVertexFormats.PADDING_1B);
	
	public ParticleSoul(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn) {
		super(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
		this.motionX = this.motionX * (double)0.01F + motionX;
		this.motionY = this.motionY * (double)0.01F + motionY;
		this.motionZ = this.motionZ * (double)0.01F + motionZ;
		this.posX += (this.rand.nextFloat() - this.rand.nextFloat()) * 0.05F;
		this.posY += (this.rand.nextFloat() - this.rand.nextFloat()) * 0.05F;
		this.posZ += (this.rand.nextFloat() - this.rand.nextFloat()) * 0.05F;
		
		this.setMaxAge((int)(8.0D / (Math.random() * 0.8D + 0.2D)) + 20);
		this.multipleParticleScaleBy(1.5F);
	}
	
	@Override
    public void onUpdate() {
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;
		if (this.particleAge++ >= this.particleMaxAge) {
			this.setExpired();
		} else {
			this.move(this.motionX, this.motionY, this.motionZ);
			this.motionX *= 0.96F;
			this.motionY *= 0.96F;
			this.motionZ *= 0.96F;
			if (this.onGround) {
				this.motionX *= 0.7F;
				this.motionZ *= 0.7F;
			}
		}
	}
    
	/*
	 * We have to manually add a new rendering method because using texture atlas sprites results in the particle texture being synced for all the particles, 
	 * meaning after the dissapearing animation you can just see a new soul spawn-mid air, creating a janky feel.
	 * Rendering code taken and tweaked from net.minecraft.client.particle.ParticleExplosionLarge
	 */
	@Override
    public void renderParticle(BufferBuilder buffer, Entity entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ) {
        int e = (int)(((float)this.particleAge + partialTicks) * 15.0F / (float)this.particleMaxAge);
        
        if (e <= 15) {
        	Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(ModReference.MOD_ID, "textures/particles/soul_death.png"));
            float f = (float)(e % 4) / 4.0F;
            float f1 = f + 0.24975F;
            float f2 = (float)(e / 4) / 4.0F;
            float f3 = f2 + 0.24975F;
            
            float f4 = 0.1F * this.particleScale;
            float f5 = (float)(this.prevPosX + (this.posX - this.prevPosX) * (double)partialTicks - interpPosX);
            float f6 = (float)(this.prevPosY + (this.posY - this.prevPosY) * (double)partialTicks - interpPosY);
            float f7 = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * (double)partialTicks - interpPosZ);
            
            int i = this.getBrightnessForRender(partialTicks);
            int j = i >> 16 & 65535;
            int k = i & 65535;
            
            buffer.begin(7, VERTEX_FORMAT);
            buffer.pos(f5 - rotationX * f4 - rotationXY * f4, f6 - rotationZ * f4, f7 - rotationYZ * f4 - rotationXZ * f4).tex(f1, f3).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(j, k).normal(0.0F, 1.0F, 0.0F).endVertex();
            buffer.pos(f5 - rotationX * f4 + rotationXY * f4, f6 + rotationZ * f4, f7 - rotationYZ * f4 + rotationXZ * f4).tex(f1, f2).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(j, k).normal(0.0F, 1.0F, 0.0F).endVertex();
            buffer.pos(f5 + rotationX * f4 + rotationXY * f4, f6 + rotationZ * f4, f7 + rotationYZ * f4 + rotationXZ * f4).tex(f, f2).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(j, k).normal(0.0F, 1.0F, 0.0F).endVertex();
            buffer.pos(f5 + rotationX * f4 - rotationXY * f4, f6 - rotationZ * f4, f7 + rotationYZ * f4 - rotationXZ * f4).tex(f, f3).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(j, k).normal(0.0F, 1.0F, 0.0F).endVertex();
            Tessellator.getInstance().draw();
        }
    }
    
    @Override
    public int getBrightnessForRender(float partialTick) {
        int i = super.getBrightnessForRender(partialTick);
        float f = (float)this.particleAge / (float)this.particleMaxAge;
        f = f * f;
        f = f * f;
        int j = i & 255;
        int k = i >> 16 & 255;
        k = k + (int)(f * 15.0F * 16.0F);

        if (k > 240) {
            k = 240;
        }

        return j | k << 16;
    }
    
	@Override
    public int getFXLayer() {
        return 3;
    }

	@SideOnly(Side.CLIENT)
	public static class Factory implements IParticleFactory {
		@Override
		public Particle createParticle(int particleID, World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, int... p_178902_15_) {
			return new ParticleSoul(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
		}
	}
}
