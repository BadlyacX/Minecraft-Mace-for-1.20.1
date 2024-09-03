package com.badlyac.mod.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import org.checkerframework.checker.nullness.qual.NonNull;

public class Mace extends SwordItem {

    public Mace(Tier tier, int attackDamage, float attackSpeed, Item.Properties properties) {
        super(tier, attackDamage, attackSpeed, properties);
    }

    @Override
    public boolean hurtEnemy(@NonNull ItemStack pStack, @NonNull LivingEntity pTarget, @NonNull LivingEntity pAttacker) {
        if (pAttacker instanceof Player player) {
            double verticalSpeed = player.getDeltaMovement().y;

            if (verticalSpeed < 0) {
                double t = (-verticalSpeed + 3.92) / 0.98;
                double calculatedSpeed = (0.98 * t - 1) * 3.92;

                float extraDamage = (float) Math.abs(calculatedSpeed * 6.0);
                pTarget.hurt(player.damageSources().playerAttack(player), extraDamage);

                player.setDeltaMovement(player.getDeltaMovement().x, 0.6 , player.getDeltaMovement().z);
            }
        }
        return super.hurtEnemy(pStack, pTarget, pAttacker);
    }
}
