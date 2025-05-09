package render.utils;

public class ColorRGB {
    private final float r,g,b;

    public static final ColorRGB black = new ColorRGB(0f,0f,0f);

    public ColorRGB(float r, float g, float b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public float getR() {
        return r;
    }

    public float getG() {
        return g;
    }

    public float getB() {
        return b;
    }

    public ColorRGB multiply(ColorRGB other) {
        return new ColorRGB(this.r * other.r, this.g * other.g, this.b * other.b);
    }

    public ColorRGB multiply(double s) {
        return new ColorRGB(this.r * (float) s, this.g * (float) s, this.b * (float) s);
    }

    public ColorRGB add(ColorRGB other) {
        return new ColorRGB(this.r + other.r, this.g + other.g, this.b + other.b);
    }
}
