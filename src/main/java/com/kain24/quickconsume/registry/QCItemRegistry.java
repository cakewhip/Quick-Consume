package com.kain24.quickconsume.registry;

import com.kain24.quickconsume.QuickConsume;
import com.kain24.quickconsume.item.ItemPotionBag;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod.EventBusSubscriber(modid = QuickConsume.MODID)
public class QCItemRegistry {
    @GameRegistry.ObjectHolder(QuickConsume.MODID + ":potion_bag")
    public static Item potionBag = new ItemPotionBag();

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> e) {
        e.getRegistry().register(potionBag);
    }
}
