import org.joda.time.DateTime;

//import javax.swing.*;

//uruchomcie sobie i zobaczcie co się dzieje powinno najpierw długo budowac a za drugim razem szybciej
public class Main {
    public static void main(String[] args){
        new DateTime();
        System.out.println("Focus Stacking Java Program");
        //JOptionPane.showMessageDialog(null,"Focus Stacking Java Program");

        //FotoDecomposition
        final int NUMBER_OF_PHOTOS = 2;
        FotoDecomposition[] allFotoPixels = new FotoDecomposition[NUMBER_OF_PHOTOS]; //MOŻE BEZ SENSU TAK TWORZYĆ TABLICĘ KLAS

        for(int i = 0; i < NUMBER_OF_PHOTOS; i++) {
            allFotoPixels[i] = new FotoDecomposition("DSC065"+(i+46)+".JPG");
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    System.out.print("[");
                    System.out.print(" " + allFotoPixels[i].getPixel(j, k));
                    System.out.print("], ");
                }
                System.out.println();
            }
            System.out.println("\n\n");
        }

        //GaussianBlur
        System.out.println("----------------------------------------------------");
        GaussianBlur[] allFotoBlurred = new GaussianBlur[NUMBER_OF_PHOTOS];
        int [][][] blurredPixels = new int[NUMBER_OF_PHOTOS][][];
        for(int i = 0; i < NUMBER_OF_PHOTOS; i++) {
            allFotoBlurred[i] = new GaussianBlur(allFotoPixels[i].getImage());
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    System.out.print("[");
                    System.out.print(" " + allFotoBlurred[i].getPixel(j,k));
                    System.out.print("], ");
                }
                System.out.println();
            }
            System.out.println("\n\n");
            blurredPixels[i] = allFotoBlurred[i].getImage();
        }

        //EdgeDetection
        EdgeDetection[] allFotoSharpness = new EdgeDetection[NUMBER_OF_PHOTOS];
        short[][][] focusMap = new short[NUMBER_OF_PHOTOS][][];
        for(int i = 0; i < NUMBER_OF_PHOTOS; i++) {
            allFotoSharpness[i] = new EdgeDetection(allFotoBlurred[i].getImage());
            /*for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                        System.out.print(" " + allFotoSharpness[i].sharpnessValue[j][k]);
                }
                System.out.println();
            }
            System.out.println("\n\n");*/
            focusMap[i] = allFotoSharpness[i].getFocusMap();
        }

        //Focus Stacking
        FocusStacking focusStacking = new FocusStacking(focusMap, blurredPixels);
        for (int j = 0; j < 5; j++) {
            for (int k = 0; k < 5; k++) {
                System.out.print("[");
                System.out.print(" " + focusStacking.getPixel(j, k));
                System.out.print("], ");
            }
            System.out.println();
        }
        System.out.println("\n\n");


        //FotoRecomposition
        new FotoRecomposition(focusStacking.getImage());
    }
}