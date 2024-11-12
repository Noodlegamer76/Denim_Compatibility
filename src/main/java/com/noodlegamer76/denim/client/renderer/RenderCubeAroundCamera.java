package com.noodlegamer76.denim.client.renderer;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Axis;
import com.noodlegamer76.denim.client.renderer.block.TestRenderer;
import com.noodlegamer76.denim.event.RegisterShadersEvent;
import com.noodlegamer76.denim.event.RenderEvents;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL44;

import java.awt.*;
import java.util.ArrayList;
import java.util.function.IntSupplier;

import static com.noodlegamer76.denim.event.RenderEvents.Fbo;
import static com.noodlegamer76.denim.event.RenderEvents.levelRenderEvent;

public class RenderCubeAroundCamera {

    public static void createCubeWithShader(PoseStack poseStack, BlockEntity entity, MultiBufferSource buffer) {

        //ill fix this code up when i get it working


       // int current = GL44.glGetInteger(GL44.GL_FRAMEBUFFER_BINDING);
       // GL44.glBindFramebuffer(GL44.GL_FRAMEBUFFER, 2);

        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder bufferBuilder = tesselator.getBuilder();


        Camera camera = Minecraft.getInstance().gameRenderer.getMainCamera();

        RenderSystem.enableBlend();
        //GL44.glEnable(GL44.GL_STENCIL_TEST);
        //RenderSystem.stencilMask(0xFF);
        //RenderSystem.stencilFunc(GL44.GL_ALWAYS, 1, 0xFF);
        //RenderSystem.stencilOp(GL44.GL_KEEP, GL44.GL_KEEP, GL44.GL_REPLACE);

        RenderSystem.setShader(() -> RegisterShadersEvent.test);
//
        GlStateManager._glUseProgram(RegisterShadersEvent.test.getId());
////
        RenderSystem.activeTexture(GL44.GL_TEXTURE0);
        RenderSystem.bindTexture(RenderEvents.skyboxTexture);
        RenderSystem.glUniform1i(GL44.glGetUniformLocation(RegisterShadersEvent.test.getId(), "Skybox"), 0);
////
        RenderSystem.activeTexture(GL44.GL_TEXTURE1);
        RenderSystem.bindTexture(RenderEvents.stencilBufferTexture);
        RenderSystem.glUniform1i(GL44.glGetUniformLocation(RegisterShadersEvent.test.getId(), "SkyboxDepth"), 1);
//////
        RenderSystem.activeTexture(GL44.GL_TEXTURE2);
        RenderSystem.bindTexture(Minecraft.getInstance().getMainRenderTarget().getDepthTextureId());
        RenderSystem.glUniform1i(GL44.glGetUniformLocation(RegisterShadersEvent.test.getId(), "MainDepth"), 2);

        //GlStateManager._glUseProgram(GameRenderer.getPositionTexShader().getId());
//
        //RenderSystem.activeTexture(GL44.GL_TEXTURE0);
        //RenderSystem.bindTexture(RenderEvents.skyboxTexture);
        //RenderSystem.glUniform1i(GL44.glGetUniformLocation(GameRenderer.getPositionTexShader().getId(), "Sampler0"), 0);

       //RenderSystem.setShader(GameRenderer::getPositionTexShader);

       //Matrix4f modelViewMat = new Matrix4f();
       //modelViewMat.identity();
       //modelViewMat.rotateY((-camera.getYRot() + 90) / 180);
       //modelViewMat.rotateX((camera.getXRot() + 180) / 360);
       /// modelViewMat.rotate(camera.getYRot(), 0, 1, 0);  // Rotate to camera's yaw
       /// modelViewMat.rotate(camera.getXRot(), 1, 0, 0); // Rotate to camera's pitch

       //RenderSystem.getModelViewMatrix().set(modelViewMat);
//
       //RenderSystem.setShaderTexture(0, RenderEvents.skyboxTexture);
       //RenderSystem.setShaderTexture(1, RenderEvents.skyboxTexture);
       //RenderSystem.setShaderTexture(2, RenderEvents.skyboxTexture);
       //RenderSystem.setShaderTexture(3, RenderEvents.skyboxTexture);

        for (int i = 0; i < 6; i++) {
            poseStack.pushPose();

            //poseStack.translate(-entity.getBlockPos().getX(), -entity.getBlockPos().getY(), -entity.getBlockPos().getZ());
            //poseStack.translate(camera.getPosition().x(), camera.getPosition().y(), camera.getPosition().z());

            switch (i) {
                case 1: poseStack.mulPose(Axis.XP.rotationDegrees(90));
                case 2: poseStack.mulPose(Axis.XP.rotationDegrees(180));
                case 3: poseStack.mulPose(Axis.XP.rotationDegrees(-90));
                case 4: poseStack.mulPose(Axis.ZP.rotationDegrees(-90));
                case 5: poseStack.mulPose(Axis.ZN.rotationDegrees(-90));

            }
            poseStack.translate(0, 1, 0);

            Matrix4f matrix4f = poseStack.last().pose();
            bufferBuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
            bufferBuilder.vertex(matrix4f, -1, 0, -1).color(255, 255, 255, 0).uv(0, 0).uv2(0, 0).normal(0, 0, 0).endVertex();
            bufferBuilder.vertex(matrix4f, 1, 0, -1).color(255, 255, 255, 0).uv(1, 0).uv2(1, 0).normal(0, 0, 0).endVertex();
            bufferBuilder.vertex(matrix4f, 1, 0, 1).color(255, 255, 255, 0).uv(1, 1).uv2(1, 1).normal(0, 0, 0).endVertex();
            bufferBuilder.vertex(matrix4f, -1, 0, 1).color(255, 255, 255, 0).uv(0, 1).uv2(0, 1).normal(0, 0, 0).endVertex();
            tesselator.end();

            poseStack.popPose();

        }

        //for some reason I couldn't get the east plane working so here it is
        //poseStack.translate(-entity.getBlockPos().getX(), -entity.getBlockPos().getY(), -entity.getBlockPos().getZ());
        //poseStack.translate(camera.getPosition().x(), camera.getPosition().y(), camera.getPosition().z());

        poseStack.pushPose();
        poseStack.mulPose(Axis.ZP.rotationDegrees(-90));
        poseStack.translate(0, 1, 0);

        Matrix4f matrix4f = poseStack.last().pose();
        bufferBuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
        bufferBuilder.vertex(matrix4f, -1, 0, -1).color(255, 255, 255, 255).uv(0, 0).uv2(0, 0).normal(0, 0, 0).endVertex();
        bufferBuilder.vertex(matrix4f, 1, 0, -1).color(255, 255, 255, 255).uv(1, 0).uv2(1, 0).normal(0, 0, 0).endVertex();
        bufferBuilder.vertex(matrix4f, 1, 0, 1).color(255, 255, 255, 255).uv(1, 1).uv2(1, 1).normal(0, 0, 0).endVertex();
        bufferBuilder.vertex(matrix4f, -1, 0, 1).color(255, 255, 255, 255).uv(0, 1).uv2(0, 1).normal(0, 0, 0).endVertex();
        tesselator.end();

        poseStack.popPose();

        RenderSystem.disableBlend();

        RenderSystem.applyModelViewMatrix();
        //GL44.glDisable(GL44.GL_STENCIL_TEST);

       // RenderSystem.clearStencil(0);


       // GL44.glBindFramebuffer(GL44.GL_FRAMEBUFFER, current);

    }

    public static void createCubeWithShader(PoseStack poseStack) {

        //ill fix this code up when i get it working


        int current = GL44.glGetInteger(GL44.GL_FRAMEBUFFER_BINDING);
        GL44.glBindFramebuffer(GL44.GL_FRAMEBUFFER, Fbo);

        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder bufferBuilder = tesselator.getBuilder();


        Camera camera = Minecraft.getInstance().gameRenderer.getMainCamera();


        RenderSystem.depthMask(true);
        RenderSystem.enableDepthTest();
        RenderSystem.enableBlend();
        GL44.glEnable(GL44.GL_STENCIL_TEST);
        RenderSystem.stencilMask(0xFF);
        RenderSystem.stencilFunc(GL44.GL_ALWAYS, 1, 0xFF);
        RenderSystem.stencilOp(GL44.GL_KEEP, GL44.GL_KEEP, GL44.GL_REPLACE);

        RenderSystem.setShader(GameRenderer::getPositionShader);

        //GlStateManager._glUseProgram(GameRenderer.getPositionTexShader().getId());
//
        //RenderSystem.activeTexture(GL44.GL_TEXTURE0);
        //RenderSystem.bindTexture(RenderEvents.skyboxTexture);
        //RenderSystem.glUniform1i(GL44.glGetUniformLocation(GameRenderer.getPositionTexShader().getId(), "Sampler0"), 0);

        //RenderSystem.setShader(GameRenderer::getPositionTexShader);

        //Matrix4f modelViewMat = new Matrix4f();
        //modelViewMat.identity();
        //modelViewMat.rotateY((-camera.getYRot() + 90) / 180);
        //modelViewMat.rotateX((camera.getXRot() + 180) / 360);
        /// modelViewMat.rotate(camera.getYRot(), 0, 1, 0);  // Rotate to camera's yaw
        /// modelViewMat.rotate(camera.getXRot(), 1, 0, 0); // Rotate to camera's pitch

        //RenderSystem.getModelViewMatrix().set(modelViewMat);
//
        //RenderSystem.setShaderTexture(0, RenderEvents.skyboxTexture);
        //RenderSystem.setShaderTexture(1, RenderEvents.skyboxTexture);
        //RenderSystem.setShaderTexture(2, RenderEvents.skyboxTexture);
        //RenderSystem.setShaderTexture(3, RenderEvents.skyboxTexture);

        for (int i = 0; i < 6; i++) {
            poseStack.pushPose();

            //poseStack.translate(-entity.getBlockPos().getX(), -entity.getBlockPos().getY(), -entity.getBlockPos().getZ());
            //poseStack.translate(camera.getPosition().x(), camera.getPosition().y(), camera.getPosition().z());

            switch (i) {
                case 1: poseStack.mulPose(Axis.XP.rotationDegrees(90));
                case 2: poseStack.mulPose(Axis.XP.rotationDegrees(180));
                case 3: poseStack.mulPose(Axis.XP.rotationDegrees(-90));
                case 4: poseStack.mulPose(Axis.ZP.rotationDegrees(-90));
                case 5: poseStack.mulPose(Axis.ZN.rotationDegrees(-90));

            }
            poseStack.translate(0, 1, 0);

            Matrix4f matrix4f = poseStack.last().pose();
            bufferBuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION);
            bufferBuilder.vertex(matrix4f, -1, 0, -1).color(255, 255, 255, 0).uv(0, 0).uv2(0, 0).normal(0, 0, 0).endVertex();
            bufferBuilder.vertex(matrix4f, 1, 0, -1).color(255, 255, 255, 0).uv(1, 0).uv2(1, 0).normal(0, 0, 0).endVertex();
            bufferBuilder.vertex(matrix4f, 1, 0, 1).color(255, 255, 255, 0).uv(1, 1).uv2(1, 1).normal(0, 0, 0).endVertex();
            bufferBuilder.vertex(matrix4f, -1, 0, 1).color(255, 255, 255, 0).uv(0, 1).uv2(0, 1).normal(0, 0, 0).endVertex();
            tesselator.end();

            poseStack.popPose();

        }

        //for some reason I couldn't get the east plane working so here it is
        //poseStack.translate(-entity.getBlockPos().getX(), -entity.getBlockPos().getY(), -entity.getBlockPos().getZ());
        //poseStack.translate(camera.getPosition().x(), camera.getPosition().y(), camera.getPosition().z());

        poseStack.pushPose();
        poseStack.mulPose(Axis.ZP.rotationDegrees(-90));
        poseStack.translate(0, 1, 0);

        Matrix4f matrix4f = poseStack.last().pose();
        bufferBuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION);
        bufferBuilder.vertex(matrix4f, -1, 0, -1).color(255, 255, 255, 0).uv(0, 0).uv2(0, 0).normal(0, 0, 0).endVertex();
        bufferBuilder.vertex(matrix4f, 1, 0, -1).color(255, 255, 255, 0).uv(1, 0).uv2(1, 0).normal(0, 0, 0).endVertex();
        bufferBuilder.vertex(matrix4f, 1, 0, 1).color(255, 255, 255, 0).uv(1, 1).uv2(1, 1).normal(0, 0, 0).endVertex();
        bufferBuilder.vertex(matrix4f, -1, 0, 1).color(255, 255, 255, 0).uv(0, 1).uv2(0, 1).normal(0, 0, 0).endVertex();
        tesselator.end();

        poseStack.popPose();

        RenderSystem.disableBlend();

        GL44.glDisable(GL44.GL_STENCIL_TEST);


         GL44.glBindFramebuffer(GL44.GL_FRAMEBUFFER, current);

    }

    public static void createCubeWithShader(PoseStack poseStack, ArrayList<BlockPos> positions) {

        //ill fix this code up when i get it working


        //int current = GL44.glGetInteger(GL44.GL_FRAMEBUFFER_BINDING);
        //GL44.glBindFramebuffer(GL44.GL_FRAMEBUFFER, Fbo);

        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder bufferBuilder = tesselator.getBuilder();


        Camera camera = Minecraft.getInstance().gameRenderer.getMainCamera();


        RenderSystem.depthMask(true);
        RenderSystem.enableDepthTest();
        RenderSystem.enableBlend();
        GL44.glEnable(GL44.GL_STENCIL_TEST);
        RenderSystem.stencilMask(0xFF);
        RenderSystem.stencilFunc(GL44.GL_ALWAYS, 1, 0xFF);
        RenderSystem.stencilOp(GL44.GL_KEEP, GL44.GL_KEEP, GL44.GL_REPLACE);

        RenderSystem.setShader(GameRenderer::getPositionShader);

        for (int j = 0; j < positions.size(); j++) {
            BlockPos pos = positions.get(j);

            for (int i = 0; i < 6; i++) {
                poseStack.pushPose();

                poseStack.translate(-camera.getPosition().x(), -camera.getPosition().y(), -camera.getPosition().z());
                poseStack.translate(pos.getX(), pos.getY(), pos.getZ());

                switch (i) {
                    case 1: poseStack.mulPose(Axis.XP.rotationDegrees(90));
                    case 2: poseStack.mulPose(Axis.XP.rotationDegrees(180));
                    case 3: poseStack.mulPose(Axis.XP.rotationDegrees(-90));
                    case 4: poseStack.mulPose(Axis.ZP.rotationDegrees(-90));
                    case 5: poseStack.mulPose(Axis.ZN.rotationDegrees(-90));

                }
                poseStack.translate(0, 1, 0);

                Matrix4f matrix4f = poseStack.last().pose();
                bufferBuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION);
                bufferBuilder.vertex(matrix4f, -1, 0, -1).color(255, 255, 255, 0).uv(0, 0).uv2(0, 0).normal(0, 0, 0).endVertex();
                bufferBuilder.vertex(matrix4f, 1, 0, -1).color(255, 255, 255, 0).uv(1, 0).uv2(1, 0).normal(0, 0, 0).endVertex();
                bufferBuilder.vertex(matrix4f, 1, 0, 1).color(255, 255, 255, 0).uv(1, 1).uv2(1, 1).normal(0, 0, 0).endVertex();
                bufferBuilder.vertex(matrix4f, -1, 0, 1).color(255, 255, 255, 0).uv(0, 1).uv2(0, 1).normal(0, 0, 0).endVertex();
                tesselator.end();

                poseStack.popPose();

            }

            //for some reason I couldn't get the east plane working so here it is

            poseStack.pushPose();

            poseStack.translate(-camera.getPosition().x(), -camera.getPosition().y(), -camera.getPosition().z());
            poseStack.translate(pos.getX(), pos.getY(), pos.getZ());

            poseStack.mulPose(Axis.ZP.rotationDegrees(-90));
            poseStack.translate(0, 1, 0);

            Matrix4f matrix4f = poseStack.last().pose();
            bufferBuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION);
            bufferBuilder.vertex(matrix4f, -1, 0, -1).color(255, 255, 255, 0).uv(0, 0).uv2(0, 0).normal(0, 0, 0).endVertex();
            bufferBuilder.vertex(matrix4f, 1, 0, -1).color(255, 255, 255, 0).uv(1, 0).uv2(1, 0).normal(0, 0, 0).endVertex();
            bufferBuilder.vertex(matrix4f, 1, 0, 1).color(255, 255, 255, 0).uv(1, 1).uv2(1, 1).normal(0, 0, 0).endVertex();
            bufferBuilder.vertex(matrix4f, -1, 0, 1).color(255, 255, 255, 0).uv(0, 1).uv2(0, 1).normal(0, 0, 0).endVertex();
            tesselator.end();

            poseStack.popPose();
        }
        RenderSystem.disableBlend();
        GL44.glDisable(GL44.GL_STENCIL_TEST);
        //GL44.glBindFramebuffer(GL44.GL_FRAMEBUFFER, current);
        positions.clear();
        }


    public static void createCubeWithShader2(PoseStack poseStack, ArrayList<BlockPos> positions, float partialTicks) {
        // Get a reference to the Tesselator and BufferBuilder for batch rendering
        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder bufferBuilder = tesselator.getBuilder();

        Vec3 camera = Minecraft.getInstance().gameRenderer.getMainCamera().getPosition();

        // Enable necessary OpenGL states
        RenderSystem.depthMask(true);
        RenderSystem.enableDepthTest();
        RenderSystem.enableBlend();
        //GL44.glEnable(GL44.GL_STENCIL_TEST);
        //RenderSystem.stencilMask(0xFF);
        //RenderSystem.stencilFunc(GL44.GL_ALWAYS, 1, 0xFF);
        //RenderSystem.stencilOp(GL44.GL_KEEP, GL44.GL_KEEP, GL44.GL_REPLACE);

        // Set the shader once for the entire batch
        RenderSystem.setShader(() -> RegisterShadersEvent.test);

        //GlStateManager._glUseProgram(RegisterShadersEvent.test.getId());
//
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

        // Begin batching by defining the geometry in a single buffer
        bufferBuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION);

        for (int j = 0; j < positions.size(); j++) {
            BlockPos pos = positions.get(j);

            // Loop through all 6 faces of the cube
            for (int i = 0; i < 6; i++) {
                poseStack.pushPose();
                poseStack.translate(0.5, 0.5, 0.5);

                // Translate the position to the correct location
                poseStack.translate(-camera.x, -camera.y, -camera.z);
                poseStack.translate(pos.getX(), pos.getY(), pos.getZ());

                // Apply the appropriate rotation for each face of the cube
                switch (i) {
                    case 0: break;  // Default orientation, no rotation
                    case 1: poseStack.mulPose(Axis.XP.rotationDegrees(90)); break;
                    case 2: poseStack.mulPose(Axis.XP.rotationDegrees(180)); break;
                    case 3: poseStack.mulPose(Axis.XP.rotationDegrees(-90)); break;
                    case 4: poseStack.mulPose(Axis.ZP.rotationDegrees(-90)); break;
                    case 5: poseStack.mulPose(Axis.ZN.rotationDegrees(-90)); break;
                }

                poseStack.translate(0, -0.5, 0);  // Adjust for quad position
                poseStack.scale(0.5f, 0.5f, 0.5f);

                // Get the transformation matrix for the current face
                Matrix4f matrix4f = poseStack.last().pose();

                // Add the 4 vertices for this quad to the buffer
                bufferBuilder.vertex(matrix4f, -1, 0, -1).color(255, 255, 255, 100).uv(0, 0).uv2(0, 0).normal(0, 0, 0).endVertex();
                bufferBuilder.vertex(matrix4f, 1, 0, -1).color(255, 255, 255, 100).uv(1, 0).uv2(1, 0).normal(0, 0, 0).endVertex();
                bufferBuilder.vertex(matrix4f, 1, 0, 1).color(255, 255, 255, 100).uv(1, 1).uv2(1, 1).normal(0, 0, 0).endVertex();
                bufferBuilder.vertex(matrix4f, -1, 0, 1).color(255, 255, 255, 100).uv(0, 1).uv2(0, 1).normal(0, 0, 0).endVertex();

                poseStack.popPose();  // Restore the original pose
            }
        }

        // After all quads are added to the buffer, draw them in a single call
        tesselator.end();

        // Disable unnecessary OpenGL states after the batch is rendered
        RenderSystem.disableBlend();
        //GL44.glDisable(GL44.GL_STENCIL_TEST);
        positions.clear();
    }

}
