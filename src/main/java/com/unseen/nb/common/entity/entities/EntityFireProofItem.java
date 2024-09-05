package com.unseen.nb.common.entity.entities;

import net.minecraft.block.material.Material;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.StatList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityFireProofItem extends EntityItem {
    /** The age of this EntityFireproofItem (used to animate it up and down as well as expire it) */
    private int age;
    private int pickupDelay;
    /** The health of this EntityFireproofItem. (For example, damage for tools) */
    private int health;

    public EntityFireProofItem(World worldIn) {
        super(worldIn);
        this.health = 5;

        this.isImmuneToFire = true;
    }

    public EntityFireProofItem(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
        this.health = 5;

        this.isImmuneToFire = true;
    }

    public EntityFireProofItem(World worldIn, double x, double y, double z, ItemStack stack) {
        super(worldIn, x, y, z, stack);

        this.isImmuneToFire = true;
    }

    public EntityFireProofItem(World worldIn, EntityItem itemIn, ItemStack stack) {
        this(worldIn, itemIn.posX, itemIn.posY, itemIn.posZ, stack);
        this.motionX = itemIn.motionX;
        this.motionY = itemIn.motionY;
        this.motionZ = itemIn.motionZ;
        NBTTagCompound tag = new NBTTagCompound();
        itemIn.writeToNBT(tag);
        this.setPickupDelay(tag.getShort("PickupDelay"));
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (this.world.isRemote || this.isDead) return false;
        if (this.isEntityInvulnerable(source)) {
            return false;
        } else if (!this.getItem().isEmpty()) {
            if(source.isExplosion()) {
                return this.getItem().getItem() != Items.NETHER_STAR;
            } else if(source.isFireDamage()) {
                return false;
            }
            return false;
        } else {
            this.markVelocityChanged();
            this.health = (int)((float)this.health - amount);

            if (this.health <= 0) {
                this.setDead();
            }
            return false;
        }
    }

    @Override
    public void onUpdate() {
        if (getItem().getItem().onEntityItemUpdate(this)) return;
        if (this.getItem().isEmpty()) {
            this.setDead();
        } else {
            if (!this.world.isRemote) {
                this.setFlag(6, this.isGlowing());
            }

            this.onEntityUpdate();

            if (this.pickupDelay > 0 && this.pickupDelay != 32767) {
                --this.pickupDelay;
            }

            this.prevPosX = this.posX;
            this.prevPosY = this.posY;
            this.prevPosZ = this.posZ;
            double d0 = this.motionX;
            double d1 = this.motionY;
            double d2 = this.motionZ;

            if (this.isInsideOfMaterial(Material.LAVA)) {
                this.motionX *= 0.95F;
                this.motionY += this.floatInLava();
                this.motionZ *= 0.95F;
            } else if (!this.hasNoGravity()) {
                this.motionY -= 0.03999999910593033D;
            }

            if (this.world.isRemote) {
                this.noClip = false;
            } else {
                this.noClip = this.pushOutOfBlocks(this.posX, (this.getEntityBoundingBox().minY + this.getEntityBoundingBox().maxY) / 2.0D, this.posZ);
            }
            this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);

            boolean flag = (int)this.prevPosX != (int)this.posX || (int)this.prevPosY != (int)this.posY || (int)this.prevPosZ != (int)this.posZ;

            if (flag || this.ticksExisted % 25 == 0) {
                if (!this.world.isRemote) {
                    this.searchForOtherItemsNearby();
                }
            }

            float f = 0.98F;

            if (this.onGround) {
                BlockPos underPos = new BlockPos(MathHelper.floor(this.posX), MathHelper.floor(this.getEntityBoundingBox().minY) - 1, MathHelper.floor(this.posZ));
                net.minecraft.block.state.IBlockState underState = this.world.getBlockState(underPos);
                f = underState.getBlock().getSlipperiness(underState, this.world, underPos, this) * 0.98F;
            }

            this.motionX *= (double)f;
            this.motionY *= 0.9800000190734863D;
            this.motionZ *= (double)f;

            if (this.onGround) {
                this.motionY *= -0.5D;
            } if (this.age != -32768) {
                ++this.age;
            }

            this.handleWaterMovement();
            this.handleLavaMovement();

            if (!this.world.isRemote) {
                double d3 = this.motionX - d0;
                double d4 = this.motionY - d1;
                double d5 = this.motionZ - d2;
                double d6 = d3 * d3 + d4 * d4 + d5 * d5;

                if (d6 > 0.01D) {
                    this.isAirBorne = true;
                }
            }

            ItemStack item = this.getItem();

            if (!this.world.isRemote && this.age >= lifespan) {
                int hook = ForgeEventFactory.onItemExpire(this, item);
                if (hook < 0) this.setDead();
                else          this.lifespan += hook;
            } if (item.isEmpty()) {
                this.setDead();
            }
        }
    }

    private double floatInLava() {
        if(this.motionY < 0.6) {
            return 5.0E-4;
        } else {
            return 0.0;
        }
    }

    private boolean handleLavaMovement() {
        if (this.world.handleMaterialAcceleration(this.getEntityBoundingBox(), Material.LAVA, this)) {
            this.motionX *= 0.75F;
            this.motionZ *= 0.75F;
        }

        return this.isInsideOfMaterial(Material.LAVA);
    }

    private void searchForOtherItemsNearby() {
        for (EntityFireProofItem entityitem : this.world.getEntitiesWithinAABB(EntityFireProofItem.class, this.getEntityBoundingBox().grow(0.5D, 0.0D, 0.5D))) {
            this.combineItems(entityitem);
        }
    }

    private boolean combineItems(EntityFireProofItem other) {
        if (other == this) {
            return false;
        } else if (other.isEntityAlive() && this.isEntityAlive()) {
            ItemStack itemstack = this.getItem();
            ItemStack itemstack1 = other.getItem();

            if (this.pickupDelay != 32767 && other.pickupDelay != 32767) {
                if (this.age != -32768 && other.age != -32768) {
                    if (itemstack1.getItem() != itemstack.getItem()) {
                        return false;
                    } else if (itemstack1.hasTagCompound() ^ itemstack.hasTagCompound()) {
                        return false;
                    } else if (itemstack1.hasTagCompound() && !itemstack1.getTagCompound().equals(itemstack.getTagCompound())) {
                        return false;
                    } else if (itemstack1.getItem() == null) {
                        return false;
                    } else if (itemstack1.getItem().getHasSubtypes() && itemstack1.getMetadata() != itemstack.getMetadata()) {
                        return false;
                    } else if (itemstack1.getCount() < itemstack.getCount()) {
                        return other.combineItems(this);
                    } else if (itemstack1.getCount() + itemstack.getCount() > itemstack1.getMaxStackSize()) {
                        return false;
                    } else if (!itemstack.areCapsCompatible(itemstack1)) {
                        return false;
                    } else {
                        itemstack1.grow(itemstack.getCount());
                        other.pickupDelay = Math.max(other.pickupDelay, this.pickupDelay);
                        other.age = Math.min(other.age, this.age);
                        other.setItem(itemstack1);
                        this.setDead();
                        return true;
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public void setAgeToCreativeDespawnTime() {
        this.age = 4800;
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        compound.setShort("Health", (short)this.health);
        compound.setShort("Age", (short)this.age);
        compound.setShort("PickupDelay", (short)this.pickupDelay);
        compound.setInteger("Lifespan", lifespan);

        if (!this.getItem().isEmpty()) {
            compound.setTag("Item", this.getItem().writeToNBT(new NBTTagCompound()));
        }
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        this.health = compound.getShort("Health");
        this.age = compound.getShort("Age");

        if (compound.hasKey("PickupDelay")) {
            this.pickupDelay = compound.getShort("PickupDelay");
        }

        NBTTagCompound nbttagcompound = compound.getCompoundTag("Item");
        this.setItem(new ItemStack(nbttagcompound));

        if (this.getItem().isEmpty()) {
            this.setDead();
        }
        if (compound.hasKey("Lifespan")) lifespan = compound.getInteger("Lifespan");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getAge() {
        return this.age;
    }

    @Override
    public void setDefaultPickupDelay() {
        this.pickupDelay = 10;
    }

    @Override
    public void setNoPickupDelay() {
        this.pickupDelay = 0;
    }

    @Override
    public void setInfinitePickupDelay() {
        this.pickupDelay = 32767;
    }

    @Override
    public void setPickupDelay(int ticks) {
        this.pickupDelay = ticks;
    }

    @Override
    public boolean cannotPickup() {
        return this.pickupDelay > 0;
    }

    @Override
    public void setNoDespawn() {
        this.age = -6000;
    }

    @Override
    public void makeFakeItem() {
        this.setInfinitePickupDelay();
        this.age = getItem().getItem().getEntityLifespan(getItem(), world) - 1;
    }

    @Override
    public void onCollideWithPlayer(EntityPlayer entityIn) {
        if (!this.world.isRemote) {
            if (this.pickupDelay > 0) return;
            ItemStack itemstack = this.getItem();
            Item item = itemstack.getItem();
            int i = itemstack.getCount();

            int hook = ForgeEventFactory.onItemPickup(this, entityIn);
            if (hook < 0) return;
            ItemStack clone = itemstack.copy();

            if (this.pickupDelay <= 0 && (this.getOwner() == null || lifespan - this.age <= 200 || this.getOwner().equals(entityIn.getName())) && (hook == 1 || i <= 0 || entityIn.inventory.addItemStackToInventory(itemstack) || clone.getCount() > this.getItem().getCount())) {
                clone.setCount(clone.getCount() - this.getItem().getCount());
                FMLCommonHandler.instance().firePlayerItemPickupEvent(entityIn, this, clone);

                if (itemstack.isEmpty()) {
                    entityIn.onItemPickup(this, i);
                    this.setDead();
                    itemstack.setCount(i);
                }

                entityIn.addStat(StatList.getObjectsPickedUpStats(item), i);
            }
        }
    }
}
