package com.feiteng.ftlearning.block;

import java.util.Map;
import java.util.stream.Stream;

import com.google.common.collect.Maps;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.BlockFamily;
import net.minecraft.world.level.block.Block;

public class ModBlockFamilies {
    // BlockFamilies
    private static final Map<Block, BlockFamily> MAP = Maps.newHashMap();

    public static final BlockFamily VOIDABYSS_STONE = familyBuilder(ModBlocks.VOIDABYSS_STONE)
            .stairs(ModBlocks.VOIDABYSS_STONE_STAIRS)
            .slab(ModBlocks.VOIDABYSS_STONE_SLAB)
            .wall(ModBlocks.VOIDABYSS_STONE_WALL)
            .fence(ModBlocks.VOIDABYSS_STONE_FENCE)
            .fenceGate(ModBlocks.VOIDABYSS_STONE_FENCE_GATE)
            .button(ModBlocks.VOIDABYSS_STONE_BUTTON)
            .pressurePlate(ModBlocks.VOIDABYSS_STONE_PRESSURE_PLATE)
            .door(ModBlocks.VOIDABYSS_STONE_DOOR)
            .trapdoor(ModBlocks.VOIDABYSS_STONE_TRAPDOOR)
            .getFamily();

    public static BlockFamily.Builder familyBuilder(Block base_block) {
        BlockFamily.Builder builder = new BlockFamily.Builder(base_block);
        BlockFamily blockFamily = MAP.put(base_block, builder.getFamily());
        if (blockFamily != null) {
            throw new IllegalStateException("Duplicate family definition for " +
                    BuiltInRegistries.BLOCK.getKey(base_block));
        } else {
            return builder;
        }
    }

    public static Stream<BlockFamily> getAllFamilies() {
        return MAP.values().stream();
    }
}
