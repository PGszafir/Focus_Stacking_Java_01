import static java.lang.Math.*;

public class EdgeDetection { // extends PixelMap {
    private static final int[][] laplacian_operator = {{-1,-1,-1},{-1,8,-1},{-1,-1,-1}};
    public EdgeDetection(int[][][] input_image) {
        int height = input_image.length;
        int width = input_image[0].length;
        int[][] sharpness_value = new int[height][width];
        int red;
        int green;
        int blue;
        for(int i=0; i<height; i++) {
            for(int j=0; j<width; j++){
                if(i>0 && j>0 && i<height-1 && j<width-1){
                    int[][][] laplacian_matrix = {{input_image[i-1][j-1],input_image[i-1][j],input_image[i-1][j+1]},
                            {input_image[i][j-1],input_image[i][j],input_image[i][j+1]},
                            {input_image[i+1][j-1],input_image[i][j],input_image[i+1][j+1]}}; //tu chyba wymiary na odwrót

                    for(int k=0; k<3; k++){
                        for(int l=0; l<3; l++){
                            red = laplacian_matrix[k][l][1];
                            green = laplacian_matrix[k][l][2];
                            blue = laplacian_matrix[k][l][3];
                            sharpness_value[i][j] += (red + green + blue) * laplacian_operator[k][l];
                        }
                    }
                    sharpness_value[i][j] = abs(sharpness_value[i][j]);
                }
                else{
                    red = input_image[i][j][1];
                    green = input_image[i][j][2];
                    blue = input_image[i][j][3];
                    sharpness_value[i][j] = red + green + blue; //to trzeba będzie udoskonalić
                }
            }
        }
    }
}
