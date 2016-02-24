package com.brilliantcrash.tacticraft.entities;

import com.brilliantcrash.tacticraft.BulletDamageSource;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.UUID;


/**
 * Created by Evan on 2/13/2016.
 * Good luck. 
 */
public class EntityBullet extends Entity implements IProjectile {

    public float damage;
    public EntityLivingBase shootingEntity;
	/**
	 * Used to find shootingEntity with getThrower().
	 */
    public String shootingEntityName;
    public int ticksInAir;

	/** 
	 * The amount that the bullet's motionY is decremented by at the end of each tick. 
	 */
    final double gravityFactor = 0.01D;

    public EntityBullet (World worldIn) {
        super(worldIn);
    }

    public EntityBullet (World worldIn, EntityLivingBase shooter, float velocity, float damage, float innacuracy) {
        // Modified from EntityArrow

        super(worldIn);
        this.damage = damage;
        this.renderDistanceWeight = 10.0D;
        this.shootingEntity = shooter;
        this.shootingEntityName = shooter.getDisplayName().toString();

        this.setLocationAndAngles(shooter.posX, shooter.posY + (double)shooter.getEyeHeight(), shooter.posZ, shooter.rotationYaw, shooter.rotationPitch);
        this.posX -= (double)(MathHelper.cos(this.rotationYaw / 180.0F * (float)Math.PI) * 0.16F);
        this.posY -= 0.10000000149011612D;
        this.posZ -= (double)(MathHelper.sin(this.rotationYaw / 180.0F * (float)Math.PI) * 0.16F);
        this.setPosition(this.posX, this.posY, this.posZ);
        this.motionX = (double)(-MathHelper.sin(this.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI));
        this.motionZ = (double)(MathHelper.cos(this.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI));
        this.motionY = (double)(-MathHelper.sin(this.rotationPitch / 180.0F * (float)Math.PI));
        this.setThrowableHeading(this.motionX, this.motionY, this.motionZ, velocity, innacuracy);
        this.setSize(5.0F, 5.0F);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void setVelocity(double x, double y, double z)
    {
        this.motionX = x;
        this.motionY = y;
        this.motionZ = z;

        if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F)
        {
            float f = MathHelper.sqrt_double(x * x + z * z);
            this.prevRotationYaw = this.rotationYaw = (float)(MathHelper.atan2(x, z) * 180.0D / Math.PI);
            this.prevRotationPitch = this.rotationPitch = (float)(MathHelper.atan2(y, (double)f) * 180.0D / Math.PI);
        }
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

	// Have fun going through this! I took a lot of it from other projectiles, and made as much sense of it as I could. 
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
				// If the projectile will hit a block, set the next position to wherever the imapct is. 
                nextPos = new Vec3(movingobjectposition.hitVec.xCoord, movingobjectposition.hitVec.yCoord, movingobjectposition.hitVec.zCoord);
            }

            //Code to determine which entity it hits (if it does at all)
            Entity hitEntity = null;
            List<Entity> list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0D, 1.0D, 1.0D));
            double d0 = 0.0D; // Distance to the closest entity in the way (defaults to 0.0D)

			// Finding the closest entity in the line of fire
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

			// Do damage to the hit entity. 
            if (movingobjectposition != null)
            {
                this.onImpact(movingobjectposition);
            }

			// Actually change the position (although the bullet itself gets moved later on)
            this.posX += this.motionX;
            this.posY += this.motionY;
            this.posZ += this.motionZ;
            float f1 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
            this.rotationYaw = (float)(MathHelper.atan2(this.motionZ, this.motionX) * 180.0D / Math.PI) + 90.0F;

            for (this.rotationPitch = (float)(MathHelper.atan2((double)f1, this.motionY) * 180.0D / Math.PI) - 90.0F; this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F)
            {
            }

			// This prevRotationPitch stuff is here from another class.
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

			// REALLY IMPORTANT - this is the thing that slows down the bullet, like air drag. The bullet's speed gets multiplied
			// by this every tick. We want the bullet to go far, so we keep it very close to 1.0F. 
            float motionFactor = 0.995F;

            if (this.isInWater())
            {
                for (int j = 0; j < 4; ++j)
                {
                    float f3 = 0.25F;
                    this.worldObj.spawnParticle(EnumParticleTypes.WATER_BUBBLE, this.posX - this.motionX * (double)f3, this.posY - this.motionY * (double)f3, this.posZ - this.motionZ * (double)f3, this.motionX, this.motionY, this.motionZ);
                }

				// Make the bullet a lot slower if it's in water. 
                motionFactor = 0.8F;
            }

			// Apply the drag to the bullet's motion.
            this.motionX *= (double)motionFactor;
            this.motionY *= (double)motionFactor;
            this.motionZ *= (double)motionFactor;
			// Make the accelerate the bullet downward according to the gravityFactor, set above. 
            this.motionY -= gravityFactor;
            //this.worldObj.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D, new int[0]);
			// Actually move the bullet.
            this.setPosition(this.posX, this.posY, this.posZ);
        } else {
			// Kill the bullet
            this.setDead();
        }
    }

    protected void onImpact(MovingObjectPosition mop) {

        if (mop.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
            this.worldObj.spawnParticle(EnumParticleTypes.BLOCK_DUST, mop.getBlockPos().getX(), mop.getBlockPos().getY(), mop.getBlockPos().getZ(), 0.0D, 0.0D, 0.0D, Block.getStateId(worldObj.getBlockState(mop.getBlockPos())));
        else
            this.worldObj.spawnParticle(EnumParticleTypes.CRIT, mop.entityHit.posX, mop.entityHit.posY, mop.entityHit.posZ, 0.0D, 0.0D, 0.0D);

        if (!this.worldObj.isRemote)
        {
            if (mop.entityHit != null)
            {
				// The first argument in the BulletDamageSource MUST be "bullet," or the death messages won't work
                if (this.shootingEntity != null)
                    mop.entityHit.attackEntityFrom(new BulletDamageSource("bullet", this.shootingEntity, this.shootingEntity.getHeldItem()), damage);
                else
                    mop.entityHit.attackEntityFrom(new BulletDamageSource("bullet", null, null), damage);
            }

            this.setDead();
        }
    }

    // Taken from EntityThrowable
    /**
     * Checks if the entity is in range to render by using the past in distance and comparing it to its average edge
     * length * 64 * renderDistanceWeight Args: distance
     */
    @SideOnly(Side.CLIENT)
    public boolean isInRangeToRenderDist(double distance)
    {
        double d0 = this.getEntityBoundingBox().getAverageEdgeLength() * 4.0D;

        if (Double.isNaN(d0))
        {
            d0 = 4.0D;
        }

        d0 = d0 * 64.0D;
        return distance < d0 * d0;
    }

	/**
	 * Looks up shootingEntityName in the player list, returns the actual
	 * EntityLivingBase that shot the projectile.
	 */
    public EntityLivingBase getThrower()
    {
        if (this.shootingEntity == null && this.shootingEntityName != null && this.shootingEntityName.length() > 0)
        {
            this.shootingEntity = this.worldObj.getPlayerEntityByName(this.shootingEntityName);

            if (this.shootingEntity == null && this.worldObj instanceof WorldServer)
            {
                try
                {
                    Entity entity = ((WorldServer)this.worldObj).getEntityFromUuid(UUID.fromString(this.shootingEntityName));

                    if (entity instanceof EntityLivingBase)
                    {
                        this.shootingEntity = (EntityLivingBase)entity;
                    }
                }
                catch (Throwable var2)
                {
                    this.shootingEntity = null;
                }
            }
        }

        return this.shootingEntity;
    }

    @Override
    protected void entityInit() {
		// Don't know what this does, but it seems important :P - it was in some of the other projectile files
        this.dataWatcher.addObject(16, (byte) 0);
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound tagCompund) {
        damage = tagCompund.getFloat("damage");
        shootingEntityName = tagCompund.getString("shootingEntityName");
        shootingEntity = getThrower();
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound tagCompound) {
        tagCompound.setFloat ("damage", damage);
        if (shootingEntity != null)
            tagCompound.setString ("shootingEntityName", shootingEntity.getDisplayName().toString());
    }
}
