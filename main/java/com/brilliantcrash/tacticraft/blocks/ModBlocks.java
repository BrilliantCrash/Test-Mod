package com.brilliantcrash.tacticraft.blocks;

import net.minecraft.block.Block;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Noah on 2/9/2016.
 */
public class ModBlocks {

    public static Block testBlock;
    public static Block secondBlock;
    public static Block oreCopper;
    public static Block oreLead;

    public static void createBlocks() {
        GameRegistry.registerBlock(testBlock = new BaseBlock("test_block"), "test_block");
        GameRegistry.registerBlock(secondBlock = new SecondBlock("second_block"), "second_block");
        GameRegistry.registerBlock(oreCopper = new CopperOre("ore_copper"), "ore_copper");
        GameRegistry.registerBlock(oreLead = new LeadOre("ore_lead"), "ore_lead");
    }
}
