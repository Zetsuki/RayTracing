package render.geometry;

import render.utils.ColorRGB;
import render.utils.Vec3f;

public class Plane extends Shape{
    private Vec3f normal;
    private double distance;

    public Plane(ColorRGB color, ColorRGB specular, double shininess, Vec3f normal, double distance) {
        super(color, specular, shininess);
        this.normal = normal.normalize();
        this.distance = distance;
    }

    @Override
    public double getIntersection(Vec3f P, Vec3f v) {
        double dotNV = normal.dotProduct(v);
        if (Math.abs(dotNV) < 1e-6) return -1;

        double t = (distance - normal.dotProduct(P)) / dotNV;
        return t > 0 ? t : -1;
    }

    @Override
    public Vec3f getNormal(Vec3f intersectionPoint) {
        return normal;
    }
}
