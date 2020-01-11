package Utilities;

public class Generator {
    public static int[] createIntVector(int length) {
        int[] vector = new int[length];

        for (int i = 0; i < length; i++) {
            vector[i] = 0;
        }

        return vector;
    }

    public static double[] createDoubleVector(int length) {
        double[] vector = new double[length];

        for (int i = 0; i < length; i++) {
            vector[i] = 0d;
        }

        return vector;
    }

    public static double[][] creatMatrix(int size) {
        // Creates unit matrix

        double[][] matrix = new double[size][size];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = (i == j) ? 1d : 0d;
            }
        }

        return matrix;
    }

    public static double[][] createMatrix(int rows, int cols) {
        // Creates matrix filled with 0d

        double[][] matrix = new double[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = 0d;
            }
        }

        return matrix;
    }
}
