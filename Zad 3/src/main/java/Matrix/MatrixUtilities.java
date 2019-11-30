package Matrix;


public class MatrixUtilities {
    public static <TType> void swapRows(TType[] A, int src, int dest) {
        TType temp = A[src];
        A[src] = A[dest];
        A[dest] = temp;
    }

    public static <TType> void swapRows(TType[][] A, int src, int dest) {
        for (int i = 0; i < A[src].length; i++) {
            TType temp = A[src][i];
            A[src][i] = A[dest][i];
            A[dest][i] = temp;
        }
    }

    public static <TType> void swapCols(TType[][] A, int src, int dest) {
        for (int i = 0; i < A.length; i++) {
            TType temp = A[i][src];
            A[i][src] = A[i][dest];
            A[i][dest] = temp;
        }
    }

    public static Double[] multiply(
            Double[][] matrix,
            Double[] vector) {
        Double[] result = new Double[matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                result[i] = 0d;
                result[i] = result[i] + (matrix[i][j] * vector[j]);
            }
        }

        return result;
    }

    public static Double[][] multiply(
            Double[][] A,
            Double[][] B) {
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

    public static Double[][] transpose(Double[][] matrix) {
        Double[][] transposed = new Double[matrix.length][matrix[0].length];

        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[0].length; j++)
                transposed[j][i] = matrix[i][j];

        return transposed;
    }

    public static Double avg(Double[] A) {
        double count = 0d;
        double sum = 0d;

        for (Double element : A) {
            count = count + 1;
            sum = sum + Math.abs(element);
        }

        return (sum / count);
    }

    public static Double[] subtract(
            Double[] A,
            Double[] B) {

        if (A.length != B.length) {
            throw new IllegalArgumentException();
        }

        Double[] result = new Double[A.length];

        for (int i = 0; i < A.length; i++) {
            result[i] = A[i] - B[i];
        }

        return result;
    }
}
