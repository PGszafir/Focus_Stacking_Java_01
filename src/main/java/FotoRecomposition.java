import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class FotoRecomposition {
    private BufferedImage image;

    public FotoRecomposition(int[][][] inputImage) {
        int height = inputImage.length;
        int width = inputImage[0].length;
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        int[][] oneDimensionalArray = new int[height*width][4];
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++) {
                oneDimensionalArray[height*i + j][0] = inputImage[i][j][0];
                oneDimensionalArray[height*i + j][1] = inputImage[i][j][1];
                oneDimensionalArray[height*i + j][2] = inputImage[i][j][2];
                oneDimensionalArray[height*i + j][3] = inputImage[i][j][3];
            }
        }

        int[] finalArray = new int[height*width];
        for(int i = 0; i < width*height; i++) {
            finalArray[i] = (oneDimensionalArray[i][0] << 24) + (oneDimensionalArray[i][1] << 16) +
                    (oneDimensionalArray[i][2] << 8) + oneDimensionalArray[i][3];
        }

        image.setRGB(0,0, width, height, finalArray, 0, width);

        File outputFile = new File("resources/stacked_foto.jpg");

        try {
            ImageIO.write(image, "JPEG", outputFile);
        } catch (IOException e) {
            System.err.println("Blad zapisu obrazka");
            e.printStackTrace();
        }
    }
}
