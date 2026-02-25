package com.feiteng.ftlearning.block.custom;

import com.feiteng.ftlearning.block.ModBlockEntityTypes;
import com.feiteng.ftlearning.item.ModItems;
import com.feiteng.ftlearning.render.EssenceExtractorMenu;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.ContainerHelper;
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

public class EssenceExtractorBlockEntity extends BaseContainerBlockEntity {
    private static final Component DEFAULT_NAME = Component.translatable(
            "container.essence_extractor");

    protected static final int SLOT_INPUT = 0;
    protected static final int SLOT_FUEL = 1;
    protected static final int SLOT_OUTPUT = 2;
    protected NonNullList<ItemStack> items = NonNullList.withSize(3, ItemStack.EMPTY);

    private int progress_timer;
    private int progress_total_time;
    protected final ContainerData data_access = new ContainerData() {
        @Override
        public int get(int index) {
            switch (index) {
                case 0:
                    return progress_timer;
                case 1:
                    return progress_total_time;
                default:
                    return 0;
            }
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
                case 0:
                    progress_timer = value;
                    break;
                case 1:
                    progress_total_time = value;
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

    public EssenceExtractorBlockEntity(BlockPos blockPos, BlockState blockState) {
        this(ModBlockEntityTypes.ESSENCE_EXTRACTOR, blockPos, blockState);
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

        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(value_input, this.items);
        this.progress_timer = value_input.getIntOr("progress_timer", 0);
        this.progress_total_time = value_input.getIntOr("progress_total_time", 0);
    }

    @Override
    protected void saveAdditional(ValueOutput value_output) {
        super.saveAdditional(value_output);

        ContainerHelper.saveAllItems(value_output, this.items);
        value_output.putInt("progress_timer", this.progress_timer);
        value_output.putInt("progress_total_time", this.progress_total_time);
    }

    public static void serverTick(Level level, BlockPos block_pos,
            BlockState block_state, EssenceExtractorBlockEntity block_entity) {
        // TODO setChanged() ?
        ItemStack recipe_output = checkRecipe(block_entity);
        if (recipe_output.isEmpty()) {
            resetProgress(block_entity);
            return;
        }
        increaseProgress(block_entity);
        if (!canOutputItem(block_entity, recipe_output)) {
            return;
        }
        outputItem(block_entity, recipe_output);
        resetProgress(block_entity);
    }

    private static ItemStack checkRecipe(EssenceExtractorBlockEntity block_entity) {
        // TODO: recipe system
        if (block_entity.items.get(SLOT_INPUT).is(Blocks.DIRT.asItem()) &&
                block_entity.items.get(SLOT_INPUT).getCount() >= 9 &&
                block_entity.items.get(SLOT_FUEL).is(ModItems.FIRST_ITEM)) {
            block_entity.progress_total_time = 60;
            return new ItemStack(ModItems.SHUODEDAOLI);
        } else {
            return ItemStack.EMPTY;
        }
    }

    private static boolean canOutputItem(EssenceExtractorBlockEntity block_entity,
            ItemStack recipe_output) {
        ItemStack existing_output = block_entity.items.get(SLOT_OUTPUT);
        if (block_entity.progress_timer < block_entity.progress_total_time) {
            return false;
        } else if (existing_output.isEmpty()) {
            return true;
        } else if (!ItemStack.isSameItemSameComponents(existing_output, recipe_output)) {
            return false;
        } else {
            return existing_output.getCount() + recipe_output.getCount() <= recipe_output.getMaxStackSize();
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
        block_entity.progress_timer++;
    }

    private static void resetProgress(EssenceExtractorBlockEntity block_entity) {
        block_entity.progress_timer = 0;
    }
}
