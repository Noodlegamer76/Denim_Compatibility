#version 150


uniform vec4 ColorModulator;
uniform float FogStart;
uniform float FogEnd;
uniform vec4 FogColor;
uniform mat4 ProjMat;
uniform mat4 ModelViewMat;

in float vertexDistance;

out vec4 fragColor;

vec3 projectAndDivide(mat4 projectionMatrix, vec3 position) {
   vec4 homogeneousPos = projectionMatrix * vec4(position, 1.0);
   return homogeneousPos.xyz / homogeneousPos.w;
}

void main() {
    //vec3 screen = projectAndDivide(ProjMat, vec3(gl_FragDepth) * 2.0 - 1.0);
    //vec3 player = mat3(inverse(ProjMat)) * screen;
    fragColor = vec4(gl_FragDepth);
    fragColor = vec4(1.0, 0.0, 0.0, 1.0);
}
