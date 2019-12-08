package Utilities;

import java.util.Random;

public class Generator {
    public static double[] createVector(int length) {
        double[] vector = new double[length];

        for (int i = 0; i < length; i++) {
            vector[i] = 0d;
        }

        return vector;
    }

    public static double[][] createMatrix(int rows, int cols) {
        double[][] matrix = new double[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = 0d;
            }
        }

        return matrix;
    }

    public static double[][] unitMatrix(int size) {
        double[][] matrix = new double[size][size];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = (i == j) ? 1d : 0d;
            }
        }

        return matrix;
    }

    public static double[][] generateMatrix(int rows, int cols) {
        double[][] matrix = new double[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = 0;
            }
        }

        return matrix;
    }

    public static double[][] generateMatrix(double min, double max, int rows, int cols) {
        Random r = new Random();

        double[][] matrix = new double[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = min + (max - min) * r.nextDouble();
            }
        }

        return matrix;
    }
}
