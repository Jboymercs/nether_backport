package com.unseen.nb.proxy;

import com.unseen.nb.client.animation.IAnimatedEntity;
import com.unseen.nb.client.particles.ParticleObsidianTear;
import com.unseen.nb.client.particles.ParticleSoul;
import com.unseen.nb.client.particles.ParticleSoulFlame;
import com.unseen.nb.handler.RenderHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy{

    public void init() {
        //Render Handler for Entities
        RenderHandler.registerEntityRenderers();
    }

    @Override
    public void registerItemRenderer(Item item, int meta, String id) {

        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), id));

    }



    @Override
    public void handleAnimationPacket(int entityId, int index) {
        EntityPlayerSP player = Minecraft.getMinecraft().player;
        if (player != null) {
            IAnimatedEntity entity = (IAnimatedEntity) player.world.getEntityByID(entityId);
            if (entity != null) {
                if (index == -1) {
                    entity.setAnimation(IAnimatedEntity.NO_ANIMATION);
                } else {
                    entity.setAnimation(entity.getAnimations()[index]);
                }
                entity.setAnimationTick(0);
            }
        }
    }

    @Override
    public void spawnParticle(int particle, double posX, double posY, double posZ, double speedX, double speedY, double speedZ, int... parameters)
    {
        Minecraft minecraft = Minecraft.getMinecraft();
        World world = minecraft.world;
        minecraft.effectRenderer.addEffect(getFactory(particle).createParticle(0, world, posX, posY, posZ, speedX, speedY, speedZ, parameters));
    }

    /**
     * This is used by the Particle Spawning as an ID system for out Particles.
     * We do not require Ids for Particles, it's just more convenient for sending over packets!
     * */
    @SideOnly(Side.CLIENT)
    public static IParticleFactory getFactory(int particleId)
    {
        switch(particleId)
        {
            default:
            case 0:
                return new ParticleObsidianTear.Factory();
            case 1:
                return new ParticleSoulFlame.Factory();
        }
    }

    public IParticleFactory getParticleFactory() {
        return new ParticleSoul.Factory();
    }

    @Override
    public void spawnSoulParticle(World worldIn, double x, double y, double z, double motX, double motY, double motZ) {
        if(worldIn == null) worldIn = Minecraft.getMinecraft().world;

        Minecraft.getMinecraft().effectRenderer.addEffect(getParticleFactory().createParticle(0, worldIn, x, y, z, motX, motY, motZ));
    }
}
