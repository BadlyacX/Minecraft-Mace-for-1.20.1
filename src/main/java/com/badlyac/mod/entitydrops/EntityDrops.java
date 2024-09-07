package com.badlyac.mod.entitydrops;

import com.badlyac.mod.item.ModItems;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class EntityDrops{

    @SubscribeEvent
    public static void onEntityDeath(LivingDeathEvent event) {
        var entity = event.getEntity();

        if (entity instanceof Warden) {
            entity.spawnAtLocation(new ItemStack(ModItems.BREEZE_ROD.get()));
        }
        if (entity instanceof EnderDragon) {
            entity.spawnAtLocation(new ItemStack(ModItems.HEAVY_CORE.get()));
        }
    }
}
