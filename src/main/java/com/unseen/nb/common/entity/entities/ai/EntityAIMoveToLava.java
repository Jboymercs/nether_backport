package com.unseen.nb.common.entity.entities.ai;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIMoveToBlock;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityAIMoveToLava extends EntityAIMoveToBlock {
    private final EntityCreature entity;
    private final double movementSpeed;
    protected int runDelay;
    private int timeoutCounter;
    private int maxStayTicks;
    protected BlockPos destinationBlock = BlockPos.ORIGIN;
    private boolean isAboveDestination;

    public EntityAIMoveToLava(EntityCreature entityIn, double speedIn) {
        super(entityIn, speedIn, 0);
        this.entity = entityIn;
        this.movementSpeed = speedIn;
        this.setMutexBits(5);
    }

    @Override
    public boolean shouldExecute() {
        return !this.entity.isInLava() && super.shouldExecute();
    }

    @Override
    public boolean shouldContinueExecuting() {
        return !this.entity.isInLava() && this.shouldMoveTo(this.entity.world, this.destinationBlock);
    }

    @Override
    public void startExecuting() {
        this.entity.getNavigator().tryMoveToXYZ((double)((float)this.destinationBlock.getX()) + 0.5D, (double)(this.destinationBlock.getY()), (double)((float)this.destinationBlock.getZ()) + 0.5D, this.movementSpeed);
        this.timeoutCounter = 0;
        this.setMaxStayTicks(this.entity.getRNG().nextInt(this.entity.getRNG().nextInt(1200) + 1200) + 1200);
    }

    @Override
    public void updateTask() {
        if (this.entity.getDistanceSqToCenter(this.destinationBlock) > 1.0D) {
            this.isAboveDestination = false;
            ++this.timeoutCounter;

            if (this.timeoutCounter % 20 == 0) {
                this.entity.getNavigator().tryMoveToXYZ((double)((float)this.destinationBlock.getX()) + 0.5D, (double)(this.destinationBlock.getY()), (double)((float)this.destinationBlock.getZ()) + 0.5D, this.movementSpeed);
            }
        } else {
            this.isAboveDestination = true;
            --this.timeoutCounter;
        }
    }

    @Override
    protected boolean getIsAboveDestination() {
        return this.isAboveDestination;
    }

    public int getMaxStayTicks() {
        return maxStayTicks;
    }

    public void setMaxStayTicks(int maxStayTicks) {
        this.maxStayTicks = maxStayTicks;
    }

    @Override
    protected boolean shouldMoveTo(World worldIn, BlockPos pos) {
        return worldIn.getBlockState(pos).getBlock() == Blocks.LAVA && !worldIn.getBlockState(pos.up()).isSideSolid(worldIn, pos, EnumFacing.UP);
    }
}
