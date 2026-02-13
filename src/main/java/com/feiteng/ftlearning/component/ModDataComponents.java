package com.feiteng.ftlearning.component;

import java.util.function.UnaryOperator;

import com.feiteng.ftlearning.FTLearning;

import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;

public class ModDataComponents {
    public static final DataComponentType<SimpleTunerData> SIMPLE_TUNER_DATA = register(
            "simple_tuner_data", builder -> builder.persistent(SimpleTunerData.CODEC));
    // .networkSynchronized() ?

    private static <T> DataComponentType<T> register(String name,
            UnaryOperator<DataComponentType.Builder<T>> operator) {
        return Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE,
                Identifier.fromNamespaceAndPath(FTLearning.MOD_ID, name),
                operator.apply(DataComponentType.builder()).build());
    }

    public static void bootstrap() {
    }
}
