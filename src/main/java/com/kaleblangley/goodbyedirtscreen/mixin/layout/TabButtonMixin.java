package com.kaleblangley.goodbyedirtscreen.mixin.layout;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.TabButton;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(TabButton.class)
public abstract class TabButtonMixin {
    @Shadow @Final private static ResourceLocation TEXTURE_LOCATION;

    @Shadow protected abstract int getTextureY();

    @WrapOperation(method = "renderWidget", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;blitNineSliced(Lnet/minecraft/resources/ResourceLocation;IIIIIIIIIIII)V"))
    public void blendTab(GuiGraphics guiGraphics, ResourceLocation atlasLocation, int x, int y, int width, int height, int leftSliceWidth, int topSliceHeight, int rightSliceWidth, int bottomSliceHeight, int uWidth, int vHeight, int textureX, int textureY, Operation<Void> original) {
        if (false) original.call(guiGraphics, atlasLocation, x, y, width, height, leftSliceWidth, topSliceHeight, rightSliceWidth, bottomSliceHeight, uWidth, vHeight, textureX, textureY);
        AbstractWidget widget = (AbstractWidget) (Object) this;
        RenderSystem.enableBlend();
        guiGraphics.blitNineSliced(TEXTURE_LOCATION, widget.getX(), widget.getY(), widget.getWidth(), widget.getHeight(), 2, 2, 2, 0, 130, 24, 0, this.getTextureY());
        RenderSystem.disableBlend();
    }
}
