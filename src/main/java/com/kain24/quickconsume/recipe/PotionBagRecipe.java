package com.kain24.quickconsume.recipe;

import com.google.gson.JsonObject;
import com.kain24.quickconsume.QCConfig;
import com.kain24.quickconsume.item.ItemPotionBag;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemLingeringPotion;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemSplashPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.crafting.IRecipeFactory;
import net.minecraftforge.common.crafting.JsonContext;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class PotionBagRecipe
        extends IForgeRegistryEntry.Impl<IRecipe>
        implements IRecipe {
    @Override
    public boolean matches(InventoryCrafting inv, World worldIn) {
        ItemStack bag = null;
        ItemStack potion = null;
        PotionType type = null;
        int amt = 0;

        for(int i = 0; i < inv.getSizeInventory(); i++) {
            ItemStack is = inv.getStackInSlot(i);

            if(is.getItem() instanceof ItemPotionBag) {
                if(bag != null) {
                    return false; //2 bags in grid = NO
                } else {
                    bag = is;
                }
            } else if(is.getItem() instanceof ItemPotion) {
                if(is.getItem() instanceof ItemSplashPotion ||
                        is.getItem() instanceof ItemLingeringPotion) {
                    return false; //Lingering/Splash potions = NO
                }

                if(potion != null) {
                    if(PotionUtils.getPotionFromItem(is).equals(type)) {
                        amt++;
                    } else {
                        return false; //2 different potions = NO
                    }
                } else {
                    potion = is;
                    type = PotionUtils.getPotionFromItem(potion);
                    amt++;
                }
            }
        }


        if(bag != null) {
            if(potion != null) {
                PotionType storedType = ItemPotionBag.getPotionType(bag);

                if(storedType == null ||
                        type.equals(storedType)) {
                    //Overfill bag = NO
                    return ItemPotionBag.getAmountStored(bag) + amt <= QCConfig.potionBag.maxStored;
                }
            } else {
                return ItemPotionBag.getAmountStored(bag) > 0;
            }
        }


        return false;
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting inv) {
        ItemStack bag = null;
        ItemStack potion = null;
        int amt = 0;

        for(int i = 0; i < inv.getSizeInventory(); i++) {
            ItemStack is = inv.getStackInSlot(i);

            if(is.getItem() instanceof ItemPotionBag) {
                bag = is.copy();
            } else if(is.getItem() instanceof ItemPotion) {
                potion = is;
                amt++;
            }
        }

        if(potion != null) {
            ItemStack newBag = bag.copy();

            ItemPotionBag.setPotionType(newBag, potion);
            ItemPotionBag.setAmountStored(newBag, ItemPotionBag.getAmountStored(newBag) + amt);

            return newBag;
        } else {
            return PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), ItemPotionBag.getPotionType(bag));
        }
    }

    @Override
    public NonNullList<ItemStack> getRemainingItems(InventoryCrafting inv) {
        ItemStack bag = null;
        int bagSlot = -1;
        ItemStack potion = null;

        for(int i = 0; i < inv.getSizeInventory(); i++) {
            ItemStack is = inv.getStackInSlot(i);

            if(is.getItem() instanceof ItemPotionBag) {
                bag = is.copy();
                bagSlot = i;
            } else if(is.getItem() instanceof ItemPotion) {
                potion = is;
            }
        }

        if(bag != null && potion == null) {
            if(ItemPotionBag.getAmountStored(bag) > 0) {
                ItemStack newBag = bag.copy();

                ItemPotionBag.setAmountStored(newBag, ItemPotionBag.getAmountStored(newBag) - 1);
                inv.setInventorySlotContents(bagSlot, newBag);


                NonNullList list = NonNullList.create();

                return list;
            }
        }

        return ForgeHooks.defaultRecipeGetRemainingItems(inv);
    }

    @Override
    public boolean canFit(int width, int height) {
        return width * height > 1;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean isDynamic() {
        return true;
    }

    public static class Factory implements IRecipeFactory {
        @Override
        public IRecipe parse(JsonContext ctx, JsonObject json) {
            return new PotionBagRecipe();
        }
    }
}
