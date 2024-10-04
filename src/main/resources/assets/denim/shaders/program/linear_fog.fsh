#version 150

out vec4 fragColor;

void main(){
    vec4 diffuseColor = texture(DiffuseSampler, texCoord);
    float maxColor = max(max(diffuseColor.r, diffuseColor.g), diffuseColor.b);
    vec4 final = fract(diffuseColor + maxColor);

    fragColor = final;
}