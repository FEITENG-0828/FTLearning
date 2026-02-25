package com.feiteng.ftlearning.render;

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
    // private static final int INGREDIENT_SLOT = 0;
    // private static final int FUEL_SLOT = 1;
    // private static final int RESULT_SLOT = 2;
    private static final int SLOT_COUNT = 3;
    private static final int DATA_COUNT = 2;
    // private static final int INV_SLOT_START = 3;
    // private static final int INV_SLOT_END = 30;
    // private static final int USE_ROW_SLOT_START = 30;
    // private static final int USE_ROW_SLOT_END = 39;

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

        addSlot(new Slot(container, 0, 56, 17));
        addSlot(new Slot(container, 1, 56, 53));
        addSlot(new Slot(container, 2, 116, 35));
        addStandardInventorySlots(inventory, 8, 84);
        addDataSlots(container_data);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int i) {
        // TODO
        return ItemStack.EMPTY;
    }

    @Override
    public boolean stillValid(Player player) { // TODO: when
        return essence_extractor.stillValid(player);
    }

}
