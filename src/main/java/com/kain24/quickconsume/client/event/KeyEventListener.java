package com.kain24.quickconsume.client.event;

import com.kain24.quickconsume.QCConfig;
import com.kain24.quickconsume.network.ConsumeRequestMessage;
import com.kain24.quickconsume.network.NetworkHandler;
import com.kain24.quickconsume.proxy.ClientProxy;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

public class KeyEventListener {
    @SubscribeEvent
    public static void onKeyInput(InputEvent.KeyInputEvent e) {
        if(ClientProxy.consumeKey.isPressed()) {
            NetworkHandler.INSTANCE.sendToServer(new ConsumeRequestMessage(QCConfig.ignoreAlwaysEdible));
        }
    }
}
