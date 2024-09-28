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
 * A very basic 1x1 particle, recolored for use for Biome Ambience.
 */
@SideOnly(Side.CLIENT)
public class ParticlePixel extends ParticleBase
{
    private static final ResourceLocation PIXEL_TEXTURE = new ResourceLocation(ModReference.MOD_ID, "textures/particles/pixel.png");

    protected ParticlePixel(TextureManager textureManager, World worldIn, double x, double y, double z, double movementX, double movementY, double movementZ)
    { this(textureManager, worldIn, x, y, z, movementX, movementY, movementZ, 1F, 1F, 1F); }

    public ParticlePixel(TextureManager textureManager, World world, double x, double y, double z, double movementX, double movementY, double movementZ, float red, float green, float blue)
    {
        super(textureManager, world, x, y, z, movementX, movementY, movementZ, PIXEL_TEXTURE, 0);
        this.textureManager = textureManager;
        this.motionX = movementX;
        this.motionY = movementY;
        this.motionZ = movementZ;
        this.particleMaxAge = (int)(64.0 / (Math.random() * 0.8 + 0.2));
        setRBGColorF(red * 0.00392156862F, green * 0.00392156862F,blue * 0.00392156862F);
        this.texSheetSeg = 1;
        this.renderYOffset = this.height / 2;
        this.particleScale =  0.7F + (float) (Math.random() * 0.3F);
    }

    /** For Spores within the Crimson Forest. */
    @SideOnly(Side.CLIENT)
    public static class SporeCrimsonFactory implements IParticleFactory
    {
        @Override
        public Particle createParticle(int particleId, World world, double posX, double posY, double posZ, double speedX, double speedY, double speedZ, int... parameters)
        { return new ParticlePixel(Minecraft.getMinecraft().getTextureManager(), world, posX, posY, posZ, (world.rand.nextFloat() - world.rand.nextFloat())/3, world.rand.nextFloat()/2, (world.rand.nextFloat() - world.rand.nextFloat())/3, 209, 87, 92); }
    }

    /** For Spores within the Warped Forest. */
    @SideOnly(Side.CLIENT)
    public static class SporeWarpedFactory implements IParticleFactory
    {
        @Override
        public Particle createParticle(int particleId, World world, double posX, double posY, double posZ, double speedX, double speedY, double speedZ, int... parameters)
        { return new ParticlePixel(Minecraft.getMinecraft().getTextureManager(), world, posX, posY, posZ, (world.rand.nextFloat() - world.rand.nextFloat())/3, world.rand.nextFloat()/2, (world.rand.nextFloat() - world.rand.nextFloat())/3, 25, 25, 75); }
    }

    /** For Ash within the Soulsand Valley. */
    @SideOnly(Side.CLIENT)
    public static class AshSoulFactory implements IParticleFactory
    {
        @Override
        public Particle createParticle(int particleId, World world, double posX, double posY, double posZ, double speedX, double speedY, double speedZ, int... parameters)
        { return new ParticlePixel(Minecraft.getMinecraft().getTextureManager(), world, posX, posY, posZ, 0, -world.rand.nextFloat()/4, 0, 103, 92, 75); }
    }

    /** For Ash within the Basalt Delta. */
    @SideOnly(Side.CLIENT)
    public static class AshBasaltFactory implements IParticleFactory
    {
        @Override
        public Particle createParticle(int particleId, World world, double posX, double posY, double posZ, double speedX, double speedY, double speedZ, int... parameters)
        { return new ParticlePixel(Minecraft.getMinecraft().getTextureManager(), world, posX, posY, posZ, -world.rand.nextFloat()/3, -world.rand.nextFloat()/3, -world.rand.nextFloat()/3, 170, 151, 141); }
    }
}