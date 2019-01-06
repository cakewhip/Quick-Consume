package com.kain24.quickconsume.item;

import com.kain24.quickconsume.QCConfig;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class ItemPotionBag extends Item {
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
        ItemStack potionType = getPotionType(is);
        tt.add(I18n.format("item.potion_bag.info.type") + " " + (potionType == ItemStack.EMPTY ? "None" :
                potionType.getItem().getUnlocalizedName(is)));

        tt.add(I18n.format("item.potion_bag.info.stored") + " " + getAmountStored(is));
    }

    @Override
    public int getDamage(ItemStack is) {
        return getMax() - getAmountStored(is) + 1;
    }

    @Override
    public void onUpdate(ItemStack is, World w, Entity e, int slot, boolean selected) {
        is.setItemDamage(getDamage(is));
    }

    public static ItemStack getPotionType(ItemStack is) {
        if(is.getSubCompound(POTION_TYPE_NBT_KEY) != null) {
            return new ItemStack(is.getSubCompound(POTION_TYPE_NBT_KEY));
        } else {
            return ItemStack.EMPTY;
        }
    }

    public static void setPotionType(ItemStack is, ItemStack potion) {
        NBTTagCompound nbt = new NBTTagCompound();
        potion.writeToNBT(nbt);

        is.setTagInfo(POTION_TYPE_NBT_KEY, nbt);
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
}
