#version 150

in vec3 Position;
in vec4 Color;
in vec2 UV0;
in vec3 Normal;

uniform mat4 ModelViewMat;
uniform mat4 ProjMat;

out vec4 vertexColor;
out vec2 texCoord0;
out vec3 normals;

void main() {
    gl_Position = ProjMat * ModelViewMat * vec4(Position, 1.0);
    normals = Normal;

    vertexColor = Color;
    texCoord0 = UV0;
}
