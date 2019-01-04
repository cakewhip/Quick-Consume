package com.kain24.quickconsume.proxy;

import com.kain24.quickconsume.event.OverlayEventListener;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends ServerProxy {
    public void preInit() {
        super.preInit();

        MinecraftForge.EVENT_BUS.register(OverlayEventListener.class);
    }

    public void init() {
        super.init();
    }
}
