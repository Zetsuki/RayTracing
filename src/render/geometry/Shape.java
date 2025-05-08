package render.geometry;

import render.utils.Vec3f;

public abstract class Shape {
    public abstract double getIntersection(Vec3f P, Vec3f v);
}
