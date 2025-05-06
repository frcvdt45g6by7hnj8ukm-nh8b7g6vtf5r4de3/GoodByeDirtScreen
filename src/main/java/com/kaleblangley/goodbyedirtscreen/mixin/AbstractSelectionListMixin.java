package com.kaleblangley.goodbyedirtscreen.mixin;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractSelectionList;
import net.minecraft.client.renderer.RenderType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(AbstractSelectionList.class)
public class AbstractSelectionListMixin {
    @Shadow protected int x0;

    @Shadow protected int y0;

    @Shadow protected int y1;

    @Shadow protected int x1;

    @Definition(id = "renderTopAndBottom", field = "Lnet/minecraft/client/gui/components/AbstractSelectionList;renderTopAndBottom:Z")
    @Expression("this.renderTopAndBottom")
    @ModifyExpressionValue(method = "render", at = @At(value = "MIXINEXTRAS:EXPRESSION"))
    public boolean cancelRenderTopAndBottom(boolean original){
        return false;
    }

    @Definition(id = "renderBackground", field = "Lnet/minecraft/client/gui/components/AbstractSelectionList;renderBackground:Z")
    @Expression("this.renderBackground")
    @ModifyExpressionValue(method = "render", at = @At(value = "MIXINEXTRAS:EXPRESSION"))
    public boolean cancelRenderBackGround(boolean original, @Local(argsOnly = true) GuiGraphics guiGraphics){
        guiGraphics.fillGradient(RenderType.guiOverlay(), this.x0, this.y0, this.x1, this.y0 + 4, -16777216, 0, 0);
        guiGraphics.fillGradient(RenderType.guiOverlay(), this.x0, this.y1 - 4, this.x1, this.y1, 0, -16777216, 0);
        return false;
    }
}
