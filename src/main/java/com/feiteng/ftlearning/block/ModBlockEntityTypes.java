package com.feiteng.ftlearning.block;

import com.feiteng.ftlearning.FTLearning;
import com.feiteng.ftlearning.block.custom.EssenceExtractorBlockEntity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class ModBlockEntityTypes {
    public static final BlockEntityType<EssenceExtractorBlockEntity> ESSENCE_EXTRACTOR = register(
            "essence_extractor", EssenceExtractorBlockEntity::new, ModBlocks.ESSENCE_EXTRACTOR);

    private static <T extends BlockEntity> BlockEntityType<T> register(String name,
            FabricBlockEntityTypeBuilder.Factory<? extends T> factory, Block... blocks) {
        Identifier id = Identifier.fromNamespaceAndPath(FTLearning.MOD_ID, name);
        return Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, id,
                FabricBlockEntityTypeBuilder.<T>create(factory, blocks).build());
    }

    public static void bootstrap() {
    }
}
