package com.unseen.nb.common.entity.entities.ai;

import com.unseen.nb.common.entity.entities.EntityHoglin;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;

public class EntityTimedAttackHoglin <T extends EntityHoglin & IAttack> extends EntityAIBase {
    private final T entity;
    private final double moveSpeedAmp;
    private final int attackCooldown;
    private final float maxAttackDistSq;
    private int attackTime;
    private boolean strafingClockwise;
    private boolean strafingBackwards;
    private int strafingTime = -1;
    private final float strafeAmount;
    private float lookSpeed;

    private static final float STRAFING_STOP_FACTOR = 0.75f;
    private static final float STRAFING_BACKWARDS_FACTOR = 0.25f;
    private static final float STRAFING_DIRECTION_TICK = 20;
    private static final float STRAFING_DIRECTION_CHANGE_CHANCE = 0.3f;

    public EntityTimedAttackHoglin(T entity, double moveSpeedAmp, int attackCooldown, float maxAttackDistance, float strafeAmount) {
        this(entity, moveSpeedAmp, attackCooldown, maxAttackDistance, strafeAmount, 30.0f);
    }

    public EntityTimedAttackHoglin(T entity, double moveSpeedAmp, int attackCooldown, float maxAttackDistance, float strafeAmount, float lookSpeed) {
        this.entity = entity;
        this.moveSpeedAmp = moveSpeedAmp;
        this.attackCooldown = attackCooldown;
        this.maxAttackDistSq = maxAttackDistance * maxAttackDistance;
        this.strafeAmount = strafeAmount;
        this.attackTime = attackCooldown;
        this.lookSpeed = lookSpeed;
        this.setMutexBits(3);
    }

    @Override
    public boolean shouldExecute() {
        return this.entity.getAttackTarget() != null && !this.entity.hasNearbyBlockItHates;
    }

    @Override
    public boolean shouldContinueExecuting() {
        return (this.shouldExecute() || !this.entity.getNavigator().noPath());
    }

    @Override
    public void resetTask() {
        super.resetTask();
        this.attackTime = Math.max(attackTime, attackCooldown);
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    @Override
    public void updateTask() {
        EntityLivingBase target = this.entity.getAttackTarget();

        if (target == null) {
            return;
        }

        double distSq = this.entity.getDistanceSq(target.posX, target.getEntityBoundingBox().minY, target.posZ);
        boolean canSee = this.entity.getEntitySenses().canSee(target);

        move(target, distSq, canSee);

        if (distSq <= this.maxAttackDistSq && canSee) {
            this.attackTime--;
            if (this.attackTime <= 0) {
                this.attackTime = this.entity.startAttack(target, (float) distSq, this.strafingBackwards);
            }
        }
    }

    public void move(EntityLivingBase target, double distSq, boolean canSee) {
        if (distSq <= (maxAttackDistSq - 2) && canSee) {
            this.entity.getNavigator().clearPath();
            ++this.strafingTime;
        } else {
            this.entity.getNavigator().tryMoveToEntityLiving(target, this.moveSpeedAmp);
            this.strafingTime = -1;
        }

        if (this.strafingTime >= STRAFING_DIRECTION_TICK) {
            if ((double) this.entity.getRNG().nextFloat() < STRAFING_DIRECTION_CHANGE_CHANCE) {
                this.strafingClockwise = !this.strafingClockwise;
            }

            if ((double) this.entity.getRNG().nextFloat() < STRAFING_DIRECTION_CHANGE_CHANCE) {
                this.strafingBackwards = !this.strafingBackwards;
            }

            this.strafingTime = 0;
        }

        if (this.strafingTime > -1) {
            if (distSq > this.maxAttackDistSq * STRAFING_STOP_FACTOR) {
                this.strafingBackwards = false;
            } else if (distSq < this.maxAttackDistSq * STRAFING_BACKWARDS_FACTOR) {
                this.strafingBackwards = true;
            }

            this.entity.getMoveHelper().strafe((this.strafingBackwards ? -1 : 1) * this.strafeAmount, (this.strafingClockwise ? 1 : -1) * this.strafeAmount);
            this.entity.faceEntity(target, this.lookSpeed, this.lookSpeed);
        } else {
            this.entity.getLookHelper().setLookPositionWithEntity(target, this.lookSpeed, this.lookSpeed);
        }
    }
}
