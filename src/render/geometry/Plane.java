package render.geometry;

import render.utils.ColorRGB;
import render.utils.Texture2D;
import render.utils.Vec3f;

public class Plane extends Shape{
    private final Vec3f normal;
    private final double distance;

    public Plane(ColorRGB color, ColorRGB specular, double shininess, Vec3f normal, double distance) {
        super(color, specular, shininess);
        this.normal = normal.normalize();
        this.distance = distance;
    }

    public Plane(ColorRGB color, ColorRGB specular, double shininess, Vec3f normal, double distance, Texture2D texture) {
        super(color, specular, shininess, texture);
        this.normal = normal.normalize();
        this.distance = distance;
    }

    @Override
    public double getIntersection(Vec3f origin, Vec3f direction) {
        double dotProductNormalDirection = normal.dotProduct(direction);

        // Direction is parallel to the normal, no intersectiob
        if (Math.abs(dotProductNormalDirection) < 1e-6) {
            return -1.0D;
        }

        double dotProductNormalOrigin = normal.dotProduct(origin);

        // Intersection point lambdaI
        double lambdaI = (distance - dotProductNormalOrigin) / dotProductNormalDirection;

        return lambdaI > 0.0001D ? lambdaI : -1.0D;
    }

    @Override
    public ColorRGB getTextureColor(Vec3f intersectionPoint) {
        // Projection of the intersection on the plane
        Vec3f pointOnPlane = intersectionPoint.sub(normal.scale(normal.dotProduct(intersectionPoint) + distance));

        // get u and v
        double u = pointOnPlane.x;
        double v = pointOnPlane.y;

        // Clamping into [0,1]
        u -= Math.floor(u);
        v -= Math.floor(v);

        return texture.getColor(u,v);
    }

    @Override
    public Vec3f getNormal(Vec3f intersectionPoint) {
        return normal;
    }
}
