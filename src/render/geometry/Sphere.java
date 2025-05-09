package render.geometry;

import render.utils.ColorRGB;
import render.utils.Vec3f;

public class Sphere extends Shape {
    private Vec3f center;
    private double radius;

    public Sphere(ColorRGB color, ColorRGB specular, double shininess, Vec3f center, double radius) {
        super(color, specular, shininess);
        this.center = center;
        this.radius = radius;
    }

    @Override
    public double getIntersection(Vec3f origin, Vec3f direction) {
        Vec3f CP = origin.sub(center);

        double a = direction.dotProduct(direction);
        double b = 2.0D * direction.dotProduct(CP);
        double c = CP.dotProduct(CP) - radius * radius;

        double delta = b * b - 4.0D * a * c;

        if (delta < 0) {
            return -1;
        }

        double sqrtDelta = Math.sqrt(delta);
        double t0 = (-b - sqrtDelta) / (2.0D * a);
        double t1 = (-b + sqrtDelta) / (2.0D * a);

        if (t0 > 0) {
            return t0;
        }
        if (t1 > 0) {
            return t1;
        }

        return -1;
    }

    @Override
    public Vec3f getNormal(Vec3f intersectionPoint) {
        Vec3f CI = intersectionPoint.sub(center);
        CI.normalize();
        return CI;
    }
}
