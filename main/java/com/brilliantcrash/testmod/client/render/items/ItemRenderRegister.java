package com.brilliantcrash.testmod.client.render.items;

import com.brilliantcrash.testmod.BaseMod;
import com.brilliantcrash.testmod.items.ModItems;
import com.brilliantcrash.testmod.items.GunBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelBakery;
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
        reg(item, 0, item.getUnlocalizedName().substring(5));
    }

    public static void reg(Item item, int meta) {
        reg(item, meta, item.getUnlocalizedName().substring(5));
    }

    public static void reg(Item item, String modelName) {
        reg(item, 0, item.getUnlocalizedName().substring(5));
    }

    public static void reg (Item item, int meta, String modelName) {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
<<<<<<< HEAD
                .register(GunBase.gunGlock, 0, new ModelResourceLocation("modid:itemname", "inventory"));
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
                .register(GunBase.gunGlock, GunGlock.ammoCount, "meta_item");
=======
                .register(item, meta, new ModelResourceLocation(modid + ":" + modelName, "inventory"));
>>>>>>> c6ef11fb64eb628f2aaa492e764a12a17a8e9e35
    }

}
