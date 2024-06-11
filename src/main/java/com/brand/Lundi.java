package com.brand;

import com.brand.content.ItemGroup;
import com.brand.content.ModItems;
import com.brand.content.enchantments.SoumisEnchantmentHandler;
import com.brand.content.enchantments.SoumisEnchantment;
import com.brand.content.sounds.ModSounds;
import com.google.common.reflect.Reflection;
import net.fabricmc.api.ModInitializer;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lundi implements ModInitializer {
	public static final String MOD_ID = "lundi";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final Enchantment SOUMIS = new SoumisEnchantment();

	public static Identifier id(String name) {
		return new Identifier(MOD_ID, name);
	}

	@Override
	public void onInitialize() {
		Reflection.initialize(ModItems.class);
		new ModSounds();
		Registry.register(Registries.ENCHANTMENT, id("enchantement_soumis"), SOUMIS);
		ItemGroup.init();

		SoumisEnchantmentHandler.init();
	}
}