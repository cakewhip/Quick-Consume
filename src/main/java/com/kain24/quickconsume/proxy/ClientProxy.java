package com.kain24.quickconsume.proxy;

import com.kain24.quickconsume.client.event.KeyEventListener;
import com.kain24.quickconsume.client.event.OverlayEventListener;
import com.kain24.quickconsume.client.event.RightClickEventListener;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.input.Keyboard;

public class ClientProxy extends ServerProxy {
    public static KeyBinding consumeKey =
            new KeyBinding("key.quickconsume", Keyboard.KEY_G, "key.categories.quickconsume");

    public void preInit() {
        super.preInit();

        MinecraftForge.EVENT_BUS.register(OverlayEventListener.class);
        MinecraftForge.EVENT_BUS.register(RightClickEventListener.class);
        MinecraftForge.EVENT_BUS.register(KeyEventListener.class);

        ClientRegistry.registerKeyBinding(consumeKey);
    }

    public void init() {
        super.init();
    }
}
