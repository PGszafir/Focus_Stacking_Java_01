import static java.lang.Math.*;

public class EdgeDetection { // extends PixelMap {
    private static final int[][] laplacian_operator = {{-1,-1,-1},{-1,8,-1},{-1,-1,-1}};
    public short[][] sharpnessValue;
    public EdgeDetection(short[][][] input_image) {
        int height = input_image.length;
        int width = input_image[0].length;
        sharpnessValue = new short[height][width];
        short red;
        short green;
        short blue;
        for(int i=0; i<height; i++) {
            for(int j=0; j<width; j++){
                if(i>0 && j>0 && i<height-1 && j<width-1){
                    short[][][] laplacian_matrix = {{input_image[i-1][j-1],input_image[i-1][j],input_image[i-1][j+1]},
                            {input_image[i][j-1],input_image[i][j],input_image[i][j+1]},
                            {input_image[i+1][j-1],input_image[i+1][j],input_image[i+1][j+1]}};

                    for(int k=0; k<3; k++){
                        for(int l=0; l<3; l++){
                            red = laplacian_matrix[k][l][1];
                            green = laplacian_matrix[k][l][2];
                            blue = laplacian_matrix[k][l][3];
                            sharpnessValue[i][j] += (red + green + blue) * laplacian_operator[k][l];
                        }
                    }
                    sharpnessValue[i][j] = (short) abs(sharpnessValue[i][j]);
                }
                else{
                    red = input_image[i][j][1];
                    green = input_image[i][j][2];
                    blue = input_image[i][j][3];
                    sharpnessValue[i][j] = (short) (red + green + blue); //to trzeba będzie udoskonalić
                }
            }
        }
    }

    public short[][] getFocusMap(){
        return this.sharpnessValue;
    }
}
