package com.badlyac.mod.item;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class AnvilEventHandler {

    @SubscribeEvent
    public static void onAnvilUpdate(AnvilUpdateEvent event) {
        ItemStack left = event.getLeft();
        ItemStack right = event.getRight();

        if (left.getItem() == ModItems.MACE.get() && right.getItem() == ModItems.BREEZE_ROD.get()) {
            ItemStack output = left.copy();
            output.setDamageValue(left.getDamageValue() - 100);

            event.setOutput(output);
            event.setCost(1);
            event.setMaterialCost(1);
        }
    }
}
