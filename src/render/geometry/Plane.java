package render.geometry;

import render.utils.ColorRGB;
import render.utils.Vec3f;

public class Plane extends Shape{
    private Vec3f point;
    private Vec3f normal;

    public Plane(ColorRGB color, ColorRGB specular, double shininess, Vec3f point, Vec3f normal) {
        super(color, specular, shininess);
        this.point = point;
        this.normal = normal;
    }

    @Override
    public double getIntersection(Vec3f P, Vec3f v) {
        double dotNV = normal.dotProduct(v);
        if (Math.abs(dotNV) < 1e-6) return -1;

        Vec3f delta = new Vec3f(point);
        delta = delta.sub(P);
        double t = delta.dotProduct(normal) / dotNV;
        return t > 0 ? t : -1;
    }

    @Override
    public Vec3f getNormal(Vec3f intersectionPoint) {
        return normal;
    }
}
