#version 150

in vec3 Position;
in vec2 UV0;

noperspective out vec3 v_ndc;
out vec2 texCoords0;

uniform mat4 ProjMat;
uniform mat4 ModelViewMat;

void main() {
    gl_Position = ProjMat * ModelViewMat * vec4(Position, 1.0);
    v_ndc = gl_Position.xyz/gl_Position.w;
    texCoords0 = UV0;
}
