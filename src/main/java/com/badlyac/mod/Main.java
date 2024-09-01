package com.badlyac.mod;

import com.badlyac.mod.block.ModBlocks;
import com.badlyac.mod.item.ModItems;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.logging.Logger;

@Mod(Main.MODID)
public class Main {
    public static final String MODID = "mace";
    private static final Logger LOGGER = Logger.getLogger(MODID);

    public Main() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);

        modEventBus.addListener(this::addCreative);

    }

    public static void info(String s) {
        LOGGER.info(s);
    }

    @SubscribeEvent
    public static void onServerStarting(ServerStartingEvent event) {
        info("Hello Minecraft!");
        info("Initializing Mace...");
    }

    @SubscribeEvent
    public static void onSererStarted(ServerStartedEvent event) {
        info("Mace is ready!");
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.COMBAT) event.accept(ModItems.MACE);
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.BREEZE_ROD);
            event.accept(ModItems.HEAVY_CORE);
        }
    }
}

