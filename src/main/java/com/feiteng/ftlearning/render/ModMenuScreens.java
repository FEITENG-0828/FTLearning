package com.feiteng.ftlearning.render;

import net.minecraft.client.gui.screens.MenuScreens;

public class ModMenuScreens {
    public static void bootstrap() {
        MenuScreens.register(ModMenuTypes.ESSENCE_EXTRACTOR, EssenceExtractorScreen::new);
    }
}
