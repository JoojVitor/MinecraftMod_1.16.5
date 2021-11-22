package com.joojvitor.firstmod.util;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;

import java.io.File;

public class Config {
    public static ForgeConfigSpec SERVER_CONFIG;
    public static ForgeConfigSpec CLIENT_CONFIG;

    public static ForgeConfigSpec.IntValue COPPERED_GLOW_DURATION;
    public static ForgeConfigSpec.IntValue COPPERED_GLOW_AMPLIFIER;
    public static ForgeConfigSpec.DoubleValue COPPERED_GLOW_PROBABILITY;

    static {
        ForgeConfigSpec.Builder SERVER_BUILDER = new ForgeConfigSpec.Builder();
        ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();

        glowingDuration(SERVER_BUILDER, CLIENT_BUILDER);
        glowingAmplifier(SERVER_BUILDER, CLIENT_BUILDER);
        glowingProbability(SERVER_BUILDER, CLIENT_BUILDER);

        SERVER_CONFIG = SERVER_BUILDER.build();
        CLIENT_CONFIG = CLIENT_BUILDER.build();
    }

    private static void glowingDuration(ForgeConfigSpec.Builder SERVER_BUILDER,
                                        ForgeConfigSpec.Builder CLIENT_BUILDER) {
        COPPERED_GLOW_DURATION = CLIENT_BUILDER.comment("How long the glowing lasts for coppered apples (in ticks)")
                .defineInRange("glow_duration", 600, 100, Integer.MAX_VALUE);
    }

    private static void glowingAmplifier(ForgeConfigSpec.Builder SERVER_BUILDER,
                                        ForgeConfigSpec.Builder CLIENT_BUILDER) {
        COPPERED_GLOW_AMPLIFIER = CLIENT_BUILDER.comment("Sets amplifier for coppered apples (in ticks)")
                .defineInRange("glow_amplifier", 600, 100, Integer.MAX_VALUE);
    }

    private static void glowingProbability(ForgeConfigSpec.Builder SERVER_BUILDER,
                                        ForgeConfigSpec.Builder CLIENT_BUILDER) {
        COPPERED_GLOW_PROBABILITY = CLIENT_BUILDER.comment("Sets probability to inflict glowing effect when using coppered apple")
                .defineInRange("glow_probability", 1f, 0f, 1f);
    }

    public static void loadConfigFile(ForgeConfigSpec config, String path) {
        final CommentedFileConfig file = CommentedFileConfig.builder(new File(path))
                .sync()
                .autosave()
                .writingMode(WritingMode.REPLACE)
                .build();

        file.load();
        config.setConfig(file);

    }
}
