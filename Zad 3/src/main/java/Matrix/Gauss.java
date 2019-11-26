package Matrix;

import Adapters.INumberAdapter;
import Utilities.CollectionUtilities;

public class Gauss<TType extends Number> {
    private INumberAdapter<TType> adapter;
    private TType[][] A;
    private TType[] B;

    public Gauss(INumberAdapter<TType> adapter, TType[][] A, TType[] B) {
        this.adapter = adapter;
        this.A = A;
        this.B = B;
    }

    public static <TType extends Number> TType[] GaussElimination(
            INumberAdapter<TType> adapter,
            TType[][] A,
            TType[] B) {
        return GaussElimination_FullPivoting(adapter, A, B);
    }

    public static <TType extends Number> TType[] GaussElimination_NoPivoting(
            INumberAdapter<TType> adapter,
            TType[][] matrix,
            TType[] vector) {

        TType[][] A = adapter.copy(matrix);
        TType[] B = adapter.copy(vector);

        for (int pos = 0; pos < A.length; pos++) {

            if (adapter.isZero(A[pos][pos])) {
                int destRow = pos;

                while (destRow < A.length && adapter.isZero(A[destRow][pos])) {
                    destRow++;
                }

                if (destRow < A.length && destRow != pos) {
                    MatrixUtilities.swapRows(A, pos, destRow);
                    MatrixUtilities.swapRows(B, pos, destRow);
                }
            }

            Eliminate(adapter, A, B, pos);
        }

//        CollectionUtilities.show(A, B);

        return CalculateResults(adapter, A, B);
    }

    public static <TType extends Number> TType[] GaussElimination_PartialPivoting(
            INumberAdapter<TType> adapter,
            TType[][] matrix,
            TType[] vector) {

        TType[][] A = adapter.copy(matrix);
        TType[] B = adapter.copy(vector);

        for (int pos = 0; pos < A.length; pos++) {

            int destRow = pos;

            for (int i = pos; i < A.length; i++) {
                if (adapter.compareTo(A[destRow][pos], A[i][pos]) < 0) {
                    destRow = i;
                }
            }

            if (destRow != pos) {
                MatrixUtilities.swapRows(A, pos, destRow);
                MatrixUtilities.swapRows(B, pos, destRow);
            }

            Eliminate(adapter, A, B, pos);
        }

//        CollectionUtilities.show(A, B);

        return CalculateResults(adapter, A, B);
    }

    public static <TType extends Number> TType[] GaussElimination_FullPivoting(
            INumberAdapter<TType> adapter,
            TType[][] matrix,
            TType[] vector) {

        TType[][] A = adapter.copy(matrix);
        TType[] B = adapter.copy(vector);

        Integer[] ColumnChanges = CollectionUtilities.getIntArr(vector.length);

        for (int pos = 0; pos < A.length; pos++) {
            int destR = pos;
            int destC = pos;

            for (int i = pos; i < A.length; i++) {
                for (int j = pos; j < A[0].length; j++) {
                    if (adapter.compareTo(A[destR][destC], A[i][j]) < 0) {
                        destR = i;
                        destC = j;
                    }
                }
            }

            if (destR != pos) {
                MatrixUtilities.swapRows(A, pos, destR);
                MatrixUtilities.swapRows(B, pos, destR);
            }

            if (destC != pos) {
                MatrixUtilities.swapCols(A, pos, destC);
                MatrixUtilities.swapRows(ColumnChanges, pos, destC);
            }

            Eliminate(adapter, A, B, pos);
        }

//        CollectionUtilities.show(A, B);

        TType[] result = CalculateResults(adapter, A, B);
        TType[] swappedResult = adapter.getArrInstance(result.length);

        for (int i = 0; i < result.length; i++) {
            swappedResult[ColumnChanges[i]] = result[i];
        }

        return swappedResult;
    }

    private static <TType extends Number> void Eliminate(
            INumberAdapter<TType> adapter,
            TType[][] A,
            TType[] B,
            int pos) {

        if (!adapter.isZero(A[pos][pos])) {

            for (int i = pos + 1; i < A.length; i++) {

                TType counter = adapter.divide(A[i][pos], A[pos][pos]);

                A[i][pos] = adapter.ZERO();

                for (int j = pos + 1; j <= A[i].length; j++) {
                    if (j < A[i].length) {
                        A[i][j] = adapter.subtract(A[i][j], adapter.multiply(A[pos][j], counter));
                    } else {
                        B[i] = adapter.subtract(B[i], adapter.multiply(B[pos], counter));
                    }
                }
            }

        }
    }

    private static <TType extends Number> TType[] CalculateResults(
            INumberAdapter<TType> adapter,
            TType[][] A,
            TType[] B) {

        TType[] results = adapter.getArrInstance(B.length);

        TType currentResult, currentX;

        for (int i = A.length - 1; i >= 0; i--) {
            currentResult = B[i];

            for (int j = A[i].length - 1; j >= i + 1; j--) {
                currentX = adapter.multiply(results[j], A[i][j]);
                currentResult = adapter.subtract(currentResult, currentX);
            }

            results[i] = adapter.divide(currentResult, A[i][i]);
        }

        return results;
    }

    public TType[] GaussElimination() {
        return GaussElimination_FullPivoting(this.adapter, this.A, this.B);
    }

    public TType[] GaussElimination_NoPivoting() {
        return GaussElimination_NoPivoting(this.adapter, this.A, this.B);
    }

    public TType[] GaussElimination_PartialPivoting() {
        return GaussElimination_PartialPivoting(this.adapter, this.A, this.B);
    }

    public TType[] GaussElimination_FullPivoting() {
        return GaussElimination_FullPivoting(this.adapter, this.A, this.B);
    }
}
