package com.brand;

import com.brand.content.ItemGroup;
import com.brand.content.ModBlocks;
import com.brand.content.ModEnchantments;
import com.brand.content.ModItems;
import com.brand.content.handlers.SoumisEnchantmentHandler;
import com.brand.content.sounds.ModSounds;
import com.google.common.reflect.Reflection;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lundi implements ModInitializer {
    public static final String MOD_ID = "lundi";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static Identifier id(String name) {
        return new Identifier(MOD_ID, name);
    }

    @Override
    public void onInitialize() {
        Reflection.initialize(ModItems.class);
        Reflection.initialize(ModBlocks.class);
        Reflection.initialize(ModSounds.class);
        Reflection.initialize(ModEnchantments.class);
        ItemGroup.init();

        SoumisEnchantmentHandler.init();
    }
}