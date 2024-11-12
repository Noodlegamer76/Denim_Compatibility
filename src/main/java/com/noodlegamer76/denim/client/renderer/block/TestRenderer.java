package com.noodlegamer76.denim.client.renderer.block;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.noodlegamer76.denim.client.models.BlockModel;
import com.noodlegamer76.denim.client.renderer.ModRenderTypes;
import com.noodlegamer76.denim.client.renderer.RenderCubeAroundCamera;
import com.noodlegamer76.denim.entity.block.RenderTester;
import com.noodlegamer76.denim.event.RenderEvents;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.lwjgl.opengl.GL44;

import static com.noodlegamer76.denim.event.RenderEvents.Fbo;
import static com.noodlegamer76.denim.event.RenderEvents.skyboxTexture;

public class TestRenderer<T extends RenderTester> implements BlockEntityRenderer<RenderTester> {
    public static final ResourceLocation SHADER_LOCATION = new ResourceLocation("denim", "shaders/core/test.json");
    public static BlockModel model;

    public TestRenderer(BlockEntityRendererProvider.Context context) {
        model = new BlockModel<>(context.bakeLayer(BlockModel.LAYER_LOCATION));
    }

    @Override
    public void render(RenderTester pBlockEntity, float pPartialTick, PoseStack poseStack, MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {

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

        //
//
       // if (Minecraft.getInstance().gameRenderer.currentEffect() == null ||
       //         !(Minecraft.getInstance().gameRenderer.currentEffect().getName().equals("denim:shaders/post/linear_fog.json"))) {
//
       //     Minecraft.getInstance().gameRenderer.loadEffect(SHADER_LOCATION);
       //     System.out.println("LOADING");
       // }
    }
}

