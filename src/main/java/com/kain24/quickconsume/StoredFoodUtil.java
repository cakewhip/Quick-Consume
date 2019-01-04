package com.kain24.quickconsume;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class StoredFoodUtil {
    private static final String STORED_FOOD_NBT_KEY = "Stored Food";

    public static ItemStack getStoredFoodItemStack(EntityPlayer p) {
        ItemStack stored = null;

        NBTTagCompound nbt = p.getEntityData().getCompoundTag(QuickConsume.MODID);

        if(nbt.hasKey(STORED_FOOD_NBT_KEY)) {
            stored = new ItemStack(nbt.getCompoundTag(STORED_FOOD_NBT_KEY));
        }

        return null;
    }

    public static void setStoredFoodItemStack(EntityPlayer p, ItemStack is) {
        NBTTagCompound nbt = new NBTTagCompound();

        is.writeToNBT(nbt);

        p.getEntityData().getCompoundTag(QuickConsume.MODID).setTag(STORED_FOOD_NBT_KEY, nbt);
    }
}
