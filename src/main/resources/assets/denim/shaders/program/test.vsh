#version 150

in vec4 Position;

uniform mat4 ProjMat;

out vec2 texCoord;

void main(){
    gl_Position = vec4(outPos.xy, 0.2, 1.0);
    v_ndc = gl_Position.xyz/gl_Position.w;
}
