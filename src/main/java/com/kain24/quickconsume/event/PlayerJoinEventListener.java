package com.kain24.quickconsume.event;

import com.kain24.quickconsume.FoodSlotUtil;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class PlayerJoinEventListener {
    @SubscribeEvent
    public static void playerJoin(EntityJoinWorldEvent e) {
        if(!e.getEntity().getEntityWorld().isRemote) {
            if(e.getEntity() instanceof EntityPlayerMP) {
                EntityPlayerMP p = (EntityPlayerMP) e.getEntity();

                FoodSlotUtil.sync(p);
            }
        }
    }
}
