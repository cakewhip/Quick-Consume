package com.kain24.quickconsume.network;

import com.kain24.quickconsume.FoodSlotUtil;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ConsumeRequestHandler implements IMessageHandler<ConsumeRequestMessage, IMessage> {
    @Override
    public IMessage onMessage(ConsumeRequestMessage m, MessageContext ctx) {
        EntityPlayerMP p = ctx.getServerHandler().player;

        p.getServerWorld().addScheduledTask(() -> {
            ItemStack is = FoodSlotUtil.getFoodSlotItemStack(p);

            if(is.getItem() instanceof ItemFood && is.getCount() > 0) {
                if(m.ignoreAlwaysEdible || FoodSlotUtil.isFoodAlwaysEdible(is)) {
                    is = is.getItem().onItemUseFinish(is, p.world, p);

                    FoodSlotUtil.setFoodSlotItemStack(p, is);
                    FoodSlotUtil.sync(p);
                }
            }
        });

        return null;
    }
}
