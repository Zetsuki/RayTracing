package render.scene;

import render.Ray3D;
import render.geometry.Plane;
import render.geometry.Shape;
import render.geometry.Sphere;
import render.utils.ColorRGB;
import render.utils.Texture2D;
import render.utils.Vec3f;

/** Contains some planes and spheres, with textures */
public class BaseSceneWithTextures extends Scene {

    public BaseSceneWithTextures() {
        super();
        Shape p1 = new Plane(ColorRGB.red, ColorRGB.lightGray, 10D, new Vec3f(0D, 0D, -1D), 6D, new Texture2D("res/textures/glass.jpg"));
        Shape p2 = new Plane(ColorRGB.green, ColorRGB.lightGray, 5D, new Vec3f(1D, 0D, 0D), 3D, new Texture2D("res/textures/glass.jpg"));
        Shape p3 = new Plane(ColorRGB.blue, ColorRGB.lightGray, 20D, new Vec3f(-1D, 0D, 0D), 3D, new Texture2D("res/textures/glass.jpg"));
        Shape p4 = new Plane(ColorRGB.orange, ColorRGB.lightGray, 20D, new Vec3f(0D, 1D, 0D), 1.5D, new Texture2D("res/textures/tiles1.jpg"));
        Shape p5 = new Plane(ColorRGB.yellow, ColorRGB.lightGray, 20D, new Vec3f(0D, -1D, 0D), 1.5D, new Texture2D("res/textures/ceiling.jpg"));

        Shape s1 = new Sphere(ColorRGB.purple, ColorRGB.lightGray, 15D, new Vec3f(2D, 0.5D, -4D), 0.7D);
        Shape s2 = new Sphere(ColorRGB.cyan, ColorRGB.lightGray, 15D, new Vec3f(0D, -0.8D, -3.5D), 0.7D);
        Shape s3 = new Sphere(ColorRGB.gray , ColorRGB.lightGray, 15D, new Vec3f(0D, 0.4D, -2.5D), 0.8D);

        shapes.add(p1);
        shapes.add(p2);
        shapes.add(p3);
        shapes.add(p4);
        shapes.add(p5);
        shapes.add(s1);
        shapes.add(s2);
        shapes.add(s3);

        Ray3D light = new Ray3D(new Vec3f(-0.7D, 0.5D, 0D), ColorRGB.white, ColorRGB.lightGray);

        lights.add(light);
    }
}
