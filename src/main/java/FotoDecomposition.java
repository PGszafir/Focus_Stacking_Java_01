import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

//import java.util.Arrays; //wyświetlanie tablicy intów

/** import i rozkład zdjęc JPG do postaci ARGB **/
public class FotoDecomposition {
    public short[][][] pixelArrayARGB;
    //obraz w formie tablicy atrybutów, które odpowiadają poszczególnym pikselom obrazka
    private BufferedImage image;

    public FotoDecomposition(String nazwaObrazu) {
        //wczytaj z pliku
        File imageFile = new File("resources/"+nazwaObrazu);
        try {
            //utwórz obiekt z wczytanego zdjęcia
            image = ImageIO.read(imageFile);
        } catch (IOException e) {
            System.err.println("Blad odczytu obrazka");
            e.printStackTrace();
        }
        int[] pixelArray = new int[image.getWidth() * image.getHeight()];
        //próba uzyskania tablicy pikseli
        image.getRGB(0, 0, image.getWidth(), image.getHeight(), pixelArray, 0, image.getWidth());
        //uzyskana tablica to tablica pikseli w postaci unsigned integer formy 0xAARRGGBB
        //System.out.println(Arrays.toString(pixelArray));

        //tablica pikseli reprezentowanych przez tablicę 4 wartości kolorów składowych
        pixelArrayARGB = new short[image.getHeight()][image.getWidth()][4];
        for (int i = 0; i < image.getWidth() * image.getHeight(); i++) {
            int x = i / image.getWidth(); //pozycja na osi poziomej
            int y = i % image.getWidth(); //pozycja na osi pionowej
            pixelArrayARGB[x][y][0] = (short)((pixelArray[i] >> 24) & 0xFF); //a, alfa
            pixelArrayARGB[x][y][1] = (short)((pixelArray[i] >> 16) & 0xFF); //r, czerwony
            pixelArrayARGB[x][y][2] = (short)((pixelArray[i] >> 8) & 0xFF); //g, zielony
            pixelArrayARGB[x][y][3] = (short)((pixelArray[i]) & 0xFF); //b, niebieski
        }
    }
}