package com.brand.content;

import com.brand.Lundi;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;

public class ItemGroup {

    public static final RegistryKey<net.minecraft.item.ItemGroup> LUNDI = RegistryKey.of(RegistryKeys.ITEM_GROUP, Lundi.id("lundi_itemgroup"));


    public static void init() {
        Registry.register(Registries.ITEM_GROUP, LUNDI, FabricItemGroup.builder()
                .displayName(Text.literal("Lundi"))
                .icon(() -> new ItemStack(ModItems.JUS_DE_MYNTHOS))
                .build()
        );

        ItemGroupEvents.modifyEntriesEvent(LUNDI).register((entries) -> {
            entries.add(ModItems.JUS_DE_MYNTHOS);
            entries.add(ModItems.MUSIC_DISC_JDG_THEME);
        });
    }
}
