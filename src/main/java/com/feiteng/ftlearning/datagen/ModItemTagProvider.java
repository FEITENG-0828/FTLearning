package com.feiteng.ftlearning.datagen;

import com.feiteng.ftlearning.item.ModItems;
import com.feiteng.ftlearning.tag.ModItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output,
                              CompletableFuture<RegistryWrapper.WrapperLookup> completable_future) {
        super(output, completable_future);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapper_lookup) {
        getOrCreateTagBuilder(ItemTags.TRIMMABLE_ARMOR)
            .add(ModItems.VOIDABYSS_STONE_HELMET,
                 ModItems.VOIDABYSS_STONE_CHESTPLATE,
                 ModItems.VOIDABYSS_STONE_LEGGINGS,
                 ModItems.VOIDABYSS_STONE_BOOTS);

        getOrCreateTagBuilder(ModItemTags.DISC_FRAGMENTS)
            .add(ModItems.DISC_FRAGMENT_GENERAL)
            .add(Items.DISC_FRAGMENT_5);

        getOrCreateTagBuilder(ItemTags.MUSIC_DISCS)
            .forceAddTag(ModItemTags.DINGZHEN_MUSIC_DISCS);

        getOrCreateTagBuilder(ModItemTags.DINGZHEN_MUSIC_DISCS)
            .add(ModItems.MUSIC_DISC_IGOTSMOKE);
    }
}
