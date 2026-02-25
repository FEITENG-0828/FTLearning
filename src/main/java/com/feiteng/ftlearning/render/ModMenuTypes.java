package com.feiteng.ftlearning.render;

import com.feiteng.ftlearning.FTLearning;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;

public class ModMenuTypes {
    public static final MenuType<EssenceExtractorMenu> ESSENCE_EXTRACTOR = register(
            "essence_extractor", EssenceExtractorMenu::new);

    private static <T extends AbstractContainerMenu> MenuType<T> register(String name,
            MenuType.MenuSupplier<T> supplier) {
        return Registry.register(BuiltInRegistries.MENU,
                Identifier.fromNamespaceAndPath(FTLearning.MOD_ID, name),
                new MenuType<>(supplier, FeatureFlags.DEFAULT_FLAGS));
    }

    public static void bootstrap() {
    }
}
