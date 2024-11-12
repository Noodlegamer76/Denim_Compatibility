#version 150

#moj_import <fog.glsl>

uniform mat4 ModelViewMat;
uniform mat4 ProjMat;
uniform sampler2D sampler1;
uniform sampler2D sampler2;
uniform sampler2D sampler3;
uniform vec2 ScreenSize;

out vec4 fragColor;

noperspective in vec3 v_ndc;
in vec2 texCoords0;


void main() {
    ivec2 coord = ivec2(gl_FragCoord.xy);
    vec4 skybox = texelFetch(sampler1, coord, 0);

    if (skybox.a == 0.0) {
        discard;
    }

    vec4 mainDepth = texelFetch(sampler2, coord, 0);
    vec4 skyboxDepth = texelFetch(sampler3, coord, 0);

    if(mainDepth.r < skyboxDepth.r) {
        discard;
    }
    fragColor = skybox;
}
