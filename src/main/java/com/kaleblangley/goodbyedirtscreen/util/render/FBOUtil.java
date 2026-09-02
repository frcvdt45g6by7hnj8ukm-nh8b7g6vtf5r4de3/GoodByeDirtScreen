package com.kaleblangley.goodbyedirtscreen.util.render;

import com.mojang.blaze3d.pipeline.RenderTarget;
import net.minecraft.client.Minecraft;

public class FBOUtil {
    private static RenderTarget backgroundFBO = null;

    public static void initBackgroundFBO(int width, int height){
        if (backgroundFBO == null) {
            backgroundFBO = new RenderTarget(true) {
            };
        }
        if (backgroundFBO.width != width || backgroundFBO.height != height) {
            backgroundFBO.resize(width, height, Minecraft.ON_OSX);
        }
    }

    public static void close() {
        if (backgroundFBO != null) {
            backgroundFBO.destroyBuffers();
            backgroundFBO = null;
        }
    }

    public static void backgroundFBOWrite(boolean setView){
        backgroundFBO.bindWrite(setView);
    }

    public static void backgroundFBOUnWrite(){
        backgroundFBO.unbindWrite();
    }

    public static int getColorTextureId(){
        return backgroundFBO.getColorTextureId();
    }

    public static RenderTarget getBackgroundFBO() {
        return backgroundFBO;
    }
}
