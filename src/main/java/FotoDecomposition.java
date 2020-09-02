import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

//import java.util.Arrays; //wyświetlanie tablicy intów

/** import i rozkład zdjęc JPG do postaci ARGB **/
public class FotoDecomposition {
    private int[][] pixelArrayTwoDim;
    //obraz w formie tablicy atrybutów, które odpowiadają poszczególnym pikselom obrazka
    private BufferedImage image;

    public FotoDecomposition(String photoPath) {
        //wczytaj z pliku
        File imageFile = new File(photoPath);
        //System.out.println(imageFile.getAbsolutePath());
        try {
            //utwórz obiekt z wczytanego zdjęcia
            image = ImageIO.read(imageFile);
        } catch (IOException e) {
            System.err.println("Blad odczytu obrazka");
            e.printStackTrace();
        }
        int width = image.getWidth();
        int height = image.getHeight();
        int[] pixelArrayOneDim; //= new int[image.getWidth() * image.getHeight()];
        //próba uzyskania tablicy pikseli
        pixelArrayOneDim = image.getRGB(0, 0, width, height, null, 0, width);
        //uzyskana tablica to tablica pikseli w postaci unsigned integer formy 0xAARRGGBB
        //System.out.println(Arrays.toString(pixelArray));

        //tablica pikseli reprezentowanych przez tablicę 4 wartości kolorów składowych
        pixelArrayTwoDim = new int[height][width];
        int x, y;
        for (int i = 0; i < width*height ; i++) {
            x = height - (i / width) - 1; //pozycja na osi poziomej
            y = width - (i % width) - 1; //pozycja na osi pionowej
            pixelArrayTwoDim[x][y] = pixelArrayOneDim[i];
            /*pixelArrayARGB[x][y][0] = (short)((pixelArray[i] >> 24) & 0xFF); //a, alfa
            pixelArrayARGB[x][y][1] = (short)((pixelArray[i] >> 16) & 0xFF); //r, czerwony
            pixelArrayARGB[x][y][2] = (short)((pixelArray[i] >> 8) & 0xFF); //g, zielony
            pixelArrayARGB[x][y][3] = (short)((pixelArray[i]) & 0xFF); //b, niebieski*/
        }
        //pixelArray = null; //CZYSZCZENIE PAMIĘCI, NADGORLIWE, ALE MOŻE ZMNIEJSZY STOS
    }

    public int[][] getImage(){
        return pixelArrayTwoDim;
    }

    public int getPixel(int x, int y){
        return pixelArrayTwoDim[x][y];
    }
}