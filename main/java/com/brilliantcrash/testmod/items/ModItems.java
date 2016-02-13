package com.brilliantcrash.testmod.items;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Evan on 2/12/2016.
 */
public final class ModItems {

    public static Item ammo_ninemm;

    public static void createItems() {
        GameRegistry.registerItem(ammo_ninemm = new BaseAmmo("ammo_ninemm"), "ammo_ninemm");
    }

}
