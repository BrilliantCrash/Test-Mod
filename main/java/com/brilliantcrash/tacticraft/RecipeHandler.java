package com.brilliantcrash.tacticraft;

import com.brilliantcrash.tacticraft.blocks.ModBlocks;
import com.brilliantcrash.tacticraft.items.ModGuns;
import com.brilliantcrash.tacticraft.items.ModItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Evan on 2/13/2016.
 */
public class RecipeHandler {

    public static void createRecipes() {
        GameRegistry.addSmelting(Blocks.coal_block, new ItemStack(ModItems.nylon, 3), 1F);
        GameRegistry.addSmelting(ModBlocks.oreCopper, new ItemStack(ModItems.ingotCopper, 1), 1F);
        GameRegistry.addSmelting(ModBlocks.oreLead, new ItemStack(ModItems.ingotLead, 1), 1F);

        GameRegistry.addRecipe(new ItemStack(ModGuns.gunGlock),
                "AAA",
                " BA",
                "  A",
                'A', ModItems.nylon,
                'B', Items.iron_ingot
        );

        GameRegistry.addRecipe(new ItemStack(ModItems.ammo_ninemm, 10),
                " A ",
                "BCB",
                "BCB",
                'A', ModItems.ingotCopper,
                'B', Items.iron_ingot,
                'C', ModItems.ingotLead
        );

        GameRegistry.addRecipe(new ItemStack(ModItems.knife, 1),
                " A ",
                " B ",
                "   ",
                'A', Items.iron_ingot,
                'B', Items.stick
        );

        GameRegistry.addRecipe(new ItemStack(ModItems.knife, 1),
                "   ",
                " A ",
                " B ",
                'A', Items.iron_ingot,
                'B', Items.stick
        );

    }

}
