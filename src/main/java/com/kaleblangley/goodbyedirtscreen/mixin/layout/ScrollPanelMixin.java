package com.kaleblangley.goodbyedirtscreen.mixin.layout;

import com.kaleblangley.goodbyedirtscreen.util.EventUtil;
import com.mojang.blaze3d.vertex.Tesselator;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraftforge.client.gui.widget.ScrollPanel;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = ScrollPanel.class, remap = false)
public class ScrollPanelMixin {
    @Shadow @Final protected int left;

    @Shadow @Final protected int top;

    @Shadow @Final protected int right;

    @Shadow @Final protected int bottom;

    @Inject(method = "drawBackground", at = @At("HEAD"), cancellable = true)
    public void cancelBackground(GuiGraphics guiGraphics, Tesselator tess, float partialTick, CallbackInfo ci){
        EventUtil.postMenuList(guiGraphics, this.left, this.top, this.right - this.left, this.bottom - this.top);
        ci.cancel();
    }
}