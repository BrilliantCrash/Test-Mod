package com.brilliantcrash.tacticraft.items.guns;

import com.brilliantcrash.tacticraft.ModCreativeTabs;
import com.brilliantcrash.tacticraft.entities.EntityBullet;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

/**
 * Created by Noah on 2/12/2016.
 * All guns should extend this class.
 */
public abstract class BaseGun extends Item {

    public int meta;
    public int maxAmmo;

    public BaseGun(String unlocalizedName, int maxAmmo) {
        super();
        this.setUnlocalizedName(unlocalizedName);
        this.setCreativeTab(ModCreativeTabs.tacticraft);
        this.maxAmmo = maxAmmo;
    }

    /**
     * What to do when firing the gun.
     * @param stack
     */
    public void fire(ItemStack stack, World worldIn, EntityPlayer player) {
        NBTTagCompound tc = stack.getTagCompound();

        if (tc.getInteger("ammo") == 0) {
            worldIn.playSoundAtEntity(player, "tacticraft:sound_gunClick", 1.0F, 1.0F);
            return;
        }

        tc.setInteger("ammo", tc.getInteger("ammo") - 1);
        System.out.println(tc.getInteger("ammo"));
        worldIn.playSoundAtEntity(player, "tacticraft:sound_gun9mmSingleShot", 1.0F, 1.0F);

        EntityBullet bullet = getBullet(worldIn, player, stack);
        if (!worldIn.isRemote) {
            worldIn.spawnEntityInWorld(bullet);
        }
    }

    public abstract EntityBullet getBullet(World worldIn, EntityPlayer player, ItemStack stack);

    /**
     * Reloads the gun if there is a clip in the player's inventory.
     */
    public void reload() {
        // TODO make this
    }

    @Override
    public void onCreated(ItemStack itemStack, World world, EntityPlayer player) {
        createNBT(itemStack);
    }

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
     * @param stack
     */
    public void createNBT (ItemStack stack) {
        if (!stack.hasTagCompound()) {
            stack.setTagCompound(new NBTTagCompound());
            stack.getTagCompound().setInteger("ammo", maxAmmo);
        }
    }

}
