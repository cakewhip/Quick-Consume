package com.kain24.quickconsume;

import com.kain24.quickconsume.api.IConsumable;
import com.kain24.quickconsume.network.FoodSlotSyncMessage;
import com.kain24.quickconsume.network.NetworkHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

public class FoodSlotUtil {
    private static final String STORED_FOOD_NBT_KEY = "Stored Food";

    /**
     * Attempts to consume whatever is inside the passed player's food slot.
     * <p>
     * Server only!
     *
     * @param p                  the player
     * @param ignoreAlwaysEdible whether or not to follow the food's ignoreAlwaysEdible field
     */
    public static void requestConsume(EntityPlayerMP p, boolean ignoreAlwaysEdible) {
        ItemStack is = FoodSlotUtil.getFoodSlotItemStack(p);

        if(is.getItem() instanceof ItemFood && is.getCount() > 0) {
            if(ignoreAlwaysEdible || FoodSlotUtil.isFoodAlwaysEdible(is) || p.getFoodStats().needFood()) {
                is = is.getItem().onItemUseFinish(is, p.world, p);

                setFoodSlotItemStack(p, is);
                sync(p);
            }
        } else if(is.getItem() instanceof IConsumable) {
            IConsumable consumable = (IConsumable) is.getItem();

            if(consumable.canConsume(is, p)) {
                consumable.onConsume(is, p);
                sync(p);
            }
        }
    }

    /**
     * Synchronizes what's inside the passed player's food slot to the player's client.
     *
     * Server only!
     *
     * @param p the player to sync with
     */
    public static void sync(EntityPlayerMP p) {
        NetworkHandler.INSTANCE.sendTo(new FoodSlotSyncMessage(p), p);
    }

    /**
     * Gets what ItemStack is inside of the passed player's food slot.
     *
     * Server only!
     * To see what the player's food slot has on the client side, use
     * {@link com.kain24.quickconsume.client.ClientData#storedFoodItemStack}
     *
     * @param p the player
     * @return the ItemStack inside the player's food slot
     */
    public static ItemStack getFoodSlotItemStack(EntityPlayer p) {
        ItemStack stored = ItemStack.EMPTY;

        NBTTagCompound nbt = getModNBT(p);

        if(nbt.hasKey(STORED_FOOD_NBT_KEY)) {
            stored = new ItemStack(nbt.getCompoundTag(STORED_FOOD_NBT_KEY));
        }

        return stored;
    }

    /**
     * Sets what ItemStack is inside of the passed player's food slot.
     *
     * @param p the player
     * @param is the ItemStack to put in the player's food slot
     */
    public static void setFoodSlotItemStack(EntityPlayer p, ItemStack is) {
        NBTTagCompound nbt = new NBTTagCompound();

        is.writeToNBT(nbt);

        getModNBT(p).setTag(STORED_FOOD_NBT_KEY, nbt);
    }

    /**
     * Precondition!: the ItemStack contains an item of type ItemFood.
     *
     * @param is ItemStack with item of type ItemFood
     * @return whether the item within the ItemStack is always edible
     */
    public static boolean isFoodAlwaysEdible(ItemStack is) {
        ItemFood food = (ItemFood) is.getItem();

        return (boolean) ObfuscationReflectionHelper.getPrivateValue(ItemFood.class, food, "field_77852_bZ");
    }

    /**
     * Gets the mod's tag compound from the passed player.
     *
     * @param p the player
     * @return the mod's tag compound
     */
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
