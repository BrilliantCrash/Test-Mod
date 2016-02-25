package com.brilliantcrash.tacticraft.packets;

import com.brilliantcrash.tacticraft.items.ModItems;
import com.brilliantcrash.tacticraft.items.guns.BaseGun;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IThreadListener;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.MinecraftDummyContainer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * Created by Evan on 2/24/2016.
 */
public class ReloadMessage implements IMessage {

    protected String playerName;
    protected int ammoType;

    public ReloadMessage () {}

    public ReloadMessage (String playerName, int ammoType) {
        this.playerName = playerName;
        this.ammoType = ammoType;
    }

    public ReloadMessage (EntityPlayer player, int ammoType) {
        this.playerName = player.getName();
        this.ammoType = ammoType;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        // ! The order of these MUST be kept in sync with the order of the calls in toBytes().
        playerName = ByteBufUtils.readUTF8String(buf);
        ammoType = ByteBufUtils.readVarInt(buf, 4);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        // ! The order of these MUST be kept in sync with the order of the calls in fromBytes().
        ByteBufUtils.writeUTF8String(buf, playerName);
        ByteBufUtils.writeVarInt(buf, ammoType, 4);
    }

    public static class Handler implements IMessageHandler<ReloadMessage, IMessage> {

        EntityPlayer entPlayer;
        int ammoType;
        ItemStack gun;
        NBTTagCompound gunTc;

        @Override
        public IMessage onMessage(final ReloadMessage message, final MessageContext ctx) {
            IThreadListener mainThread = (WorldServer) ctx.getServerHandler().playerEntity.worldObj;
            mainThread.addScheduledTask(new Runnable() {
                @Override
                public void run() {
                    entPlayer = ctx.getServerHandler().playerEntity.worldObj.getPlayerEntityByName(message.playerName);
                    ammoType = message.ammoType;
                    gun = entPlayer.getHeldItem();
                    gunTc = gun.getTagCompound();

                    if (entPlayer.inventory.hasItem(Item.getItemById(ammoType))) {
                        entPlayer.inventory.consumeInventoryItem(Item.getItemById(ammoType));
                        gunTc.setInteger("ammo", ((BaseGun) gun.getItem()).maxAmmo);
                    }
                }
            });
            return null;
        }
    }
}
