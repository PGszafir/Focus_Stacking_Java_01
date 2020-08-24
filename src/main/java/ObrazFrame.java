import javax.swing.JPanel;
import javax.swing.JFrame;

public class ObrazFrame extends JFrame {

    public ObrazFrame() {
        super("Program obrazkowy");

        JPanel obrazPanel = new FotoShow();
        add(obrazPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack(); //rozmiar ramki dopasuje się automatycznie
        setVisible(true);
    }
}