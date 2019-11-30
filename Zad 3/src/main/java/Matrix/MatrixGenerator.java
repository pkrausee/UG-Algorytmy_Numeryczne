package Matrix;

import java.util.Random;

public class MatrixGenerator {
    private final static Random random = new Random();
    private final static int pow = (int) Math.pow(2, 16);
    private final static int min = (int) (-pow);
    private final static int max = pow - 1;

    public static void generateValues(Double[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int r = min + (int) (Math.random() * ((max - min) + 1));

            arr[i] = (double) r / pow;
        }
    }

    public static void generateValues(Double[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                int r = min + (int) (Math.random() * ((max - min) + 1));

                matrix[i][j] = (double) r / pow;
            }
        }
    }

    public static Double[][] unitMatrix(int size) {
        Double[][] matrix = new Double[size][size];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = (i == j) ? 1d : 0d;
            }
        }

        return matrix;
    }
}
