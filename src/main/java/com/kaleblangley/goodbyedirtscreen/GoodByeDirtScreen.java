package com.kaleblangley.goodbyedirtscreen;

import net.minecraft.client.gui.screens.GenericDirtMessageScreen;
import net.minecraft.client.gui.screens.LevelLoadingScreen;
import net.minecraftforge.common.ForgeConfig;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


@Mod(GoodByeDirtScreen.MODID)
public class GoodByeDirtScreen {
    public static final String MODID = "goodbye_dirt_screen";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec.BooleanValue ENABLE_DARKENING_EFFECTS = BUILDER
            .comment("Whether to enable the screen darkening effect")
            .comment("是否启用屏幕黯淡效果")
            .define("darkening", true);
    public static final ForgeConfigSpec.BooleanValue ENABLE_BLUR_EFFECTS = BUILDER
            .comment("Whether to enable the screen blur effect（Not realized）")
            .comment("是否启用屏幕模糊效果（未实现）")
            .define("blur", true);
    public static final ForgeConfigSpec.ConfigValue<List<? extends String>> ALLOW_SCREEN_LIST = BUILDER
            .comment("Always add a screen list for panoramic background (enter the screen class name)")
            .comment("始终添加全景图背景的屏幕列表（输入屏幕类名）")
            .defineList("allow screen list", List.of(GenericDirtMessageScreen.class.getName(), LevelLoadingScreen.class.getName()), str -> true);
    public static final ForgeConfigSpec.ConfigValue<List<? extends String>> EXCLUDE_SCREEN_LIST = BUILDER
            .comment("Always exclude the screen list of panoramic background (enter the screen class name)")
            .comment("始终拒绝全景图背景的屏幕列表（输入屏幕类名）")
            .defineList("exclude screen list", List.of(), str -> true);
    private static final ForgeConfigSpec SPEC = BUILDER.build();

    public GoodByeDirtScreen(){
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, SPEC);
    }
}
