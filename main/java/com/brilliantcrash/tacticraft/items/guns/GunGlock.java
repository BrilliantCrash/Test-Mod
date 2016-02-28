package com.brilliantcrash.tacticraft.items.guns;

import com.brilliantcrash.tacticraft.entities.EntityBullet;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Created by Evan on 2/21/2016.
 */
public class GunGlock extends BaseGun {

    public GunGlock (String unlocalizedName, int maxAmmo, Item ammoType, int reloadTime) {
        super(unlocalizedName, maxAmmo, ammoType, reloadTime);
    }

    @Override
    public EntityBullet getBullet(World worldIn, EntityPlayer player, ItemStack stack) {
        return new EntityBullet(worldIn, player, 15.0F, 15.0F, 0);
    }
}
