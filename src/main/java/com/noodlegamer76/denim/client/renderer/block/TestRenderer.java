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
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.PostChain;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import java.util.Objects;

public class TestRenderer<T extends RenderTester> implements BlockEntityRenderer<RenderTester> {
    public static final ResourceLocation SHADER_LOCATION = new ResourceLocation("denim", "shaders/post/linear_fog.json");

    public TestRenderer(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(RenderTester pBlockEntity, float pPartialTick, PoseStack poseStack, MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {
         RenderCubeAroundCamera.createCubeWithShader(poseStack, pBlockEntity, pBuffer);


       // if (Minecraft.getInstance().gameRenderer.currentEffect() == null ||
       //         !(Minecraft.getInstance().gameRenderer.currentEffect().getName().equals("denim:shaders/post/linear_fog.json"))) {
       //     Minecraft.getInstance().gameRenderer.loadEffect(SHADER_LOCATION);
       //     System.out.println("LOADING");
       // }
    }
}

