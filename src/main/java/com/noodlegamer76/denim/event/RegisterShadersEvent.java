package com.noodlegamer76.denim.event;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.noodlegamer76.denim.DenimMod;
import com.noodlegamer76.denim.client.renderer.ModRenderTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterEntitySpectatorShadersEvent;
import net.minecraftforge.client.event.RegisterNamedRenderTypesEvent;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;
import org.openjdk.nashorn.internal.runtime.GlobalFunctions;
import org.lwjgl.opengl.GL44;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;


@Mod.EventBusSubscriber(modid = DenimMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class RegisterShadersEvent {
    public static ShaderInstance test;
    public static ShaderInstance skybox1;

    @SubscribeEvent
    public static void registerShaders(net.minecraftforge.client.event.RegisterShadersEvent event) throws IOException {
        event.registerShader(new ShaderInstance(event.getResourceProvider(),
                        new ResourceLocation(DenimMod.MODID, "test"),
                        DefaultVertexFormat.BLOCK),
                (e) -> test = e);

        event.registerShader(new ShaderInstance(event.getResourceProvider(),
                        new ResourceLocation(DenimMod.MODID, "skybox1"),
                        DefaultVertexFormat.POSITION),
                (e) -> skybox1 = e);
    }

    @SubscribeEvent
    public static void registerNamedRenderTypes(RegisterNamedRenderTypesEvent event) {
    }

   // @SubscribeEvent
   // public static void RenderLevelAfterThing(RenderLevelStageEvent event) throws IOException {
//
   //     RenderSystem.depthFunc(GL44.GL_LESS);
//
   //     int[] FBO = {1, 0};
   //     GL44.glGenFramebuffers(FBO);
   //     GL44.glBindTextures(GL44.GL_FRAMEBUFFER, FBO);
//
   //     int[] FBT = {1, 0};
   //     final int GL_CLAMP_TO_EDGE = 0x812F;
   //     GL44.glGenTextures(FBT);
   //     GL44.glBindTexture(GL44.GL_TEXTURE_2D, FBT[1]);
   //     GL44.glTexImage2D(GL44.GL_TEXTURE_2D, 0, GL11.GL_RGB,
   //             Minecraft.getInstance().getWindow().getWidth(),
   //             Minecraft.getInstance().getWindow().getHeight(),
   //             0, GL11.GL_RGB, GL11.GL_UNSIGNED_BYTE, (ByteBuffer) null);
   //     GL44.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
   //     GL44.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
   //     GL44.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
   //     GL44.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);
   //     GL44.glFramebufferTexture2D(GL30.GL_FRAMEBUFFER, GL30.GL_COLOR_ATTACHMENT0, GL11.GL_TEXTURE_2D, FBT[1], 0);
   // }
}
