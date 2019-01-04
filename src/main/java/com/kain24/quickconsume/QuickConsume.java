package com.kain24.quickconsume;

import com.kain24.quickconsume.proxy.ServerProxy;
import net.minecraft.init.Blocks;
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
    public static final String VERSION = "1.12.2-1.0.0.0";

    private static Logger logger;

    @SidedProxy(clientSide = "com.kain24.quickconsume.proxy.ClientProxy", serverSide = "com.kain24.quickconsume.proxy" +
            ".ServerProxy")
    private ServerProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        logger = e.getModLog();
    }

    @EventHandler
    public void init(FMLInitializationEvent e) {
        logger.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }
}
