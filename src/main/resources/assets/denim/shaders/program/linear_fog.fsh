#version 150

out vec4 fragColor;

uniform mat4 ProjMat;

void main(){
    vec3 position = vec3(gl_FragCoord.xyz / gl_FragCoord.w);
    vec3 viewPos = inverse(ProjMat).xyz * position;
    fragColor = vec4(1., 1., 1., viewPos.z);
}