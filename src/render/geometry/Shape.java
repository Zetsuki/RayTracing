package render.geometry;

import render.utils.ColorRGB;
import render.utils.Texture2D;
import render.utils.Vec3f;

public abstract class Shape {
    private final ColorRGB color, specular;
    private final double shininess;

    protected Texture2D texture;

    private double kr = 0;
    private double kt = 0;
    private double eta = 1;

    protected Shape(ColorRGB color, ColorRGB specular, double shininess) {
        this.color = color;
        this.specular = specular;
        this.shininess = shininess;
    }

    protected Shape(ColorRGB color, ColorRGB specular, double shininess, Texture2D texture) {
        this.color = color;
        this.specular = specular;
        this.shininess = shininess;
        this.texture = texture;
    }

    protected Shape(ColorRGB color, ColorRGB specular, double shininess, Texture2D texture, double kr, double kt, double eta) {
        this.color = color;
        this.specular = specular;
        this.shininess = shininess;
        this.texture = texture;
        this.kr = kr;
        this.kt = kt;
        this.eta = eta;
    }

    public abstract double getIntersection(Vec3f P, Vec3f v);

    public Vec3f getIntersectionPoint(Vec3f origin, Vec3f direction, double lambdaI) {
        return origin.add(direction.scale(lambdaI));
    }

    public abstract ColorRGB getTextureColor(Vec3f intersectionPoint);

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

    public Texture2D getTexture() {
        return texture;
    }

    public double getKr() {
        return kr;
    }

    public double getKt() {
        return kt;
    }

    public double getEta() {
        return eta;
    }
}
