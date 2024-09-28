package com.noodlegamer76.denim.event;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.noodlegamer76.denim.DenimMod;
import com.noodlegamer76.denim.client.renderer.ModRenderTypes;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterEntitySpectatorShadersEvent;
import net.minecraftforge.client.event.RegisterNamedRenderTypesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.io.IOException;

@Mod.EventBusSubscriber(modid = DenimMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class RegisterShadersEvent {
    public static ShaderInstance test;

    @SubscribeEvent
    public static void registerShaders(net.minecraftforge.client.event.RegisterShadersEvent event) throws IOException {

        event.registerShader(new ShaderInstance(event.getResourceProvider(),
                new ResourceLocation(DenimMod.MODID, "test"),
                DefaultVertexFormat.POSITION_TEX_COLOR),
                (e) -> test = e);
    }

    @SubscribeEvent
    public static void registerNamedRenderTypes(RegisterNamedRenderTypesEvent event) {
    }
}
