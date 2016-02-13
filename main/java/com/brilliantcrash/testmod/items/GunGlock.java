package com.brilliantcrash.testmod.items;

import com.brilliantcrash.testmod.ModCreativeTabs;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import java.util.List;

/**
 * Created by Noah on 2/12/2016.
 */
public class GunGlock extends Item {


    public GunGlock(String unlocalizedName) {
        super();
        this.setHasSubtypes(true);
        this.setUnlocalizedName(unlocalizedName);
        this.setCreativeTab(ModCreativeTabs.testMod);
    }

    @Override
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
    }

    @Override
    public void getSubItems(Item itemIn, CreativeTabs tab, List subItems) {
        for (int i=0; i<20; i++){
            subItems.add(new ItemStack(itemIn, 1, i));
        }
    }
}
