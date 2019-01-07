package com.kain24.quickconsume.item;

import com.kain24.quickconsume.QCConfig;
import com.kain24.quickconsume.api.IConsumable;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class ItemPotionBag extends Item implements IConsumable {
    public ItemPotionBag() {
        setRegistryName("potion_bag");
        setUnlocalizedName("potion_bag");
        setMaxStackSize(1);
        setMaxDamage(getMax());
        setCreativeTab(CreativeTabs.BREWING);
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if(this.isInCreativeTab(tab)) {
            ItemStack is = new ItemStack(this, 1);
            setAmountStored(is, 0);
            is.setItemDamage(getDamage(is));
            items.add(is);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack is, @Nullable World w, List<String> tt, ITooltipFlag f) {
        PotionType potion = getPotionType(is);

        if(potion != null) {
            ItemStack pStack = new ItemStack(Items.POTIONITEM);
            pStack = PotionUtils.addPotionToItemStack(pStack, potion);

            PotionUtils.addPotionTooltip(pStack, tt, 1.0F);

            tt.add(I18n.format("item.potion_bag.info.stored") + " " + getAmountStored(is));
        } else {
            tt.add("None");
        }
    }

    @Override
    public int getDamage(ItemStack is) {
        return getMax() - getAmountStored(is) + 1;
    }

    @Override
    public void onUpdate(ItemStack is, World w, Entity e, int slot, boolean selected) {
        is.setItemDamage(getDamage(is));
    }

    public static PotionType getPotionType(ItemStack is) {
        if(is.getTagCompound() != null) {
            if(is.getTagCompound().hasKey(POTION_TYPE_NBT_KEY)) {
                String type = is.getTagCompound().getString(POTION_TYPE_NBT_KEY);
                return type.isEmpty() ? null : PotionType.getPotionTypeForName(type);
            }
        }

        return null;
    }

    public static void setPotionType(ItemStack is, ItemStack potion) {
        PotionType type = PotionUtils.getPotionFromItem(potion);

        is.getTagCompound().setString(POTION_TYPE_NBT_KEY, type.getRegistryName().toString());
    }

    public static int getAmountStored(ItemStack is) {
        if(is.getTagCompound() == null) {
            return 0;
        }

        return is.getTagCompound().getInteger(AMOUNT_STORED_NBT_KEY);
    }

    public static void setAmountStored(ItemStack is, int amt) {
        if(is.getTagCompound() == null) {
            is.setTagCompound(new NBTTagCompound());
        }

        is.getTagCompound().setInteger(AMOUNT_STORED_NBT_KEY, amt);
    }

    public static final int getMax() {
        return QCConfig.potionBag.maxStored;
    }

    private static final String POTION_TYPE_NBT_KEY = "Potion Type";
    private static final String AMOUNT_STORED_NBT_KEY = "Potions Stored";

    @Override
    public boolean canConsume(ItemStack is, EntityPlayer p) {
        return true;
    }

    @Override
    public void onConsume(ItemStack is, EntityPlayer p) {
        if(!p.world.isRemote) {
            for(PotionEffect effect : getPotionType(is).getEffects()) {
                if(effect.getPotion().isInstant()) {
                    effect.getPotion().affectEntity(p, p, p, effect.getAmplifier(), 1.0D);
                } else {
                    p.addPotionEffect(new PotionEffect(effect));
                }
            }
        }
    }
}
