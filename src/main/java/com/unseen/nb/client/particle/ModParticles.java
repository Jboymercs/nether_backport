package com.unseen.nb.client.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleDrip;
import net.minecraft.client.particle.ParticleSmokeNormal;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ModParticles {


    public static void spawnColoredDrip(World worldIn, Vec3d pos, Vec3d baseColor, Vec3d vel) {
        Particle particleToo = new ParticleDrip.WaterFactory().createParticle(0, worldIn, pos.x, pos.y, pos.z, vel.x, vel.y, vel.z);
        baseColor = ModColors.variateColor(baseColor, 0.2f);
        particleToo.setRBGColorF((float) baseColor.x, (float) baseColor.y, (float) baseColor.z);

        Minecraft.getMinecraft().effectRenderer.addEffect(particleToo);
    }
}
