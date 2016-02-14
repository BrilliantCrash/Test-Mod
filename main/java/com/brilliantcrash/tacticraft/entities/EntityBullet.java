package com.brilliantcrash.tacticraft.entities;

import com.brilliantcrash.tacticraft.BulletDamageSource;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.world.World;

import java.util.List;


/**
 * Created by Evan on 2/13/2016.
 */
public class EntityBullet extends Entity implements IProjectile {

    public float damage;
    public EntityLivingBase shootingEntity;
    public int ticksInAir;

    public EntityBullet (World worldIn) {
        super(worldIn);
    }

    public EntityBullet (World worldIn, EntityLivingBase shooter, float velocity, float damage, float innacuracy) {
        // Modified from EntityArrow

        super(worldIn);
        this.damage = damage;
        this.renderDistanceWeight = 10.0D;
        this.shootingEntity = shooter;

        this.setLocationAndAngles(shooter.posX, shooter.posY + (double)shooter.getEyeHeight(), shooter.posZ, shooter.rotationYaw, shooter.rotationPitch);
        this.posX -= (double)(MathHelper.cos(this.rotationYaw / 180.0F * (float)Math.PI) * 0.16F);
        this.posY -= 0.10000000149011612D;
        this.posZ -= (double)(MathHelper.sin(this.rotationYaw / 180.0F * (float)Math.PI) * 0.16F);
        this.setPosition(this.posX, this.posY, this.posZ);
        this.motionX = (double)(-MathHelper.sin(this.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI));
        this.motionZ = (double)(MathHelper.cos(this.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI));
        this.motionY = (double)(-MathHelper.sin(this.rotationPitch / 180.0F * (float)Math.PI));
        this.setThrowableHeading(this.motionX, this.motionY, this.motionZ, velocity * 1.5F, innacuracy);
        this.setSize(1.0F, 1.0F);
    }

    @Override
    public void setThrowableHeading(double x, double y, double z, float velocity, float inaccuracy) {
        // From EntityArrow
        float f = MathHelper.sqrt_double(x * x + y * y + z * z);
        x = x / (double)f;
        y = y / (double)f;
        z = z / (double)f;
        x = x + this.rand.nextGaussian() * (double)(this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * (double)inaccuracy;
        y = y + this.rand.nextGaussian() * (double)(this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * (double)inaccuracy;
        z = z + this.rand.nextGaussian() * (double)(this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * (double)inaccuracy;
        x = x * (double)velocity;
        y = y * (double)velocity;
        z = z * (double)velocity;
        this.motionX = x;
        this.motionY = y;
        this.motionZ = z;
        float f1 = MathHelper.sqrt_double(x * x + z * z);
        this.prevRotationYaw = this.rotationYaw = (float)(MathHelper.atan2(x, z) * 180.0D / Math.PI);
        this.prevRotationPitch = this.rotationPitch = (float)(MathHelper.atan2(y, (double)f1) * 180.0D / Math.PI);
    }

    @Override
    public void onUpdate() {
        if (this.worldObj.isRemote || (this.shootingEntity == null || !this.shootingEntity.isDead) && this.worldObj.isBlockLoaded(new BlockPos(this))) {
            super.onUpdate();

            this.ticksInAir++;

            Vec3 currentPos = new Vec3(this.posX, this.posY, this.posZ);
            Vec3 nextPos = new Vec3(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
            // Determine if the projectile will hit a block
            MovingObjectPosition movingobjectposition = this.worldObj.rayTraceBlocks(currentPos, nextPos);
            currentPos = new Vec3(this.posX, this.posY, this.posZ);
            nextPos = new Vec3(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);

            if (movingobjectposition != null)
            {
                nextPos = new Vec3(movingobjectposition.hitVec.xCoord, movingobjectposition.hitVec.yCoord, movingobjectposition.hitVec.zCoord);
            }

            //Code to determine which entity it hits (if it does at all)
            Entity hitEntity = null;
            List<Entity> list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0D, 1.0D, 1.0D));
            double d0 = 0.0D; // Distance to the closest entity in the way (defaults to 0.0D)

            for (int i = 0; i < list.size(); ++i)
            {
                Entity tmpEntity = list.get(i);

                if (tmpEntity.canBeCollidedWith() && (!tmpEntity.isEntityEqual(this.shootingEntity)))
                {
                    float f = 0.3F;
                    AxisAlignedBB axisalignedbb = tmpEntity.getEntityBoundingBox().expand((double)f, (double)f, (double)f);
                    MovingObjectPosition movingobjectposition1 = axisalignedbb.calculateIntercept(currentPos, nextPos);

                    if (movingobjectposition1 != null)
                    {
                        double d1 = currentPos.squareDistanceTo(movingobjectposition1.hitVec);

                        if (d1 < d0 || d0 == 0.0D)
                        {
                            hitEntity = tmpEntity;
                            d0 = d1;
                        }
                    }
                }
            }

            // Set the hit position to the entity, if it collides with it
            if (hitEntity != null)
            {
                movingobjectposition = new MovingObjectPosition(hitEntity);
            }

            if (movingobjectposition != null)
            {
                this.onImpact(movingobjectposition);
            }

            this.posX += this.motionX;
            this.posY += this.motionY;
            this.posZ += this.motionZ;
            float f1 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
            this.rotationYaw = (float)(MathHelper.atan2(this.motionZ, this.motionX) * 180.0D / Math.PI) + 90.0F;

            for (this.rotationPitch = (float)(MathHelper.atan2((double)f1, this.motionY) * 180.0D / Math.PI) - 90.0F; this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F)
            {
                ; // TODO figure out what this does
            }

            while (this.rotationPitch - this.prevRotationPitch >= 180.0F)
            {
                this.prevRotationPitch += 360.0F;
            }

            while (this.rotationYaw - this.prevRotationYaw < -180.0F)
            {
                this.prevRotationYaw -= 360.0F;
            }

            while (this.rotationYaw - this.prevRotationYaw >= 180.0F)
            {
                this.prevRotationYaw += 360.0F;
            }

            this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
            this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
            float motionFactor = 0.95F;

            if (this.isInWater())
            {
                for (int j = 0; j < 4; ++j)
                {
                    float f3 = 0.25F;
                    this.worldObj.spawnParticle(EnumParticleTypes.WATER_BUBBLE, this.posX - this.motionX * (double)f3, this.posY - this.motionY * (double)f3, this.posZ - this.motionZ * (double)f3, this.motionX, this.motionY, this.motionZ, new int[0]);
                }

                motionFactor = 0.8F;
            }

            this.motionX *= (double)motionFactor;
            this.motionY *= (double)motionFactor;
            this.motionZ *= (double)motionFactor;
            this.worldObj.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, this.posX, this.posY + 0.5D, this.posZ, 0.0D, 0.0D, 0.0D, new int[0]);
            this.setPosition(this.posX, this.posY, this.posZ);
        } else {
            this.setDead();
        }
    }

    protected void onImpact(MovingObjectPosition mop) {
        if (!this.worldObj.isRemote)
        {
            if (mop.entityHit != null)
            {
                mop.entityHit.attackEntityFrom(new BulletDamageSource("bullet", this.shootingEntity, this.shootingEntity.getHeldItem()), 6.0F);
            }

            this.setDead();
        }
    }

    @Override
    protected void entityInit() {
        this.dataWatcher.addObject(16, (byte) 0);
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound tagCompund) {
        damage = tagCompund.getFloat("damage");
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound tagCompound) {
        tagCompound.setFloat ("damage", damage);
    }
}
