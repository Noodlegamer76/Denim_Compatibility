#version 150

uniform sampler2D Skybox;
uniform sampler2D SkyboxDepth;
uniform sampler2D MainDepth;
uniform vec2 ScreenSize;

uniform vec4 ColorModulator;

in vec4 vertexColor;
in vec2 texCoord0;

out vec4 fragColor;

void main() {

    vec2 texCoord = gl_FragCoord.xy / ScreenSize;

    // Sample the texture at the calculated coordinates
    fragColor = texture(Skybox, texCoord);
}
