package com.feiteng.ftlearning.datagen;

import java.util.Comparator;
import java.util.concurrent.CompletableFuture;

import com.feiteng.ftlearning.block.ModBlocks;
import com.feiteng.ftlearning.item.ModItems;
import com.feiteng.ftlearning.tag.ModItemTags;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput data_output,
            CompletableFuture<HolderLookup.Provider> lookup) {
        super(data_output, lookup);
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

        valueLookupBuilder(ModItemTags.MUSIC_DISCS)
                .addAll(wrapper_lookup.lookupOrThrow(Registries.ITEM).listElements()
                        .filter(ref -> ((Item) ref.value()).components().has(DataComponents.JUKEBOX_PLAYABLE))
                        .sorted(Comparator.comparing(ref -> ref.key().identifier()))
                        .map(Holder.Reference::value));

        valueLookupBuilder(ModItemTags.DINGZHEN_MUSIC_DISCS)
                .add(ModItems.MUSIC_DISC_IGOTSMOKE);

        valueLookupBuilder(ModItemTags.REPAIRS_VOIDABYSS_STONE_ARMOR)
                .add(ModBlocks.VOIDABYSS_STONE.asItem());

        valueLookupBuilder(ModItemTags.VOIDABYSS_STONE_TOOL_MATERIALS)
                .add(ModBlocks.VOIDABYSS_STONE.asItem());

        // TODO: enchantable items need tags
    }
}
