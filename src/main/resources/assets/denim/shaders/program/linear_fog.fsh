#version 150

out vec4 fragColor;

uniform mat4 ProjMat;

void main(){
    vec4 position = vec4(gl_FragCoord.xyz / gl_FragCoord.w, 1.0);
    vec4 viewPos = inverse(ProjMat) * position;
    fragColor = vec4(0.5, 0.5, 0.5, 0.5);
}