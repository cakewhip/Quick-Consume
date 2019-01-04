package com.kain24.quickconsume.client.gui;

import com.kain24.quickconsume.QuickConsume;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.config.GuiUtils;
import org.lwjgl.opengl.GL11;

public class GuiFoodSlot {
    /**
     * Not actually a super magic number, just the offset from the center of the screen to 6 units away from the hotbar
     */
    private static int SUPER_MAGIC_NUMBER = 97;

    private static ResourceLocation OVERLAY = new ResourceLocation(QuickConsume.MODID, "textures/gui/overlay.png");

    public static void drawSlot(ScaledResolution sr) {
        Minecraft.getMinecraft().getTextureManager().bindTexture(OVERLAY);

        int x = sr.getScaledWidth() / 2 + SUPER_MAGIC_NUMBER;
        int y = sr.getScaledHeight() - 22;

        GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_BLEND);

        GuiUtils.drawTexturedModalRect(x, y, 0, 0, 22, 22, 1F);

        GL11.glPopMatrix();
    }
}
