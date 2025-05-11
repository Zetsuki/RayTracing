package render.scene;

import render.Ray3D;
import render.geometry.Plane;
import render.geometry.Shape;
import render.geometry.Sphere;
import render.utils.ColorRGB;
import render.utils.Texture2D;
import render.utils.Vec3f;

/** Contains some planes and spheres */
public class ReflectionScene extends Scene {

    public ReflectionScene() {
        super();
        Shape p1 = new Plane(ColorRGB.red, ColorRGB.lightGray, 10D, new Vec3f(0D, 0D, -1D), 6D, new Texture2D("res/textures/glass.jpg"));
        Shape p2 = new Plane(ColorRGB.green, ColorRGB.lightGray, 5D, new Vec3f(1D, 0D, 0D), 3D, new Texture2D("res/textures/glass.jpg"));
        Shape p3 = new Plane(ColorRGB.blue, ColorRGB.lightGray, 20D, new Vec3f(-1D, 0D, 0D), 3D, new Texture2D("res/textures/glass.jpg"));
        Shape p4 = new Plane(ColorRGB.orange, ColorRGB.lightGray, 100D, new Vec3f(0D, 1D, 0D), 1.5D, new Texture2D("res/textures/floor.jpg"), 0.4D, 0D, 1D);
        Shape p5 = new Plane(ColorRGB.yellow, ColorRGB.lightGray, 20D, new Vec3f(0D, -1D, 0D), 1.5D, new Texture2D("res/textures/ceiling.jpg"));

        Shape s1 = new Sphere(ColorRGB.green, ColorRGB.lightGray, 150D, new Vec3f(0D, 0.6D, -3.25D), 0.5D, null, 0.3D, 0D, 1D);
        Shape s2 = new Sphere(ColorRGB.red, ColorRGB.lightGray, 150D, new Vec3f(-1.3D, 0.3D, -3.25D), 0.5D,  null, 0.3D, 0D, 1D);
        Shape s3 = new Sphere(ColorRGB.blue, ColorRGB.lightGray, 150D, new Vec3f(1.3D, 0.9D, -3.25D), 0.5D,  null, 0.3D, 0D, 1D);

        shapes.add(p1);
        shapes.add(p2);
        shapes.add(p3);
        shapes.add(p4);
        shapes.add(p5);

        shapes.add(s1);
        shapes.add(s2);
        shapes.add(s3);

        Ray3D light = new Ray3D(new Vec3f(0D, 0D, 0D), ColorRGB.white, ColorRGB.lightGray);

        lights.add(light);
    }
}
