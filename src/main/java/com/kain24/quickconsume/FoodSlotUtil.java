package com.kain24.quickconsume;

import com.kain24.quickconsume.network.FoodSlotSyncMessage;
import com.kain24.quickconsume.network.NetworkHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.lang.reflect.Field;

public class FoodSlotUtil {
    private static final String STORED_FOOD_NBT_KEY = "Stored Food";

    public static void requestConsume(EntityPlayerMP p, boolean ignoreAlwaysEdible) {
        ItemStack is = FoodSlotUtil.getFoodSlotItemStack(p);

        if(is.getItem() instanceof ItemFood && is.getCount() > 0) {
            if(ignoreAlwaysEdible || FoodSlotUtil.isFoodAlwaysEdible(is) || p.getFoodStats().needFood()) {
                is = is.getItem().onItemUseFinish(is, p.world, p);

                setFoodSlotItemStack(p, is);
                sync(p);
            }
        }
    }

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

    /**
     * Precondition!: the ItemStack contains an item of type ItemFood
     *
     * @param is ItemStack with item of type ItemFood
     * @return whether the item within the ItemStack is always edible
     */
    public static boolean isFoodAlwaysEdible(ItemStack is) {
        ItemFood food = (ItemFood) is.getItem();

        try {
            Field f = ItemFood.class.getDeclaredField("alwaysEdible");
            f.setAccessible(true);

            return (boolean) f.get(food);
        } catch(NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return false;
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
