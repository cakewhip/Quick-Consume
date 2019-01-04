package com.kain24.quickconsume;

import com.kain24.quickconsume.network.FoodSlotSyncMessage;
import com.kain24.quickconsume.network.NetworkHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class FoodSlotUtil {
    private static final String STORED_FOOD_NBT_KEY = "Stored Food";

    public static void sync(EntityPlayerMP p) {
        NetworkHandler.INSTANCE.sendTo(new FoodSlotSyncMessage(p), p);
    }

    public static ItemStack getFoodSlotItemStack(EntityPlayer p) {
        ItemStack stored = ItemStack.EMPTY;

        NBTTagCompound nbt = getModNBT(p);

        if(nbt.hasKey(STORED_FOOD_NBT_KEY)) {
            stored = new ItemStack(nbt.getCompoundTag(STORED_FOOD_NBT_KEY));
        }

        return stored;
    }

    public static void setFoodSlotItemStack(EntityPlayer p, ItemStack is) {
        NBTTagCompound nbt = new NBTTagCompound();

        is.writeToNBT(nbt);

        getModNBT(p).setTag(STORED_FOOD_NBT_KEY, nbt);
    }

    private static NBTTagCompound getModNBT(EntityPlayer p) {
        NBTTagCompound pNbt = p.getEntityData();

        if(pNbt.hasKey(QuickConsume.MODID)) {
            return pNbt.getCompoundTag(QuickConsume.MODID);
        } else {
            NBTTagCompound nbt = new NBTTagCompound();

            pNbt.setTag(QuickConsume.MODID, nbt);

            return nbt;
        }
    }
}
