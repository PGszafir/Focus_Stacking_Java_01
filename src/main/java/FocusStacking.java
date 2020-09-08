import static java.lang.Math.*;

public class FocusStacking {
    private int [][] image;
    private short [][] focus;
    public FocusStacking(short[][] currentFocus, short[][] newFocus, int[][] currentImage, int[][] newImage){
        int height, width;
        int COLOR_TRESHOLD = 100;
        height = currentImage.length;
        width = currentImage[0].length;
        this.image = new int[height][width];
        this.focus = new short[height][width];
        int colorDifference;
        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                if (newFocus[i][j] > currentFocus[i][j]) {
                    colorDifference = abs(getRed(currentImage[i][j]) - getRed(newImage[i][j])) +
                            abs(getGreen(currentImage[i][j]) - getGreen(newImage[i][j])) +
                            abs(getBlue(currentImage[i][j]) - getBlue(newImage[i][j]));
                    if(colorDifference < COLOR_TRESHOLD){
                        this.image[i][j] = newImage[i][j];
                        this.focus[i][j] = newFocus[i][j];
                    }
                    else{
                        this.image[i][j] = currentImage[i][j];
                        this.focus[i][j] = currentFocus[i][j];
                    }
                }
                else {
                    this.image[i][j] = currentImage[i][j];
                    this.focus[i][j] = currentFocus[i][j];
                }

            }
        }
    }
    public int getPixel(int x, int y){
        return this.image[x][y];
    }
    public int[][] getImage(){
        return this.image;
    }
    public short[][] getFocus(){
        return this.focus;
    }
    public short getRed(int pixel) {
        return (short)((pixel >> 16) & 0xFF);
    }
    public short getGreen(int pixel) {
        return (short)((pixel >> 8) & 0xFF);

    }
    public short getBlue(int pixel) {
        return (short)(pixel & 0xFF);
    }
}
