#version 330

uniform mat4 ModelViewMat;
uniform mat4 ProjMat;

in vec3 Position;

out vec4 color;

void main() {
    color = vec4(1.0, 1.0, Position.z, 1.0);
    gl_Position = ModelViewMat * ProjMat * vec4(Position, 1);
}
