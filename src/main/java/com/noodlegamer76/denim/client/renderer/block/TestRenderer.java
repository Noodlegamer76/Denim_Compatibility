package com.noodlegamer76.denim.client.renderer.block;

import com.eliotlash.mclib.math.functions.limit.Min;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Axis;
import com.noodlegamer76.denim.client.renderer.ModRenderTypes;
import com.noodlegamer76.denim.client.renderer.RenderCubeAroundCamera;
import com.noodlegamer76.denim.entity.block.RenderTester;
import com.noodlegamer76.denim.event.RegisterShadersEvent;
import com.noodlegamer76.denim.event.RenderEvents;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL44;

import java.util.Objects;

import static com.noodlegamer76.denim.event.RenderEvents.Fbo;
import static com.noodlegamer76.denim.event.RenderEvents.stencilBufferTexture;

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


        RenderCubeAroundCamera.createCubeWithShader(poseStack, pBlockEntity, pBuffer);

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

