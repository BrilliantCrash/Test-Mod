package com.brilliantcrash.testmod.blocks;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * Created by Evan on 2/12/2016.
 */
public class BaseAmmo extends Item {

    public BaseAmmo (String unlocalizedName, CreativeTabs tab) {
        this.setUnlocalizedName(unlocalizedName);
        this.setCreativeTab(tab);
    }

    public BaseAmmo (String unlocalizedName) {

    }

}
