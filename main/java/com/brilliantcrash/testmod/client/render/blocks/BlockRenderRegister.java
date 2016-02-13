package com.brilliantcrash.testmod.client.render.blocks;

import com.brilliantcrash.testmod.BaseMod;
import com.brilliantcrash.testmod.blocks.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;

/**
 * Created by Noah on 2/9/2016.
 */
public class BlockRenderRegister {

    public static String modid = BaseMod.MODID;

    public static void registerBlockRenderer() {
        reg(ModBlocks.testBlock);
        reg(ModBlocks.secondBlock);
        reg(ModBlocks.oreCopper);
        reg(ModBlocks.oreLead);
    }

    public static void reg(Block block) {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
                .register(Item.getItemFromBlock(block), 0, new ModelResourceLocation(modid + ":" + block.getUnlocalizedName().substring(5), "inventory"));
    }
}