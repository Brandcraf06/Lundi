package com.brand.content;

import com.brand.Lundi;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModParticles {

    public static final DefaultParticleType ITEM_DIAMOND = register("item_diamond", FabricParticleTypes.simple());


    public static DefaultParticleType register(String id, DefaultParticleType particle) {
        return Registry.register(Registries.PARTICLE_TYPE, Lundi.id(id), particle);
    }

}
