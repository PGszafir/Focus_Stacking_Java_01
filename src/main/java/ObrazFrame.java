import javax.swing.JPanel;
import javax.swing.JFrame;

public class ObrazFrame extends JFrame {

    public ObrazFrame(String photoPath) {
        super(photoPath);

        JPanel obrazPanel = new FotoShow(photoPath);
        add(obrazPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
}