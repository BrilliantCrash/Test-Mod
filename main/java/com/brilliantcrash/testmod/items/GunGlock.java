package com.brilliantcrash.testmod.items;

import com.brilliantcrash.testmod.ModCreativeTabs;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IntegerCache;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

import java.util.List;

/**
 * Created by Noah on 2/12/2016.
 */
public class GunGlock extends Item {

<<<<<<< HEAD
=======
    public int meta;
    public int maxAmmo;
>>>>>>> c6ef11fb64eb628f2aaa492e764a12a17a8e9e35

    public GunGlock(String unlocalizedName, int maxAmmo) {
        super();
        this.setUnlocalizedName(unlocalizedName);
        this.setCreativeTab(ModCreativeTabs.testMod);
        this.maxAmmo = maxAmmo;
    }

    /**
     * What to do when firing the gun.
     * @param stack
     */
    public void fire(ItemStack stack, World worldIn, EntityPlayer player) {
        NBTTagCompound tc = stack.getTagCompound();

        if (tc.getInteger("ammo") == 0) {
            worldIn.playSoundAtEntity(player, "testmod:sound_gunClick", 1.0F, 1.0F);
            return;
        }

        tc.setInteger("ammo", tc.getInteger("ammo") - 1);
        System.out.println(tc.getInteger("ammo"));
        // Will fill in later
    }

    @Override
<<<<<<< HEAD
    public String getUnlocalizedName(ItemStack stack) {
        public String ammoCount;
        switch (int ammo = default) {
            case 1: ammoCount = "20";

            case 2: ammoCount = "19";

            case 3: ammoCount = "18";

            case 4: ammoCount = "17";

            case 5: ammoCount = "16";

            case 6: ammoCount = "15";

            case 7: ammoCount = "14";

            case 8: ammoCount = "13";

            case 9: ammoCount = "12";

            case 10: ammoCount = "11";

            case 11: ammoCount = "10";

            case 12: ammoCount = "9";

            case 13: ammoCount = "8";

            case 14: ammoCount = "7";

            case 15: ammoCount = "6";

            case 16: ammoCount = "5";

            case 17: ammoCount = "4";

            case 18: ammoCount = "3";

            case 19: ammoCount = "2";

            case 20: ammoCount = "1";

            case 21: ammoCount = "empty";

            case default: ammoCount = "empty";
        }

        return super.getUnlocalizedName() + "." + ammoCount);
=======
    public void onCreated(ItemStack itemStack, World world, EntityPlayer player) {
        createNBT(itemStack);
    }

    public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn)
    {
        createNBT(itemStackIn);
        fire(itemStackIn, worldIn, playerIn);
        return itemStackIn;
>>>>>>> c6ef11fb64eb628f2aaa492e764a12a17a8e9e35
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
