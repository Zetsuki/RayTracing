package render.utils;

public class ColorRGB {
    private final float r,g,b;

    public static final ColorRGB black = new ColorRGB(0f, 0f, 0f);
    public static final ColorRGB white = new ColorRGB(1f, 1f, 1f);
    public static final ColorRGB red = new ColorRGB(1f, 0f, 0f);
    public static final ColorRGB green = new ColorRGB(0f, 1f, 0f);
    public static final ColorRGB blue = new ColorRGB(0f, 0f, 1f);
    public static final ColorRGB yellow = new ColorRGB(1f, 1f, 0f);
    public static final ColorRGB cyan = new ColorRGB(0f, 1f, 1f);
    public static final ColorRGB magenta = new ColorRGB(1f, 0f, 1f);
    public static final ColorRGB gray = new ColorRGB(0.5f, 0.5f, 0.5f);
    public static final ColorRGB darkGray = new ColorRGB(0.25f, 0.25f, 0.25f);
    public static final ColorRGB lightGray = new ColorRGB(0.75f, 0.75f, 0.75f);
    public static final ColorRGB orange = new ColorRGB(1f, 0.5f, 0f);
    public static final ColorRGB pink = new ColorRGB(1f, 0.75f, 0.8f);
    public static final ColorRGB purple = new ColorRGB(0.5f, 0f, 0.5f);

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
