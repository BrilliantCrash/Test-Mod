package com.brilliantcrash.tacticraft.items.guns;

import com.brilliantcrash.tacticraft.entities.EntityBullet;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Created by Noah on 2/24/2016.
 */
public class GunM4 extends BaseGun{

    public GunM4 (String unlocalizedName, int maxAmmo) {
        super(unlocalizedName, maxAmmo);
    }

    @Override
    public EntityBullet getBullet(World worldIn, EntityPlayer player, ItemStack stack) {
        return new EntityBullet(worldIn, player, 20.0F, 10.0F, 1);
    }
}
