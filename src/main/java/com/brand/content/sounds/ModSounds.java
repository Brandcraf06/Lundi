package com.brand.content.sounds;

import com.brand.Lundi;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {

    public static SoundEvent MUSIC_DISC_JDG_THEME = register("music_disc.jdg_theme");
    public static SoundEvent SOUMIS_VA = register("soumis_va");
    public static SoundEvent LIKE_A_DIAMOND = register("like_a_diamond");


    public static SoundEvent register(String id) {
        Identifier soundId = Lundi.id(id);
        return Registry.register(Registries.SOUND_EVENT, soundId, SoundEvent.of(soundId));
    }
}
