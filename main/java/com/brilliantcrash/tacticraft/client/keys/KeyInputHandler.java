package com.brilliantcrash.tacticraft.client.keys;

import com.brilliantcrash.tacticraft.items.guns.BaseGun;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
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
            if (Minecraft.getMinecraft().thePlayer.inventory.getCurrentItem().getItem() instanceof BaseGun) {
                // TODO put stuff here
            }
        }
    }

}
