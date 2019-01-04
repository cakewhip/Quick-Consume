package com.kain24.quickconsume.network;

import com.kain24.quickconsume.FoodSlotUtil;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ConsumeRequestHandler implements IMessageHandler<ConsumeRequestMessage, IMessage> {
    @Override
    public IMessage onMessage(ConsumeRequestMessage m, MessageContext ctx) {
        EntityPlayerMP p = ctx.getServerHandler().player;

        p.getServerWorld().addScheduledTask(() -> {
            FoodSlotUtil.requestConsume(p, m.ignoreAlwaysEdible);
        });

        return null;
    }
}
