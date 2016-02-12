package com.brilliantcrash.testmod;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = BaseMod.MODID, name = BaseMod.MODNAME, version = BaseMod.VERSION)
public class BaseMod {

    public static final String MODID = "testMod";
    public static final String MODNAME = "Test Mod";
    public static final String VERSION = "0.0.1";

    @Instance
    public static BaseMod instance = new BaseMod();

    @EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        proxy.preInit(e);
    }

    @EventHandler
    public void init(FMLInitializationEvent e) {
        proxy.init(e);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        proxy.postInit(e);

    }

    @SidedProxy(clientSide = "com.brilliantcrash.testmod.ClientProxy", serverSide = "com.brilliantcrash.testmod.ServerProxy")
    public static CommonProxy proxy;
}