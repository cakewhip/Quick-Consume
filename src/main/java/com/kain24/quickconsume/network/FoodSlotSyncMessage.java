package com.kain24.quickconsume.network;

import com.kain24.quickconsume.FoodSlotUtil;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class FoodSlotSyncMessage implements IMessage {
    public ItemStack updatedItemStack;

    public FoodSlotSyncMessage(EntityPlayer p) {
        updatedItemStack = FoodSlotUtil.getFoodSlotItemStack(p);
    }

    public FoodSlotSyncMessage() {
    }

    @Override
    public void fromBytes(ByteBuf b) {
        updatedItemStack = ByteBufUtils.readItemStack(b);
    }

    @Override
    public void toBytes(ByteBuf b) {
        ByteBufUtils.writeItemStack(b, updatedItemStack);
    }
}
