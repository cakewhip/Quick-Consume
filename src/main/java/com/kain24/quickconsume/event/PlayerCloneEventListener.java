package com.kain24.quickconsume.event;

import com.kain24.quickconsume.FoodSlotUtil;
import com.kain24.quickconsume.QuickConsume;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = QuickConsume.MODID)
public class PlayerCloneEventListener {
    @SubscribeEvent
    public static void playerClone(PlayerEvent.Clone e) {
        EntityPlayer newP = e.getEntityPlayer();

        if(!newP.world.isRemote) {
            EntityPlayer ogP = e.getOriginal();

            if(ogP.getServer().getWorld(ogP.dimension).getGameRules().getBoolean("keepInventory")) {
                FoodSlotUtil.setFoodSlotItemStack(newP, FoodSlotUtil.getFoodSlotItemStack(ogP));
            }
        }
    }
}
