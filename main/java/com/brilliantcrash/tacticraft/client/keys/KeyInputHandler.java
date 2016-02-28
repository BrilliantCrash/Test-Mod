package com.brilliantcrash.tacticraft.client.keys;

import com.brilliantcrash.tacticraft.BaseMod;
import com.brilliantcrash.tacticraft.items.guns.BaseGun;
import com.brilliantcrash.tacticraft.packets.ReloadMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

/**
 * Created by Evan on 2/21/2016.
 * Do all things that require specific key input here.
 * Use this class to call methods in other ones.
 */
public class KeyInputHandler {

    @SubscribeEvent
    public void onKeyInput (InputEvent.KeyInputEvent event) {
        if (KeyBinder.reload.isPressed()) {
            EntityPlayer player = Minecraft.getMinecraft().thePlayer;
            if (player.inventory.getCurrentItem() != null) {
                Item gunItem = player.inventory.getCurrentItem().getItem();
                if (gunItem instanceof BaseGun) {
                    BaseGun gun = (BaseGun) gunItem;
                    BaseMod.network.sendToServer(new ReloadMessage(player.getName(), Item.getIdFromItem(gun.ammoType)));
                }
            }
        }
    }

}
