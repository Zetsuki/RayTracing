package render.geometry;

import render.utils.Vec3f;

public abstract class Shape {
    public abstract double getIntersection(Vec3f P, Vec3f v);

    public Vec3f getIntersectionPoint(Vec3f origin, Vec3f direction, double lambdaI) {
        return origin.add(direction.scale(lambdaI));
    }

    public abstract Vec3f getNormal(Vec3f intersectionPoint);

}
