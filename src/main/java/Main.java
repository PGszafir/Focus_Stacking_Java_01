import org.joda.time.DateTime;

import java.util.Scanner;

//import javax.swing.*;

//uruchomcie sobie i zobaczcie co się dzieje powinno najpierw długo budowac a za drugim razem szybciej
public class Main {
    public static void main(String[] args){
        new DateTime();
        System.out.println("Focus Stacking Java Program");
        //JOptionPane.showMessageDialog(null,"Focus Stacking Java Program");

        Scanner scan = new Scanner(System.in);
        System.out.println("Wybierz numer zdjęcia do wyostrzenia:\n" +
                "1 - tulipan");
        String fotoChoice = scan.nextLine();

        switch(fotoChoice){
            case "1":
                allSharpeningOperation("C:/Users/Łukasz/Desktop/Java_Obrazy/tulipan/1", "DSC065", 46, 41);
                break;
            default:
                System.out.println("Niepoprawny numer zdjęcia!");
        }
    }

    public static void allSharpeningOperation(String folderPath, String firstPhotoNameCore, int firstPhotoNameNumber, int numberOfPhotos) {
        final int NUMBER_OF_PHOTOS = numberOfPhotos;
        int[][] OriginalPixelMap; //tablica przechowująca oryginalną mapę pikseli przed GaussianBlur
        int[][] currentBlurredPixelMap; //tablica przechowująca aktualnie wyostrzane zdjęcie
        int[][] newBlurredPixelMap; //tablica przechowująca nowe zdjęcie po GaussianBlur
        short[][] currentFocusMap; //tablica ostrości pikseli aktualnie wyostrzanego zdjęcia
        short[][] newFocusMap; //tablica przechowująca ostrość pikseli nowego zdjęcia

        //OPERACJE NA PIERWSZYM ZDJĘCIU KOLEKCJI
        OriginalPixelMap = new FotoDecomposition(folderPath + firstPhotoNameCore + firstPhotoNameNumber + ".JPG").getImage();
        currentBlurredPixelMap = new GaussianBlur(OriginalPixelMap).getImage();
        currentFocusMap = new EdgeDetection(currentBlurredPixelMap).getFocusMap();


        //OPERACJE NA KOLEJNYCH ZDJĘCIACH KOLEKCJI
        for(int i = 1; i < NUMBER_OF_PHOTOS; i++) {
            OriginalPixelMap = new FotoDecomposition(folderPath + firstPhotoNameCore + (firstPhotoNameNumber+i) + ".JPG").getImage();
            newBlurredPixelMap = new GaussianBlur(OriginalPixelMap).getImage();
            newFocusMap = new EdgeDetection(newBlurredPixelMap).getFocusMap();
            System.out.println("Przetworzone zdjęcie:"+(i+1));
            FocusStacking focusStacking = new FocusStacking(currentFocusMap, newFocusMap, currentBlurredPixelMap, newBlurredPixelMap);
            currentBlurredPixelMap = focusStacking.getImage();
            currentFocusMap = focusStacking.getFocus();
        }

        new FotoRecomposition(currentBlurredPixelMap, folderPath + firstPhotoNameCore + firstPhotoNameNumber + ".JPG");
    }
}