import render.scene.*;
import render.utils.ColorRGB;
import render.utils.Vec3f;

import java.io.IOException;
import java.util.ArrayList;

import static render.utils.JavaTGA.saveTGA;

public class Main {

    private static final int DEFAULT_MAX_DEPTH = 10;
    private static final int DEFAULT_WIDTH = 1024;
    private static final int DEFAULT_HEIGHT = 768;

    /**
     * @param args no command line arguments
     */
    public static void main(String[] args) throws IOException {
        ArrayList<Scene> scenes = new ArrayList<>();
        Scene scene1 = new BaseScene();
        Scene scene2 = new BaseSceneWithTextures();
        Scene scene3 = new BaseSceneWithReflection();
        Scene scene4 = new BaseSceneWithRefraction();

        scenes.add(scene1);
        scenes.add(scene2);
        scenes.add(scene3);
        scenes.add(scene4);

        for(Scene scene : scenes) {
            String name = getFileName(scene);
            saveTGA(name, getBuffer(scene), DEFAULT_WIDTH, DEFAULT_HEIGHT);
            System.out.println("Image saved as: " + name);
        }
    }

    private static byte[] getBuffer(Scene scene) {
        Vec3f camPosition = scene.getCamPosition();
        double screenDistance = scene.getScreenDistance();

        // Buffer holding pixel color data
        byte[] buffer = new byte[3 * DEFAULT_WIDTH * DEFAULT_HEIGHT];

        // Loopign through each pixel in the image
        for (int row = 0; row < DEFAULT_HEIGHT; row++) {
            for (int col = 0; col < DEFAULT_WIDTH; col++) {

                int index = 3 * (row * DEFAULT_WIDTH + col);

                // Compute the camera direction vector for the current pixel
                Vec3f direction = new Vec3f(
                        (2 * ((col + 0.5f) / DEFAULT_WIDTH) - 1) * (float) DEFAULT_WIDTH / DEFAULT_HEIGHT,
                        1 - 2 * ((row + 0.5f) / DEFAULT_HEIGHT),
                        (float) -screenDistance);

                // Getting the color for the current pixel
                ColorRGB color = scene.findColor(camPosition, direction, DEFAULT_MAX_DEPTH);

                // Storing the color in the buffer (clamping values [0, 255])
                buffer[index] = (byte) (Math.min(255, color.getB() * 255));
                buffer[index + 1] = (byte) (Math.min(255, color.getG() * 255));
                buffer[index + 2] = (byte) (Math.min(255, color.getR() * 255));
            }
        }
        return buffer;
    }

    private static String getFileName(Scene scene) {
        return "output/" + scene.getClass().getSimpleName() + "_" + DEFAULT_MAX_DEPTH + "_" + DEFAULT_WIDTH + "x" + DEFAULT_HEIGHT + ".tga";
    }
}
