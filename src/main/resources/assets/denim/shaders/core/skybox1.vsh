#version 150

#moj_import <fog.glsl>

in vec3 Position;
in vec2 UV0;

noperspective out vec3 v_ndc;
out vec2 texCoord;

uniform mat4 ProjMat;
uniform mat4 ModelViewMat;

void main() {
    gl_Position = ProjMat * ModelViewMat * vec4(Position, 1.0);
    v_ndc = gl_Position.xyz/gl_Position.w;
    texCoord = UV0;
}
