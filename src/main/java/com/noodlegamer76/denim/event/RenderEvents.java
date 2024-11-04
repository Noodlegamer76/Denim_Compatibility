package com.noodlegamer76.denim.event;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.noodlegamer76.denim.DenimMod;
import com.noodlegamer76.denim.client.renderer.RenderCubeAroundCamera;
import com.noodlegamer76.denim.client.renderer.SkyBoxRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.opengl.GL44;

import java.nio.ByteBuffer;

@Mod.EventBusSubscriber(modid = DenimMod.MODID, value = Dist.CLIENT)
public class RenderEvents {
    public static final ResourceLocation TEXTURE = new ResourceLocation(DenimMod.MODID, "textures/environment/layer1/skybox1");
    private static boolean fboSetup = false;
    public static int Fbo;
    public static int rbo;
    public static int skyboxTexture;
    public static int stencilBufferTexture;
    public static int finalTexture;
    public static int width;
    public static int height;

    private static int previousSizeX;
    private static int previousSizeY;
    @SubscribeEvent
    public static void levelRenderEvent(RenderLevelStageEvent event) {

        if (event.getStage() == RenderLevelStageEvent.Stage.AFTER_SKY && !fboSetup) {
            int current = GL44.glGetInteger(GL44.GL_FRAMEBUFFER_BINDING);
            previousSizeX = width;
            previousSizeY = height;
            fboSetup = true;
            Fbo = GlStateManager.glGenFramebuffers();
            GlStateManager._glBindFramebuffer(GL44.GL_FRAMEBUFFER, Fbo);

            skyboxTexture = GlStateManager._genTexture();
            RenderSystem.bindTexture(skyboxTexture);

            GlStateManager._texImage2D(GL44.GL_TEXTURE_2D, 0, GL44.GL_RGBA,
                    width, height,
                    0, GL44.GL_RGBA, GL44.GL_UNSIGNED_BYTE, null);
            GlStateManager._texParameter(GL44.GL_TEXTURE_2D, GL44.GL_TEXTURE_MIN_FILTER, GL44.GL_LINEAR);
            GlStateManager._texParameter(GL44.GL_TEXTURE_2D, GL44.GL_TEXTURE_MAG_FILTER, GL44.GL_LINEAR);
            GlStateManager._glFramebufferTexture2D(GL44.GL_FRAMEBUFFER, GL44.GL_COLOR_ATTACHMENT0, GL44.GL_TEXTURE_2D, skyboxTexture, 0);

            stencilBufferTexture = GlStateManager._genTexture();
            GlStateManager._bindTexture(stencilBufferTexture);
//
            GlStateManager._texImage2D(GL44.GL_TEXTURE_2D, 0, GL44.GL_DEPTH24_STENCIL8,
                    width, height,
                    0, GL44.GL_DEPTH_STENCIL, GL44.GL_UNSIGNED_INT_24_8, null);
            GlStateManager._texParameter(GL44.GL_TEXTURE_2D, GL44.GL_TEXTURE_MIN_FILTER, GL44.GL_NEAREST);
            GlStateManager._texParameter(GL44.GL_TEXTURE_2D, GL44.GL_TEXTURE_MAG_FILTER, GL44.GL_NEAREST);
//
            GlStateManager._glFramebufferTexture2D(GL44.GL_FRAMEBUFFER, GL44.GL_DEPTH_STENCIL_ATTACHMENT, GL44.GL_TEXTURE_2D, stencilBufferTexture, 0);

        //    finalTexture = GlStateManager._genTexture();
        //    RenderSystem.bindTexture(finalTexture);
//
        //    GlStateManager._texImage2D(GL44.GL_TEXTURE_2D, 0, GL44.GL_RGBA,
        //            width, height,
        //            0, GL44.GL_RGBA, GL44.GL_UNSIGNED_BYTE, null);
        //    GlStateManager._texParameter(GL44.GL_TEXTURE_2D, GL44.GL_TEXTURE_MIN_FILTER, GL44.GL_LINEAR);
        //    GlStateManager._texParameter(GL44.GL_TEXTURE_2D, GL44.GL_TEXTURE_MAG_FILTER, GL44.GL_LINEAR);
        //    GlStateManager._glFramebufferTexture2D(GL44.GL_FRAMEBUFFER, GL44.GL_COLOR_ATTACHMENT1, GL44.GL_TEXTURE_2D, finalTexture, 0);
//
        //    GlStateManager._glBindFramebuffer(GL44.GL_FRAMEBUFFER, current);

        }

        if (event.getStage() == RenderLevelStageEvent.Stage.AFTER_SKY) {
            width = Minecraft.getInstance().getWindow().getWidth();
            height = Minecraft.getInstance().getWindow().getHeight();
            changeTextureSize();



            int current = GL44.glGetInteger(GL44.GL_FRAMEBUFFER_BINDING);
            GlStateManager._glBindFramebuffer(GL44.GL_FRAMEBUFFER, Fbo);
            RenderSystem.clear(GL44.GL_DEPTH_BUFFER_BIT | GL44.GL_STENCIL_BUFFER_BIT | GL44.GL_COLOR_BUFFER_BIT, true);
            GlStateManager._glBindFramebuffer(GL44.GL_FRAMEBUFFER, current);
        }

        if (event.getStage() == RenderLevelStageEvent.Stage.AFTER_SKY) {


            int current = GL44.glGetInteger(GL44.GL_FRAMEBUFFER_BINDING);
            GlStateManager._glBindFramebuffer(GL44.GL_FRAMEBUFFER, Fbo);
            RenderSystem.bindTexture(skyboxTexture);
//
            SkyBoxRenderer.renderSimple(event.getPoseStack(), TEXTURE);

////
//////
            GlStateManager._glBindFramebuffer(GL44.GL_FRAMEBUFFER, current);

        }

        if (event.getStage() == RenderLevelStageEvent.Stage.AFTER_WEATHER) {
            previousSizeY = height;
            previousSizeX = width;
        }



       // if (event.getStage() == RenderLevelStageEvent.Stage.AFTER_WEATHER) {
//
//
       //     SkyBoxRenderer.renderSimple4(event.getPoseStack(), TEXTURE);
//
       // }






        //int width = Minecraft.getInstance().getWindow().getWidth();
        //int height = Minecraft.getInstance().getWindow().getHeight();
        //if (event.getStage() == RenderLevelStageEvent.Stage.AFTER_SKY && !fboSetup) {
        //    fboSetup = true;
        //    int[] FBO = {2};
        //    int current = GL44.glGetInteger(GL44.GL_FRAMEBUFFER_BINDING);
        //    GL44.glGenFramebuffers(FBO);
        //    GL44.glBindFramebuffer(GL44.GL_FRAMEBUFFER, FBO[0]);
//////
        //    int[] skyboxTextures = {696969, 420420};
        //    GL44.glGenTextures(skyboxTextures);
        //    GL44.glBindTexture(GL44.GL_TEXTURE_2D, skyboxTextures[0]);
//////
        //    GL44.glTexImage2D(GL44.GL_TEXTURE_2D, 0, GL44.GL_RGBA,
        //            width, height,
        //            0, GL44.GL_RGBA, GL44.GL_UNSIGNED_BYTE, (ByteBuffer) null);
//////
        //    GL44.glTexParameteri(GL44.GL_TEXTURE_2D, GL44.GL_TEXTURE_MIN_FILTER, GL44.GL_LINEAR);
        //    GL44.glTexParameteri(GL44.GL_TEXTURE_2D, GL44.GL_TEXTURE_MAG_FILTER, GL44.GL_LINEAR);
//////
        //    GL44.glFramebufferTexture2D(GL44.GL_FRAMEBUFFER, GL44.GL_COLOR_ATTACHMENT0, GL44.GL_TEXTURE_2D, skyboxTextures[0], 0);
//////
//////
        //    GL44.glBindTexture(GL44.GL_TEXTURE_2D, skyboxTextures[1]);
//////
        //    GL44.glTexImage2D(GL44.GL_TEXTURE_2D, 0, GL44.GL_DEPTH24_STENCIL8,
        //            width, height,
        //            0, GL44.GL_DEPTH_STENCIL, GL44.GL_UNSIGNED_INT_24_8, (ByteBuffer) null);
//////
        //    GL44.glTexParameteri(GL44.GL_TEXTURE_2D, GL44.GL_TEXTURE_MIN_FILTER, GL44.GL_LINEAR);
        //    GL44.glTexParameteri(GL44.GL_TEXTURE_2D, GL44.GL_TEXTURE_MAG_FILTER, GL44.GL_LINEAR);
//////
        //    GL44.glFramebufferTexture2D(GL44.GL_FRAMEBUFFER, GL44.GL_STENCIL_ATTACHMENT, GL44.GL_TEXTURE_2D, skyboxTextures[1], 0);
//////
        //    if (GL44.glCheckFramebufferStatus(GL44.GL_FRAMEBUFFER) != GL44.GL_FRAMEBUFFER_COMPLETE) {
        //        throw new RuntimeException("Framebuffer is not complete!" + GL44.glCheckFramebufferStatus(FBO[0]));
        //    }
//////
        //    GL44.glFramebufferTexture2D(GL44.GL_FRAMEBUFFER, GL44.GL_COLOR_ATTACHMENT0, GL44.GL_TEXTURE_2D, skyboxTextures[0], 0);
//////
        //    int[] RBO = {2};
        //    GL44.glGenRenderbuffers(RBO);
        //    GL44.glBindRenderbuffer(GL44.GL_RENDERBUFFER, RBO[0]);
        //    GL44.glRenderbufferStorage(GL44.GL_RENDERBUFFER, GL44.GL_DEPTH24_STENCIL8, width, height);
        //    GL44.glBindRenderbuffer(GL44.GL_RENDERBUFFER, 0);
//////
        //    GL44.glFramebufferRenderbuffer(GL44.GL_FRAMEBUFFER, GL44.GL_DEPTH_STENCIL_ATTACHMENT, GL44.GL_RENDERBUFFER, RBO[0]);
//////
        //    GL44.glBindFramebuffer(GL44.GL_FRAMEBUFFER, 1);
        //    if (event.getStage() == RenderLevelStageEvent.Stage.AFTER_SKY) {
//
        //        //RenderSystem.enableBlend();
        //        //RenderSystem.blendFunc(GL44.GL_SRC_ALPHA, GL44.GL_ONE_MINUS_SRC_ALPHA);
        //        //GL44.glBindFramebuffer(GL44.GL_FRAMEBUFFER, 2);
        //        //GL44.glFramebufferTexture2D(GL44.GL_FRAMEBUFFER, GL44.GL_COLOR_ATTACHMENT0,
        //        //        GL44.GL_TEXTURE_2D, 0, 0);
        //        //GL44.glBindFramebuffer(GL44.GL_FRAMEBUFFER, 1);
        //        //GL44.glBlitFramebuffer(0, 0, width, height, 0, 0, width, height,
        //        //        GL44.GL_COLOR_BUFFER_BIT, GL44.GL_NEAREST);
        //    }
        //}
        //if (event.getStage() == RenderLevelStageEvent.Stage.AFTER_PARTICLES) {
        //    RenderCubeAroundCamera.createCubeWithShader2(event.getPoseStack());
        //}
        }


    public static void changeTextureSize() {
        if (previousSizeX != width || previousSizeY != height) {
            int current = GL44.glGetInteger(GL44.GL_FRAMEBUFFER_BINDING);

            GlStateManager._glDeleteFramebuffers(Fbo);
            Fbo = GlStateManager.glGenFramebuffers();


            GlStateManager._glBindFramebuffer(GL44.GL_FRAMEBUFFER, Fbo);

            GlStateManager._deleteTexture(skyboxTexture);

            System.out.println("resize");
            skyboxTexture = GlStateManager._genTexture();
            RenderSystem.bindTexture(skyboxTexture);

            GlStateManager._texImage2D(GL44.GL_TEXTURE_2D, 0, GL44.GL_RGBA,
                    width, height,
                    0, GL44.GL_RGBA, GL44.GL_UNSIGNED_BYTE, null);
            GlStateManager._texParameter(GL44.GL_TEXTURE_2D, GL44.GL_TEXTURE_MIN_FILTER, GL44.GL_LINEAR);
            GlStateManager._texParameter(GL44.GL_TEXTURE_2D, GL44.GL_TEXTURE_MAG_FILTER, GL44.GL_LINEAR);
            GlStateManager._glFramebufferTexture2D(GL44.GL_FRAMEBUFFER, GL44.GL_COLOR_ATTACHMENT0, GL44.GL_TEXTURE_2D, skyboxTexture, 0);

            GlStateManager._deleteTexture(stencilBufferTexture);

            stencilBufferTexture = GlStateManager._genTexture();
            RenderSystem.bindTexture(stencilBufferTexture);

//
            GlStateManager._texImage2D(GL44.GL_TEXTURE_2D, 0, GL44.GL_DEPTH24_STENCIL8,
                    width, height,
                    0, GL44.GL_DEPTH_STENCIL, GL44.GL_UNSIGNED_INT_24_8, null);
            GlStateManager._texParameter(GL44.GL_TEXTURE_2D, GL44.GL_TEXTURE_MIN_FILTER, GL44.GL_NEAREST);
            GlStateManager._texParameter(GL44.GL_TEXTURE_2D, GL44.GL_TEXTURE_MAG_FILTER, GL44.GL_NEAREST);
//
            GlStateManager._glFramebufferTexture2D(GL44.GL_FRAMEBUFFER, GL44.GL_DEPTH_STENCIL_ATTACHMENT, GL44.GL_TEXTURE_2D, stencilBufferTexture, 0);

            GlStateManager._glBindFramebuffer(GL44.GL_FRAMEBUFFER, current);
        }
    }
}
