package com.kain24.quickconsume;

import net.minecraftforge.common.config.Config;

@Config(modid = QuickConsume.MODID)
public class QCConfig {
    @Config.Name("Ignore Always Edible")
    @Config.Comment({
            "Food items like golden apples can be \"always edible\",",
            "meaning that it can be consumed even if the player isn't hungry.",
            "As opposed to food like cookies or cooked chicken,",
            "where the player can only eat them if they are hungry.",
            "",
            "Set this to true if you want to be able to eat ANYTHING regardless of hunger.",
            "NOTE: This is meant for clients only! Changing this on the server will affect nothing."
    })
    public static boolean ignoreAlwaysEdible = false;

    @Config.Name("Auto Consume")
    @Config.Comment("NOTE: The server configuration will have priority than the client!")
    public static AutoConsume autoConsume = new AutoConsume();

    public static class AutoConsume {
        @Config.Name("Auto Consume Enabled")
        @Config.Comment("Toggles auto consumption")
        public boolean autoConsumeEnabled = true;

        @Config.Name("Optimize Consumption")
        @Config.Comment({
                "If true, optimizes the auto consumption by only consuming food",
                "when the player is hungry by the same amount that the food restores.",
                "",
                "Example: say the player has cooked steak in their food slot. If this value is true,",
                "the steak will only be consumed when the player is missing 8 points of hunger (since",
                "steak restores 8 points of hunger"
        })
        public boolean optConsumption = false;

        @Config.Name("Auto Consume Tick Timer")
        @Config.RangeInt(min = 1)
        @Config.Comment({
                "The amount of ticks the client waits before checking to see if they can consume from the food slot.",
                "This config is available for performance issues (if existing).",
                "Higher = Better Performance, but less time in-between auto consumption"
        })
        public int autoConsumeTickTimer = 10;
    }
}
