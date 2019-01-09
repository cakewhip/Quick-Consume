package com.kain24.quickconsume.enchantment;

import com.kain24.quickconsume.QCConfig;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;

public class EnchantmentAutoConsume extends Enchantment {
    public static final EnchantmentAutoConsume AUTO_CONSUME = new EnchantmentAutoConsume();

    protected EnchantmentAutoConsume() {
        super(Rarity.values()[QCConfig.autoConsume.enchantRarity], EnumEnchantmentType.ARMOR_HEAD,
                new EntityEquipmentSlot[] {EntityEquipmentSlot.HEAD});
        this.setName("autoconsume");
        this.setRegistryName("autoconsume");
    }


    @Override
    public int getMinEnchantability(int enchantmentLevel) {
        return 20; //TODO Add configurability
    }

    @Override
    public int getMaxEnchantability(int enchantmentLevel) {
        return 50; //TODO Add configurability
    }
}
