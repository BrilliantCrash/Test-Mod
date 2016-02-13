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

    public int meta;
    public int maxAmmo;

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
        worldIn.playSoundAtEntity(player, "testmod:sound_gun9mmSingleShot", 1.0F, 1.0F);
        // Will fill in later
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
