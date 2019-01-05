package com.kain24.quickconsume.proxy;

import com.kain24.quickconsume.network.NetworkHandler;

public class ServerProxy {
    public void preInit() {
        NetworkHandler.init();
    }

    public void init() {
    }
}
