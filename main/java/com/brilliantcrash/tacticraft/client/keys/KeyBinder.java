package com.brilliantcrash.tacticraft.client.keys;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;

/**
 * Created by Evan on 2/21/2016.
 */
public class KeyBinder {

    public static KeyBinding reload;

    public static void init () {
        // Find keycodes at http://minecraft.gamepedia.com/Key_codes
        reload = new KeyBinding("key.reload", 19, "key.categories.tacticraft"); // 19 is the code for the "R" key
        ClientRegistry.registerKeyBinding(reload);
    }

}
