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
        GameRegistry.registerItem(gunGlock = new GunGlock("gun_glock", 20), "gun_glock");
        GameRegistry.registerItem(gunM4 = new GunM4("gun_m4", 30), "gun_m4");
    }



}
