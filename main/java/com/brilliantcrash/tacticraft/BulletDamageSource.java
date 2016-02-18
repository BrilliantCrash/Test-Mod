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
        ItemStack itemstack = null;
        if (this.player != null) {
            itemstack = this.player instanceof EntityLivingBase ? (this.player).getHeldItem() : null;
        }
        String s = "death.attack." + this.damageType;
        String s1 = s + ".item";
        if (itemstack != null && itemstack.hasDisplayName() && this.player != null)
            return new ChatComponentTranslation(s1, new Object[] {deadPlayer.getDisplayName(), this.player.getDisplayName(), itemstack.getChatComponent()});
        else if (this.player != null) {
            return new ChatComponentTranslation(s + ".player", new Object[] {deadPlayer.getDisplayName(), this.player.getDisplayName()});
        } else {
            return new ChatComponentTranslation(s, new Object[] {deadPlayer.getDisplayName()});
        }

    }



}
