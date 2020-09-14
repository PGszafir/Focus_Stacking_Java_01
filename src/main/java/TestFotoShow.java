import java.awt.EventQueue;

public class TestFotoShow {
    public TestFotoShow(String photoPath) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ObrazFrame(photoPath);
            }
        });
    }
}
