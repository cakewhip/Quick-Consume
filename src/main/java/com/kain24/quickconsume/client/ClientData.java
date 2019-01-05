package com.kain24.quickconsume.client;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientData {
    public static ItemStack storedFoodItemStack = ItemStack.EMPTY;
}
