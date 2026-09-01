package com.kaleblangley.goodbyedirtscreen.util.shader;

import net.minecraft.client.renderer.PostChain;
import net.minecraft.resources.ResourceLocation;
import com.mojang.blaze3d.systems.RenderSystem;

import static com.kaleblangley.goodbyedirtscreen.util.MinecraftUtil.getMainRenderTarget;
import static com.kaleblangley.goodbyedirtscreen.util.shader.EffectUtil.getPostChain;
import static com.kaleblangley.goodbyedirtscreen.util.shader.EffectUtil.setUniform;

public class BlurUtil {
    public static PostChain blurEffect;
    private static boolean loadAttempted;
    private static int width;
    private static int height;

    public static void closeBlur(){
        if (blurEffect != null){
            blurEffect.close();
            blurEffect = null;
        }
        loadAttempted = false;
        width = height = 0;
    }

    public static void loadBlurEffect() {
        if (!loadAttempted) {
            loadAttempted = true;
            blurEffect = getPostChain(new ResourceLocation("shaders/post/fade_in_blur.json"));
        }
        if (blurEffect != null && (width != getMainRenderTarget().width || height != getMainRenderTarget().height)) {
            width = getMainRenderTarget().width;
            height = getMainRenderTarget().height;
            blurEffect.resize(width, height);
        }
    }

    public static void processBlurEffect(float pPartialTick) {
        if (blurEffect != null) {
            setUniform(blurEffect, "Radius", 2f);
            RenderSystem.disableBlend();
            RenderSystem.disableDepthTest();
            try {
                blurEffect.process(pPartialTick);
            } finally {
                getMainRenderTarget().bindWrite(true);
                RenderSystem.enableDepthTest();
                RenderSystem.defaultBlendFunc();
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            }
        }
    }
}
