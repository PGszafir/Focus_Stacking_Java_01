public class FocusStacking {
    private int [][] image;
    private short [][] focus;
    public FocusStacking(short[][] currentFocus, short[][] newFocus, int[][] currentImage, int[][] newImage){
        int height, width;
        int COMPLEX_SIZE = 1;
        height = currentImage.length;
        width = currentImage[0].length;
        this.image = new int[height][width];
        this.focus = new short[height][width];
        short [][] currentFocusComplex = new short[(height/COMPLEX_SIZE)+1][(width/COMPLEX_SIZE)+1];
        short [][] newFocusComplex = new short[(height/COMPLEX_SIZE)+1][(width/COMPLEX_SIZE)+1];
        for(int i=0; i < (height/COMPLEX_SIZE)+1; i++) {
            for (int j = 0; j < (width/COMPLEX_SIZE)+1; j++) {
                currentFocusComplex[i][j] = 0;
                newFocusComplex[i][j] = 0;
            }
        }
        for(int i=0; i<height; i++) {
            for (int j = 0; j < width; j++) {
                currentFocusComplex[i/COMPLEX_SIZE][j/COMPLEX_SIZE] += currentFocus[i][j];
                newFocusComplex[i/COMPLEX_SIZE][j/COMPLEX_SIZE] += newFocus[i][j];
            }
        }

        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                //if (newFocus[i][j] > currentFocus[i][j]){
                if (newFocusComplex[i/COMPLEX_SIZE][j/COMPLEX_SIZE] > currentFocusComplex[i/COMPLEX_SIZE][j/COMPLEX_SIZE]) {
                    this.image[i][j] = newImage[i][j];
                    this.focus[i][j] = newFocus[i][j];
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
}
