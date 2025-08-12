package com.feiteng.ftlearning.sound;

import com.feiteng.ftlearning.FTLearning;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSoundEvents {
    public static final SoundEvent ITEM_PROSPECTOR_USE_SUCCESS =
        register("item.prospector.use.success");
    public static final SoundEvent ITEM_PROSPECTOR_USE_FAILURE =
        register("item.prospector.use.failure");

    public static final SoundEvent BLOCK_SHUODEDAOLI_BLOCK_BREAK =
        register("block.shuodedaoli_block.break");
    public static final SoundEvent BLOCK_SHUODEDAOLI_BLOCK_STEP =
        register("block.shuodedaoli_block.step");
    public static final SoundEvent BLOCK_SHUODEDAOLI_BLOCK_PLACE =
        register("block.shuodedaoli_block.place");
    public static final SoundEvent BLOCK_SHUODEDAOLI_BLOCK_HIT =
        register("block.shuodedaoli_block.hit");
    public static final SoundEvent BLOCK_SHUODEDAOLI_BLOCK_FALL =
        register("block.shuodedaoli_block.fall");

    public static final SoundEvent MUSIC_DISC_IGOTSMOKE =
        register("music_disc.igotsmoke");

    private static SoundEvent register(String name) {
        Identifier id = new Identifier(FTLearning.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerModSoundEvents() {
    }
}
