package com.feiteng.ftlearning.sound;

import com.feiteng.ftlearning.FTLearning;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Util;
import net.minecraft.world.item.JukeboxSong;

public interface ModJukeboxSongs {
    ResourceKey<JukeboxSong> IGOTSMOKE = create("igotsmoke");

    private static ResourceKey<JukeboxSong> create(String name) {
        return ResourceKey.create(Registries.JUKEBOX_SONG,
                Identifier.fromNamespaceAndPath(FTLearning.MOD_ID, name));
    }

    private static void register(BootstrapContext<JukeboxSong> context,
            ResourceKey<JukeboxSong> key, Holder.Reference<SoundEvent> reference, int length, int output) {
        context.register(key, new JukeboxSong(reference,
                Component.translatable(Util.makeDescriptionId("jukebox_song", key.identifier())),
                length, output));
    }

    static void bootstrap(BootstrapContext<JukeboxSong> context) {
        register(context, IGOTSMOKE, ModSoundEvents.MUSIC_DISC_IGOTSMOKE, 246, 15);
    }
}
