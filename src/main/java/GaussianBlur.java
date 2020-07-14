class GaussianBlur {
    private int[][] image;
    private static int[][] weight_matrix = {{1,2,1},{2,4,2},{1,2,1}};
    public GaussianBlur(int[][] input_image) {
        int x = input_image.length;
        int y = input_image[0].length;
        this.image = new int[x][y];
        for(int i=0; i<x; i++) {
            for(int j=0; j<y; j++){
                if(i>0 && j>0 && i<x-1 && j<y-1){
                    int[][] blur_matrix = {{input_image[i-1][j-1],input_image[i-1][j],input_image[i-1][j+1]},
                                            {input_image[i][j-1],input_image[i][j],input_image[i][j+1]},
                                            {input_image[i+1][j-1],input_image[i][j],input_image[i+1][j+1]}};
                    int blurred_pixel=0;
                    int green;
                    for(int k=0; k<3; k++){
                        for(int l=0; l<3; l++){
                            //oblicz średnią ważoną pixel.green
                            green = blur_matrix[k][l] >> 8 & 0xFF;
                            blurred_pixel += green * weight_matrix[k][l];
                        }
                    }
                    blurred_pixel /= 16;
                    this.image[i][j] = (input_image[i][j] & 0xFFFF00FF) + blurred_pixel;
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
}
