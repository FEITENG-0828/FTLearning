package com.feiteng.ftlearning.block;

import java.util.function.Function;

import com.feiteng.ftlearning.FTLearning;
import com.feiteng.ftlearning.block.custom.TherapeuticTableBlock;
import com.feiteng.ftlearning.sound.ModBlockSoundGroups;
import com.feiteng.ftlearning.util.HelpfulFuncs;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.ColorCode;
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

    public static final Block[] COBBLESTONE_COMPRESSED_BLOCKS = registerCompressedBlockKind(
        Blocks.COBBLESTONE, 9, Block::new
    );
    public static final Block[] DIRT_COMPRESSED_BLOCKS = registerCompressedBlockKind(
        Blocks.DIRT, 9, Block::new
    );
    public static final Block[] SAND_COMPRESSED_BLOCKS = registerCompressedBlockKind(
        Blocks.SAND, 9,
        settings -> new ColoredFallingBlock(new ColorCode(14406560), settings)
    );
    public static final Block[] GRAVEL_COMPRESSED_BLOCKS = registerCompressedBlockKind(
        Blocks.GRAVEL, 9,
        settings -> new ColoredFallingBlock(new ColorCode(-8356741), settings)
    );
    public static final Block[] COBBLED_DEEPSLATE_COMPRESSED_BLOCKS = registerCompressedBlockKind(
        Blocks.COBBLED_DEEPSLATE, 9, Block::new
    );
    public static final Block[] REDSTONE_BLOCK_COMPRESSED_BLOCKS = registerCompressedBlockKind(
        Blocks.REDSTONE_BLOCK, 3, RedstoneBlock::new
    );
    public static final Block[] LAPIS_BLOCK_COMPRESSED_BLOCKS = registerCompressedBlockKind(
        Blocks.LAPIS_BLOCK, 3, Block::new
    );
    public static final Block[] AMETHYST_BLOCK_COMPRESSED_BLOCKS = registerCompressedBlockKind(
        Blocks.AMETHYST_BLOCK, 3, AmethystBlock::new
    );
    public static final Block[] NETHERRACK_COMPRESSED_BLOCKS = registerCompressedBlockKind(
        Blocks.NETHERRACK, 9, NetherrackBlock::new
    );
    public static final Block[] SOUL_SAND_COMPRESSED_BLOCKS = registerCompressedBlockKind(
        Blocks.SOUL_SAND, 3, SoulSandBlock::new
    );

    private static Block register(String name, Block block) {
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

    public static Block[] registerCompressedBlockKind(
        Block origin_block,
        int max_compress_level,
        Function<FabricBlockSettings, ? extends Block> block_factory
    ) {
        if (max_compress_level < 1 || max_compress_level > 9) {
            max_compress_level = 9;
        }

        Block[] blocks = new Block[max_compress_level];
        for (int i = 0; i < max_compress_level; ++i) {
            String name = HelpfulFuncs.getSimpleIdStr(origin_block) + "_compressed_" + (i + 1);
            if (i == 0) {
                blocks[i] = registerCompressedBlock(name, origin_block, block_factory);
            } else {
                blocks[i] = registerCompressedBlock(name, blocks[i - 1], block_factory);
            }
        }
        return blocks;
    }

    public static Block registerCompressedBlock(
        String name,
        Block base_block,
        Function<FabricBlockSettings, ? extends Block> block_factory
    ) {
        final float hardness_multiplier = 2.0f;
        final float resistance_multiplier = 3.0f;

        FabricBlockSettings settings = FabricBlockSettings.copyOf(base_block)
            .hardness(base_block.getHardness() * hardness_multiplier)
            .resistance(base_block.getBlastResistance() * resistance_multiplier);

        return register(name, block_factory.apply(settings));
    }

    public static void registerModBlocks() {
    }
}
