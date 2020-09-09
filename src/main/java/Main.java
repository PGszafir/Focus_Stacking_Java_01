import org.joda.time.DateTime;

import java.util.Scanner;

//import javax.swing.*;

//uruchomcie sobie i zobaczcie co się dzieje powinno najpierw długo budowac a za drugim razem szybciej
public class Main {
    public static void main(String[] args) {
        new DateTime();
        System.out.println("Focus Stacking Java Program");
        //JOptionPane.showMessageDialog(null,"Focus Stacking Java Program");

        Scanner scan = new Scanner(System.in);
        System.out.println("Wybierz numer zdjęcia do wyostrzenia:\n" +
                "1 - tulipan\n" +
                "2 - tulipan_slupek\n" +
                "3 - slonecznik-center-1\n" +
                "4 - slonecznik-center-2\n" +
                "5 - slonecznik-all-1\n" +
                "6 - slonecznik-all-2\n" +
                "7 - slonecznik-tył");

        String fotoChoice = scan.nextLine();

        switch (fotoChoice) {
            case "1":
                allSharpeningOperation("C:/Users/Łukasz/Desktop/Java_Obrazy/tulipan/", "DSC065", 46, 41, "tulipan");
                break;
            case "2":
                allSharpeningOperation("C:/Users/Łukasz/Desktop/Java_Obrazy/tulipan_słupek/", "DSC06", 493, 53, "tulipan_slupek");
                break;
            case "3":
                allSharpeningOperation("C:/Users/Łukasz/Desktop/Java_Obrazy/słonecznik-center-1/", "DSC050", 24, 20, "slonecznik-center-1");
                break;
            case "4":
                allSharpeningOperation("C:/Users/Łukasz/Desktop/Java_Obrazy/słonecznik-center-2/", "DSC0", 4972, 31, "slonecznik-center-2");
                break;
            case "5":
                allSharpeningOperation("C:/Users/Łukasz/Desktop/Java_Obrazy/słonecznik-all-1/", "DSC050", 3, 21, "slonecznik-all-1");
                break;
            case "6":
                allSharpeningOperation("C:/Users/Łukasz/Desktop/Java_Obrazy/słonecznik-all-2/", "DSC049", 46, 24, "slonecznik-all-2");
                break;
            case "7":
                allSharpeningOperation("C:/Users/Łukasz/Desktop/Java_Obrazy/słonecznik-tył/jpg/", "DSC050", 44, 42, "slonecznik-tył");
                break;
            default:
                System.out.println("Niepoprawny numer zdjęcia!");
        }
    }

    public static void allSharpeningOperation(String folderPath, String firstPhotoNameCore, int firstPhotoNameNumber, int numberOfPhotos, String finalPhotoName) {
        int[][] OriginalPixelMap; //tablica przechowująca oryginalną mapę pikseli przed GaussianBlur
        int[][] currentBlurredPixelMap; //tablica przechowująca aktualnie wyostrzane zdjęcie
        int[][] newBlurredPixelMap; //tablica przechowująca nowe zdjęcie po GaussianBlur
        short[][] currentFocusMap; //tablica ostrości pikseli aktualnie wyostrzanego zdjęcia
        short[][] newFocusMap; //tablica przechowująca ostrość pikseli nowego zdjęcia

        //OPERACJE NA PIERWSZYM ZDJĘCIU KOLEKCJI
        String additionalZero = "";
        if(firstPhotoNameNumber < 10){
            additionalZero = "0";
        }

        String firstPhotoPath = folderPath + firstPhotoNameCore + additionalZero + firstPhotoNameNumber + ".JPG";
        OriginalPixelMap = new FotoDecomposition(firstPhotoPath).getImage();
        currentBlurredPixelMap = new GaussianBlur(OriginalPixelMap).getImage();
        currentFocusMap = new EdgeDetection(currentBlurredPixelMap).getFocusMap();


        //OPERACJE NA KOLEJNYCH ZDJĘCIACH KOLEKCJI
        String nextPhotoPath;
        for (int i = 1; i < numberOfPhotos; i++) {
            if(firstPhotoNameNumber + i >= 10){
                additionalZero = "";
            }

            nextPhotoPath = folderPath + firstPhotoNameCore + additionalZero + (firstPhotoNameNumber + i) + ".JPG";
            OriginalPixelMap = new FotoDecomposition(nextPhotoPath).getImage();
            newBlurredPixelMap = new GaussianBlur(OriginalPixelMap).getImage();
            newFocusMap = new EdgeDetection(newBlurredPixelMap).getFocusMap();
            System.out.println("Przetworzone zdjęcie:" + (i + 1));
            FocusStacking focusStacking = new FocusStacking(currentFocusMap, newFocusMap, currentBlurredPixelMap, newBlurredPixelMap);
            currentBlurredPixelMap = focusStacking.getImage();
            currentFocusMap = focusStacking.getFocus();
        }

        new FotoRecomposition(currentBlurredPixelMap, firstPhotoPath, finalPhotoName);
    }
}