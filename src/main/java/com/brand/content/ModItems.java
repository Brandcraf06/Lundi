package com.brand.content;

import com.brand.Lundi;
import com.brand.content.items.JuiceItem;
import com.brand.content.sounds.ModSounds;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Rarity;

public class ModItems {

    public static final Item MUSIC_DISC_JDG_THEME = register("music_disc_jdg_theme", new MusicDiscItem(12, ModSounds.MUSIC_DISC_JDG_THEME, (new Item.Settings()).maxCount(1).rarity(Rarity.RARE), 35));
    public static final Item JUS_DE_MYNTHOS = register("jus_de_mynthos", new JuiceItem(new Item.Settings().maxCount(16).food(new FoodComponent.Builder().hunger(8).saturationModifier(0.6F)
            .statusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 200, 4, true, false, true), 1.0F)
            .statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 200, 1, true, false, true), 1.0F).alwaysEdible().build())));

    public static Item register(String id, Item item) {
        return Registry.register(Registries.ITEM, Lundi.id(id), item);
    }

}