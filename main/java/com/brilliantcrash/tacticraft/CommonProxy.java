package com.brilliantcrash.tacticraft;

/**
 * Created by Noah on 2/9/2016.
 */
import com.brilliantcrash.tacticraft.blocks.ModBlocks;
import com.brilliantcrash.tacticraft.blocks.ModOreGen;
import com.brilliantcrash.tacticraft.entities.ModEntities;
import com.brilliantcrash.tacticraft.items.ModGuns;
import com.brilliantcrash.tacticraft.items.ModItems;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent e) {
        ModBlocks.createBlocks();
        ModGuns.createItems();
        ModItems.createItems();
        ModEntities.createEntities();
    }

    public void init(FMLInitializationEvent e) {

        GameRegistry.registerWorldGenerator(new ModOreGen(), 0);

    }

    public void postInit(FMLPostInitializationEvent e) {

    }
}