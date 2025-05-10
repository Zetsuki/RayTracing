package render.geometry;

import render.utils.ColorRGB;
import render.utils.Texture2D;
import render.utils.Vec3f;

public class Sphere extends Shape {
    private final Vec3f center;
    private final double radius;

    public Sphere(ColorRGB color, ColorRGB specular, double shininess, Vec3f center, double radius) {
        super(color, specular, shininess);
        this.center = center;
        this.radius = radius;
    }

    public Sphere(ColorRGB color, ColorRGB specular, double shininess, Vec3f center, double radius, Texture2D texture) {
        super(color, specular, shininess, texture);
        this.center = center;
        this.radius = radius;
    }

    public Sphere(ColorRGB color, ColorRGB specular, double shininess, Vec3f center, double radius, Texture2D texture, double kr, double kt, double eta) {
        super(color, specular, shininess, texture, kr, kt, eta);
        this.center = center;
        this.radius = radius;
    }

    @Override
    public double getIntersection(Vec3f origin, Vec3f direction) {
        Vec3f CP = origin.sub(center);

        // Coeff for quadratic equation
        double a = direction.dotProduct(direction);
        double b = 2.0D * direction.dotProduct(CP);
        double c = CP.dotProduct(CP) - radius * radius;

        // Discriminant
        double delta = b * b - 4.0D * a * c;

        // No intersection
        if (delta < 0) {
            return -1;
        }

        double sqrtDelta = Math.sqrt(delta);

        // Two possible intersection point
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
    public ColorRGB getTextureColor(Vec3f intersectionPoint) {
        return ColorRGB.black;
    }

    @Override
    public Vec3f getNormal(Vec3f intersectionPoint) {
        Vec3f CI = intersectionPoint.sub(center);
        CI.normalize();
        return CI;
    }
}
