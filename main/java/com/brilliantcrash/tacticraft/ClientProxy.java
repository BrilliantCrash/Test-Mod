package com.brilliantcrash.tacticraft;

import com.brilliantcrash.tacticraft.client.keys.KeyBinder;
import com.brilliantcrash.tacticraft.client.keys.KeyInputHandler;
import com.brilliantcrash.tacticraft.client.render.blocks.BlockRenderRegister;
import com.brilliantcrash.tacticraft.client.render.entities.EntityRenderRegister;
import com.brilliantcrash.tacticraft.client.render.items.ItemRenderRegister;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * Created by Noah on 2/9/2016.
 */
public class ClientProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent e) {
        super.preInit(e);
        MinecraftForge.EVENT_BUS.register(new KeyInputHandler());
    }

    @Override
    public void init(FMLInitializationEvent e) {
        super.init(e);

        BlockRenderRegister.registerBlockRenderer();
        ItemRenderRegister.registerItemRenderer();
        EntityRenderRegister.registerEntityRenderer();

        KeyBinder.init();
    }

    @Override
    public void postInit(FMLPostInitializationEvent e) {
        super.postInit(e);
    }
}