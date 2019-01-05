package com.kain24.quickconsume.event;

import com.kain24.quickconsume.FoodSlotUtil;
import com.kain24.quickconsume.QCConfig;
import com.kain24.quickconsume.QuickConsume;
import com.kain24.quickconsume.enchantment.EnchantmentAutoConsume;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = QuickConsume.MODID)
public class PlayerTickEventListener {
    @SubscribeEvent
    public static void playerTick(LivingEvent.LivingUpdateEvent e) {
        if(QCConfig.autoConsume.autoConsumeEnabled) {
            if(e.getEntityLiving() instanceof EntityPlayerMP) {
                EntityPlayerMP p = (EntityPlayerMP) e.getEntityLiving();

                if(p.ticksExisted % QCConfig.autoConsume.autoConsumeTickTimer == 0) {
                    ItemStack is = FoodSlotUtil.getFoodSlotItemStack(p);

                    if(!is.isEmpty()) {
                        if(QCConfig.autoConsume.optConsumption) {
                            ItemFood food = (ItemFood) is.getItem();

                            if((20 - p.getFoodStats().getFoodLevel()) >= food.getHealAmount(is)) {
                                FoodSlotUtil.requestConsume(p, false);
                            }
                        } else if(p.getFoodStats().needFood()) {
                            FoodSlotUtil.requestConsume(p, false);
                        }
                    }
                }
            }
        }
    }
}
