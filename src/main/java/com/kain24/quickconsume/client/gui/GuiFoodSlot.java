package com.kain24.quickconsume.client.gui;

import com.kain24.quickconsume.QuickConsume;
import com.kain24.quickconsume.client.ClientData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.config.GuiUtils;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiFoodSlot extends Gui {
    /**
     * Not actually a super magic number, just the offset from the center of the screen to 6 units away from the hotbar.
     */
    private static int SUPER_MAGIC_NUMBER = 97;

    private static ResourceLocation OVERLAY = new ResourceLocation(QuickConsume.MODID, "textures/gui/overlay.png");

    public static void drawSlot(ScaledResolution sr) {
        Minecraft mc = Minecraft.getMinecraft();

        mc.getTextureManager().bindTexture(OVERLAY);

        int x = sr.getScaledWidth() / 2 + SUPER_MAGIC_NUMBER;
        int y = sr.getScaledHeight() - 22;

        GlStateManager.enableBlend();

        GuiUtils.drawTexturedModalRect(x, y, 0, 0, 22, 22, 1F);

        ItemStack is = ClientData.storedFoodItemStack;

        if(is.equals(ItemStack.EMPTY)) {
            GuiUtils.drawTexturedModalRect(x, y, 22, 0, 22, 22, 1F);
        } else {
            drawItemStack(ClientData.storedFoodItemStack, x + 3, y + 3);
        }

        GlStateManager.color(1, 1, 1, 1);

        GlStateManager.disableLighting();
        GlStateManager.disableDepth();
        GlStateManager.disableBlend();
    }

    private static void drawItemStack(ItemStack is, int x, int y) {
        Minecraft mc = Minecraft.getMinecraft();
        FontRenderer fr = is.getItem().getFontRenderer(is);
        RenderItem ri = mc.getRenderItem();

        if(fr == null) {
            fr = mc.fontRenderer;
        }

        GlStateManager.translate(0.0F, 0.0F, 32.0F);
        ri.zLevel = 200F;

        ri.renderItemAndEffectIntoGUI(is, x, y);
        ri.renderItemOverlayIntoGUI(fr, is,
                x, y, null);

        ri.zLevel = 0F;
    }
}
