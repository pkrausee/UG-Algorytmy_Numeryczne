package Matrix;

import Adapters.INumberAdapter;

import java.lang.reflect.Array;

public class MatrixUtilities {

    public static <TType> void swapRows(TType[] A, int src, int dest) {
        TType temp = A[src];
        A[src] = A[dest];
        A[dest] = temp;
    }

    public static <TType> void swapRows(TType[][] A, int src, int dest) {
        TType[] temp = A[src];
        A[src] = A[dest];
        A[dest] = temp;
    }

    public static <TType> void swapCols(TType[][] A, int src, int dest) {
        for (int i = 0; i < A.length; i++) {
            TType temp = A[i][src];
            A[i][src] = A[i][dest];
            A[i][dest] = temp;
        }
    }

    public static <TType extends Number> TType[] multiplyByVector(
            Class<TType> content,
            INumberAdapter<TType> adapter,
            TType[][] matrix,
            TType[] vector) {

        if (matrix[0].length != vector.length) {
            throw new IllegalArgumentException();
        }

        @SuppressWarnings("unchecked")
        TType[] result = (TType[]) Array.newInstance(content, matrix.length);

        for (int i = 0; i < matrix.length; i++) {

            TType currentValue = adapter.ZERO();

            for (int j = 0; j < matrix[i].length; j++) {
                currentValue = adapter.add(currentValue, adapter.multiply(matrix[i][j], vector[j]));
            }

            result[i] = currentValue;
        }

        return result;
    }

    public static <TType extends Number> TType[][] transpose(Class<TType> content, TType[][] matrix) {

        @SuppressWarnings("unchecked")
        TType[][] result = (TType[][]) Array.newInstance(content, matrix.length, matrix[0].length);

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                result[j][i] = matrix[i][j];
            }
        }

        return result;
    }

    public static <TType extends Number> TType norm(
            Class<TType> content,
            INumberAdapter<TType> adapter,
            TType[] A) {

        TType result = adapter.ZERO();

        for (TType element : A) {
            result = adapter.add(result, adapter.pow(element, 2));
        }

        return adapter.sqrt(result);
    }

    public static <TType extends Number> TType[] subtract(
            Class<TType> content,
            INumberAdapter<TType> adapter,
            TType[] A,
            TType[] B) {

        if (A.length != B.length) {
            throw new IllegalArgumentException();
        }

        @SuppressWarnings("unchecked")
        TType[] result = (TType[]) Array.newInstance(content, A.length);

        for (int i = 0; i < A.length; i++) {
            result[i] = adapter.subtract(A[i], B[i]);
        }

        return result;
    }
}
