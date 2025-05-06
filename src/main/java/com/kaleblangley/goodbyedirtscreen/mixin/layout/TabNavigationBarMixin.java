package com.kaleblangley.goodbyedirtscreen.mixin.layout;

import com.google.common.collect.ImmutableList;
import com.kaleblangley.goodbyedirtscreen.util.EventUtil;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.TabButton;
import net.minecraft.client.gui.components.tabs.TabNavigationBar;
import net.minecraft.client.gui.layouts.GridLayout;
import net.minecraft.client.gui.screens.worldselection.CreateWorldScreen;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(TabNavigationBar.class)
public class TabNavigationBarMixin {
    @Shadow @Final private GridLayout layout;

    @Shadow private int width;

    @Shadow @Final private ImmutableList<TabButton> tabButtons;

    @WrapOperation(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;blit(Lnet/minecraft/resources/ResourceLocation;IIFFIIII)V"))
    public void blendTab(GuiGraphics guiGraphics, ResourceLocation atlasLocation, int x, int y, float uOffset, float vOffset, int width, int height, int textureWidth, int textureHeight, Operation<Void> original) {
        if (false) original.call(guiGraphics, atlasLocation, x, y, uOffset, vOffset, width, height, textureWidth, textureHeight);
        EventUtil.postHeader(guiGraphics, 0, this.layout.getY() + this.layout.getHeight() - 2, this.tabButtons.get(0).getX());
        TabButton lastTab = this.tabButtons.get(this.tabButtons.size() - 1);
        int i = lastTab.getX() + lastTab.getWidth();
        EventUtil.postHeader(guiGraphics, i, this.layout.getY() + this.layout.getHeight() - 2, this.width - lastTab.getX());
    }

    @WrapOperation(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;fill(IIIII)V"))
    public void cancelFill(GuiGraphics guiGraphics, int minX, int minY, int maxX, int maxY, int color, Operation<Void> original) {
        if (false) original.call(guiGraphics, minX, minY, maxX, maxY, color);
    }
}
