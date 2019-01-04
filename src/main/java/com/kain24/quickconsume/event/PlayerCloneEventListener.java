package com.kain24.quickconsume.event;

import com.kain24.quickconsume.StoredFoodUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class PlayerCloneEventListener {
    @SubscribeEvent
    public static void playerClone(PlayerEvent.Clone e) {
        EntityPlayer newP = e.getEntityPlayer();

        if(!newP.world.isRemote) {
            EntityPlayer ogP = e.getOriginal();

            if(ogP.getServer().getWorld(ogP.dimension).getGameRules().getBoolean("keepInventory")) {
                StoredFoodUtil.setStoredFoodItemStack(newP, StoredFoodUtil.getStoredFoodItemStack(ogP));
            }
        }
    }
}
