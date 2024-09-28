package com.noodlegamer76.denim.client.renderer.block;

import com.eliotlash.mclib.math.functions.limit.Min;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Axis;
import com.noodlegamer76.denim.entity.block.RenderTester;
import com.noodlegamer76.denim.event.RegisterShadersEvent;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.PostChain;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import java.util.Objects;

public class TestRenderer<T extends RenderTester> implements BlockEntityRenderer<RenderTester> {
    public static final ResourceLocation SHADER_LOCATION = new ResourceLocation("denim", "shaders/post/test.json");

    public TestRenderer(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(RenderTester pBlockEntity, float pPartialTick, PoseStack poseStack, MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {
       // Tesselator tesselator = Tesselator.getInstance();
       // BufferBuilder bufferbuilder = tesselator.getBuilder();
//
       // RenderSystem.setShader(() -> RegisterShadersEvent.test);
//
       // Matrix4f matrix4f = poseStack.last().pose();
       // bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);
       // Camera camera = Minecraft.getInstance().gameRenderer.getMainCamera();
       // Vec3 worldCenter = new Vec3(-pBlockEntity.getBlockPos().getX(), -pBlockEntity.getBlockPos().getY(), -pBlockEntity.getBlockPos().getZ());
       // Camera.NearPlane near = camera.getNearPlane();
       // //Vec3 last = new Vec3(worldCenter.x + neat, worldCenter.y + cameraPos.y, worldCenter.z + near.);
//
       // bufferbuilder.vertex(matrix4f, (float) near.getBottomLeft().x, (float) near.getBottomLeft().y, (float) near.getBottomLeft().z)
       //         .color(255, 255, 255, 255).endVertex();
       // bufferbuilder.vertex(matrix4f, (float) near.getTopLeft().x, (float) near.getTopLeft().y, (float) near.getTopLeft().z)
       //         .color(255, 255, 255, 255).endVertex();
       // bufferbuilder.vertex(matrix4f, (float) near.getTopRight().x, (float) near.getTopRight().y, (float) near.getTopRight().z)
       //         .color(255, 255, 255, 255).endVertex();
       // bufferbuilder.vertex(matrix4f, (float) near.getBottomRight().x, (float) near.getBottomRight().y, (float) near.getBottomRight().z)
       //         .color(255, 255, 255, 255).endVertex();
//
       //tesselator.end();


        Minecraft.getInstance().gameRenderer.loadEffect(SHADER_LOCATION);
       // RenderSystem.setShader(() -> RegisterShadersEvent.test);
//
       // RenderSystem.enableBlend();
       // Minecraft.getInstance().getMainRenderTarget().blitToScreen(Minecraft.getInstance().getWindow().getWidth(),
       //         Minecraft.getInstance().getWindow().getHeight(), false);
       // RenderSystem.disableBlend();
    }
}
