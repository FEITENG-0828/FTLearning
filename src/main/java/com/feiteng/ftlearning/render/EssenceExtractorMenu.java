package com.feiteng.ftlearning.render;

import com.feiteng.ftlearning.block.custom.EssenceExtractorBlockEntity;

import net.minecraft.util.Mth;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class EssenceExtractorMenu extends AbstractContainerMenu {
    private static final int SLOT_COUNT = 3;
    private static final int DATA_COUNT = 2;

    private static final int INPUT_SLOT = 0;
    private static final int FUEL_SLOT = 1;
    private static final int OUTPUT_SLOT = 2;
    private static final int INV_SLOT_START = 3;
    private static final int INV_SLOT_END = 39;
    private static final int HOTBAR_SLOT_START = 30;

    private final Container essence_extractor;
    private final ContainerData essence_extractor_data;

    public EssenceExtractorMenu(int container_id, Inventory inventory) {
        this(container_id, inventory, new SimpleContainer(SLOT_COUNT), new SimpleContainerData(DATA_COUNT));
    }

    public EssenceExtractorMenu(int container_id, Inventory inventory,
            Container container, ContainerData container_data) {
        super(ModMenuTypes.ESSENCE_EXTRACTOR, container_id);
        checkContainerSize(container, SLOT_COUNT);
        checkContainerDataCount(container_data, DATA_COUNT);
        essence_extractor = container;
        essence_extractor_data = container_data;

        addSlot(new Slot(container, INPUT_SLOT, 44, 35));
        addSlot(new Slot(container, FUEL_SLOT, 72, 16));
        addSlot(new Slot(container, OUTPUT_SLOT, 116, 35) {
            @Override
            public boolean mayPlace(ItemStack item_stack) {
                return false;
            }
        });
        addStandardInventorySlots(inventory, 8, 84);
        addDataSlots(container_data);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        Slot slot = slots.get(index);
        if (slot == null || !slot.hasItem()) {
            return ItemStack.EMPTY;
        }

        ItemStack source = slot.getItem();
        ItemStack origin = source.copy();
        boolean moved = false;

        if (index == OUTPUT_SLOT) {
            if (moved = moveItemStackTo(source, INV_SLOT_START, INV_SLOT_END, true)) {
                slot.onQuickCraft(source, origin);
            }
        } else if (index == INPUT_SLOT || index == FUEL_SLOT) {
            moved = moveItemStackTo(source, INV_SLOT_START, INV_SLOT_END, false);
        } else if (index >= INV_SLOT_START && index < INV_SLOT_END) {
            if (EssenceExtractorBlockEntity.isInputItem(source)) {
                moved = moveItemStackTo(source, INPUT_SLOT, FUEL_SLOT, false);
            } else if (EssenceExtractorBlockEntity.isFuelItem(source)) {
                moved = moveItemStackTo(source, FUEL_SLOT, OUTPUT_SLOT, false);
            } else if (index >= INV_SLOT_START && index < HOTBAR_SLOT_START) {
                moved = moveItemStackTo(source, HOTBAR_SLOT_START, INV_SLOT_END, false);
            } else if (index >= HOTBAR_SLOT_START && index < INV_SLOT_END) {
                moved = moveItemStackTo(source, INV_SLOT_START, HOTBAR_SLOT_START, false);
            }
        }
        if (!moved || source.getCount() == origin.getCount()) {
            return ItemStack.EMPTY;
        }

        if (source.isEmpty()) {
            slot.setByPlayer(ItemStack.EMPTY);
        } else {
            slot.setChanged();
        }

        slot.onTake(player, source);
        return origin;
    }

    @Override
    public boolean stillValid(Player player) {
        return essence_extractor.stillValid(player);
    }

    public float getProgress() {
        int process_timer = essence_extractor_data.get(0);
        int process_total_time = essence_extractor_data.get(1);
        return (process_total_time != 0 && process_timer != 0)
                ? Mth.clamp((float) process_timer / process_total_time, 0.0F, 1.0F)
                : 0.0F;
    }
}
