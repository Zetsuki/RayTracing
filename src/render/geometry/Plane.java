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
        Vec3f[] axes = generateLocalAxes(normal); // Consistent local axes
        Vec3f uAxis = axes[0];
        Vec3f vAxis = axes[1];

        // Project the intersection point onto the plane's local coordinate system
        Vec3f pointOnPlane = intersectionPoint.sub(normal.scale(normal.dotProduct(intersectionPoint) + distance));
        double u = pointOnPlane.dotProduct(uAxis);
        double v = pointOnPlane.dotProduct(vAxis);

        // Normalize the UV coordinates
        double uNormalized = u - Math.floor(u);
        double vNormalized = v - Math.floor(v);

        // Get the color from the texture using the normalized UV coordinates
        return texture.getColor(uNormalized, vNormalized);
    }

    private Vec3f[] generateLocalAxes(Vec3f normal) {
        Vec3f reference = (Math.abs(normal.y) < 0.9) ? new Vec3f(0, 1, 0) : new Vec3f(1, 0, 0);

        Vec3f uAxis = normal.crossProduct(reference).normalize();
        Vec3f vAxis = normal.crossProduct(uAxis).normalize();

        return new Vec3f[] { uAxis, vAxis };
    }

    @Override
    public Vec3f getNormal(Vec3f intersectionPoint) {
        return normal;
    }
}
