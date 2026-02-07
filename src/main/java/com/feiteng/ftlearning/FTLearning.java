package com.feiteng.ftlearning;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feiteng.ftlearning.block.ModBlocks;
import com.feiteng.ftlearning.item.ModItemGroups;
import com.feiteng.ftlearning.item.ModItems;
import com.feiteng.ftlearning.item.ModPotions;
// import com.feiteng.ftlearning.item.custom.ArGlassesItem;
import com.feiteng.ftlearning.sound.ModSoundEvents;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder;
// import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.registry.FuelRegistryEvents;
import net.minecraft.world.item.Items;
// import net.minecraft.core.RegistryAccess.RegistryEntry;
// import net.minecraft.server.network.ServerPlayerEntity;
// import net.minecraft.tags.DamageTypeTags;
// import net.minecraft.world.damagesource.DamageType;
// import net.minecraft.world.entity.EquipmentSlot;
// import net.minecraft.world.item.ItemStack;

public class FTLearning implements ModInitializer {
    public static final String MOD_ID = "ftlearning";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModBlocks.bootstrap();
        ModItems.bootstrap();
        ModItemGroups.bootstrap();
        ModSoundEvents.bootstrap();
        ModPotions.bootstrap();

        FuelRegistryEvents.BUILD.register((builder, context) -> {
            builder.add(ModItems.FIRST_ITEM, 160 * 20);
        });
        FuelRegistryEvents.BUILD.register((builder, context) -> {
            builder.add(ModBlocks.FIRST_ITEM_BLOCK, 1600 * 20);
        });

        FabricBrewingRecipeRegistryBuilder.BUILD.register(builder -> {
            builder.addStartMix(Items.CLOCK, ModPotions.FURY);
        });

        // ServerLivingEntityEvents.ALLOW_DAMAGE.register((entity, source, amount) -> {
        //     if (entity instanceof ServerPlayerEntity player)
        //     {
        //         ItemStack stack = player.getEquippedStack(EquipmentSlot.HEAD);
        //         if (stack.getItem() instanceof ArGlassesItem) {
        //             RegistryEntry<DamageType> damage_type = source.getTypeRegistryEntry();
        //             if (damage_type != null && (damage_type.isIn(DamageTypeTags.DAMAGES_HELMET) ||
        //                     damage_type.isIn(DamageTypeTags.IS_EXPLOSION) ||
        //                     damage_type.isIn(DamageTypeTags.IS_LIGHTNING))
        //                 ) {
        //                 stack.damage(3, player, p -> p.sendEquipmentBreakStatus(EquipmentSlot.HEAD));
        //             } else {
        //                 stack.damage(1, player, p -> p.sendEquipmentBreakStatus(EquipmentSlot.HEAD));
        //             }
        //         }
        //     }
        //     return true;
        // });
    }
}