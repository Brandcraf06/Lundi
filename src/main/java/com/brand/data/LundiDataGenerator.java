package com.brand.data;

import com.brand.data.generators.LundiModelGenerator;
import com.brand.data.generators.LundiRecipeGenerator;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.resource.conditions.v1.ConditionJsonProvider;
import net.fabricmc.fabric.api.resource.conditions.v1.DefaultResourceConditions;

public class LundiDataGenerator implements DataGeneratorEntrypoint {
    public static ConditionJsonProvider getLoadCondition(String... modIds) {
        return DefaultResourceConditions.allModsLoaded(modIds);
    }

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator dataGenerator) {

        final FabricDataGenerator.Pack pack = dataGenerator.createPack();

        pack.addProvider(LundiRecipeGenerator::new);
        pack.addProvider(LundiModelGenerator::new);
    }
}
