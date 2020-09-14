import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/** import i prezentacja zdjęc JPG **/
public class FotoShow extends JPanel {
    //obraz w formie tablicy atrybutów, które odpowiadają poszczególnym pikselom obrazka
    private BufferedImage image;

    public FotoShow(String photoPath) {
        super();
        //wczytaj z pliku
        File imageFile = new File(photoPath);
        try {
            //utwórz obiekt z wczytanego zdjęcia
            image = ImageIO.read(imageFile);
        } catch (IOException e) {
            System.err.println("Blad odczytu obrazka");
            e.printStackTrace();
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
