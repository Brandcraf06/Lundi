package com.brand.content.enchantments;

import com.brand.Lundi;
import com.brand.content.sounds.ModSounds;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.util.ActionResult;

import java.util.HashMap;
import java.util.Map;

public class SoumisEnchantmentHandler {
    public static void init() {
        AttackEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            if (entity instanceof LivingEntity) {
                ItemStack itemStack = player.getStackInHand(hand);
                if (isBook(itemStack)) {
                    processBook(player, itemStack);
                }
            }
            return ActionResult.PASS;
        });
    }

    private static boolean isBook(ItemStack itemStack) {
        Item item = itemStack.getItem();
        return item == Items.WRITABLE_BOOK || item == Items.WRITTEN_BOOK;
    }

    private static void processBook(PlayerEntity player, ItemStack itemStack) {
        NbtCompound tag = itemStack.getNbt();
        if (tag != null && tag.contains("pages", 9)) {
            NbtList pages = tag.getList("pages", 8);
            for (int i = 0; i < pages.size(); i++) {
                String pageContent = pages.getString(i);
                if (pageContent.toLowerCase().contains("soumis")) {
                    enchantedBook(player, itemStack);
                    break;
                }
            }
        }
    }

    private static void enchantedBook(PlayerEntity player, ItemStack itemStack) {
        ItemStack book = new ItemStack(Items.ENCHANTED_BOOK);
        Map<Enchantment, Integer> enchantments = new HashMap<>();
        enchantments.put(Lundi.SOUMIS, 1);
        EnchantmentHelper.set(enchantments, book);
        itemStack.decrement(1);
        for (int j = 0; j < player.getInventory().size(); j++) {
            ItemStack itemStack2 = player.getInventory().getStack(j);
            if (itemStack2.isEmpty()) {
                player.getInventory().setStack(j, book);
                break;
            }
        }
        player.getWorld().playSound(null, player.getX(), player.getY(), player.getZ(), ModSounds.SOUMIS_VA, player.getSoundCategory(), 0.7f, 1.0f);
    }
}
