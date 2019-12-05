package Utilities;

public class MathUtils {
    public static Double[] multiply(Double scalar, Double[] vector) {
        Double[] result = new Double[vector.length];

        for (int i = 0; i < vector.length; i++) {
            result[i] = scalar * vector[i];
        }

        return result;
    }

    public static Double[][] multiply(Double scalar, Double[][] matrix) {
        Double[][] result = new Double[matrix.length][matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                result[i][j] = scalar * matrix[i][j];
            }
        }

        return result;
    }

    public static Double[] multiply(Double[] vector, Double[][] matrix) {
        Double[] result = new Double[matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                result[i] = 0d;
                result[i] = result[i] + (matrix[i][j] * vector[j]);
            }
        }

        return result;
    }

    public static Double[][] multiply(Double[][] A, Double[][] B) {
        Double[][] result = new Double[A.length][B[0].length];

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                result[i][j] = 0d;
                for (int k = 0; k < B.length; k++) {
                    result[i][j] = result[i][j] + (A[i][k] * B[k][j]);
                }
            }
        }

        return result;
    }

    public static Double[] add(Double[] A, Double[] B) {
        if (A.length != B.length) {
            throw new IllegalArgumentException();
        }

        Double[] result = new Double[A.length];

        for (int i = 0; i < A.length; i++) {
            result[i] = A[i] + B[i];
        }

        return result;
    }

    public static Double[][] add(Double[][] A, Double[][] B) {
        if(A.length != B.length || A[0].length != B[0].length) {
            throw new IllegalArgumentException();
        }

        Double[][] result = new Double[A.length][A[0].length];

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                result[i][j] = 0d;
                for (int k = 0; k < B.length; k++) {
                    result[i][j] = A[i][j] + B[i][j];
                }
            }
        }

        return result;
    }

    public static Double[][] transpose(Double[][] matrix) {
        Double[][] transposed = new Double[matrix[0].length][matrix.length];

        for (int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix[0].length; j++) {
                transposed[j][i] = matrix[i][j];
            }
        }

        return transposed;
    }
}
