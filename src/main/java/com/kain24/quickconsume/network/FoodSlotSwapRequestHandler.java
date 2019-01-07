package com.kain24.quickconsume.network;

import com.kain24.quickconsume.FoodSlotUtil;
import com.kain24.quickconsume.api.IConsumable;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class FoodSlotSwapRequestHandler implements IMessageHandler<FoodSlotSwapRequestMessage, IMessage> {
    @Override
    public IMessage onMessage(FoodSlotSwapRequestMessage m, MessageContext ctx) {
        EntityPlayerMP p = ctx.getServerHandler().player;

        p.getServerWorld().addScheduledTask(() -> {
            ItemStack held = p.inventory.getCurrentItem().copy();
            ItemStack stored = FoodSlotUtil.getFoodSlotItemStack(p).copy();

            if(held.getItem() instanceof ItemFood || held.getItem() instanceof IConsumable ||
                    held.getItem() == ItemStack.EMPTY.getItem()) {
                FoodSlotUtil.setFoodSlotItemStack(p, held);
                p.inventory.setInventorySlotContents(p.inventory.currentItem, stored);

                FoodSlotUtil.sync(p);
            }
        });

        return null;
    }
}
