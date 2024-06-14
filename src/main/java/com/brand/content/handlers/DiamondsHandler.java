package com.brand.content.handlers;

import com.brand.content.ModParticles;
import com.brand.content.sounds.ModSounds;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DiamondsHandler {
    private static final Map<UUID, Long> cooldown = new HashMap<>();

    public static void init() {

        UseBlockCallback.EVENT.register((user, world, hand, hitResult) -> {
            BlockPos blockPos = hitResult.getBlockPos();
            if (world.getBlockState(blockPos).getBlock() == Blocks.DIAMOND_BLOCK && user.getStackInHand(hand).getItem() == Items.DIAMOND) {
                if (!cooldown.containsKey(user.getUuid()) || world.getTime() - cooldown.get(user.getUuid()) >= 35) { // 35 ticks = 1.75 seconds

                    world.playSound(user, blockPos, SoundEvents.BLOCK_AMETHYST_BLOCK_CHIME, SoundCategory.BLOCKS, 2.0F, 0.7F);
                    world.playSound(user, blockPos, ModSounds.LIKE_A_DIAMOND, SoundCategory.BLOCKS, 1.0F, 1.0F);

                    double centerX = blockPos.getX() + 0.5;
                    double centerY = blockPos.getY() + 0.5;
                    double centerZ = blockPos.getZ() + 0.5;

                    for (int i = 0; i < 50; i++) {
                        double offsetX = world.random.nextGaussian() * 0.1;
                        double offsetY = world.random.nextGaussian() * 0.1;
                        double offsetZ = world.random.nextGaussian() * 0.1;
                        world.addParticle(ModParticles.ITEM_DIAMOND, centerX, centerY, centerZ, offsetX, offsetY, offsetZ);
                    }

                    cooldown.put(user.getUuid(), world.getTime());

                    return ActionResult.SUCCESS;
                }
            }
            return ActionResult.PASS;
        });
    }
}
