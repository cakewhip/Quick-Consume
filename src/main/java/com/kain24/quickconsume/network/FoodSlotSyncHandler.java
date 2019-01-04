package com.kain24.quickconsume.network;

import com.kain24.quickconsume.client.ClientData;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class FoodSlotSyncHandler implements IMessageHandler<FoodSlotSyncMessage, IMessage> {
    @Override
    public IMessage onMessage(FoodSlotSyncMessage m, MessageContext ctx) {
        ClientData.storedFoodItemStack = m.updatedItemStack;

        return null;
    }
}
