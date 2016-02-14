/**
package com.brilliantcrash.tacticraft.tileentity;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.registry.GameRegistry;
public final class ModTileEntity extends TileEntity {

    public static void init() {
        GameRegistry.registerTileEntity(ModTileEntity.class, "alloy_furnace_te");
    }

    //@Override
    public void update() {
        if(!this.worldObj.isRemote)
            System.out.println("TC: Tile entity created.");
    }
} **/
