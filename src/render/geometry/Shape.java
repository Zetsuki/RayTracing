package render.geometry;

import render.utils.ColorRGB;
import render.utils.Vec3f;

public abstract class Shape {
    private final ColorRGB color, specular;
    private final double shininess;

    protected Shape(ColorRGB color, ColorRGB specular, double shininess) {
        this.color = color;
        this.specular = specular;
        this.shininess = shininess;
    }

    public abstract double getIntersection(Vec3f P, Vec3f v);

    public Vec3f getIntersectionPoint(Vec3f origin, Vec3f direction, double lambdaI) {
        return origin.add(direction.scale(lambdaI));
    }

    public abstract Vec3f getNormal(Vec3f intersectionPoint);

    public ColorRGB getColor() {
        return color;
    }

    public ColorRGB getSpecular() {
        return specular;
    }

    public double getShininess() {
        return shininess;
    }
}
