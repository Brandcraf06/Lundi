package com.brand.content;

import com.brand.Lundi;
import com.brand.content.items.CustomHornItem;
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
import net.minecraft.util.UseAction;

public class ModItems {

    public static final Item MUSIC_DISC_JDG_THEME = register("music_disc_jdg_theme", new MusicDiscItem(12, ModSounds.MUSIC_DISC_JDG_THEME, (new Item.Settings()).maxCount(1).rarity(Rarity.UNCOMMON), 35));
    public static final Item MUSIC_DISC_MINECRAFT = register("music_disc_minecraft", new MusicDiscItem(13, ModSounds.MUSIC_DISC_MINECRAFT, (new Item.Settings()).maxCount(1).rarity(Rarity.RARE), 79));
    public static final Item DIAMOND_HORN = register("diamond_horn", new CustomHornItem(new Item.Settings(), ModSounds.LIKE_A_DIAMOND, 40, 5.0F, UseAction.NONE));
    public static final Item CLOWN_HORN = register("clown_horn", new CustomHornItem(new Item.Settings(), ModSounds.CLOWN_HORN, 7, 5.0F, UseAction.NONE));
    public static final Item HORNY_HORN = register("horny_horn", new CustomHornItem(new Item.Settings(), ModSounds.AIR_HORN, 20, 5.0F, UseAction.NONE));

    public static final Item JUS_DE_MYNTHOS = register("jus_de_mynthos", new JuiceItem(new Item.Settings().maxCount(16).food(new FoodComponent.Builder().hunger(8).saturationModifier(0.6F)
            .statusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 200, 4, true, false, true), 1.0F)
            .statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 200, 1, true, false, true), 1.0F).alwaysEdible().build())));

    public static Item register(String id, Item item) {
        return Registry.register(Registries.ITEM, Lundi.id(id), item);
    }

}
