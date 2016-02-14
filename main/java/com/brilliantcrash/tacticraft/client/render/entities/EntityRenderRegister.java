package com.brilliantcrash.tacticraft.client.render.entities;

import com.brilliantcrash.tacticraft.items.ModItems;
import com.brilliantcrash.tacticraft.entities.EntityBullet;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

/**
 * Created by Evan on 2/13/2016.
 */
public class EntityRenderRegister {

    public static void registerEntityRenderer () {
        /*RenderingRegistry.registerEntityRenderingHandler(EntityBullet.class, new IRenderFactory<EntityBullet>() {
            @Override //hi
            public Render<? super EntityBullet> createRenderFor(RenderManager manager) {
                return new RenderSnowball<EntityBullet>(manager, ModItems.bullet, Minecraft.getMinecraft().getRenderItem());
            }
        });*/ // <-- this is supposed to work but it doesn't so ¯\_(ツ)_/¯

        RenderingRegistry.registerEntityRenderingHandler(EntityBullet.class, new RenderSnowball<EntityBullet>(Minecraft.getMinecraft().getRenderManager(), ModItems.bullet, Minecraft.getMinecraft().getRenderItem()));
    }

}
