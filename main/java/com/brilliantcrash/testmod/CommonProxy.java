package com.brilliantcrash.testmod;

/**
 * Created by Noah on 2/9/2016.
 */
import com.brilliantcrash.testmod.blocks.ModBlocks;
import com.brilliantcrash.testmod.items.GunBase;
import com.brilliantcrash.testmod.items.ModItems;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent e) {
        ModBlocks.createBlocks();
        GunBase.createItems();
        ModItems.createItems();
    }

    public void init(FMLInitializationEvent e) {

    }

    public void postInit(FMLPostInitializationEvent e) {

    }
}