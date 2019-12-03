package Utilities;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class Generator {
    public static Double[] createVector(int length) {
        Double[] vector = new Double[length];

        for(int i = 0; i < length; i++) {
            vector[i] = 0d;
        }

        return vector;
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

    public static Double[][] generateMatrix(double min, double max, int rows, int cols) {
        Random r = new Random();

        Double[][] matrix = new Double[rows][cols];

        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                matrix[i][j] = round(min + (max - min) * r.nextDouble(), 2);
            }
        }

        return matrix;
    }

    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
