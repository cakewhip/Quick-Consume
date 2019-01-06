package com.kain24.quickconsume.client.event.registry;

import com.kain24.quickconsume.QuickConsume;
import com.kain24.quickconsume.registry.QCItemRegistry;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(value = Side.CLIENT, modid = QuickConsume.MODID)
public class QCModelRegistry {
    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent e) {
        ModelLoader.setCustomModelResourceLocation(QCItemRegistry.potionBag, 0,
                new ModelResourceLocation(QCItemRegistry.potionBag.getRegistryName(), "inventory"));
    }
}
