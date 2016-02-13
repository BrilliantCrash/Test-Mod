package com.brilliantcrash.testmod.items;

import com.brilliantcrash.testmod.ModCreativeTabs;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IntegerCache;

import java.util.List;

/**
 * Created by Noah on 2/12/2016.
 */
public class GunGlock extends Item {

    public int meta;

    public GunGlock(String unlocalizedName) {
        super();
        this.setHasSubtypes(true);
        this.setUnlocalizedName(unlocalizedName);
        this.setCreativeTab(ModCreativeTabs.testMod);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        int ammoCount;
        if (stack.getItemDamage() < 20) {
            ammoCount = 20-stack.getItemDamage();
            return super.getUnlocalizedName() + "." + Integer.toString(ammoCount);
        } else {
            return super.getUnlocalizedName() + ".0";
        }
    }

    @Override
    public void getSubItems(Item itemIn, CreativeTabs tab, List subItems) {
        for (int i=0; i<20; i++){
            subItems.add(new ItemStack(itemIn, 1, i));
        }
    }
}
