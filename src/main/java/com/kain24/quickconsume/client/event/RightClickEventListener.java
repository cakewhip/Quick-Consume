package com.kain24.quickconsume.client.event;

import com.kain24.quickconsume.QuickConsume;
import com.kain24.quickconsume.network.FoodSlotSwapRequestMessage;
import com.kain24.quickconsume.network.NetworkHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(value = Side.CLIENT, modid = QuickConsume.MODID)
public class RightClickEventListener {
    @SubscribeEvent
    public static void onClick(MouseEvent e) {
        if(e.getButton() == 1 && e.isButtonstate()) {
            EntityPlayer p = Minecraft.getMinecraft().player;

            if(p.isSneaking()) {
                NetworkHandler.INSTANCE.sendToServer(new FoodSlotSwapRequestMessage());
            }
        }
    }
}
