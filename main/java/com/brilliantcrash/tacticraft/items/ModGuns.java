package com.brilliantcrash.tacticraft.items;

import com.brilliantcrash.tacticraft.items.guns.GunGlock;
import com.brilliantcrash.tacticraft.items.guns.GunM4;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Noah on 2/12/2016.
 */
public class ModGuns {

    public static Item gunGlock;
    public static Item gunM4;

    public static void createItems() {
        GameRegistry.registerItem(gunGlock = new GunGlock("gun_glock", 20, ModItems.ammo_ninemm, 40), "gun_glock");
        //GameRegistry.registerItem(gunP250 = new GunGlock("gun_p250", 17), "gun_p250");
    }



}
