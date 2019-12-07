package MatrixOperations;

public class MathUtils {
    public static double[] multiply(double scalar, double[] vector) {
        double[] result = new double[vector.length];

        for (int i = 0; i < vector.length; i++) {
            result[i] = scalar * vector[i];
        }

        return result;
    }

    public static double[][] multiply(double scalar, double[][] matrix) {
        double[][] result = new double[matrix.length][matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                result[i][j] = scalar * matrix[i][j];
            }
        }

        return result;
    }

    public static double multiply(double[] vector1, double[] vector2) {
        double result = 0;

        for ( int i = 0; i < vector1.length; i++ ) {
            result += vector1[i] * vector2[i];
        }

        return result;
    }

    public static double[] multiply(double[] vector, double[][] matrix) {
        double[] result = new double[matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                result[i] = 0d;
                result[i] = result[i] + (matrix[i][j] * vector[j]);
            }
        }

        return result;
    }

    public static double[][] multiply(double[][] A, double[][] B) {
        double[][] result = new double[A.length][B[0].length];

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

    public static double[] add(double[] A, double[] B) {
        if (A.length != B.length) {
            throw new IllegalArgumentException();
        }

        double[] result = new double[A.length];

        for (int i = 0; i < A.length; i++) {
            result[i] = A[i] + B[i];
        }

        return result;
    }

    public static double[][] add(double[][] A, double[][] B) {
        if (A.length != B.length || A[0].length != B[0].length) {
            throw new IllegalArgumentException();
        }

        double[][] result = new double[A.length][A[0].length];

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

    public static double[][] transpose(double[][] matrix) {
        double[][] transposed = new double[matrix[0].length][matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                transposed[j][i] = matrix[i][j];
            }
        }

        return transposed;
    }

    public static double norm(double[] vector) {
        double result = 0;

        for (double v : vector) {
            result += Math.pow(v, 2);
        }

        return Math.sqrt(result);
    }
}
