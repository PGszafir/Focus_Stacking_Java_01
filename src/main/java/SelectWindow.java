import java.awt.FileDialog;
import java.awt.Frame;

public class SelectWindow {
    public void SelectWindow(String[] args) {
        System.out.println("Hello world!");

        Frame a = new Frame ("Okno macierzyste");
        a.setBounds(20,20,400,500);
        a.setVisible(true);

        FileDialog fd =new FileDialog(a,"Wczytaj",FileDialog.LOAD);
        // Ewentualnie: FileDialog fd =new FileDialog(a,"Zapisz",FileDialog.SAVE);
        fd.setVisible(true);
        String katalog=fd.getDirectory();
        String plik=fd.getFile();
        System.out.println("Wybrano plik: " + plik);
        System.out.println("w katalogu: "+ katalog);
        System.out.println("Ścieżka: "+ katalog + plik);
    }
}