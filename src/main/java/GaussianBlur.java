class GaussianBlur {
    private int[][] image;
    private final static int[][] weight_matrix = {{1,2,1},{2,4,2},{1,2,1}};
    public GaussianBlur(int[][] input_image) {
        int height = input_image.length;
        int width = input_image[0].length;
        image = new int[height][width];
        for(int i=0; i<height; i++) {
            for(int j=0; j<width; j++){
                if(i>0 && j>0 && i<height-1 && j<width-1){
                    int[][] blur_matrix = {{input_image[i-1][j-1],input_image[i-1][j],input_image[i-1][j+1]},
                                            {input_image[i][j-1],input_image[i][j],input_image[i][j+1]},
                                            {input_image[i+1][j-1],input_image[i+1][j],input_image[i+1][j+1]}};
                    short blurred_green=0;
                    short green;
                    for(int k=0; k<3; k++){
                        for(int l=0; l<3; l++){
                            //oblicz średnią ważoną pixel.green
                            green = getGreen(blur_matrix[k][l]);
                            blurred_green += green * weight_matrix[k][l];
                        }
                    }
                    blurred_green /= 16;
                    this.image[i][j] = (input_image[i][j] & 0xFFFF00FF) + (blurred_green << 8);
                }
                else{
                    this.image[i][j] = input_image[i][j];
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
