package com.noodlegamer76.denim.client.renderer.block;

import com.mojang.blaze3d.vertex.*;
import com.noodlegamer76.denim.client.renderer.RenderCubeAroundCamera;
import com.noodlegamer76.denim.entity.block.RenderTester;
import com.noodlegamer76.denim.event.RenderEvents;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class TestRenderer<T extends RenderTester> implements BlockEntityRenderer<RenderTester> {
    public static final ResourceLocation SHADER_LOCATION = new ResourceLocation("denim", "shaders/post/linear_fog.json");

    public TestRenderer(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(RenderTester pBlockEntity, float pPartialTick, PoseStack poseStack, MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {
        //int current = GL44.glGetInteger(GL44.GL_FRAMEBUFFER_BINDING);
        //GlStateManager._glBindFramebuffer(GL44.GL_FRAMEBUFFER, Fbo);
//
        //RenderSystem.bindTexture(stencilBufferTexture);

        //int current = GL44.glGetInteger(GL44.GL_FRAMEBUFFER_BINDING);
        //GlStateManager._glBindFramebuffer(GL44.GL_FRAMEBUFFER, Fbo);
        //poseStack.pushPose();
        //poseStack.translate(0.5, -0.5, 0.5);
        //this.model.renderToBuffer(poseStack, pBuffer.getBuffer(ModRenderTypes.TEST_RENDERER), pPackedLight, 0, 1.0F, 1.0F, 1.0F, 1.0F);
//
        //poseStack.popPose();
//
        //GlStateManager._glBindFramebuffer(GL44.GL_FRAMEBUFFER, current);

        RenderEvents.positions.add(pBlockEntity.getBlockPos());
        //RenderCubeAroundCamera.createCubeWithShader(poseStack);

        //GlStateManager._glBindFramebuffer(GL44.GL_FRAMEBUFFER, current);
//
       // if (Minecraft.getInstance().gameRenderer.currentEffect() == null ||
       //         !(Minecraft.getInstance().gameRenderer.currentEffect().getName().equals("denim:shaders/post/linear_fog.json"))) {
//
       //     Minecraft.getInstance().gameRenderer.loadEffect(SHADER_LOCATION);
       //     System.out.println("LOADING");
       // }
    }
}

