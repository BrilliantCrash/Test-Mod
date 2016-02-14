package com.brilliantcrash.tacticraft.items;

import com.brilliantcrash.tacticraft.ModCreativeTabs;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * Created by Noah on 2/13/2016.
 */
public class BaseItem extends Item {
    public BaseItem (String unlocalizedName, CreativeTabs tab) {
        super();

        this.setUnlocalizedName(unlocalizedName);
        this.setCreativeTab(tab);
    }

    public BaseItem (String unlocalizedName) {
        this(unlocalizedName, ModCreativeTabs.tacticraftItems);
    }
}
