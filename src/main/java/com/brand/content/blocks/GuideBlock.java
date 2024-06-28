package com.brand.content.blocks;

import com.brand.content.sounds.ModSounds;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public class GuideBlock extends HorizontalFacingBlock {
    public static final BooleanProperty POWERED;
    public static final DirectionProperty FACING;
    private static final VoxelShape SHAPE = VoxelShapes.union(
            Block.createCuboidShape(1.0, 6.0, 1.0, 15.0, 13.0, 15.0),
            Block.createCuboidShape(4.0, 0.0, 4.0, 12.0, 6.0, 12.0)
    );

    public GuideBlock(FabricBlockSettings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(POWERED, false).with(FACING, Direction.NORTH));
    }

    @Override
    public void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(POWERED, FACING);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing());
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.getRegistryKey() != World.OVERWORLD) {
            player.sendMessage(Text.literal("Non.").styled(style -> style.withColor(Formatting.RED)), true);
            return ActionResult.success(world.isClient);
        }

        if (state.get(POWERED)) {
            return ActionResult.CONSUME;
        } else {
            powerOn(state, world, pos);
            playSound(world, pos);
            giveSpawnDistance(player, world, pos);
            world.emitGameEvent(player, GameEvent.BLOCK_ACTIVATE, pos);
            return ActionResult.success(world.isClient);
        }
    }

    public void powerOn(BlockState state, World world, BlockPos pos) {
        world.setBlockState(pos, state.with(POWERED, true), 3);
        world.updateNeighborsAlways(pos, this);
        world.scheduleBlockTick(pos, this, 17);
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        world.setBlockState(pos, state.with(POWERED, false), 3);
        world.updateNeighborsAlways(pos, this);
    }

    public void playSound(World world, BlockPos pos) {
        world.playSound(null, pos, ModSounds.VOTRE_GUIDE, SoundCategory.BLOCKS, 0.9f, 1.0f);
    }

    public void giveSpawnDistance(PlayerEntity player, World world, BlockPos pos) {
        BlockPos spawnPos = world.getSpawnPos();
        int deltaX = Math.abs(spawnPos.getX() - pos.getX());
        int deltaZ = Math.abs(spawnPos.getZ() - pos.getZ());
        int distance = Math.max(deltaX, deltaZ);

        Text message = Text.literal("Tu es à " + distance + " blocs du spawn ("
                        + spawnPos.getX() + ", " + spawnPos.getY() + ", " + spawnPos.getZ() + ")")
                .styled(style -> style.withColor(Formatting.LIGHT_PURPLE));

        player.sendMessage(message, true);
    }

    static {
        POWERED = Properties.POWERED;
        FACING = HorizontalFacingBlock.FACING;
    }
}
