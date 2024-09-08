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

@SideOnly(Side.CLIENT)
public class ParticleObsidianTear extends ParticleBase
{
    /** How long the Obsidian Tear stays floating, before falling down */
    private int bobTimer;
    private static final ResourceLocation OOZING_TEXTURE = new ResourceLocation(ModReference.MOD_ID, "textures/particles/obsidian_tear.png");

    public ParticleObsidianTear(TextureManager textureManager, World world, double x, double y, double z, double movementX, double movementY, double movementZ)
    {
        super(textureManager, world, x, y, z, movementX, movementY, movementZ, OOZING_TEXTURE, 0);
        this.textureManager = textureManager;
        this.motionX = movementX;
        this.motionY = movementY;
        this.motionZ = movementZ;
        this.bobTimer = 40;
        this.particleMaxAge = (int)(64.0 / (Math.random() * 0.8 + 0.2));
        this.texSheetSeg = 2;
        this.renderYOffset = this.height / 2;
        this.canCollide = true;
        this.particleScale =  0.7F + (float) (Math.random() * 0.3F);
    }

    public void onUpdate()
    {
        super.onUpdate();

        if (this.bobTimer-- < 0)
        {
            if (!this.onGround)
            {
                this.texSpot = 0;
                this.motionY -= 0.01F;
            }
            else
            {
                this.texSpot = 1;
            }
        }
    }

    @Override
    public int getBrightnessForRender(float partialTicks)
    { return 15728880; }

    @SideOnly(Side.CLIENT)
    public static class Factory implements IParticleFactory
    {
        @Override
        public Particle createParticle(int particleId, World world, double posX, double posY, double posZ, double speedX, double speedY, double speedZ, int... parameters)
        { return new ParticleObsidianTear(Minecraft.getMinecraft().getTextureManager(), world, posX, posY, posZ, speedX, speedY, speedZ); }
    }
}