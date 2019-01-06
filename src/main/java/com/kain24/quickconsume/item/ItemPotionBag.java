package com.kain24.quickconsume.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemPotionBag extends Item {
    public ItemPotionBag() {
        setRegistryName("potion_bag");
        setUnlocalizedName("potion_bag");
        setMaxStackSize(1);
        setCreativeTab(CreativeTabs.BREWING);
    }
}
