package com.noodlegamer76.denim.client.renderer.entity;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.noodlegamer76.denim.block.ConfettiTnt;
import com.noodlegamer76.denim.block.InitBlocks;
import com.noodlegamer76.denim.client.renderer.ModRenderTypes;
import com.noodlegamer76.denim.entity.block.PrimedConfettiTnt;
import com.noodlegamer76.denim.event.RegisterShadersEvent;
import com.noodlegamer76.denim.event.RenderEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.ZombieModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.client.renderer.blockentity.BeaconRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.TntMinecartRenderer;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.model.data.ModelData;
import org.lwjgl.opengl.GL44;

import static com.noodlegamer76.denim.event.RegisterShadersEvent.test;
import static com.noodlegamer76.denim.event.RenderEvents.*;

@OnlyIn(Dist.CLIENT)
public class ConfettiTntRenderer extends EntityRenderer<PrimedConfettiTnt> {
    private final BlockRenderDispatcher blockRenderer;
    PlayerModel model;

    public ConfettiTntRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
        this.shadowRadius = 0.5F;
        this.blockRenderer = pContext.getBlockRenderDispatcher();
        this.model = new PlayerModel<>(pContext.bakeLayer(ModelLayers.PLAYER), false);
    }

    @Override
    public void render(PrimedConfettiTnt pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {
        //int current = GL44.glGetInteger(GL44.GL_FRAMEBUFFER_BINDING);
        //GlStateManager._glBindFramebuffer(GL44.GL_FRAMEBUFFER, Fbo);
//
        //GL44.glEnable(GL44.GL_STENCIL_TEST);
        //RenderSystem.stencilMask(0xFF);
        //RenderSystem.stencilFunc(GL44.GL_EQUAL, 1, 0xFF);
        //RenderSystem.stencilOp(GL44.GL_KEEP, GL44.GL_KEEP, GL44.GL_KEEP);

        pMatrixStack.pushPose();
        pMatrixStack.translate(0.0F, 0.5F, 0.0F);
        int i = pEntity.getFuse();
        if ((float)i - pPartialTicks + 1.0F < 10.0F) {
            float f = 1.0F - ((float)i - pPartialTicks + 1.0F) / 10.0F;
            f = Mth.clamp(f, 0.0F, 1.0F);
            f *= f;
            f *= f;
            float f1 = 1.0F + f * 0.3F;
            pMatrixStack.scale(f1, f1, f1);
        }

        pMatrixStack.mulPose(Axis.YP.rotationDegrees(-90.0F));
        pMatrixStack.translate(-0.5F, -0.5F, 0.5F);



        pMatrixStack.mulPose(Axis.YP.rotationDegrees(90.0F));
        //blockRenderer.renderSingleBlock(InitBlocks.CONFETTI_TNT.get().defaultBlockState(), pMatrixStack, pBuffer, pPackedLight, 1, net.minecraftforge.client.model.data.ModelData.EMPTY, ModRenderTypes.SKYBOX);
        //TntMinecartRenderer.renderWhiteSolidBlock(this.blockRenderer, InitBlocks.CONFETTI_TNT.get().defaultBlockState(), pMatrixStack, pBuffer, pPackedLight, i / 5 % 2 == 0);

        model.renderToBuffer(pMatrixStack, pBuffer.getBuffer(ModRenderTypes.SKYBOX), pPackedLight, 0, 255, 255, 255, 255);


        pMatrixStack.popPose();
        //GlStateManager._glBindFramebuffer(GL44.GL_FRAMEBUFFER, current);
        //super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(PrimedConfettiTnt pEntity) {
        return TextureAtlas.LOCATION_BLOCKS;
    }
}