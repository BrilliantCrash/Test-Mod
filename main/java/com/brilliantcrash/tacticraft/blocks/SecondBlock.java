package com.brilliantcrash.tacticraft.blocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

/**
 * Created by Evan on 2/12/2016.
 */
public class SecondBlock extends BaseBlock {

    public SecondBlock (String unlocalizedName) {
        super (unlocalizedName, 2.0f, 10.0f);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
        System.out.println("Broke second_block");
        explode(worldIn, pos);
        return true;
    }

    public void explode(World worldIn, BlockPos pos) {
            worldIn.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), 2F, true);
    }


}
