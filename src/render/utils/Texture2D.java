package render.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Texture2D {
    private final BufferedImage img;

    public Texture2D(String filePath) {
        BufferedImage loadedImg = null;
        try {
            InputStream resourceStream = getClass().getResourceAsStream(filePath);
            if (resourceStream != null) {
                loadedImg = ImageIO.read(resourceStream);
            } else {
                loadedImg = ImageIO.read(new File(filePath));
            }

            if (loadedImg == null) {
                throw new IOException("Image could not be read from " + filePath);
            }
        } catch (IOException e) {
            System.err.println("Error loading texture from file: " + filePath);
            e.printStackTrace();
            throw new IllegalArgumentException("The texture file (" + filePath + ") could not be loaded.");
        }

        this.img = loadedImg;
    }

    public ColorRGB getColor(double u, double v) {
        // Clamping U and V into [0,1]
        u = Math.max(0, Math.min(1, u));
        v = Math.max(0, Math.min(1, v));

        // From UV to coordinates
        int x = (int) (u * (img.getWidth() - 1));
        int y = (int) ((1 - v) * (img.getHeight() - 1));

        // Check (x,y) not out of bounds
        x = Math.max(0, Math.min(x, img.getWidth() - 1));
        y = Math.max(0, Math.min(y, img.getHeight() - 1));

        // Get color from coords
        Color color = new Color(img.getRGB(x, y));

        float red = color.getRed() / 255.0f;
        float green = color.getGreen() / 255.0f;
        float blue = color.getBlue() / 255.0f;

        return new ColorRGB(red, green, blue);
    }
}
