import org.joda.time.DateTime;

//import javax.swing.*;

//uruchomcie sobie i zobaczcie co się dzieje powinno najpierw długo budowac a za drugim razem szybciej
public class Main {
    public static void main(String[] args){
        new DateTime();
        System.out.println("Focus Stacking Java Program");
        //JOptionPane.showMessageDialog(null,"Focus Stacking Java Program");

        //FotoDecomposition
        final int NUMBER_OF_PHOTOS = 7;
        int[][] OriginalPixelMap;
        int[][][] allBlurredPixelMaps = new int[NUMBER_OF_PHOTOS][][];
        short[][][] allFocusMaps = new short[NUMBER_OF_PHOTOS][][];
        for(int i = 0; i < NUMBER_OF_PHOTOS; i++) {
            OriginalPixelMap = new FotoDecomposition("DSC065"+(i+46)+".JPG").getImage();
            allBlurredPixelMaps[i] = new GaussianBlur(OriginalPixelMap).getImage();
            allFocusMaps[i] = new EdgeDetection(allBlurredPixelMaps[i]).getFocusMap();
            System.out.println("Przetworzone zdjęcie:"+(i+1));
            /*for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    System.out.print("[");
                    System.out.print(" " + allOriginalPixelMaps[i][j][k]);
                    System.out.print("], ");
                }
                System.out.println();
            }
            System.out.println("\n\n");*/
        }
        FocusStacking focusStacking = new FocusStacking(allFocusMaps, allBlurredPixelMaps);
        new FotoRecomposition(focusStacking.getImage());
        //GaussianBlur
        //System.out.println("----------------------------------------------------");
        //int [][][] allBlurredPixelMaps = new int[NUMBER_OF_PHOTOS][][];
        //for(int i = 0; i < NUMBER_OF_PHOTOS; i++) {
            //allBlurredPixelMaps[i] = new GaussianBlur(allOriginalPixelMaps[i]).getImage();
            /*for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    System.out.print("[");
                    System.out.print(" " + allBlurredPixelMaps[i][j][k]);
                    System.out.print("], ");
                }
                System.out.println();
            }
            System.out.println("\n\n");*/
        //}

        //EdgeDetection
        //short[][][] allFocusMaps = new short[NUMBER_OF_PHOTOS][][];
        //for(int i = 0; i < NUMBER_OF_PHOTOS; i++) {
            //allFocusMaps[i] = new EdgeDetection(allBlurredPixelMaps[i]).getFocusMap();
            /*for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                        System.out.print(" " + allFotoSharpness[i].sharpnessValue[j][k]);
                }
                System.out.println();
            }
            System.out.println("\n\n");*/
       // }

        //Focus Stacking
        //FocusStacking focusStacking = new FocusStacking(allFocusMaps, allBlurredPixelMaps);
        /*for (int j = 0; j < 5; j++) {
            for (int k = 0; k < 5; k++) {
                System.out.print("[");
                System.out.print(" " + focusStacking.getPixel(j, k));
                System.out.print("], ");
            }
            System.out.println();
        }
        System.out.println("\n\n");*/


        //FotoRecomposition
        //new FotoRecomposition(focusStacking.getImage());
    }
}