package Matrix;

import Adapters.INumberAdapter;

public class MatrixUtilities {

    public static <TType> void swapRows(TType[] A, int src, int dest) {
        TType temp = A[src];
        A[src] = A[dest];
        A[dest] = temp;
    }

    public static <TType> void swapRows(TType[][] A, int src, int dest) {
        for(int i = 0; i < A[src].length; i++){
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

    public static <TType extends Number> TType[] multiplyByVector(
            INumberAdapter<TType> adapter,
            TType[][] matrix,
            TType[] vector) {

        if (matrix[0].length != vector.length) {
            throw new IllegalArgumentException();
        }

        TType[] result = adapter.getArrInstance(matrix.length);

        for (int i = 0; i < matrix.length; i++) {

            TType currentValue = adapter.ZERO();

            for (int j = 0; j < matrix[i].length; j++) {
                currentValue = adapter.add(currentValue, adapter.multiply(matrix[i][j], vector[j]));
            }

            result[i] = currentValue;
        }

        return result;
    }

    public static <TType extends Number> TType[][] transpose(INumberAdapter<TType> adapter, TType[][] matrix) {

        TType[][] result = adapter.getMatrixInstance(matrix.length);

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                result[j][i] = matrix[i][j];
            }
        }

        return result;
    }

    public static <TType extends Number> TType norm(
            INumberAdapter<TType> adapter,
            TType[] A) {

        TType result = adapter.getInstance();

        for (TType element : A) {
            result = adapter.add(result, adapter.pow(element, 2));
        }

        return adapter.sqrt(result);
    }

    public static <TType extends Number> TType avg(INumberAdapter<TType> adapter, TType[] A) {
        TType count = adapter.ZERO();
        TType sum = adapter.ZERO();

        for (TType element : A) {
            count = adapter.add(count, adapter.ONE());
            sum = adapter.add(sum, adapter.abs(element));
        }

        return adapter.divide(sum, count);
    }

    public static <TType extends Number> TType[] subtract(
            INumberAdapter<TType> adapter,
            TType[] A,
            TType[] B) {

        if (A.length != B.length) {
            throw new IllegalArgumentException();
        }

        TType[] result = adapter.getArrInstance(A.length);

        for (int i = 0; i < A.length; i++) {
            result[i] = adapter.subtract(A[i], B[i]);
        }

        return result;
    }
}
