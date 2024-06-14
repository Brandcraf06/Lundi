package com.brand;

import com.brand.content.ModParticles;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.minecraft.client.particle.FireworksSparkParticle;

public class LundiClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ParticleFactoryRegistry.getInstance().register(ModParticles.ITEM_DIAMOND, FireworksSparkParticle.ExplosionFactory::new);
    }

}
