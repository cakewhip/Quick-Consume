package com.kain24.quickconsume.client.event;

import com.kain24.quickconsume.QCConfig;
import com.kain24.quickconsume.QuickConsume;
import com.kain24.quickconsume.network.ConsumeRequestMessage;
import com.kain24.quickconsume.network.NetworkHandler;
import com.kain24.quickconsume.proxy.ClientProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(value = Side.CLIENT, modid = QuickConsume.MODID)
public class KeyEventListener {
    @SubscribeEvent
    public static void onKeyInput(InputEvent.KeyInputEvent e) {
        if(ClientProxy.consumeKey.isPressed()) {
            NetworkHandler.INSTANCE.sendToServer(new ConsumeRequestMessage(QCConfig.ignoreAlwaysEdible));
        }
    }
}
