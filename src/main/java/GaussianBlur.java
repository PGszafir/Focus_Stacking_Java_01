class GaussianBlur {
    public int[][] image;
    private static int[][] matrix = [[1,2,1],[2,4,2],[1,2,1]];
    public GaussianBlur(int[][] input_image) {
        x = input_image.length;
        y = input_image[0].length;
        for(int i=0; i<x; i++) {
            for(int j=0; j<y; j++){
                if(i>0 && j>0 && i<x-1 && j<y-1){
                    int[][] blur_matrix = [[input_image[i-1][j-1],input_image[i-1][j],input_image[i-1][j+1]],
                                            [input_image[i][j-1],input_image[i][j],input_image[i][j+1]],
                                            [input_image[i+1][j-1],input_image[i][j],input_image[i+1][j+1]]];
                    int blured_pixel=0;
                    for(int k=0; k<3; k++){
                        for(int l=0; l<3; l++){
                            //oblicz średnią ważoną pixel.green
                        }
                    }
                    blured_pixel /= 16;
                }
                else{
                    //dodaj niezmieniony piksel
                }
            }
        }
    }
}