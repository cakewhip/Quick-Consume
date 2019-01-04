package com.kain24.quickconsume.network;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class ConsumeRequestMessage implements IMessage {
    public boolean ignoreAlwaysEdible;

    public ConsumeRequestMessage(boolean ignoreAlwaysEdible) {
        this.ignoreAlwaysEdible = ignoreAlwaysEdible;
    }

    public ConsumeRequestMessage() {
    }

    @Override
    public void fromBytes(ByteBuf b) {
        ignoreAlwaysEdible = b.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf b) {
        b.writeBoolean(ignoreAlwaysEdible);
    }
}
