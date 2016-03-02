package com.brilliantcrash.tacticraft.items.guns;

import com.brilliantcrash.tacticraft.ModCreativeTabs;
import com.brilliantcrash.tacticraft.entities.EntityBullet;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

/**
 * Created by Noah on 2/12/2016.
 * All guns should extend this class.
 */
public abstract class BaseGun extends Item {

    public int maxAmmo;
    public Item ammoType;
    /**
     * Delay after reload in which a shot cannot be fired in ticks.
     */
    public int reloadTime;
    /**
     * Delay between shots in ticks (20 ticks = 1 sec)
     */
    public int fireRate;

    public BaseGun(String unlocalizedName, int maxAmmo, Item ammoType, int reloadTime, int fireRate) {
        super();
        this.setUnlocalizedName(unlocalizedName);
        this.setCreativeTab(ModCreativeTabs.tacticraft);
        this.maxAmmo = maxAmmo;
        this.ammoType = ammoType;
        this.reloadTime = reloadTime;
        this.fireRate = fireRate;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.NONE;
    }

    /**
     * What to do when firing the gun.
     * @param stack The gun itself.
     */
    public void fire(ItemStack stack, World worldIn, EntityPlayer player) {
        if (worldIn.getTotalWorldTime() - stack.getTagCompound().getLong("lastReload") > reloadTime &&
                worldIn.getTotalWorldTime() - stack.getTagCompound().getLong("lastShot") > fireRate) {
            NBTTagCompound tc = stack.getTagCompound();

            if (tc.getInteger("ammo") == 0) {
                worldIn.playSoundAtEntity(player, "tacticraft:sound_gunClick", 1.0F, 1.0F);
                return;
            }

            tc.setInteger("ammo", tc.getInteger("ammo") - 1);
            System.out.println(tc.getInteger("ammo"));
            tc.setLong("lastShot", worldIn.getTotalWorldTime());
            worldIn.playSoundAtEntity(player, "tacticraft:sound_gun9mmSingleShot", 1.0F, 1.0F);

            EntityBullet bullet = getBullet(worldIn, player, stack);
            if (!worldIn.isRemote) {
                worldIn.spawnEntityInWorld(bullet);
            }
        }
    }

    /**
     * @param worldIn The world.
     * @param player The player that fired the gun.
     * @param stack The actual bullet itself.
     * @return What bullet this gun uses.
     */
    public abstract EntityBullet getBullet(World worldIn, EntityPlayer player, ItemStack stack);

    /**
     * Reloads the gun if there is a clip in the player's inventory.
     */
    public static void reload(EntityPlayer entPlayer, int ammoType2, ItemStack gun, World worldIn) {
        NBTTagCompound gunTc = gun.getTagCompound();

        if (entPlayer.inventory.hasItem(Item.getItemById(ammoType2))) {
            entPlayer.inventory.consumeInventoryItem(Item.getItemById(ammoType2));
            gunTc.setInteger("ammo", ((BaseGun) gun.getItem()).maxAmmo);
            gunTc.setLong("lastReload", worldIn.getTotalWorldTime());
            worldIn.playSoundAtEntity(entPlayer, ((BaseGun) gun.getItem()).getSound(), 1.0F, 1.0F);
        }
    }

    /**
     * @return What sound the gun makes.
     */
    public abstract String getSound();

    /**
     * @return What firemode the gun uses. Comes in the form of an array, the first item of which is the default.
     */
    public abstract FireMode[] getFireModes();

    @Override
    public void onCreated(ItemStack itemStack, World world, EntityPlayer player) {
        createNBT(itemStack);
    }

    //TODO integrate firemodes
    public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn)
    {
        createNBT(itemStackIn);
        fire(itemStackIn, worldIn, playerIn);
        return itemStackIn;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        if (stack.hasTagCompound())
            return super.getUnlocalizedName() + "." + stack.getTagCompound().getInteger("ammo");
        else
            return super.getUnlocalizedName();
    }

    /**
     * Method to quickly create an NBTTagCompound if it doesn't already exist.
     * @param stack Item to make a NBTTagCompound for.
     */
    public void createNBT (ItemStack stack) {
        if (!stack.hasTagCompound()) {
            stack.setTagCompound(new NBTTagCompound());
            stack.getTagCompound().setInteger("ammo", maxAmmo);
            stack.getTagCompound().setLong("lastReload", 0);
            stack.getTagCompound().setLong("lastShot", 0);
        }
    }

}
