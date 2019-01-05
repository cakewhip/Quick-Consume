package com.kain24.quickconsume.registry;

import com.kain24.quickconsume.QCConfig;
import com.kain24.quickconsume.QuickConsume;
import com.kain24.quickconsume.enchantment.EnchantmentAutoConsume;
import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = QuickConsume.MODID)
public class QCEnchantmentRegistry {
    @SubscribeEvent
    public static void registerEnchantments(RegistryEvent.Register<Enchantment> e) {
        if(QCConfig.autoConsume.autoConsumeEnabled && QCConfig.autoConsume.basedOnEnchant) {
            e.getRegistry().register(EnchantmentAutoConsume.AUTO_CONSUME);
        }
    }
}
