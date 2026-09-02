#version 150

uniform sampler2D DiffuseSampler;

in vec2 texCoord;
in vec2 oneTexel;

uniform vec2 InSize;

uniform vec2 BlurDir;
uniform float Radius;

out vec4 fragColor;

void main() {
    vec4 blurred = vec4(0.0);
    float totalSamples = 0.0;
    float progRadius = max(0.0, floor(Radius));
    for(float r = -progRadius; r <= progRadius; r += 1.0) {
        vec4 sample = texture(DiffuseSampler, texCoord + oneTexel * r * BlurDir);

        totalSamples = totalSamples + 1.0;
        blurred = blurred + sample;
    }
    fragColor = blurred / totalSamples;
}
