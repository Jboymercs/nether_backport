package com.unseen.nb.client.animation;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;



public class EZLegSolver {
        public final Leg[] legs;

        public EZLegSolver(Leg... legs) {
            this.legs = legs;
        }

        public final void update(EntityLiving entity, float scale) {
            this.update(entity, entity.rotationYaw,scale);
        }


    public final void update(EntityLiving entity, float yaw, float scale) {
        double sideTheta = yaw / (180 / Math.PI);
        double sideX = Math.cos(sideTheta) * scale;
        double sideZ = Math.sin(sideTheta) * scale;
        double forwardTheta = sideTheta + Math.PI / 2;
        double forwardX = Math.cos(forwardTheta) * scale;
        double forwardZ = Math.sin(forwardTheta) * scale;
        for (Leg leg : this.legs) {
            leg.update(entity, sideX, sideZ, forwardX, forwardZ, scale);
        }
    }


    public static class Leg {
        public final float forward;
        public final float side;
        private final float range;
        private float height;
        private float prevHeight;
        private boolean isWing;

        public Leg(float forward, float side, float range, boolean isWing) {
            this.forward = forward;
            this.side = side;
            this.range = range;
            this.isWing = isWing;
        }

        public float getHeight(float delta) {
            return this.prevHeight + (this.height - this.prevHeight) * delta;
        }

        public void update(EntityLiving entity, double sideX, double sideZ, double forwardX, double forwardZ, float scale) {
            this.prevHeight = height;
            double posY = entity.posY;
            float settledHeight = this.settle(entity, entity.posX + sideX * this.side + forwardX * this.forward, posY, entity.posZ + sideZ * this.side + forwardZ * this.forward, this.height);
            this.height = clamp(settledHeight, -this.range * scale, this.range * scale);

        }

        public static float clamp(double value, double min, double max) {
            return (float) Math.max(min, Math.min(max, value));
        }


        private float settle(EntityLiving entity, double x, double y, double z, float height) {
            BlockPos pos = new BlockPos(x, y + 1e-3, z);
            Vec3d vec3 = new Vec3d(x, y, z);
            float dist = this.getDistance(entity.world, pos, vec3);
            if ((double)(1.0F - dist) < 0.001D) {
                dist = this.getDistance(entity.world, pos.down(), vec3) + (float) y % 1;
            } else {
                dist = (float)((double)dist - (1.0D - y % 1.0D));
            }
            if (entity.world.isBlockFullCube(entity.getPosition().down()) && height <= dist) {
                return height == dist ? height : Math.min(height + this.getFallSpeed(), dist);
            } else if (height > 0) {
                return height == dist ? height : Math.max(height - this.getRiseSpeed(), dist);
            }
            return height;
        }

        private float getDistance(World world, BlockPos pos, Vec3d position) {
            IBlockState state = world.getBlockState(pos);
             AxisAlignedBB block = state.getCollisionBoundingBox(world, pos);
            if(block == null){
                return 1.0F;
            }
            //Something with testing leg moving reference JurrasiCraft 1 to look into Leg Solver further
            return block == null ? 1.0F : 1.0F - Math.min(0.5F, 1.0F);
        }

        protected float getFallSpeed() {
            return 0.25F;
        }

        protected float getRiseSpeed() {
            return 0.25F;
        }
    }
}
