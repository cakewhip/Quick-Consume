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
        @Config.RequiresWorldRestart
        @Config.RequiresMcRestart
        @Config.Name("Auto Consume Enabled")
        @Config.Comment({
                "Toggles auto consumption, whether it's enabled from the start or via enchantment.",
                "",
                "NOTE: Disabling this also removes the enchantment!",
                "      A world restart might also be required!"
        })
        public boolean autoConsumeEnabled = true;

        @Config.RequiresWorldRestart
        @Config.RequiresMcRestart
        @Config.Name("Based On Enchantment")
        @Config.Comment({
                "If set to true, the ability to auto consume will be based on",
                "whether or not the player has a helmet enchanted with \"Auto Consume\".",
                "",
                "The enchantment will only exist in-game if this config is enabled"
        })
        public boolean basedOnEnchant = true;

        @Config.Name("Enchantment Rarity")
        @Config.RangeInt(min = 0, max = 3)
        @Config.Comment({
                "Rarity of the enchantment if enabled.",
                "0 = COMMON",
                "1 = UNCOMMON",
                "2 = RARE",
                "3 = VERY_RARE"
        })
        public int enchantRarity = 1;

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
        public int autoConsumeTickTimer = 20;
    }

    @Config.Name("Potion Bag")
    @Config.Comment("NOTE: The server configuration will have priority than the client!")
    public static PotionBag potionBag = new PotionBag();

    public static class PotionBag {
        @Config.RequiresWorldRestart
        @Config.RequiresMcRestart
        @Config.Name("Max Number of Potions In Bag")
        @Config.Comment({
                "Pretty self explanatory. The max number of potions a player can store",
                "inside of the potion bag."
        })
        public int maxStored = 8;
    }
}
