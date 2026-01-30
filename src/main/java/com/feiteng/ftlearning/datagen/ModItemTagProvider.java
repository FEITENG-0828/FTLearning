package com.feiteng.ftlearning.datagen;

import java.util.concurrent.CompletableFuture;

import com.feiteng.ftlearning.item.ModItems;
import com.feiteng.ftlearning.tag.ModItemTags;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output,
            CompletableFuture<HolderLookup.Provider> future) {
        super(output, future);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapper_lookup) {
        valueLookupBuilder(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.VOIDABYSS_STONE_HELMET,
                        ModItems.VOIDABYSS_STONE_CHESTPLATE,
                        ModItems.VOIDABYSS_STONE_LEGGINGS,
                        ModItems.VOIDABYSS_STONE_BOOTS);

        valueLookupBuilder(ModItemTags.DISC_FRAGMENTS)
                .add(ModItems.DISC_FRAGMENT_GENERAL)
                .add(Items.DISC_FRAGMENT_5);

        valueLookupBuilder(ModItemTags.DINGZHEN_MUSIC_DISCS)
                .add(ModItems.MUSIC_DISC_IGOTSMOKE);

        // valueLookupBuilder(ItemTags.MUSIC_DISCS)
        //         .addOptionalTag(ModItemTags.DINGZHEN_MUSIC_DISCS);
    }
}
