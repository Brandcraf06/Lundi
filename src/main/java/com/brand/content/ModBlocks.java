package com.brand.content;

import com.brand.Lundi;
import com.brand.content.blocks.GuideBlock;
import com.brand.content.items.JuiceItem;
import com.brand.content.sounds.ModSounds;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.block.enums.Instrument;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Rarity;

public class ModBlocks {

    public static final Block VOTRE_GUIDE = register("votre_guide", new GuideBlock(FabricBlockSettings.create().mapColor(MapColor.BRIGHT_RED).instrument(Instrument.BIT).strength(0.2F).sounds(BlockSoundGroup.WOOD).burnable()));

    public static Block register(String id, Block block, BlockItem item) {
        Registry.register(Registries.BLOCK, Lundi.id(id), block);
        Registry.register(Registries.ITEM, Lundi.id(id), item);
        return block;
    }

    public static Block register(String id, Block block) {
        return register(id, block, new BlockItem(block, new Item.Settings()));
    }
}
