package com.kain24.quickconsume.proxy;

import com.kain24.quickconsume.event.PlayerJoinEventListener;
import com.kain24.quickconsume.network.NetworkHandler;
import net.minecraftforge.common.MinecraftForge;

public class ServerProxy {
    public void preInit() {
        NetworkHandler.init();

        MinecraftForge.EVENT_BUS.register(PlayerJoinEventListener.class);
    }

    public void init() {
    }
}
