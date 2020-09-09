import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class FotoRecomposition {
    private BufferedImage image;

    public FotoRecomposition(int[][] inputImage, String photoPath, String photoName) {
        int height = inputImage.length;
        int width = inputImage[0].length;
        //Szablon na którym zmieniamy piksele
        File initialImage = new File(photoPath);
        try {
            //utwórz obiekt z wczytanego zdjęcia
            image = ImageIO.read(initialImage);
        } catch (IOException e) {
            System.err.println("Blad odczytu obrazka");
            e.printStackTrace();
        }

        //image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB); TO NIE DZIAŁA TAK JAK POWINNO
        int[] finalArray = new int[height*width];

        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++) {
                finalArray[width*i + j] = inputImage[i][j];
            }
        }

        image.setRGB(0,0, width, height, finalArray, 0, width);

        File outputFile = new File("resources/stacked_foto_"+photoName+".jpg");

        try {
            ImageIO.write(image, "jpg", outputFile);
        } catch (IOException e) {
            System.err.println("Blad zapisu obrazka");
            e.printStackTrace();
        }
    }
}
