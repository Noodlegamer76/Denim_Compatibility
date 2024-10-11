#version 150

in vec3 Position;

noperspective out vec3 v_ndc;
out vec2 texCoord;

uniform mat4 ProjMat;
uniform vec2 OutSize;

void main() {
    vec4 outPos = ProjMat * vec4(Position.xy, 0.0, 1.0);
    gl_Position = vec4(outPos.xy, 0.2, 1.0);
    v_ndc = gl_Position.xyz/gl_Position.w;
    texCoord = Position.xy / OutSize;
}
