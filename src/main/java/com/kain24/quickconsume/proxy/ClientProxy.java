package com.kain24.quickconsume.proxy;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.input.Keyboard;

public class ClientProxy extends ServerProxy {
    public static KeyBinding consumeKey =
            new KeyBinding("key.quickconsume", Keyboard.KEY_G, "key.categories.quickconsume");

    public void preInit() {
        super.preInit();

        ClientRegistry.registerKeyBinding(consumeKey);
    }

    public void init() {
        super.init();
    }
}
