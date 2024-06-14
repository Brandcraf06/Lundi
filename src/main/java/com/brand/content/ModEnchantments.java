package com.brand.content;

import com.brand.Lundi;
import com.brand.content.enchantments.SoumisEnchantment;
import com.brand.content.enchantments.SoumisEnchantment100;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModEnchantments {

    public static final Enchantment SOUMIS = register("enchantement_soumis", new SoumisEnchantment());
    public static final Enchantment SOUMIS_100 = register("enchantement_soumis_100", new SoumisEnchantment100());


    public static Enchantment register(String id, Enchantment enchantment) {
        return Registry.register(Registries.ENCHANTMENT, Lundi.id(id), enchantment);
    }

}
