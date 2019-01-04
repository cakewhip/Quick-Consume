package com.kain24.quickconsume.network;

import com.kain24.quickconsume.QuickConsume;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class NetworkHandler {
    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(QuickConsume.MODID);

    private static int ID_COUNTER = 0;

    public static void init() {
        INSTANCE.registerMessage(FoodSlotSwapRequestHandler.class,
                FoodSlotSwapRequestMessage.class, ID_COUNTER++, Side.SERVER);
        INSTANCE.registerMessage(FoodSlotSyncHandler.class,
                FoodSlotSyncMessage.class, ID_COUNTER++, Side.CLIENT);
    }
}
