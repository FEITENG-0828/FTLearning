package com.feiteng.ftlearning.block.custom;

import java.util.function.Consumer;

import org.jspecify.annotations.Nullable;

import com.feiteng.ftlearning.block.ModBlockEntityTypes;
import com.feiteng.ftlearning.item.ModItems;
import com.feiteng.ftlearning.render.EssenceExtractorMenu;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

public class EssenceExtractorBlockEntity extends BaseContainerBlockEntity implements WorldlyContainer {
    public static final String TRANSLATION_KEY = "container.essence_extractor";
    private static final Component DEFAULT_NAME = Component.translatable(TRANSLATION_KEY);

    protected static final int SLOT_INPUT = 0;
    protected static final int SLOT_FUEL = 1;
    protected static final int SLOT_OUTPUT = 2;
    private static final int[] SLOTS_FOR_UP = new int[] { SLOT_INPUT };
    private static final int[] SLOTS_FOR_SIDES = new int[] { SLOT_FUEL };
    private static final int[] SLOTS_FOR_DOWN = new int[] { SLOT_FUEL, SLOT_OUTPUT };
    protected NonNullList<ItemStack> items = NonNullList.withSize(3, ItemStack.EMPTY);

    private int process_timer;
    private int process_total_time;
    protected final ContainerData data_access = new ContainerData() {
        @Override
        public int get(int index) {
            switch (index) {
                case 0:
                    return process_timer;
                case 1:
                    return process_total_time;
                default:
                    return 0;
            }
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
                case 0:
                    process_timer = value;
                    break;
                case 1:
                    process_total_time = value;
                    break;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    };

    public EssenceExtractorBlockEntity(BlockEntityType<?> type,
            BlockPos block_pos, BlockState block_state) {
        super(type, block_pos, block_state);
    }

    public EssenceExtractorBlockEntity(BlockPos block_pos, BlockState block_state) {
        this(ModBlockEntityTypes.ESSENCE_EXTRACTOR, block_pos, block_state);
    }

    @Override
    public int getContainerSize() {
        return items.size();
    }

    @Override
    protected Component getDefaultName() {
        return DEFAULT_NAME;
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return items;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> list) {
        items = list;
    }

    @Override
    protected AbstractContainerMenu createMenu(int container_id, Inventory inventory) {
        return new EssenceExtractorMenu(container_id, inventory, this, data_access);
    }

    @Override
    protected void loadAdditional(ValueInput value_input) {
        super.loadAdditional(value_input);

        items = NonNullList.withSize(getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(value_input, items);
        process_timer = value_input.getIntOr("process_timer", 0);
        process_total_time = value_input.getIntOr("process_total_time", 0);
    }

    @Override
    protected void saveAdditional(ValueOutput value_output) {
        super.saveAdditional(value_output);

        ContainerHelper.saveAllItems(value_output, items);
        value_output.putInt("process_timer", process_timer);
        value_output.putInt("process_total_time", process_total_time);
    }

    @Override
    public CompoundTag getUpdateTag(Provider provider) { //TODO: when
        return saveWithoutMetadata(provider);
    }

    public static void serverTick(Level level, BlockPos block_pos,
            BlockState block_state, EssenceExtractorBlockEntity block_entity) {
        Consumer<Boolean> set_block_state_processing = is_processing -> level.setBlock(block_pos,
                block_state.setValue(EssenceExtractorBlock.PROCESSING, is_processing), 3);

        ItemStack recipe_output = checkRecipe(block_entity);
        if (recipe_output.isEmpty()) {
            resetProgress(block_entity);
            set_block_state_processing.accept(false);
            setChanged(level, block_pos, block_state);
            return;
        }
        set_block_state_processing.accept(true);
        increaseProgress(block_entity);
        if (!checkOutputSlot(block_entity, recipe_output, set_block_state_processing)) {
            setChanged(level, block_pos, block_state);
            return;
        }
        outputItem(block_entity, recipe_output);
        resetProgress(block_entity);
        setChanged(level, block_pos, block_state);
    }

    private static ItemStack checkRecipe(EssenceExtractorBlockEntity block_entity) {
        // TODO: recipe system
        if (block_entity.items.get(SLOT_INPUT).is(Blocks.DIRT.asItem()) &&
                block_entity.items.get(SLOT_INPUT).getCount() >= 9 &&
                block_entity.items.get(SLOT_FUEL).is(ModItems.FIRST_ITEM)) {
            block_entity.process_total_time = 60;
            return new ItemStack(ModItems.SHUODEDAOLI);
        } else {
            return ItemStack.EMPTY;
        }
    }

    private static boolean checkOutputSlot(EssenceExtractorBlockEntity block_entity,
            ItemStack recipe_output, Consumer<Boolean> setBlockStateProcessing) {
        ItemStack existing_output = block_entity.items.get(SLOT_OUTPUT);
        if (block_entity.process_timer < block_entity.process_total_time) {
            return false;
        } else if (existing_output.isEmpty()) {
            return true;
        } else if (!ItemStack.isSameItemSameComponents(existing_output, recipe_output)) {
            setBlockStateProcessing.accept(false);
            return false;
        } else if (existing_output.getCount()
                + recipe_output.getCount() <= existing_output.getMaxStackSize()) {
            return true;
        } else {
            setBlockStateProcessing.accept(false);
            return false;
        }
    }

    private static void outputItem(EssenceExtractorBlockEntity block_entity, ItemStack recipe_output) {
        block_entity.items.get(SLOT_INPUT).shrink(9);
        block_entity.items.get(SLOT_FUEL).shrink(1);
        ItemStack existing_output = block_entity.items.get(SLOT_OUTPUT);
        if (existing_output.isEmpty()) {
            block_entity.items.set(SLOT_OUTPUT, recipe_output);
        } else {
            existing_output.grow(recipe_output.getCount());
        }
    }

    private static void increaseProgress(EssenceExtractorBlockEntity block_entity) {
        if (block_entity.process_timer < block_entity.process_total_time) {
            ++block_entity.process_timer;
        }
    }

    private static void resetProgress(EssenceExtractorBlockEntity block_entity) {
        block_entity.process_timer = 0;
    }

    public static boolean isInputItem(ItemStack item_stack) {
        return item_stack.is(Blocks.DIRT.asItem()); // TODO
    }

    public static boolean isFuelItem(ItemStack item_stack) {
        return item_stack.is(ModItems.FIRST_ITEM); // TODO
    }

    @Override
    public boolean canPlaceItem(int i, ItemStack item_stack) {
        if (i == SLOT_OUTPUT) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public int[] getSlotsForFace(Direction direction) {
        if (direction == Direction.UP) {
            return SLOTS_FOR_UP;
        } else if (direction == Direction.DOWN) {
            return SLOTS_FOR_DOWN;
        } else {
            return SLOTS_FOR_SIDES;
        }
    }

    @Override
    public boolean canPlaceItemThroughFace(int i, ItemStack item_stack, @Nullable Direction direction) {
        return canPlaceItem(i, item_stack);
    }

    @Override
    public boolean canTakeItemThroughFace(int i, ItemStack item_stack, Direction direction) {
        return true;
    }
}
