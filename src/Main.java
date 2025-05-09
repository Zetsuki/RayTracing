import static render.utils.JavaTGA.saveTGA;

public class Main {
    /**
     * @param args no command line arguments
     */
    public static void main(String[] args) {
        int w=1024;
        int h=768;
        byte buffer[]=new byte[3*w*h];

        for(int row = 0; row < h; row++){ // for each row of the image
            for(int col = 0; col < w; col++){ // for each column of the image

                int index = 3*((row*w)+col); // compute index of color for pixel (x,y) in the buffer

                // Ensure that the pixel is black
                buffer[index]=0; // blue : take care, blue is the first component !!!
                buffer[index+1]=0; // green
                buffer[index+2]=0; // red (red is the last component !!!)

                // Depending on the x position, select a color...
                if (col<w/3) buffer[index]=(byte)255; // Blue in the left part of the image
                else if (col<2*w/3) buffer[index+1]=(byte)255; // Green in the middle
                else buffer[index+2]=(byte)255; // Red in the right part
            }
        }
        try {
            saveTGA("imagetest.tga",buffer,w,h);
        }
        catch(Exception e)
        {
            System.err.println("TGA file not created :"+e);
        }
    }
}
