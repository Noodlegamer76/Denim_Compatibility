package com.noodlegamer76.denim.client.renderer;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Axis;
import com.noodlegamer76.denim.event.RegisterShadersEvent;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL44;

import java.awt.*;
import java.util.function.IntSupplier;

public class RenderCubeAroundCamera {

    public static void createCubeWithShader(PoseStack poseStack, BlockEntity entity, MultiBufferSource buffer) {

        //ill fix this code up when i get it working


       // int current = GL44.glGetInteger(GL44.GL_FRAMEBUFFER_BINDING);
       // GL44.glBindFramebuffer(GL44.GL_FRAMEBUFFER, 2);

        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder bufferBuilder = tesselator.getBuilder();

        VertexConsumer vertex = buffer.getBuffer(RenderType.armorEntityGlint());


        Camera camera = Minecraft.getInstance().gameRenderer.getMainCamera();

        RenderSystem.enableBlend();
        //RenderSystem.stencilMask(0xFF);
        //RenderSystem.stencilFunc(GL44.GL_LESS, 1, 0xFF);
        //RenderSystem.stencilOp(GL44.GL_ZERO, GL44.GL_ZERO, GL44.GL_KEEP);

        RenderSystem.setShader(() -> RegisterShadersEvent.skybox1);

        for (int i = 0; i < 6; i++) {
            poseStack.pushPose();

            poseStack.translate(-entity.getBlockPos().getX(), -entity.getBlockPos().getY(), -entity.getBlockPos().getZ());
            poseStack.translate(camera.getPosition().x(), camera.getPosition().y(), camera.getPosition().z());

            switch (i) {
                case 1: poseStack.mulPose(Axis.XP.rotationDegrees(90));
                case 2: poseStack.mulPose(Axis.XP.rotationDegrees(180));
                case 3: poseStack.mulPose(Axis.XP.rotationDegrees(-90));
                case 4: poseStack.mulPose(Axis.ZP.rotationDegrees(-90));
                case 5: poseStack.mulPose(Axis.ZN.rotationDegrees(-90));

            }
            poseStack.translate(0, 1, 0);

            Matrix4f matrix4f = poseStack.last().pose();
            bufferBuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.BLOCK);
            bufferBuilder.vertex(matrix4f, -1, 0, -1).color(255, 255, 255, 255).uv(0, 0).uv2(0, 0).normal(0, 0, 0).endVertex();
            bufferBuilder.vertex(matrix4f, 1, 0, -1).color(255, 255, 255, 255).uv(1, 0).uv2(1, 0).normal(0, 0, 0).endVertex();
            bufferBuilder.vertex(matrix4f, 1, 0, 1).color(255, 255, 255, 255).uv(1, 1).uv2(1, 1).normal(0, 0, 0).endVertex();
            bufferBuilder.vertex(matrix4f, -1, 0, 1).color(255, 255, 255, 255).uv(0, 1).uv2(0, 1).normal(0, 0, 0).endVertex();
            tesselator.end();

            poseStack.popPose();

        }

        //for some reason I couldn't get the east plane working so here it is
        poseStack.translate(-entity.getBlockPos().getX(), -entity.getBlockPos().getY(), -entity.getBlockPos().getZ());
        poseStack.translate(camera.getPosition().x(), camera.getPosition().y(), camera.getPosition().z());

        poseStack.pushPose();
        poseStack.mulPose(Axis.ZP.rotationDegrees(-90));
        poseStack.translate(0, 1, 0);

        Matrix4f matrix4f = poseStack.last().pose();
        bufferBuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.BLOCK);
        bufferBuilder.vertex(matrix4f, -1, 0, -1).color(255, 255, 255, 255).uv(0, 0).uv2(0, 0).normal(0, 0, 0).endVertex();
        bufferBuilder.vertex(matrix4f, 1, 0, -1).color(255, 255, 255, 255).uv(1, 0).uv2(1, 0).normal(0, 0, 0).endVertex();
        bufferBuilder.vertex(matrix4f, 1, 0, 1).color(255, 255, 255, 255).uv(1, 1).uv2(1, 1).normal(0, 0, 0).endVertex();
        bufferBuilder.vertex(matrix4f, -1, 0, 1).color(255, 255, 255, 255).uv(0, 1).uv2(0, 1).normal(0, 0, 0).endVertex();
        tesselator.end();

        poseStack.popPose();

        RenderSystem.disableBlend();

       // RenderSystem.clearStencil(0);


       // GL44.glBindFramebuffer(GL44.GL_FRAMEBUFFER, current);

    }
}
