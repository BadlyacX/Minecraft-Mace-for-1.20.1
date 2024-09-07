package com.badlyac.mod.item;

import com.badlyac.mod.Main;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Main.MODID);

    public static final RegistryObject<SoundEvent> MACE_SMASH_HIT = registerSoundEvent("mace_smash_hit");
    public static final RegistryObject<SoundEvent> MACE_HIT = registerSoundEvent("mace_hit");

    public static RegistryObject<SoundEvent> registerSoundEvent(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Main.MODID, name)));
    }

    public static void register(IEventBus ieventBus) {
        SOUND_EVENTS.register(ieventBus);
    }
}
