package com.brilliantcrash.testmod.client.render.items;

import com.brilliantcrash.testmod.items.GunBase;
import com.brilliantcrash.testmod.items.GunGlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;

/**
 * Created by Noah on 2/12/2016.
 */
public final class ItemRenderRegister {

    public static void registerItemRenderer() {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
                .register(GunBase.gunGlock, 0, new ModelResourceLocation("modid:itemname", "inventory"));
    }
}
