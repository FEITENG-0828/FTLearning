package com.feiteng.ftlearning.block;

import java.util.function.Function;

import com.feiteng.ftlearning.FTLearning;
import com.feiteng.ftlearning.block.custom.EssenceExtractorBlock;
import com.feiteng.ftlearning.block.custom.TherapeuticTableBlock;
import com.feiteng.ftlearning.block.custom.TunableEmitterBlock;
import com.feiteng.ftlearning.sound.ModBlockSoundType;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

@SuppressWarnings("deprecation")
public class ModBlocks {
    public static final Block FIRST_ITEM_BLOCK = register(
            "first_item_block",
            Block::new,
            BlockBehaviour.Properties.ofLegacyCopy(Blocks.IRON_BLOCK).mapColor(MapColor.GOLD));

    public static final Block THERAPEUTIC_TABLE = register(
            "therapeutic_table",
            TherapeuticTableBlock::new,
            BlockBehaviour.Properties.ofLegacyCopy(Blocks.ENCHANTING_TABLE).mapColor(MapColor.FIRE));

    public static final Block VOIDABYSS_STONE = register( // TODO : random rotations
            "voidabyss_stone",
            Block::new,
            BlockBehaviour.Properties.ofLegacyCopy(Blocks.DEEPSLATE).mapColor(MapColor.COLOR_BLUE));

    public static final Block VOIDABYSS_STONE_STAIRS = register(
            "voidabyss_stone_stairs",
            properties -> new StairBlock(VOIDABYSS_STONE.defaultBlockState(), properties),
            BlockBehaviour.Properties.ofLegacyCopy(VOIDABYSS_STONE));

    public static final Block VOIDABYSS_STONE_SLAB = register(
            "voidabyss_stone_slab",
            SlabBlock::new,
            BlockBehaviour.Properties.ofLegacyCopy(VOIDABYSS_STONE));

    public static final Block VOIDABYSS_STONE_WALL = register(
            "voidabyss_stone_wall",
            WallBlock::new,
            BlockBehaviour.Properties.ofLegacyCopy(VOIDABYSS_STONE).forceSolidOn()); // TODO: forceSolidOn

    public static final Block VOIDABYSS_STONE_FENCE = register(
            "voidabyss_stone_fence",
            FenceBlock::new,
            BlockBehaviour.Properties.ofLegacyCopy(VOIDABYSS_STONE));

    public static final Block VOIDABYSS_STONE_FENCE_GATE = register(
            "voidabyss_stone_fence_gate",
            properties -> new FenceGateBlock(WoodType.WARPED, properties), // TODO: WoodType.WARPED
            BlockBehaviour.Properties.ofLegacyCopy(VOIDABYSS_STONE));

    public static final Block VOIDABYSS_STONE_BUTTON = register(
            "voidabyss_stone_button",
            properties -> new ButtonBlock(BlockSetType.STONE, 20, properties),
            BlockBehaviour.Properties.ofLegacyCopy(VOIDABYSS_STONE)
                    .noCollision().strength(0.5F).pushReaction(PushReaction.DESTROY));

    public static final Block VOIDABYSS_STONE_PRESSURE_PLATE = register(
            "voidabyss_stone_pressure_plate",
            properties -> new PressurePlateBlock(BlockSetType.STONE, properties),
            BlockBehaviour.Properties.ofLegacyCopy(VOIDABYSS_STONE)
                    .forceSolidOn()
                    .noCollision()
                    .strength(0.5F)
                    .pushReaction(PushReaction.DESTROY));

    public static final Block VOIDABYSS_STONE_DOOR = register(
            "voidabyss_stone_door",
            properties -> new DoorBlock(BlockSetType.STONE, properties),
            BlockBehaviour.Properties.ofLegacyCopy(VOIDABYSS_STONE)
                    .strength(5.0F).noOcclusion().pushReaction(PushReaction.DESTROY));

    public static final Block VOIDABYSS_STONE_TRAPDOOR = register(
            "voidabyss_stone_trapdoor",
            properties -> new TrapDoorBlock(BlockSetType.STONE, properties),
            BlockBehaviour.Properties.ofLegacyCopy(VOIDABYSS_STONE)
                    .strength(5.0F).noOcclusion().isValidSpawn(Blocks::never));

    public static final Block SHUODEDAOLI_BLOCK = register(
            "shuodedaoli_block",
            Block::new,
            BlockBehaviour.Properties.ofLegacyCopy(Blocks.DIRT)
                    .sound(ModBlockSoundType.SHUODEDAOLI_BLOCK));

    public static final Block VOIDABYSS_STONE_PILLAR = register(
            "voidabyss_stone_pillar",
            RotatedPillarBlock::new,
            BlockBehaviour.Properties.ofLegacyCopy(VOIDABYSS_STONE).lightLevel(state -> 10));

    public static final Block TUNABLE_EMITTER = register(
            "tunable_emitter",
            TunableEmitterBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.TERRACOTTA_ORANGE)
                    .strength(1F)
                    .sound(SoundType.LANTERN)
                    .lightLevel(TunableEmitterBlock.LIGHT_EMISSION));

    public static final Block ESSENCE_EXTRACTOR = register(
            "essence_extractor",
            EssenceExtractorBlock::new,
            BlockBehaviour.Properties.ofLegacyCopy(VOIDABYSS_STONE)); // TODO

    public static Block register(String name,
            Function<BlockBehaviour.Properties, Block> func, BlockBehaviour.Properties properties) {
        ResourceKey<Block> block_key = ResourceKey.create(Registries.BLOCK,
                Identifier.fromNamespaceAndPath(FTLearning.MOD_ID, name));
        Block block = (Block) func.apply(properties.setId(block_key));

        ResourceKey<Item> item_key = ResourceKey.create(Registries.ITEM,
                Identifier.fromNamespaceAndPath(FTLearning.MOD_ID, name));
        BlockItem block_item = new BlockItem(block, new Item.Properties()
                .setId(item_key).useBlockDescriptionPrefix());

        block_item.registerBlocks(Item.BY_BLOCK, block_item);
        Registry.register(BuiltInRegistries.ITEM, item_key, block_item);

        return Registry.register(BuiltInRegistries.BLOCK, block_key, block);
    }

    public static void bootstrap() {
    }
}
