package com.brilliantcrash.tacticraft.packets;

import com.brilliantcrash.tacticraft.BaseMod;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;

/**
 * Created by Evan on 2/24/2016.
 */
public class NetworkRegister {

    public static void setupNetwork() {
        int packetID = -1;

        BaseMod.network = NetworkRegistry.INSTANCE.newSimpleChannel("TacticraftChannel");
        BaseMod.network.registerMessage(ReloadMessage.Handler.class, ReloadMessage.class, ++packetID, Side.SERVER);
        // Use the format shown in the line above to add any more packets.
    }

}
