package com.feiteng.ftlearning.block;

import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.registry.Registries;

import java.util.Map;
import java.util.stream.Stream;

public class ModBlockFamilies {
    private static final Map<Block, BlockFamily> BASE_BLOCKS_TO_FAMILIES = Maps.newHashMap();

    public static final BlockFamily VOIDABYSS_STONE = register(ModBlocks.VOIDABYSS_STONE)
        .stairs(ModBlocks.VOIDABYSS_STONE_STAIRS)
        .slab(ModBlocks.VOIDABYSS_STONE_SLAB)
        .wall(ModBlocks.VOIDABYSS_STONE_WALL)
        .fence(ModBlocks.VOIDABYSS_STONE_FENCE)
        .fenceGate(ModBlocks.VOIDABYSS_STONE_FENCE_GATE)
        .button(ModBlocks.VOIDABYSS_STONE_BUTTON)
        .pressurePlate(ModBlocks.VOIDABYSS_STONE_PRESSURE_PLATE)
        .door(ModBlocks.VOIDABYSS_STONE_DOOR)
        .trapdoor(ModBlocks.VOIDABYSS_STONE_TRAPDOOR)
        .build();

    public static BlockFamily.Builder register(Block base_block) {
        BlockFamily.Builder builder = new BlockFamily.Builder(base_block);
        BlockFamily blockFamily = BASE_BLOCKS_TO_FAMILIES.put(base_block, builder.build());
        if (blockFamily != null) {
            throw new IllegalStateException("Duplicate family definition for " +
                Registries.BLOCK.getId(base_block));
        } else {
            return builder;
        }
    }

    public static Stream<BlockFamily> getFamilies() {
        return BASE_BLOCKS_TO_FAMILIES.values().stream();
    }
}
