public class FocusStacking {
    private char [][][] image;
    public FocusStacking(int[][][] focus, char[][][][] input_images){
        int x, y, z;
        x = input_images.length;
        y = input_images[0].length;
        z = input_images[0][0].length;
        this.image = new char[y][z][4];
        char [] pixel;
        int max_focus_value;

        for(int i=0; i<y; i++){
            for(int j=0; j<z; j++){
                pixel = input_images[0][i][j];
                max_focus_value = focus[0][i][j];
                for(int k=1; k<x; k++){
                    if(focus[k][i][j]>max_focus_value){
                        max_focus_value = focus[k][i][j];
                        pixel = input_images[k][i][j];
                    }
                }
                this.image[i][j] = pixel;
            }
        }
    }
    public char[] getPixel(int x, int y){
        return this.image[x][y];
    }
    public char[][][] getImage(){
        return this.image;
    }
}
