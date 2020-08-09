//import i eksport zdjęc JPG

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import java.util.Arrays; //wyświetlanie tablicy intów

public class FotoJPG extends JPanel {
    //obraz w formie tablicy atrybutów, które odpowiadają poszczególnym pikselom obrazka
    private BufferedImage image;

    public FotoJPG() {
        super();
        //wczytaj z pliku
        File imageFile = new File("psiaki.jpg");
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
        System.out.println(Arrays.toString(pixelArray));


        //tablica pikseli reprezentowanych przez tablicę 4 wartości kolorów składowych
        int[][][] pixelArrayARGB = new int[image.getWidth()][image.getHeight()][4];
        for (int i = 0; i < image.getWidth() * image.getHeight(); i++) {
            int x = i / image.getWidth(); //pozycja na osi poziomej
            int y = i % image.getWidth(); //pozycja na osi pionowej
            pixelArrayARGB[y][x][0] = (pixelArray[i] >> 24) & 0xFF; //a, alfa
            pixelArrayARGB[y][x][1] = (pixelArray[i] >> 16) & 0xFF; //r, czerwony
            pixelArrayARGB[y][x][2] = (pixelArray[i] >> 8) & 0xFF; //g, zielony
            pixelArrayARGB[y][x][3] = (pixelArray[i]) & 0xFF; //b, niebieski
        }
        for(int i = 0; i < 50; i++) {
            for(int j = 0; j < 50; j++) {
                System.out.print("[");
                for(int k = 0; k < 4; k++) {
                    System.out.print(" "+pixelArrayARGB[i][j][k]);
                }
                System.out.print("], ");
            }
            System.out.println();
        }
        Dimension dimension = new Dimension(image.getWidth(), image.getHeight());
        setPreferredSize(dimension);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        //wyświetlenie obrazka
        g2d.drawImage(image, 0, 0, this);
    }
}
