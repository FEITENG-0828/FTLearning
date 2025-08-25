package com.feiteng.ftlearning.item.custom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

import org.jetbrains.annotations.Nullable;

import com.feiteng.ftlearning.FTLearning;
import com.feiteng.ftlearning.block.ModBlocks;
import com.feiteng.ftlearning.item.ModItems;
import com.feiteng.ftlearning.sound.ModSoundEvents;
import com.feiteng.ftlearning.tag.ModBlockTags;
import com.feiteng.ftlearning.util.HelpfulFuncs;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtOps;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;

public class AdvancedProspectorItem extends Item {
    public static final int SCAN_CHUNK_RADIUS = 1;
    // public static final Map<Block, Integer> PROSPECT_LIST = new HashMap<>();

    public AdvancedProspectorItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack item_stack = user.getStackInHand(hand);
        if (!world.isClient()) {
            Map<Block, List<BlockPos>> ore_cache = new HashMap<>();

            ChunkPos.stream(user.getChunkPos(), SCAN_CHUNK_RADIUS).forEach(
                chunk_pos -> world.getChunk(chunk_pos.x, chunk_pos.z)
                    .forEachBlockMatchingPredicate(
                        AdvancedProspectorItem::isRightBlock,
                        (pos, state) -> {
                            ore_cache.computeIfAbsent(state.getBlock(), key -> new ArrayList<>())
                                .add(pos.toImmutable());
                        }
                    )
            );

            item_stack.setSubNbt("OreCache", new NbtCompound());
            if (!ore_cache.isEmpty()) {
                user.sendMessage(
                    Text.translatable(getTranslationKey() + ".use.done").formatted(Formatting.GREEN),
                    true);
                world.playSoundFromEntity(null, user,
                    ModSoundEvents.ITEM_PROSPECTOR_USE_SUCCESS,
                    user.getSoundCategory(), 1.0F, 1.0F);
                writeOreCacheNbt(item_stack.getOrCreateSubNbt("OreCache"),
                    world.getRegistryKey(), ore_cache);
                item_stack.damage(3, user, player -> player.sendToolBreakStatus(hand));
            } else {
                user.sendMessage(
                    Text.translatable(getTranslationKey() + ".use.done").formatted(Formatting.RED),
                    true);
                world.playSoundFromEntity(null, user,
                    ModSoundEvents.ITEM_PROSPECTOR_USE_FAILURE,
                    user.getSoundCategory(), 1.0F, 1.0F);
                item_stack.damage(1, user, player -> player.sendToolBreakStatus(hand));
            }
        }
        return TypedActionResult.success(item_stack);
    }

    public static boolean isRightBlock(BlockState state) {
        return state.isIn(ModBlockTags.ADVANCED_PROSPECTOR_LIST);
    }

    @Override
    public boolean canRepair(ItemStack stack, ItemStack ingredient) {
        return ingredient.isOf(ModBlocks.FIRST_ITEM_BLOCK.asItem()) || super.canRepair(stack, ingredient);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world,
                              List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        if (!Screen.hasShiftDown()) {
            tooltip.add(HelpfulFuncs.getTooltipMaskText("shift"));
        } else {
            tooltip.add(Text.translatable(
                ModItems.ADVANCED_PROSPECTOR.getTranslationKey() + ".tooltip").formatted(Formatting.GRAY));
        }
    }

    private static void writeOreCacheNbt(
        NbtCompound root_nbt,
        RegistryKey<World> dimension_key,
        Map<Block, List<BlockPos>> ore_map
    ) {
        World.CODEC.encodeStart(NbtOps.INSTANCE, dimension_key)
            .resultOrPartial(FTLearning.LOGGER::error)
            .ifPresent(nbt_element -> root_nbt.put("ScanDimension", nbt_element));

        NbtList ore_list_nbt = new NbtList();
        for (Map.Entry<Block, List<BlockPos>> entry : ore_map.entrySet()) {
            NbtCompound ore_entry_nbt = new NbtCompound();

            Identifier block_id = Registries.BLOCK.getId(entry.getKey());
            Identifier.CODEC.encodeStart(NbtOps.INSTANCE, block_id)
                .resultOrPartial(FTLearning.LOGGER::error)
                .ifPresent(nbt_element -> ore_entry_nbt.put("BlockId", nbt_element));

            NbtList pos_list_nbt = new NbtList();
            for (BlockPos pos : entry.getValue()) {
                pos_list_nbt.add(NbtHelper.fromBlockPos(pos));
            }
            ore_entry_nbt.put("PosList", pos_list_nbt);

            ore_list_nbt.add(ore_entry_nbt);
        }

        root_nbt.put("OreList", ore_list_nbt);
    }

    public static boolean isInScanDimension(ItemStack stack, World world) {
        if (stack.getSubNbt("OreCache") != null &&
            stack.getSubNbt("OreCache").contains("ScanDimension")) {
            RegistryKey<World> scan_dimension = World.CODEC.parse(NbtOps.INSTANCE,
                stack.getSubNbt("OreCache").get("ScanDimension")).result().get();
            return world.getRegistryKey().equals(scan_dimension);
        }
        return false;
    }

    public static void forEachOre(
        ItemStack stack,
        BiConsumer<? super Block, ? super List<BlockPos>> action
    ) {
        if (stack.getSubNbt("OreCache") != null &&
            stack.getSubNbt("OreCache").contains("OreList")) {
            NbtList ore_list_nbt = stack.getSubNbt("OreCache")
                .getList("OreList", NbtElement.COMPOUND_TYPE);
            for (int i = 0; i < ore_list_nbt.size(); ++i) {
                NbtCompound ore_entry_nbt = ore_list_nbt.getCompound(i);
                if (ore_entry_nbt.contains("BlockId") &&
                    ore_entry_nbt.contains("PosList")) {
                    Block block = Registries.BLOCK.get(Identifier.CODEC.parse(NbtOps.INSTANCE,
                        ore_entry_nbt.get("BlockId")).result().get());
                    NbtList pos_list_nbt =
                        ore_entry_nbt.getList("PosList", NbtElement.COMPOUND_TYPE);
                    if (block != null && !pos_list_nbt.isEmpty()) {
                        List<BlockPos> pos_list = new ArrayList<>();
                        for (int j = 0; j < pos_list_nbt.size(); ++j) {
                            pos_list.add(NbtHelper.toBlockPos(pos_list_nbt.getCompound(j)));
                        }
                        action.accept(block, pos_list);
                    }
                }
            }
        }
    }

    // dimension, per item, render distance, color

}
