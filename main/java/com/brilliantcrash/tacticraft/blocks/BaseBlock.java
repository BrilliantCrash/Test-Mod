package com.brilliantcrash.tacticraft.blocks;

import com.brilliantcrash.tacticraft.ModCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

/**
 * Created by Noah on 2/9/2016.
 */
public class BaseBlock extends Block{
    
    public BaseBlock(String unlocalizedName, Material material, float hardness, float resistance) {
        super(material);
        this.setUnlocalizedName(unlocalizedName);
        this.setCreativeTab(ModCreativeTabs.tacticraft);
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
