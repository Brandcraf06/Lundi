package com.brand.data.generators;

import com.brand.content.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class LundiModelGenerator extends FabricModelProvider {

    public LundiModelGenerator(FabricDataOutput output) {
        super(output);
    }
    @Override
    public void generateBlockStateModels(BlockStateModelGenerator modelGenerator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.JUS_DE_MYNTHOS, Models.GENERATED);
        itemModelGenerator.register(ModItems.MUSIC_DISC_JDG_THEME, Models.GENERATED);

    }
}
