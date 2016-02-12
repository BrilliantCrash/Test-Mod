package com.brilliantcrash.testmod;

import com.brilliantcrash.testmod.blocks.ModBlocks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by Noah on 2/12/2016.
 */
public class ModCreativeTabs {
    public static final CreativeTabs testMod = new CreativeTabs("testMod")
    {
        @Override
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem()
        {
            return Item.getItemFromBlock(ModBlocks.testBlock);
        }
    };
}
