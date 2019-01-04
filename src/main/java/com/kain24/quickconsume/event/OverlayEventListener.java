package com.kain24.quickconsume.event;

import com.kain24.quickconsume.client.gui.GuiFoodSlot;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class OverlayEventListener {
    @SubscribeEvent
    public static void renderHotbar(RenderGameOverlayEvent.Post e) {
        if(e.getType() == RenderGameOverlayEvent.ElementType.HOTBAR) {
            GuiFoodSlot.drawSlot(e.getResolution());
        }
    }
}
