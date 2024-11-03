package com.noodlegamer76.denim.item;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import static com.noodlegamer76.denim.client.renderer.block.TestRenderer.SHADER_LOCATION;

public class BlindGlassesHelmet extends ArmorItem {
    public BlindGlassesHelmet(ArmorMaterial pMaterial, Type pType, Properties pProperties) {
        super(pMaterial, pType, pProperties);
    }

    @Override
    public void onArmorTick(ItemStack stack, Level level, Player player) {
        if(!level.isClientSide) {
            super.onArmorTick(stack, level, player);
        }

        if (Minecraft.getInstance().gameRenderer.currentEffect() == null ||
                !(Minecraft.getInstance().gameRenderer.currentEffect().getName().equals("denim:shaders/post/linear_fog.json"))) {
            Minecraft.getInstance().gameRenderer.loadEffect(SHADER_LOCATION);
            System.out.println("LOADING");
        }
        super.onArmorTick(stack, level, player);
    }
}
