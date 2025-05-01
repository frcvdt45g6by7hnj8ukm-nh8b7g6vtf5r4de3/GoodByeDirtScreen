package com.kaleblangley.goodbyedirtscreen.util.shader;

import net.minecraft.client.renderer.PostChain;
import net.minecraft.resources.ResourceLocation;

import static com.kaleblangley.goodbyedirtscreen.util.shader.EffectUtil.*;
import static com.kaleblangley.goodbyedirtscreen.util.MinecraftUtil.*;

public class BlurUtil {
    public static PostChain blurEffect;

    public static void closeBlur(){
        if (blurEffect != null){
            blurEffect.close();
        }
    }

    public static void loadBlurEffect() {
        if (blurEffect != null) {
            blurEffect.close();
        }

        blurEffect = getPostChain(new ResourceLocation("shaders/post/fade_in_blur.json"));
        blurEffect.resize(getWindow().getWidth(), getWindow().getHeight());
    }

    public static void processBlurEffect(float pPartialTick) {
        if (blurEffect != null) {
            setUniform(blurEffect, "Radius", 2f);
            blurEffect.process(pPartialTick);
        }
    }
}
