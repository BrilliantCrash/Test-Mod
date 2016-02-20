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
        /* This is where we will register any entities and projectiles, just like the statment  below.
        We decided not to render the bullet because it was causing too much trouble, and you shouldn't really
        be able to see it in the first place.*/

        // Changing the texture to be nothing so it won't render.
        RenderingRegistry.registerEntityRenderingHandler(EntityBullet.class, new RenderSnowball<EntityBullet>(
            Minecraft.getMinecraft().getRenderManager(),
            ModItems.bullet,
            Minecraft.getMinecraft().getRenderItem()));
    }

}
