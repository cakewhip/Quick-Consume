package com.kain24.quickconsume.client.event;

import com.kain24.quickconsume.QuickConsume;
import com.kain24.quickconsume.client.gui.GuiFoodSlot;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(value = Side.CLIENT, modid = QuickConsume.MODID)
public class OverlayEventListener {
    @SubscribeEvent
    public static void renderHotbar(RenderGameOverlayEvent.Post e) {
        if(e.getType() == RenderGameOverlayEvent.ElementType.HOTBAR) {
            GuiFoodSlot.drawSlot(e.getResolution());
        }
    }
}
