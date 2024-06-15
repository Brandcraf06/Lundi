package com.brand.content.enchantments;

import com.brand.content.ModEnchantments;
import com.brand.content.sounds.ModSounds;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class SoumisEnchantment extends Enchantment {

    private static int totalHits = 0;
    private static boolean lastHitProcessed = true;

    public SoumisEnchantment() {
        super(Rarity.VERY_RARE, EnchantmentTarget.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    public boolean isTreasure() {
        return true;
    }

    public boolean isAvailableForEnchantedBookOffer() {
        return false;
    }

    public boolean isAvailableForRandomSelection() {
        return false;
    }

    public boolean canAccept(Enchantment other) {
        return super.canAccept(other) && other != ModEnchantments.SOUMIS_100;
    }

    public boolean isAcceptableItem(ItemStack stack) {
        return stack.getItem() == Items.STICK || super.isAcceptableItem(stack);
    }

    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if (!user.getWorld().isClient && target instanceof LivingEntity) {
            if (lastHitProcessed) {
                totalHits++;
                lastHitProcessed = false;
            } else {
                lastHitProcessed = true;
            }
            if (totalHits >= 5 && Math.random() < 0.17) {
                user.getWorld().playSound(null, user.getX(), user.getY(), user.getZ(), ModSounds.SOUMIS_VA, user.getSoundCategory(), 0.7f, 1.0f);
                totalHits = 0;
            }
        }
    }
}