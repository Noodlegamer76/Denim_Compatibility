#version 150

uniform mat4 ProjMat;
uniform sampler2D DiffuseSampler;
uniform sampler2D DiffuseDepthSampler;

out vec4 fragColor;

noperspective in vec3 v_ndc;
in vec2 texCoord;

vec3 projectAndDivide(mat4 projectionMatrix, vec3 position) {
    vec4 homogeneousPos = projectionMatrix * vec4(position, 1.0);
    return homogeneousPos.xyz / homogeneousPos.w;
}

void main() {

    vec3 color = vec3( texture( DiffuseSampler, texCoord ).rgb);
    vec3 depth = vec3( texture( DiffuseDepthSampler, texCoord ).rgb);

    //vec4 ndc = vec4(
    //(gl_FragCoord.x / ScreenSize.x - 0.5) * 2.0,
    //(gl_FragCoord.y / ScreenSize.y - 0.5) * 2.0,
    //(gl_FragCoord.z - 0.5) * 2.0,
    //1.0);

    vec3 viewPos = projectAndDivide(inverse(ProjMat), depth * 2.0 - 1.0);

    float DistanceFromCamera = length(viewPos.r / 10.);

    vec3 fogColorClose = vec3(1.0,0.,0.);
    vec3 fogColorFar = vec3(1.0,0.,0.);

    float fogMinimum = 0.;
    float fogMaximum = 15;

    float fogStrength = float(max(min((DistanceFromCamera - fogMinimum) / (fogMaximum - fogMinimum), 1.0), 0.0));

    vec3 fogColorBlend = mix(fogColorClose, fogColorFar, fogStrength);

    vec3 finalColorBlend = mix(color, fogColorBlend, fogStrength);

    fragColor = vec4(vec3(DistanceFromCamera), fogStrength);
}
