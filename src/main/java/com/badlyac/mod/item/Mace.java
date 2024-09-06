package com.badlyac.mod.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import org.checkerframework.checker.nullness.qual.NonNull;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Mod.EventBusSubscriber
public class Mace extends SwordItem {

    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private static final int p = 5;

    public Mace(Tier tier, int attackDamage, float attackSpeed, Item.Properties properties) {
        super(tier, attackDamage, attackSpeed, properties);
    }

    @Override
    public boolean hurtEnemy(@NonNull ItemStack pStack, @NonNull LivingEntity pTarget, @NonNull LivingEntity pAttacker) {
        if (pAttacker instanceof Player player) {
            double verticalSpeed = player.getDeltaMovement().y;

            if (verticalSpeed < 0) {
                double height = player.fallDistance;

                double damage = calculateDamage(height, p);

                pTarget.hurt(player.damageSources().playerAttack(player), (float) damage);

                if (player instanceof ServerPlayer serverPlayer) serverPlayer.fallDistance = 0.0F;

                scheduler.schedule(() -> restoreFallDamage(player), 2, TimeUnit.SECONDS);
            }
        }
        return super.hurtEnemy(pStack, pTarget, pAttacker);
    }

    private void restoreFallDamage(Player player) {
        MinecraftForge.EVENT_BUS.register(new Object() {
            @SubscribeEvent
            public void onPlayerFall(LivingFallEvent event) {
                if (event.getEntity() == player) {
                    event.setCanceled(false);
                    MinecraftForge.EVENT_BUS.unregister(this);
                }
            }
        });
    }

    private double calculateDamage(double height, int p) {
        double damagePart1 = Math.min(6, 2 * height);
        double damagePart2 = Math.min(8, height);
        double damagePart3 = (1 + 0.5 * p) * height;
        return damagePart1 + damagePart2 + damagePart3;
    }
}
