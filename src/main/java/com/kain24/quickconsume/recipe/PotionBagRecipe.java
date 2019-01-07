package com.kain24.quickconsume.recipe;

import com.google.gson.JsonObject;
import com.kain24.quickconsume.item.ItemPotionBag;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;
import net.minecraft.world.World;
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


        if(bag != null && potion != null) {
            PotionType storedType = ItemPotionBag.getPotionType(bag);

            if(storedType == null ||
                    type.equals(storedType)) {
                //Overfill bag = NO
                return ItemPotionBag.getAmountStored(bag) + amt <= ItemPotionBag.getMax();
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

        ItemPotionBag.setPotionType(bag, potion);
        ItemPotionBag.setAmountStored(bag, ItemPotionBag.getAmountStored(bag) + amt);

        return bag;
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
