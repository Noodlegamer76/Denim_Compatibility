package com.noodlegamer76.denim.client.renderer;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Axis;
import com.noodlegamer76.denim.event.RegisterShadersEvent;
import com.noodlegamer76.denim.event.RenderEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL44;

public class SkyBoxRenderer {

    public static void render(PoseStack poseStack, int ticks, float partialTick, int alpha, float speed,
                              ResourceLocation frontTexture, ResourceLocation backTexture, ResourceLocation leftTexture,
                              ResourceLocation rightTexture, ResourceLocation topTexture, ResourceLocation bottomTexture) {

        RenderSystem.enableBlend();
        RenderSystem.setShader(GameRenderer::getPositionTexShader);


        //GL44.glEnable(GL44.GL_STENCIL_TEST);
        //RenderSystem.stencilMask(0xFF);
        //RenderSystem.stencilFunc(GL44.GL_EQUAL, 1, 0xFF);
        //RenderSystem.stencilOp(GL44.GL_KEEP, GL44.GL_KEEP, GL44.GL_KEEP);


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
            float far = (float) Minecraft.getInstance().gameRenderer.getRenderDistance();
            Matrix4f matrix4f = poseStack.last().pose();
            bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX_COLOR);
            bufferbuilder.vertex(matrix4f, -far, -far, -far).uv(0.0F, 0.0F).color(255, 255, 255, alpha).endVertex();
            bufferbuilder.vertex(matrix4f, -far, -far, far).uv(0.0F, 1.0F).color(255, 255, 255, alpha).endVertex();
            bufferbuilder.vertex(matrix4f, far, -far, far).uv(1.0F, 1.0F).color(255, 255, 255, alpha).endVertex();
            bufferbuilder.vertex(matrix4f, far, -far, -far).uv(1.0F, 0.0F).color(255, 255, 255, alpha).endVertex();
            tesselator.end();
            poseStack.popPose();
        }

        RenderSystem.depthMask(true);
        //GL44.glDisable(GL44.GL_STENCIL_TEST);
        RenderSystem.disableBlend();
    }

    public static void render4(PoseStack poseStack, int ticks, float partialTick, int alpha, float speed,
                              ResourceLocation frontTexture, ResourceLocation backTexture, ResourceLocation leftTexture,
                              ResourceLocation rightTexture, ResourceLocation topTexture, ResourceLocation bottomTexture) {


        RenderSystem.enableBlend();
        RenderSystem.depthMask(true);
        RenderSystem.setShader(() -> RegisterShadersEvent.test);

        GlStateManager._glUseProgram(RegisterShadersEvent.test.getId());


        //RenderSystem.activeTexture(GL44.GL_TEXTURE0);
        //RenderSystem.bindTexture(RenderEvents.skyboxTexture);
        //RenderSystem.glUniform1i(GL44.glGetUniformLocation(RegisterShadersEvent.test.getId(), "Skybox"), 0);
//////
        //RenderSystem.activeTexture(GL44.GL_TEXTURE1);
        //RenderSystem.bindTexture(RenderEvents.stencilBufferTexture);
        //RenderSystem.glUniform1i(GL44.glGetUniformLocation(RegisterShadersEvent.test.getId(), "SkyboxDepth"), 1);
//////
        //RenderSystem.activeTexture(GL44.GL_TEXTURE2);
        //RenderSystem.bindTexture(Minecraft.getInstance().getMainRenderTarget().getDepthTextureId());
        //RenderSystem.glUniform1i(GL44.glGetUniformLocation(RegisterShadersEvent.test.getId(), "MainDepth"), 2);


        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder bufferbuilder = tesselator.getBuilder();
        for(int i = 0; i < 6; ++i) {
            poseStack.pushPose();
            poseStack.mulPose(Axis.YN.rotationDegrees((ticks + partialTick) * speed));
            if (i == 0) {
               // RenderSystem.setShaderTexture(0, frontTexture);
                poseStack.mulPose(Axis.XP.rotationDegrees(-90.0F));
                poseStack.mulPose(Axis.ZP.rotationDegrees(0.0F));
                poseStack.mulPose(Axis.YN.rotationDegrees(180));
            }

            if (i == 1) {
               // RenderSystem.setShaderTexture(0, rightTexture);
                poseStack.mulPose(Axis.XP.rotationDegrees(0.0F));
                poseStack.mulPose(Axis.ZP.rotationDegrees(-90.0F));
                poseStack.mulPose(Axis.YN.rotationDegrees(-90));
            }

            if (i == 2) {
               // RenderSystem.setShaderTexture(0, leftTexture);
                poseStack.mulPose(Axis.ZP.rotationDegrees(90.0F));
                poseStack.mulPose(Axis.ZP.rotationDegrees(0.0F));
                poseStack.mulPose(Axis.YN.rotationDegrees(90));
            }

            if (i == 3) {
               // RenderSystem.setShaderTexture(0, backTexture);
                poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
                poseStack.mulPose(Axis.ZP.rotationDegrees(0.0F));
            }

            if (i == 4) {
               // RenderSystem.setShaderTexture(0, bottomTexture);
                poseStack.mulPose(Axis.XP.rotationDegrees(0.0F));
                poseStack.mulPose(Axis.ZP.rotationDegrees(0.0F));
                poseStack.mulPose(Axis.YN.rotationDegrees(180));
            }

            if (i == 5) {
               // RenderSystem.setShaderTexture(0, topTexture);
                poseStack.mulPose(Axis.XP.rotationDegrees(180.0F));
                poseStack.mulPose(Axis.ZP.rotationDegrees(0.0F));
                poseStack.mulPose(Axis.YN.rotationDegrees(180));
            }

            Matrix4f matrix4f = poseStack.last().pose();
            float far = (float) Minecraft.getInstance().gameRenderer.getMainCamera().getNearPlane().getBottomRight().length() * 2;
            bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION);
            bufferbuilder.vertex(matrix4f, -far, -far, -far).uv(0.0F, 0.0F).color(255, 255, 255, alpha).endVertex();
            bufferbuilder.vertex(matrix4f, -far, -far, far).uv(0.0F, 1.0F).color(255, 255, 255, alpha).endVertex();
            bufferbuilder.vertex(matrix4f, far, -far, far).uv(1.0F, 1.0F).color(255, 255, 255, alpha).endVertex();
            bufferbuilder.vertex(matrix4f, far, -far, -far).uv(1.0F, 0.0F).color(255, 255, 255, alpha).endVertex();
            tesselator.end();
            poseStack.popPose();
        }

        GL44.glDisable(GL44.GL_STENCIL_TEST);

        RenderSystem.depthMask(false);
        RenderSystem.disableBlend();

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
    public static void renderSimple4(PoseStack poseStack, ResourceLocation folder) {
        SkyBoxRenderer.render4(poseStack, 0, 0, 255, 0,
                folder.withSuffix("/front.png"),
                folder.withSuffix("/back.png"),
                folder.withSuffix("/left.png"),
                folder.withSuffix("/right.png"),
                folder.withSuffix("/top.png"),
                folder.withSuffix("/bottom.png")
        );
    }
}
