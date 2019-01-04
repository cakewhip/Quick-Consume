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
}
