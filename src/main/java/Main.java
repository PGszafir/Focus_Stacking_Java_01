import org.joda.time.DateTime;

import javax.swing.*;

//uruchomcie sobie i zobaczcie co się dzieje powinno najpierw długo budowac a za drugim razem szybciej
public class Main {
    public static void main(String[] args){
        new DateTime();
        System.out.println("Focus Stacking Java Program");
        JOptionPane.showMessageDialog(null,"Focus Stacking Java Program");

        final int NUMBER_OF_PHOTOS = 5;
        FotoDecomposition[] allFotoPixels = new FotoDecomposition[NUMBER_OF_PHOTOS];

        for(int i = 1; i <= NUMBER_OF_PHOTOS; i++) {
            allFotoPixels[i-1] = new FotoDecomposition("foto"+i+".jpg");
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    System.out.print("[");
                    for (int l = 0; l < 4; l++)
                        System.out.print(" " + allFotoPixels[i - 1].pixelArrayARGB[j][k][l]);
                    System.out.print("], ");
                }
                System.out.println();
            }
            System.out.println("\n\n");
        }
    }
}