package com.brilliantcrash.testmod;

import com.brilliantcrash.testmod.blocks.ModBlocks;
import com.brilliantcrash.testmod.items.GunBase;
import com.brilliantcrash.testmod.items.GunGlock;
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

    public static final CreativeTabs testModAmmo = new CreativeTabs("TestModAmmo") {
        @Override
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem()
        {
            return GunBase.gunGlock;
        }
    };
}
