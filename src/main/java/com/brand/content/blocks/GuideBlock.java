package com.brand.content.blocks;

import com.brand.content.sounds.ModSounds;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;
import net.minecraft.util.math.random.Random;

import java.text.DecimalFormat;

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
        if (state.get(POWERED)) {
            return ActionResult.CONSUME;
        } else {
            powerOn(state, world, pos);
            playSound(world, pos);
            giveSpawnDistance(player, world, pos); // <-- Pass player to display spawn distance
            world.emitGameEvent(player, GameEvent.BLOCK_ACTIVATE, pos);
            return ActionResult.success(world.isClient);
        }
    }

    public void powerOn(BlockState state, World world, BlockPos pos) {
        world.setBlockState(pos, state.with(POWERED, true), 3);
        world.updateNeighborsAlways(pos, this);
        world.scheduleBlockTick(pos, this, 20);
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
        double deltaX = spawnPos.getX() - pos.getX();
        double deltaY = spawnPos.getY() - pos.getY();
        double deltaZ = spawnPos.getZ() - pos.getZ();

        DecimalFormat df = new DecimalFormat("#.#");
        String distanceX = df.format(deltaX);
        String distanceY = df.format(deltaY);
        String distanceZ = df.format(deltaZ);

        // Concatenate messages
        Text message = Text.literal("")
                .append(Text.literal("X: " + distanceX)
                        .styled(style -> style.withColor(TextColor.parse("#f54040"))))
                .append(Text.literal(" Y: " + distanceY)
                        .styled(style -> style.withColor(TextColor.parse("#61fa61"))))
                .append(Text.literal(" Z: " + distanceZ)
                        .styled(style -> style.withColor(TextColor.parse("#fd6c9e"))));

        // Send message to player
        player.sendMessage(message, true); // false for chat message
    }

    static {
        POWERED = Properties.POWERED;
        FACING = HorizontalFacingBlock.FACING;
    }
}
