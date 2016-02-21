package com.brilliantcrash.tacticraft.items;

import com.brilliantcrash.tacticraft.ModCreativeTabs;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSword;
import scala.xml.dtd.impl.Base;

/**
 * Created by Noah on 2/20/2016.
 */
public class BaseSword extends ItemSword {

    public BaseSword(String unlocalizedName, ToolMaterial material) {
        super(material);
        this.setUnlocalizedName(unlocalizedName);
        this.setCreativeTab(ModCreativeTabs.tacticraftItems);
    }
}
