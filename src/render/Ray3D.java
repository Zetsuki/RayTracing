package render;

import render.utils.ColorRGB;
import render.utils.Vec3f;

public class Ray3D {
    private final Vec3f pos;
    private final ColorRGB diff, spec;

    public static ColorRGB AMBIENT_RAY = ColorRGB.black;

    public Ray3D(Vec3f pos, ColorRGB diff, ColorRGB spec) {
        this.pos = pos;
        this.diff = diff;
        this.spec = spec;
    }

    public Vec3f getPos() {
        return pos;
    }

    public ColorRGB getDiff() {
        return diff;
    }

    public ColorRGB getSpec() {
        return spec;
    }
}
