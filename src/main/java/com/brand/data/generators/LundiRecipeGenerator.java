package com.brand.data.generators;

import com.brand.content.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;

import java.util.function.Consumer;

public class LundiRecipeGenerator extends FabricRecipeProvider {
    public LundiRecipeGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.DIAMOND_HORN).input(Items.GOAT_HORN).input(Items.DIAMOND).criterion(hasItem(Items.GOAT_HORN), conditionsFromItem(Items.GOAT_HORN)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.HORNY_HORN).input('H', Items.GOAT_HORN).input('R', Items.RED_WOOL).input('I', Items.IRON_BLOCK).input('D', Items.BLUE_DYE).pattern("RRH").pattern(" I ").pattern(" D ").criterion(hasItem(Items.GOAT_HORN), conditionsFromItem(Items.GOAT_HORN)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.CLOWN_HORN).input('H', Items.GOAT_HORN).input('G', Items.GOLD_BLOCK).input('C', Items.COAL_BLOCK).pattern("CGH").criterion(hasItem(Items.GOAT_HORN), conditionsFromItem(Items.GOAT_HORN)).offerTo(exporter);

    }
}