package com.noodlegamer76.denim.client.renderer.block;

import com.eliotlash.mclib.math.functions.limit.Min;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Axis;
import com.noodlegamer76.denim.entity.block.RenderTester;
import com.noodlegamer76.denim.event.RegisterShadersEvent;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class TestRenderer<T extends RenderTester> implements BlockEntityRenderer<RenderTester> {
    public static final ResourceLocation TEXTURE = new ResourceLocation("minecraft", "textures/block/stone.png");

    public TestRenderer(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(RenderTester pBlockEntity, float pPartialTick, PoseStack poseStack, MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {
        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder bufferbuilder = tesselator.getBuilder();

        RenderSystem.setShader(() -> RegisterShadersEvent.test);

        Matrix4f matrix4f = poseStack.last().pose();
        bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX_COLOR);
       // bufferbuilder.vertex(matrix4f, 0, 0, 0).uv(1.0F, 0.0F).color(255, 255, 0, 255).endVertex();
       // bufferbuilder.vertex(matrix4f, 0, 1, 0).uv(0.0F, 1.0F).color(255, 0, 255, 255).endVertex();
       // bufferbuilder.vertex(matrix4f, 1, 1, 0).uv(1.0F, 1.0F).color(0, 255, 255, 255).endVertex();
       // bufferbuilder.vertex(matrix4f, 1, 0, 0).uv(1.0F, 0.0F).color(255, 255, 255, 255).endVertex();
        Camera camera = Minecraft.getInstance().gameRenderer.getMainCamera();
        Vector3f worldCenter = new Vector3f(pBlockEntity.getBlockPos().getX(), pBlockEntity.getBlockPos().getY(), pBlockEntity.getBlockPos().getZ());
        for(int i = 0; i < 4; i++) {
            Vec3 corner;
            switch (i) {
                case 1 : corner = new Vec3(camera.getLeftVector().x, camera.getUpVector().y, camera.getLookVector().z);
                case 2 : corner = new Vec3(-camera.getLeftVector().x, camera.getUpVector().y, camera.getLookVector().z);
                case 3 : corner = new Vec3(-camera.getLeftVector().x, -camera.getUpVector().y, camera.getLookVector().z);
                case 4 : corner = new Vec3(camera.getLeftVector().x, -camera.getUpVector().y, camera.getLookVector().z);
            }

            bufferbuilder.vertex(matrix4f,-worldCenter.x + corner.x, -worldCenter.y() + corner.y, -worldCenter.z() + corner.z).uv(1.0F, 0.0F).color(255, 255, 255, 255).endVertex();
        }
        tesselator.end();
    }
}
