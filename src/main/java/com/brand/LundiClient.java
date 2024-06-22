package com.brand;

import com.brand.content.ModBlocks;
import com.brand.content.ModParticles;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.minecraft.client.particle.FireworksSparkParticle;
import net.minecraft.client.render.RenderLayer;

public class LundiClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ParticleFactoryRegistry.getInstance().register(ModParticles.ITEM_DIAMOND, FireworksSparkParticle.ExplosionFactory::new);

        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutoutMipped(), ModBlocks.VOTRE_GUIDE
        );
    }

}
