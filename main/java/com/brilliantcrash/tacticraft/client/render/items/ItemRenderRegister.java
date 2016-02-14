package com.brilliantcrash.tacticraft.client.render.items;

import com.brilliantcrash.tacticraft.BaseMod;
import com.brilliantcrash.tacticraft.items.ModItems;
import com.brilliantcrash.tacticraft.items.GunBase;
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
        reg(ModItems.nylon);
        reg(ModItems.ingotCopper);
        reg(ModItems.ingotLead);
        reg(ModItems.bullet);
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
                .register(item, meta, new ModelResourceLocation(modid + ":" + modelName, "inventory"));
    }

}
