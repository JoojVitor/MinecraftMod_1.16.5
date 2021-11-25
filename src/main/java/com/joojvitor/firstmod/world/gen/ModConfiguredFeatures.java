package com.joojvitor.firstmod.world.gen;

import com.joojvitor.firstmod.block.ModBlocks;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;

public class ModConfiguredFeatures {
    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> REDWOOD =
            register("oak", Feature.TREE.withConfiguration((
                    new BaseTreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(ModBlocks.REDWOOD_LOG.get().getDefaultState()),
                    new SimpleBlockStateProvider(ModBlocks.REDWOOD_LEAVES.get().getDefaultState()),
                    new BlobFoliagePlacer(FeatureSpread.create(2), FeatureSpread.create(0), 3),
                    new StraightTrunkPlacer(7, 3, 3),
                    new TwoLayerFeature(1, 0, 1)))
                    .setIgnoreVines()
                    .build()));

    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String key, ConfiguredFeature<FC, ?> configuredFeature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, key, configuredFeature);
    }
}
