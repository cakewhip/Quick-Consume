package com.kain24.quickconsume.client;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientData {
    /**
     * Used to display what is inside the player's food slot.
     * <p>
     * Client only!
     */
    public static ItemStack storedFoodItemStack = ItemStack.EMPTY;
}
