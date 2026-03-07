package com.feiteng.ftlearning.datagen;

import java.util.concurrent.CompletableFuture;

import com.feiteng.ftlearning.enchantment.MireEnchantmentEffect;
import com.feiteng.ftlearning.enchantment.ModEnchantments;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentTarget;
import net.minecraft.world.item.enchantment.LevelBasedValue;

public class ModDynamicRegistryProvider extends FabricDynamicRegistryProvider {
    public ModDynamicRegistryProvider(FabricDataOutput data_output,
            CompletableFuture<HolderLookup.Provider> lookup) {
        super(data_output, lookup);
    }

    @Override
    protected void configure(HolderLookup.Provider registries, Entries entries) {
        entries.addAll(registries.lookupOrThrow(Registries.JUKEBOX_SONG));

        registerEnchantment(entries, ModEnchantments.MIRE,
                Enchantment.enchantment(Enchantment.definition(
                        registries.lookupOrThrow(Registries.ITEM).getOrThrow(ItemTags.WEAPON_ENCHANTABLE),
                        7, 3,
                        Enchantment.dynamicCost(10, 10), Enchantment.dynamicCost(40, 20),
                        4, EquipmentSlotGroup.HAND))
                        .withEffect(EnchantmentEffectComponents.POST_ATTACK,
                                EnchantmentTarget.ATTACKER, EnchantmentTarget.VICTIM,
                                new MireEnchantmentEffect(LevelBasedValue.constant(0F))));
    }

    private void registerEnchantment(Entries entries,
            ResourceKey<Enchantment> key, Enchantment.Builder builder) {
        entries.add(key, builder.build(key.identifier()));
    }

    @Override
    public String getName() {
        return "Dynamic Registries";
    }
}
