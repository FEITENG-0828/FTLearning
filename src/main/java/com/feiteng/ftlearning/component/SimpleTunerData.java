package com.feiteng.ftlearning.component;

import com.feiteng.ftlearning.FTLearning;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public record SimpleTunerData(Mode mode, int signal, int emission) {
    public static final Codec<SimpleTunerData> CODEC = RecordCodecBuilder.create(instance -> instance
            .group(Codec.STRING.fieldOf("mode").forGetter(data -> data.mode().toString()),
                    Codec.INT.fieldOf("signal").forGetter(SimpleTunerData::signal),
                    Codec.INT.fieldOf("emission").forGetter(SimpleTunerData::emission))
            .apply(instance, SimpleTunerData::of));

    public static final SimpleTunerData DEFAULT = new SimpleTunerData(Mode.SIGNAL, 0, 0);

    public static SimpleTunerData of(String mode_str, int signal, int emission) {
        signal = Math.clamp(signal, 0, 15);
        emission = Math.clamp(emission, 0, 15);
        return new SimpleTunerData(Mode.fromString(mode_str), signal, emission);
    }

    public SimpleTunerData changeMode() {
        return new SimpleTunerData(mode == Mode.SIGNAL ? Mode.EMISSION : Mode.SIGNAL, signal, emission);
    }

    public SimpleTunerData increase() {
        if (mode == Mode.SIGNAL) {
            return new SimpleTunerData(mode, (signal + 1) % 16, emission);
        } else {
            return new SimpleTunerData(mode, signal, (emission + 1) % 16);
        }
    }

    public String getModeTranslationKey() {
        return Mode.getTranslationKey(mode);
    }

    public int getValue() {
        return mode == Mode.SIGNAL ? signal : emission;
    }

    public enum Mode {
        SIGNAL("signal"),
        EMISSION("emission");

        private final String name;

        Mode(String name) {
            this.name = name;
        }

        public static Mode fromString(String str) {
            return Mode.valueOf(str.toUpperCase());
        }

        @Override
        public String toString() {
            return name;
        }

        public static String getTranslationKey(Mode mode) {
            return "data_component." + FTLearning.MOD_ID + ".simple_tuner_data.mode." + mode.name;
        }
    }
}
