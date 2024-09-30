#version 150

uniform sampler2D DiffuseSampler;

uniform float InverseAmount;

out vec4 fragColor;

void main(){
    vec3 ScreenDepth = gl_FragCoord.xyz * 2.0 - 1;

    fragColor = vec4(outColor.rgb, 1.0);

}
