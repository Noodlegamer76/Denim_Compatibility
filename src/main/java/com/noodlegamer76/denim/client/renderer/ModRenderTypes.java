package com.noodlegamer76.denim.client.renderer;

import com.mojang.blaze3d.pipeline.RenderTarget;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.noodlegamer76.denim.event.RegisterShadersEvent;
import com.noodlegamer76.denim.event.RenderEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.TheEndPortalRenderer;
import net.minecraft.client.renderer.texture.TextureAtlas;

public class ModRenderTypes extends RenderStateShard {

    public static final RenderType TEST_RENDERER = RenderType.create(
            "test",
            DefaultVertexFormat.POSITION,
            VertexFormat.Mode.QUADS,
            100000,
            true,
            true,
            RenderType.CompositeState.builder()
                    .setLightmapState(LIGHTMAP)
                    .setShaderState(new RenderStateShard.ShaderStateShard(() -> RegisterShadersEvent.test))
                    .setTextureState(NO_TEXTURE)
                    .setTransparencyState(TRANSLUCENT_TRANSPARENCY)
                    .setOutputState(TRANSLUCENT_TARGET)
                    .createCompositeState(true)
    );

    public static final RenderType SKYBOX1_RENDERTYPE = RenderType.create(
            "test",
            DefaultVertexFormat.POSITION,
            VertexFormat.Mode.QUADS,
            100000,
            true,
            true,
            RenderType.CompositeState.builder()
                    .setLightmapState(LIGHTMAP)
                    .setShaderState(new RenderStateShard.ShaderStateShard(() -> RegisterShadersEvent.skybox1))
                    .setTextureState(NO_TEXTURE)
                    .setTransparencyState(TRANSLUCENT_TRANSPARENCY)
                    .setOutputState(TRANSLUCENT_TARGET)
                    .createCompositeState(true)
    );

    public static final RenderType SKYBOX = RenderType.create(
            "test",
            DefaultVertexFormat.POSITION,
            VertexFormat.Mode.QUADS,
            100000,
            true,
            true,
            RenderType.CompositeState.builder()
                    .setShaderState(new RenderStateShard.ShaderStateShard(() -> RegisterShadersEvent.test))
                    .createCompositeState(true)
    );

    public ModRenderTypes(String pName, Runnable pSetupState, Runnable pClearState) {
        super(pName, pSetupState, pClearState);
    }
}
