package com.kain24.quickconsume.api;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface IConsumable {
    /**
     * Checks to see if the item stack can be consumed by the player.
     * <p>
     * Only called on the logical-server side.
     *
     * @param is the item stack to check for consume-ability
     * @param p  the player that's trying to consume the item stack
     * @return true if the player can consume the item stack
     */
    boolean canConsume(ItemStack is, EntityPlayer p);

    /**
     * Used to apply any effects to the player after they consume the item stack.
     * This is also where you'd shrink the item stack.
     *
     * Only called on the logical-server side.
     *
     * @param is
     * @param p
     */
    void onConsume(ItemStack is, EntityPlayer p);
}
