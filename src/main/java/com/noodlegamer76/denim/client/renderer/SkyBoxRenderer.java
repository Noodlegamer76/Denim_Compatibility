package com.noodlegamer76.denim.client.renderer;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Axis;
import com.noodlegamer76.denim.event.RegisterShadersEvent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL44;

public class SkyBoxRenderer {

    public static void render(PoseStack poseStack, int ticks, float partialTick, int alpha, float speed,
                              ResourceLocation frontTexture, ResourceLocation backTexture, ResourceLocation leftTexture,
                              ResourceLocation rightTexture, ResourceLocation topTexture, ResourceLocation bottomTexture) {

        int FBO = 2;
        int current = GL44.glGetInteger(GL44.GL_FRAMEBUFFER_BINDING);
        GL44.glBindFramebuffer(GL44.GL_FRAMEBUFFER, FBO);


        RenderSystem.enableBlend();
        RenderSystem.depthMask(false);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder bufferbuilder = tesselator.getBuilder();
        for(int i = 0; i < 6; ++i) {
            poseStack.pushPose();
            poseStack.mulPose(Axis.YN.rotationDegrees((ticks + partialTick) * speed));
            if (i == 0) {
                RenderSystem.setShaderTexture(0, frontTexture);
                poseStack.mulPose(Axis.XP.rotationDegrees(-90.0F));
                poseStack.mulPose(Axis.ZP.rotationDegrees(0.0F));
                poseStack.mulPose(Axis.YN.rotationDegrees(180));
            }

            if (i == 1) {
                RenderSystem.setShaderTexture(0, rightTexture);
                poseStack.mulPose(Axis.XP.rotationDegrees(0.0F));
                poseStack.mulPose(Axis.ZP.rotationDegrees(-90.0F));
                poseStack.mulPose(Axis.YN.rotationDegrees(-90));
            }

            if (i == 2) {
                RenderSystem.setShaderTexture(0, leftTexture);
                poseStack.mulPose(Axis.ZP.rotationDegrees(90.0F));
                poseStack.mulPose(Axis.ZP.rotationDegrees(0.0F));
                poseStack.mulPose(Axis.YN.rotationDegrees(90));
            }

            if (i == 3) {
                RenderSystem.setShaderTexture(0, backTexture);
                poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
                poseStack.mulPose(Axis.ZP.rotationDegrees(0.0F));
            }

            if (i == 4) {
                RenderSystem.setShaderTexture(0, bottomTexture);
                poseStack.mulPose(Axis.XP.rotationDegrees(0.0F));
                poseStack.mulPose(Axis.ZP.rotationDegrees(0.0F));
                poseStack.mulPose(Axis.YN.rotationDegrees(180));
            }

            if (i == 5) {
                RenderSystem.setShaderTexture(0, topTexture);
                poseStack.mulPose(Axis.XP.rotationDegrees(180.0F));
                poseStack.mulPose(Axis.ZP.rotationDegrees(0.0F));
                poseStack.mulPose(Axis.YN.rotationDegrees(180));
            }

            Matrix4f matrix4f = poseStack.last().pose();
            bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX_COLOR);
            bufferbuilder.vertex(matrix4f, -1.0F, -1.0F, -1.0F).uv(0.0F, 0.0F).color(255, 255, 255, alpha).endVertex();
            bufferbuilder.vertex(matrix4f, -1.0F, -1.0F, 1.0F).uv(0.0F, 1.0F).color(255, 255, 255, alpha).endVertex();
            bufferbuilder.vertex(matrix4f, 1.0F, -1.0F, 1.0F).uv(1.0F, 1.0F).color(255, 255, 255, alpha).endVertex();
            bufferbuilder.vertex(matrix4f, 1.0F, -1.0F, -1.0F).uv(1.0F, 0.0F).color(255, 255, 255, alpha).endVertex();
            tesselator.end();
            poseStack.popPose();
        }

        RenderSystem.depthMask(true);
        RenderSystem.disableBlend();


        GL44.glBindFramebuffer(GL44.GL_FRAMEBUFFER, 1);
    }

    public static void render(PoseStack poseStack, int ticks, float partialTick, int alpha, float speed, ResourceLocation folder) {
        SkyBoxRenderer.render(poseStack, ticks, partialTick, alpha, speed,
                folder.withSuffix("/front.png"),
                folder.withSuffix("/back.png"),
                folder.withSuffix("/left.png"),
                folder.withSuffix("/right.png"),
                folder.withSuffix("/top.png"),
                folder.withSuffix("/bottom.png")
        );
    }

    public static void renderSimple(PoseStack poseStack, ResourceLocation folder) {
        SkyBoxRenderer.render(poseStack, 0, 0, 255, 0,
                folder.withSuffix("/front.png"),
                folder.withSuffix("/back.png"),
                folder.withSuffix("/left.png"),
                folder.withSuffix("/right.png"),
                folder.withSuffix("/top.png"),
                folder.withSuffix("/bottom.png")
        );
    }
}
