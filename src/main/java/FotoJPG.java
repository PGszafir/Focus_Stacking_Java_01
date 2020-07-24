//import i eksport zdjęc JPG

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

//import java.util.Arrays; //wyświetlanie tablicy intów

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
        //int[] pixelArray = new int[10000];
        //próba uzyskania tablicy pikseli
        //image.getRGB(0, 0, image.getWidth(), image.getHeight(), pixelArray, 0, 1);
        //System.out.println(Arrays.toString(pixelArray));

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
