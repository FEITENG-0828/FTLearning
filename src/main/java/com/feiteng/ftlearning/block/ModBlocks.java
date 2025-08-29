package com.feiteng.ftlearning.block;

import com.feiteng.ftlearning.FTLearning;
import com.feiteng.ftlearning.block.custom.TherapeuticTableBlock;
import com.feiteng.ftlearning.sound.ModBlockSoundGroups;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSetType;
import net.minecraft.block.Blocks;
import net.minecraft.block.ButtonBlock;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.MapColor;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.TrapdoorBlock;
import net.minecraft.block.WallBlock;
import net.minecraft.block.WoodType;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block FIRST_ITEM_BLOCK = register(
        "first_item_block",
        new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)
            .mapColor(MapColor.GOLD))
    );

    public static final Block THERAPEUTIC_TABLE = register(
        "therapeutic_table",
        new TherapeuticTableBlock(FabricBlockSettings.copyOf(Blocks.ENCHANTING_TABLE)
            .mapColor(MapColor.BRIGHT_RED))
    );

    public static final Block VOIDABYSS_STONE = register(
        "voidabyss_stone",
        new Block(FabricBlockSettings.copyOf(Blocks.DEEPSLATE)
            .mapColor(MapColor.BLUE))
    );

    public static final Block VOIDABYSS_STONE_STAIRS = register(
        "voidabyss_stone_stairs",
        new StairsBlock(ModBlocks.VOIDABYSS_STONE.getDefaultState(),
            FabricBlockSettings.copyOf(ModBlocks.VOIDABYSS_STONE))
    );

    public static final Block VOIDABYSS_STONE_SLAB = register(
        "voidabyss_stone_slab",
        new SlabBlock(FabricBlockSettings.copyOf(ModBlocks.VOIDABYSS_STONE))
    );

    public static final Block VOIDABYSS_STONE_WALL = register(
        "voidabyss_stone_wall",
        new WallBlock(FabricBlockSettings.copyOf(ModBlocks.VOIDABYSS_STONE)
            .solid())
    );

    public static final Block VOIDABYSS_STONE_FENCE = register(
        "voidabyss_stone_fence",
        new FenceBlock(FabricBlockSettings.copyOf(ModBlocks.VOIDABYSS_STONE))
    );

    public static final Block VOIDABYSS_STONE_FENCE_GATE = register(
        "voidabyss_stone_fence_gate",
        new FenceGateBlock(WoodType.OAK,
            FabricBlockSettings.copyOf(ModBlocks.VOIDABYSS_STONE))
    );

    public static final Block VOIDABYSS_STONE_BUTTON = register(
        "voidabyss_stone_button",
        new ButtonBlock(BlockSetType.STONE,
            20,
            FabricBlockSettings.copyOf(ModBlocks.VOIDABYSS_STONE)
                .noCollision()
                .strength(0.5F)
                .pistonBehavior(PistonBehavior.DESTROY))
    );

    public static final Block VOIDABYSS_STONE_PRESSURE_PLATE = register(
        "voidabyss_stone_pressure_plate",
        new PressurePlateBlock(BlockSetType.STONE,
            FabricBlockSettings.copyOf(ModBlocks.VOIDABYSS_STONE)
                .solid()
                .noCollision()
                .strength(0.5F)
                .pistonBehavior(PistonBehavior.DESTROY))
    );

    public static final Block VOIDABYSS_STONE_DOOR = register(
        "voidabyss_stone_door",
        new DoorBlock(BlockSetType.STONE,
            FabricBlockSettings.copyOf(ModBlocks.VOIDABYSS_STONE)
                .strength(5.0F)
                .nonOpaque()
                .pistonBehavior(PistonBehavior.DESTROY))
    );

    public static final Block VOIDABYSS_STONE_TRAPDOOR = register(
        "voidabyss_stone_trapdoor",
        new TrapdoorBlock(BlockSetType.STONE,
            FabricBlockSettings.copyOf(ModBlocks.VOIDABYSS_STONE)
                .strength(3.0F)
                .nonOpaque()
                .allowsSpawning(Blocks::never))
    );

    public static final Block SHUODEDAOLI_BLOCK = register(
        "shuodedaoli_block",
        new Block(FabricBlockSettings.copyOf(Blocks.DIRT)
            .sounds(ModBlockSoundGroups.SHUODEDAOLI_BLOCK))
    );

    public static Block register(String name, Block block) {
        Registry.register(
            Registries.ITEM,
            new Identifier(FTLearning.MOD_ID, name),
            new BlockItem(block, new FabricItemSettings())
        );
        return Registry.register(
            Registries.BLOCK,
            new Identifier(FTLearning.MOD_ID, name),
            block);
    }

    public static void registerModBlocks() {
    }
}
