package com.brilliantcrash.tacticraft.items;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Noah on 2/12/2016.
 */
public class GunBase {

    public static Item gunGlock;

    public static void createItems() {
        GameRegistry.registerItem(gunGlock = new GunGlock("gun_glock", 20), "gun_glock");

    }

}
