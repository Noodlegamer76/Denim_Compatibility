#version 150

#moj_import <fog.glsl>

uniform mat4 ModelViewMat;
uniform mat4 ProjMat;
uniform vec2 ScreenSize;

out vec4 fragColor;

noperspective in vec3 v_ndc;

vec3 projectAndDivide(mat4 projectionMatrix, vec3 position) {
    vec4 homogeneousPos = projectionMatrix * vec4(position, 1.0);
    return homogeneousPos.xyz / homogeneousPos.w;
}

void main() {

    //vec4 ndc = vec4(
    //(gl_FragCoord.x / ScreenSize.x - 0.5) * 2.0,
    //(gl_FragCoord.y / ScreenSize.y - 0.5) * 2.0,
    //(gl_FragCoord.z - 0.5) * 2.0,
    //1.0);

    vec3 viewPos = projectAndDivide(inverse(ProjMat), v_ndc);
    vec3 eyePlayerPos = mat3(inverse(ModelViewMat)) * viewPos;

    float DistanceFromCamera = length(viewPos);

    vec3 fogColorClose = vec3(0.8,0.8,0.8);
    vec3 fogColorFar = vec3(0.8,0.8,0.8);

    float fogMinimum = 0.;
    float fogMaximum = 1.25;

    float fogStrength = float(max(min((DistanceFromCamera - fogMinimum) / (fogMaximum - fogMinimum), 1.0), 0.0));

    vec3 fogColorBlend = mix(fogColorClose, fogColorFar, fogStrength);

    fragColor = vec4(fogColorBlend, fogStrength);
}
