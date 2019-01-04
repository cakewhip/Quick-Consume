package com.kain24.quickconsume.proxy;

import com.kain24.quickconsume.event.PlayerCloneEventListener;
import com.kain24.quickconsume.event.PlayerDropsEventListener;
import com.kain24.quickconsume.event.PlayerJoinEventListener;
import com.kain24.quickconsume.event.PlayerTickEventListener;
import com.kain24.quickconsume.network.NetworkHandler;
import net.minecraftforge.common.MinecraftForge;

public class ServerProxy {
    public void preInit() {
        NetworkHandler.init();

        MinecraftForge.EVENT_BUS.register(PlayerJoinEventListener.class);
        MinecraftForge.EVENT_BUS.register(PlayerDropsEventListener.class);
        MinecraftForge.EVENT_BUS.register(PlayerCloneEventListener.class);
        MinecraftForge.EVENT_BUS.register(PlayerTickEventListener.class);
    }

    public void init() {
    }
}
