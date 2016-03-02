package com.brilliantcrash.tacticraft.items;

import com.brilliantcrash.tacticraft.items.guns.GunGlock;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Noah on 2/12/2016.
 */
public class ModGuns {

    public static Item gunGlock;

    public static void createItems() {
        GameRegistry.registerItem(gunGlock = new GunGlock("gun_glock", 20, ModItems.ammo_ninemm, 40), "gun_glock");
    }



}
