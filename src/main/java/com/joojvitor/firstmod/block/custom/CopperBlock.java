package com.joojvitor.firstmod.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;

public class CopperBlock extends Block {
    public CopperBlock(Properties properties) {
        super(properties);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onBlockClicked(BlockState blockState, World world, BlockPos blockPos, PlayerEntity player) {
        if(world.isRemote) {
            LogManager.getLogger().info("Hello you left clicked on me, Sir");
        }

        super.onBlockClicked(blockState, world, blockPos, player);
    }

    @Override
    public void onFallenUpon(World world, BlockPos blockPos, Entity entity, float fallDistance) {
        if (world.isRemote) {
            LogManager.getLogger().info(String.format("%s", fallDistance));
        }

        super.onFallenUpon(world, blockPos, entity, fallDistance);
    }
}
