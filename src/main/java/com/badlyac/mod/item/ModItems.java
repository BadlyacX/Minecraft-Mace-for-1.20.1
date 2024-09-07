package com.badlyac.mod.item;

import com.badlyac.mod.Main;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Main.MODID);

    public static final RegistryObject<Item> MACE = ITEMS.register("mace", () -> new Mace(Tiers.DIAMOND, 2, -3.4f, new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> BREEZE_ROD = ITEMS.register("breeze_rod", ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> HEAVY_CORE = ITEMS.register("heavy_core", ()-> new Item(new Item.Properties()));

    public static void register(IEventBus ieventBus) {
        ITEMS.register(ieventBus);
    }
}
