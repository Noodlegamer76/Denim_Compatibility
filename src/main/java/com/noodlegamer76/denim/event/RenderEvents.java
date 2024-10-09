package com.noodlegamer76.denim.event;

import com.mojang.blaze3d.platform.NativeImage;
import com.mojang.blaze3d.systems.RenderSystem;
import com.noodlegamer76.denim.DenimMod;
import com.noodlegamer76.denim.client.renderer.RenderCubeAroundCamera;
import com.noodlegamer76.denim.client.renderer.SkyBoxRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.SpriteContents;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.metadata.animation.AnimationMetadataSection;
import net.minecraft.client.resources.metadata.animation.FrameSize;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterNamedRenderTypesEvent;
import net.minecraftforge.client.event.RegisterTextureAtlasSpriteLoadersEvent;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.client.textures.ForgeTextureMetadata;
import net.minecraftforge.client.textures.ITextureAtlasSpriteLoader;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.opengl.GL44;

import java.nio.ByteBuffer;

@Mod.EventBusSubscriber(modid = DenimMod.MODID, value = Dist.CLIENT)
public class RenderEvents {

    @SubscribeEvent
    public static void levelRenderEvent(RenderLevelStageEvent event) {
        if (event.getStage() == RenderLevelStageEvent.Stage.AFTER_SKY) {
            SkyBoxRenderer.renderSimple(event.getPoseStack(),
                    new ResourceLocation(DenimMod.MODID, "textures/environment/layer1/skybox1")
            );
        }
        if (event.getStage() == RenderLevelStageEvent.Stage.AFTER_WEATHER) {
            int width = Minecraft.getInstance().getWindow().getWidth();
            int height = Minecraft.getInstance().getWindow().getHeight();
            //RenderSystem.enableBlend();
            //RenderSystem.blendFunc(GL44.GL_SRC_ALPHA, GL44.GL_ONE_MINUS_SRC_ALPHA);
            GL44.glBindFramebuffer(GL44.GL_FRAMEBUFFER, 2);
            GL44.glFramebufferTexture2D(GL44.GL_FRAMEBUFFER, GL44.GL_COLOR_ATTACHMENT0,
                    GL44.GL_TEXTURE_2D, 0, 0);
            GL44.glBindFramebuffer(GL44.GL_FRAMEBUFFER, 1);
            GL44.glBlitFramebuffer(0, 0, width, height, 0, 0, width, height,
                    GL44.GL_COLOR_BUFFER_BIT, GL44.GL_NEAREST);
        }

    }
}
