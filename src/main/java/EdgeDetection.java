import static java.lang.Math.*;

public class EdgeDetection { // extends PixelMap {
    private static final int[][] laplacian_operator = {{-1,-1,-1},{-1,8,-1},{-1,-1,-1}};
    public short[][] sharpnessValue;
    public EdgeDetection(int[][] input_image) {
        int height = input_image.length;
        int width = input_image[0].length;
        sharpnessValue = new short[height][width];
        short red;
        short green;
        short blue;
        for(int i=0; i<height; i++) {
            for(int j=0; j<width; j++){
                if(i>0 && j>0 && i<height-1 && j<width-1){
                    int[][] laplacian_matrix = {{input_image[i-1][j-1],input_image[i-1][j],input_image[i-1][j+1]},
                            {input_image[i][j-1],input_image[i][j],input_image[i][j+1]},
                            {input_image[i+1][j-1],input_image[i+1][j],input_image[i+1][j+1]}};
                    //short redValue = 0;
                    //short blueValue = 0;
                    //short greenValue = 0;
                    for(int k=0; k<3; k++){
                        for(int l=0; l<3; l++){
                            red = getRed(laplacian_matrix[k][l]);
                            green = getGreen(laplacian_matrix[k][l]);
                            blue = getBlue(laplacian_matrix[k][l]);
                            //redValue += red * laplacian_operator[k][l];
                            //blueValue += blue * laplacian_operator[k][l];
                            //greenValue += green * laplacian_operator[k][l];
                            sharpnessValue[i][j] += (red + green + blue) * laplacian_operator[k][l];
                        }
                    }
                    sharpnessValue[i][j] = (short) (abs(sharpnessValue[i][j]));
                    //sharpnessValue[i][j] = (short) (abs(redValue) + abs(blueValue) + abs(greenValue));
                }
                else{
                    red = getRed(input_image[i][j]);
                    green = getGreen(input_image[i][j]);
                    blue = getBlue(input_image[i][j]);
                    sharpnessValue[i][j] = (short) (red + green + blue); //to trzeba będzie udoskonalić
                }
            }
        }
    }

    public short[][] getFocusMap(){
        return this.sharpnessValue;
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
