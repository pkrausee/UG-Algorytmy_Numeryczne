package Matrix;

import Models.Fraction;

import java.util.Random;

public class MatrixGenerator {
    private static Random r = new Random();

    public static void generateValues(Double[] arr, double min, double max) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = min + (max - min) * r.nextDouble();
        }
    }

    public static void generateValues(Float[] arr, float min, float max) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = min + (max - min) * r.nextFloat();
        }
    }

    public static void generateValues(Integer[] arr, int min, int max) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = min + r.nextInt(max);
        }
    }

    public static void generateValues(Fraction[] arr, int min, int max) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new Fraction((min + r.nextInt(max)), (min + r.nextInt(max)));
        }
    }

    public static void generateValues(Double[][] matrix, double min, double max) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = min + (max - min) * r.nextDouble();
            }
        }
    }

    public static void generateValues(Float[][] matrix, float min, float max) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = min + (max - min) * r.nextFloat();
            }
        }
    }

    public static void generateValues(Integer[][] matrix, int min, int max) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = min + r.nextInt(max);
            }
        }
    }

    public static void generateValues(Fraction[][] matrix, int min, int max) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = new Fraction((min + r.nextInt(max)), (min + r.nextInt(max)));
            }
        }
    }
}
