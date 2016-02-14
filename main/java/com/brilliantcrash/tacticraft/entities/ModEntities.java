package com.brilliantcrash.tacticraft.entities;

import com.brilliantcrash.tacticraft.BaseMod;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.registry.EntityRegistry;

/**
 * Created by Evan on 2/14/2016.
 */
public class ModEntities {

    public static void createEntities () {
        // Change 256 to a lower value if it lags; it is the tracking range.
        EntityRegistry.registerModEntity(EntityBullet.class, "bullet", 1, BaseMod.instance, 256, 10, true);
    }

}
