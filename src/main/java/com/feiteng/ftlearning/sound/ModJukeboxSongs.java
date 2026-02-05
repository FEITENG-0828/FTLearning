package com.feiteng.ftlearning.sound;

import com.feiteng.ftlearning.FTLearning;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.JukeboxSong;
import net.minecraft.core.registries.BuiltInRegistries;

public interface ModJukeboxSongs {
    Identifier IGOTSMOKE_ID = Identifier.fromNamespaceAndPath(FTLearning.MOD_ID, "igotsmoke");
    ResourceKey<JukeboxSong> IGOTSMOKE = ResourceKey.create(Registries.JUKEBOX_SONG, IGOTSMOKE_ID);

    static void bootstrap(
        BootstrapContext<JukeboxSong> context) {
        register(context, IGOTSMOKE, IGOTSMOKE_ID, ModSoundEvents.MUSIC_DISC_IGOTSMOKE, 246, 15);
    }

    private static void register(
        BootstrapContext<JukeboxSong> context, ResourceKey<JukeboxSong> key, Identifier id, SoundEvent soundEvent, int lengthInSeconds, int comparatorOutput
    ) {
        context.register(
            key, new JukeboxSong(
            BuiltInRegistries.SOUND_EVENT.wrapAsHolder(soundEvent),
            Component.translatable("jukebox_song." + id.getNamespace() + "." + id.getPath()),
            (float) lengthInSeconds,
            comparatorOutput
        ));
    }
}
