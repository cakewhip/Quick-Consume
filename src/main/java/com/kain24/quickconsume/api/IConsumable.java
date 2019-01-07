package com.kain24.quickconsume.api;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface IConsumable {
    boolean canConsume(ItemStack is, EntityPlayer p);

    void onConsume(ItemStack is, EntityPlayer p);
}
