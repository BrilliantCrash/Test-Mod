package com.brilliantcrash.testmod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

/**
 * Created by Noah on 2/9/2016.
 */
public final class BaseBlock extends Block{
    
    public BaseBlock(String unlocalizedName, Material material, float hardness, float resistance) {
        super(material);
        this.setUnlocalizedName(unlocalizedName);
        //this.setCreativeTab(CreativeTabs.tabBlock);
        this.setHardness(hardness);
        this.setResistance(resistance);
        this.setHarvestLevel("pickaxe", 2);
    }
    
    public BaseBlock(String unlocalizedName, float hardness, float resistance) {
        this(unlocalizedName, Material.rock, hardness, resistance);
    }
    
    public BaseBlock(String unlocalizedName) {
        this(unlocalizedName, 2.0f, 10.0f);
    }

}
