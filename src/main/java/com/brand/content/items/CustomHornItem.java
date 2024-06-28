package com.brand.content.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;


public class CustomHornItem extends Item {
    private final SoundEvent instrument;
    private final int useDuration;
    private final float soundRange;
    private final UseAction actionType;

    public CustomHornItem(Item.Settings settings, SoundEvent instrument, int useDuration, float soundRange, UseAction actionType) {
        super(settings);
        this.instrument = instrument;
        this.useDuration = useDuration;
        this.soundRange = soundRange;
        this.actionType = actionType;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        user.setCurrentHand(hand);
        playSound(world, user);
        user.getItemCooldownManager().set(this, useDuration);
        user.incrementStat(Stats.USED.getOrCreateStat(this));
        return TypedActionResult.consume(itemStack);
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return useDuration;
    }

    public UseAction getUseAction(ItemStack stack) {
        return actionType;
    }

    private void playSound(World world, PlayerEntity player) {
        world.playSoundFromEntity(player, player, instrument, SoundCategory.RECORDS, soundRange, 1.0F);
        world.emitGameEvent(GameEvent.INSTRUMENT_PLAY, player.getPos(), GameEvent.Emitter.of(player));
    }
}
