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
    public double getIntersection(Vec3f origin, Vec3f direction) {
        double dotProductNormalDirection = normal.dotProduct(direction);

        if (Math.abs(dotProductNormalDirection) < 1e-6) {
            return -1.0D;
        }

        double dotProductNormalOrigin = normal.dotProduct(origin);
        double lambdaI = (-dotProductNormalOrigin - distance) / dotProductNormalDirection;

        return lambdaI > 0.0001D ? lambdaI : -1.0D;
    }


    @Override
    public Vec3f getNormal(Vec3f intersectionPoint) {
        return normal;
    }
}
