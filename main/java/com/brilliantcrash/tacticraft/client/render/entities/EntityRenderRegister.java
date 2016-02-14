package com.brilliantcrash.tacticraft.client.render.entities;

import com.brilliantcrash.tacticraft.items.ModItems;
import com.brilliantcrash.tacticraft.items.projectiles.EntityBullet;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

/**
 * Created by Evan on 2/13/2016.
 */
public class EntityRenderRegister {

    public static void registerEntityRenderer () {
        RenderingRegistry.registerEntityRenderingHandler(EntityBullet.class, new IRenderFactory<EntityBullet>() {
            @Override
            public Render<? super EntityBullet> createRenderFor(RenderManager manager) {
                return new RenderSnowball<EntityBullet>(manager, ModItems.bullet, null);
            }
        });
    }

}
