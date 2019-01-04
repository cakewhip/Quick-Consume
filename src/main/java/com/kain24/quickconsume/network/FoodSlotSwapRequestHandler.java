package com.kain24.quickconsume.network;

import com.kain24.quickconsume.StoredFoodUtil;
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
            ItemStack stored = StoredFoodUtil.getStoredFoodItemStack(p).copy();

            System.out.println(held.getItem().getUnlocalizedName());

            if(held.getItem() instanceof ItemFood || held.getItem() == ItemStack.EMPTY.getItem()) {
                StoredFoodUtil.setStoredFoodItemStack(p, held);
                p.inventory.setInventorySlotContents(p.inventory.currentItem, stored);

                NetworkHandler.INSTANCE.sendTo(new FoodSlotSyncMessage(p), p);
            }
        });

        return null;
    }
}
