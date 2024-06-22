package com.brand.content.sounds;

import com.brand.Lundi;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

public class ModSounds {

    public static final SoundEvent MUSIC_DISC_JDG_THEME = register("music_disc.jdg_theme");
    public static final SoundEvent MUSIC_DISC_MINECRAFT = register("music_disc.minecraft");
    public static final SoundEvent SOUMIS_VA = register("soumis_va");
    public static final SoundEvent LIKE_A_DIAMOND = register("like_a_diamond");
    public static final SoundEvent VOTRE_GUIDE = register("votre_guide");

    public static SoundEvent register(String id) {
        Identifier soundId = Lundi.id(id);
        return Registry.register(Registries.SOUND_EVENT, soundId, SoundEvent.of(soundId));
    }
}
