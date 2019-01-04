package com.kain24.quickconsume.event;

import com.kain24.quickconsume.StoredFoodUtil;
import com.kain24.quickconsume.network.FoodSlotSyncMessage;
import com.kain24.quickconsume.network.NetworkHandler;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class PlayerDropsEventListener {
    @SubscribeEvent
    public static void playerDrops(PlayerDropsEvent e) {
        EntityPlayer p = e.getEntityPlayer();

        if(!p.world.isRemote) {
            ItemStack is = StoredFoodUtil.getStoredFoodItemStack(p);

            if(!is.isEmpty()) {
                e.getDrops().add(new EntityItem(p.world, p.posX, p.posY, p.posZ, is));

                StoredFoodUtil.setStoredFoodItemStack(p, ItemStack.EMPTY);
                NetworkHandler.INSTANCE.sendTo(new FoodSlotSyncMessage(p), (EntityPlayerMP) p);
            }
        }
    }
}
