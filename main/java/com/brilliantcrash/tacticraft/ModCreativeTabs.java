package com.brilliantcrash.tacticraft;

import com.brilliantcrash.tacticraft.blocks.ModBlocks;
import com.brilliantcrash.tacticraft.items.GunBase;
import com.brilliantcrash.tacticraft.items.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by Noah on 2/12/2016.
 */
public class ModCreativeTabs {
    public static final CreativeTabs tacticraft = new CreativeTabs("tacticraft")
    {
        @Override
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem()
        {
            return GunBase.gunGlock;
        }
    };

    public static final CreativeTabs tacticraftAmmo = new CreativeTabs("tacticraftAmmo") {
        @Override
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem()
        {
            return ModItems.ammo_ninemm;
        }
    };

    public static final CreativeTabs tacticraftItems = new CreativeTabs("tacticraftItems") {
        @Override
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem() {
            return ModItems.nylon;
        }
    };
}
