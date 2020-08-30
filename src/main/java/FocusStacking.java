public class FocusStacking {
    private int [][] image;
    private short [][] focus;
    public FocusStacking(short[][] currentFocus, short[][] newFocus, int[][] currentImage, int[][] newImage){
        int height, width;
        height = currentImage.length;
        width = currentImage[0].length;
        this.image = new int[height][width];
        this.focus = new short[height][width];
        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                if (newFocus[i][j] > currentFocus[i][j]){
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
