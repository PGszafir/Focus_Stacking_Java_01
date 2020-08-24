public class FocusStacking {
    private int [][][] image;
    public FocusStacking(int[][][] focus, int[][][][] input_images){ //to się później zmieni na chara podczas optymalizacji, ale na razie chcę to ze sobą połączyć i int mi jest potrzebny
        int numberOfPhotos, height, width;
        numberOfPhotos = input_images.length;
        height = input_images[0].length;
        width = input_images[0][0].length;
        this.image = new int[height][width][4];
        int [] pixel;
        int max_focus_value;

        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                pixel = input_images[0][i][j];
                max_focus_value = focus[0][i][j];
                for(int k=1; k<numberOfPhotos; k++){
                    if(focus[k][i][j]>max_focus_value){
                        max_focus_value = focus[k][i][j];
                        pixel = input_images[k][i][j];
                    }
                }
                this.image[i][j] = pixel;
            }
        }
    }
    public int[] getPixel(int x, int y){
        return this.image[x][y];
    }
    public int[][][] getImage(){
        return this.image;
    }
}
