package com.kain24.quickconsume;

import com.kain24.quickconsume.proxy.ServerProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = QuickConsume.MODID, name = QuickConsume.NAME, version = QuickConsume.VERSION)
public class QuickConsume {
    public static final String MODID = "quickconsume";
    public static final String NAME = "Quick Consume";

    /**
     * MCVERSION
     * Always matches the Minecraft version the mod is for.
     * MAJORMOD
     * Removing items, blocks, tile entities, etc.
     * Changing or removing previously existing mechanics.
     * Updating to a new Minecraft version.
     * MAJORAPI
     * Changing the order or variables of enums.
     * Changing return types of methods.
     * Removing public methods altogether.
     * MINOR
     * Adding items, blocks, tile entities, etc.
     * Adding new mechanics.
     * Deprecating public methods. (This is not a MAJORAPI increment since it doesn’t break an API.)
     * PATCH
     * Bugfixes.
     */
    public static final String VERSION = "1.12.2-1.0.1.0";

    public static Logger logger;

    @SidedProxy(clientSide = "com.kain24.quickconsume.proxy.ClientProxy",
            serverSide = "com.kain24.quickconsume.proxy.ServerProxy")
    private static ServerProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        logger = e.getModLog();

        proxy.preInit();
    }

    @EventHandler
    public void init(FMLInitializationEvent e) {
        proxy.init();
    }
}
