package com.kain24.quickconsume.network;

import com.kain24.quickconsume.StoredFoodUtil;
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
            ItemStack is = StoredFoodUtil.getStoredFoodItemStack(p);

            if(is.getItem() instanceof ItemFood && is.getCount() > 0) {
                is = is.getItem().onItemUseFinish(is, p.world, p);

                StoredFoodUtil.setStoredFoodItemStack(p, is);
                NetworkHandler.INSTANCE.sendTo(new FoodSlotSyncMessage(p), p);
            }
        });

        return null;
    }
}
