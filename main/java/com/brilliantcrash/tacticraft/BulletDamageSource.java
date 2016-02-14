package com.brilliantcrash.tacticraft;

import com.brilliantcrash.tacticraft.items.GunBase;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;

/**
 * Created by Evan on 2/13/2016.
 */
public class BulletDamageSource extends DamageSource {

    EntityLivingBase player;
    ItemStack weapon;

    public BulletDamageSource(String damageType, EntityLivingBase player, ItemStack weapon) {
        super(damageType);
        this.player = player;
        this.weapon = weapon;
    }

    @Override
    public IChatComponent getDeathMessage (EntityLivingBase deadPlayer) {
        ItemStack itemstack = this.player instanceof EntityLivingBase ? (this.player).getHeldItem() : null;
        String s = "death.attack." + this.damageType;
        String s1 = s + ".item";
        return itemstack != null && itemstack.hasDisplayName() ?
                new ChatComponentTranslation(s1, new Object[] {deadPlayer.getDisplayName(), this.player.getDisplayName(), itemstack.getChatComponent()})
                : new ChatComponentTranslation(s, new Object[] {deadPlayer.getDisplayName(), this.player.getDisplayName()});

    }



}
