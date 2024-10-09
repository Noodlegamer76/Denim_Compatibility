package com.noodlegamer76.denim.event;

import com.eliotlash.mclib.math.functions.limit.Min;
import com.mojang.blaze3d.systems.RenderSystem;
import com.noodlegamer76.denim.DenimMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.telemetry.events.WorldLoadEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.event.RegisterNamedRenderTypesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import org.lwjgl.opengl.GL44;

import java.nio.ByteBuffer;

@Mod.EventBusSubscriber(modid = DenimMod.MODID, value = Dist.CLIENT)
public class GameLoadingEvents {
    private static boolean fboSettup = false;

    @SubscribeEvent
    public static void loadCompleteEvent(TickEvent.RenderTickEvent event) {
        int width = Minecraft.getInstance().getWindow().getWidth();
        int height = Minecraft.getInstance().getWindow().getHeight();
        if (event.phase == TickEvent.Phase.START && !fboSettup) {
            fboSettup = true;
            int[] FBO = {2};
            int current = GL44.glGetInteger(GL44.GL_FRAMEBUFFER_BINDING);
            GL44.glGenFramebuffers(FBO);
            GL44.glBindFramebuffer(GL44.GL_FRAMEBUFFER, FBO[0]);

            int[] skyboxTextures = {0, 1};
            GL44.glGenTextures(skyboxTextures);
            GL44.glBindTexture(GL44.GL_TEXTURE_2D, skyboxTextures[0]);

            GL44.glTexImage2D(GL44.GL_TEXTURE_2D, 0, GL44.GL_RGB,
                    width, height,
                    0, GL44.GL_RGB, GL44.GL_UNSIGNED_BYTE, (ByteBuffer) null);

            GL44.glTexParameteri(GL44.GL_TEXTURE_2D, GL44.GL_TEXTURE_MIN_FILTER, GL44.GL_LINEAR);
            GL44.glTexParameteri(GL44.GL_TEXTURE_2D, GL44.GL_TEXTURE_MAG_FILTER, GL44.GL_LINEAR);

            GL44.glFramebufferTexture2D(GL44.GL_FRAMEBUFFER, GL44.GL_COLOR_ATTACHMENT0, GL44.GL_TEXTURE_2D, skyboxTextures[0], 0);


            GL44.glBindTexture(GL44.GL_TEXTURE_2D, skyboxTextures[1]);

            GL44.glTexImage2D(GL44.GL_TEXTURE_2D, 0, GL44.GL_DEPTH24_STENCIL8,
                    width, height,
                    0, GL44.GL_DEPTH_STENCIL, GL44.GL_UNSIGNED_INT_24_8, (ByteBuffer) null);

            GL44.glTexParameteri(GL44.GL_TEXTURE_2D, GL44.GL_TEXTURE_MIN_FILTER, GL44.GL_LINEAR);
            GL44.glTexParameteri(GL44.GL_TEXTURE_2D, GL44.GL_TEXTURE_MAG_FILTER, GL44.GL_LINEAR);

            GL44.glFramebufferTexture2D(GL44.GL_FRAMEBUFFER, GL44.GL_STENCIL_ATTACHMENT, GL44.GL_TEXTURE_2D, skyboxTextures[1], 0);

            if (GL44.glCheckFramebufferStatus(GL44.GL_FRAMEBUFFER) != GL44.GL_FRAMEBUFFER_COMPLETE) {
                throw new RuntimeException("Framebuffer is not complete!" + GL44.glCheckFramebufferStatus(FBO[0]));
            }

            GL44.glFramebufferTexture2D(GL44.GL_FRAMEBUFFER, GL44.GL_COLOR_ATTACHMENT0, GL44.GL_TEXTURE_2D, skyboxTextures[0], 0);

            int[] RBO = {2};
            GL44.glGenRenderbuffers(RBO);
            GL44.glBindRenderbuffer(GL44.GL_RENDERBUFFER, RBO[0]);
            GL44.glRenderbufferStorage(GL44.GL_RENDERBUFFER, GL44.GL_DEPTH24_STENCIL8, width, height);
            GL44.glBindRenderbuffer(GL44.GL_RENDERBUFFER, 0);

            GL44.glFramebufferRenderbuffer(GL44.GL_FRAMEBUFFER, GL44.GL_DEPTH_STENCIL_ATTACHMENT, GL44.GL_RENDERBUFFER, RBO[0]);

            GL44.glBindFramebuffer(GL44.GL_FRAMEBUFFER, current);

            //GL44.glBindFramebuffer(GL44.GL_FRAMEBUFFER, 1);
        } else if (event.phase == TickEvent.Phase.END) {
           // //RenderSystem.enableBlend();
           // //RenderSystem.blendFunc(GL44.GL_SRC_ALPHA, GL44.GL_ONE_MINUS_SRC_ALPHA);
           // GL44.glBindFramebuffer(GL44.GL_FRAMEBUFFER, 2);
           // GL44.glFramebufferTexture2D(GL44.GL_FRAMEBUFFER, GL44.GL_COLOR_ATTACHMENT0,
           //         GL44.GL_TEXTURE_2D, 0, 0);
           // GL44.glBindFramebuffer(GL44.GL_FRAMEBUFFER, 1);
           // GL44.glBlitFramebuffer(0, 0, width, height, 0, 0, width, height,
           //         GL44.GL_COLOR_BUFFER_BIT, GL44.GL_NEAREST);
        }
    }
}
