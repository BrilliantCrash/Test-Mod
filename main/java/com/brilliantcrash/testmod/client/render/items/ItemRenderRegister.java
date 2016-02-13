package com.brilliantcrash.testmod.client.render.items;

import com.brilliantcrash.testmod.BaseMod;
import com.brilliantcrash.testmod.items.ModItems;
import com.brilliantcrash.testmod.items.GunBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;

/**
 * Created by Noah on 2/12/2016.
 */
public final class ItemRenderRegister {

    public static String modid = BaseMod.MODID;

    public static void registerItemRenderer() {
        reg(GunBase.gunGlock);
        reg(ModItems.ammo_ninemm);
    }

    public static void reg(Item item) {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
                .register(item, 0, new ModelResourceLocation(modid + ":" + item.getUnlocalizedName().substring(5), "inventory"));
    }
}
