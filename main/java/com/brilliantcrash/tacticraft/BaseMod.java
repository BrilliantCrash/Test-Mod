package com.brilliantcrash.tacticraft;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = BaseMod.MODID, name = BaseMod.MODNAME, version = BaseMod.VERSION)
public class BaseMod {

    public static final String MODID = "tactiCraft";
    public static final String MODNAME = "TactiCraft";
    public static final String VERSION = "0.0.2";

    @Instance
    public static BaseMod instance = new BaseMod();

    @EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        proxy.preInit(e);
    }

    @EventHandler
    public void init(FMLInitializationEvent e) {
        proxy.init(e);
        RecipeHandler.createRecipes();
    }


    @EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        proxy.postInit(e);

    }

    @SidedProxy(clientSide = "com.brilliantcrash.tacticraft.ClientProxy", serverSide = "com.brilliantcrash.tacticraft.ServerProxy")
    public static CommonProxy proxy;
}