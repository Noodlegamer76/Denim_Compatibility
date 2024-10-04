package com.noodlegamer76.denim.item;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.noodlegamer76.denim.block.InitBlocks;
import com.noodlegamer76.denim.client.renderer.ModRenderTypes;
import com.noodlegamer76.denim.event.RegisterShadersEvent;
import net.minecraft.SharedConstants;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.io.IOException;
import java.util.Objects;

public class TestItem extends Item {

    //item i use to trigger stuff in the mod
    public TestItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        //  if (pLevel.isClientSide) {
            //      //Minecraft.getInstance().gameRenderer.togglePostEffect();
            //
            //      RenderSystem.setShader(() -> RegisterShadersEvent.test);
            //
            //      RenderSystem.enableBlend();
            //      RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ZERO, GlStateManager.DestFactor.ONE);
            //      Minecraft.getInstance().getMainRenderTarget().blitToScreen(Minecraft.getInstance().getWindow().getWidth(),
                    //              Minecraft.getInstance().getWindow().getHeight(), false);
            //      RenderSystem.disableBlend();
            //      RenderSystem.defaultBlendFunc();
            //
            //  }

        if (pLevel.isClientSide) {
            if (Minecraft.getInstance().gameRenderer.currentEffect() != null &&
                    Minecraft.getInstance().gameRenderer.currentEffect().getName().equals("denim:shaders/post/linear_fog.json")) {
                Minecraft.getInstance().gameRenderer.shutdownEffect();
                System.out.println("YES");
            }
        }

         return super.use(pLevel, pPlayer, pUsedHand);
    }
}
