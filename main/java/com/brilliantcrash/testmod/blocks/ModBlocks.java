package com.brilliantcrash.testmod.blocks;

import net.minecraft.block.Block;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Noah on 2/9/2016.
 */
public class ModBlocks {

    public static Block testBlock;

    public static void createBlocks() {
        GameRegistry.registerBlock(testBlock = new BaseBlock("test_block"), "test_block");
    }
}
