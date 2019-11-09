package Matrix;

import Adapters.INumberAdapter;
import Utilities.CollectionUtilities;

public class MyMatrixV1<TType extends Number> {
    private INumberAdapter<TType> adapter;
    private TType[][] A;
    private TType[] B;

    public MyMatrixV1(INumberAdapter<TType> adapter, TType[][] A, TType[] B) {
        this.adapter = adapter;
        this.A = A;
        this.B = B;
    }

    public static <TType extends Number> TType[] GaussJordanElimination(
            INumberAdapter<TType> adapter,
            TType[][] A,
            TType[] B) {
        return GaussJordanElimination_FullPivoting(adapter, A, B);
    }

    public static <TType extends Number> TType[] GaussJordanElimination_NoPivoting(
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

        return B;
    }

    public static <TType extends Number> TType[] GaussJordanElimination_PartialPivoting(
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

            if(destRow != pos) {
                MatrixUtilities.swapRows(A, pos, destRow);
                MatrixUtilities.swapRows(B, pos, destRow);
            }

            Eliminate(adapter, A, B, pos);
        }

//        CollectionUtilities.show(A, B);

        return B;
    }

    public static <TType extends Number> TType[] GaussJordanElimination_FullPivoting(
            INumberAdapter<TType> adapter,
            TType[][] matrix,
            TType[] vector) {

        TType[][] A = adapter.copy(matrix);
        TType[] B = adapter.copy(vector);

        Integer[] ColumnChanges = CollectionUtilities.getIntArr(vector.length);

        for (int pos = 0; pos < A.length; pos++) {
            int destR = pos;
            int destC = pos;

            for(int i = pos; i < A.length; i++)
            {
                for(int j = pos; j < A[0].length; j++)
                {
                    if(adapter.compareTo(A[destR][destC], A[i][j]) < 0)
                    {
                        destR = i;
                        destC = j;
                    }
                }
            }

            if(destR != pos)
            {
                MatrixUtilities.swapRows(A, pos, destR);
                MatrixUtilities.swapRows(B, pos, destR);
            }

            if(destC != pos)
            {
                MatrixUtilities.swapCols(A, pos, destC);
                MatrixUtilities.swapRows(ColumnChanges, pos, destC);
            }

            Eliminate(adapter, A, B, pos);
        }

//        CollectionUtilities.show(A, B);

        TType[] swappedResult = adapter.getArrInstance(B.length);

        for ( int i = 0; i < B.length; i++ ) {
            swappedResult[ColumnChanges[i]] = B[i];
        }

        return swappedResult;
    }

    private static <TType extends Number> void Eliminate(
            INumberAdapter<TType> adapter,
            TType[][] A,
            TType[] B,
            int pos) {
        if (!adapter.isZero(A[pos][pos])) {

            for (int i = pos + 1; i < A[0].length + 1; i++) {
                if (i < A[0].length) {
                    A[pos][i] = adapter.divide(A[pos][i], A[pos][pos]);
                } else {
                    B[pos] = adapter.divide(B[pos], A[pos][pos]);
                }
            }

            A[pos][pos] = adapter.ONE();

            for (int i = 0; i < A.length; i++) {
                if (i != pos) {
                    TType count = adapter.multiply(A[i][pos], A[pos][pos]);

                    for (int j = pos; j < A[i].length + 1; j++) {
                        if (j < A[i].length) {
                            A[i][j] = adapter.subtract(A[i][j], adapter.multiply(A[pos][j], count));
                        } else {
                            B[i] = adapter.subtract(B[i], adapter.multiply(B[pos], count));
                        }
                    }
                }
            }

        }
    }

    public TType[] GaussJordanElimination() {
        return GaussJordanElimination_FullPivoting(this.adapter, this.A, this.B);
    }

    public TType[] GaussJordanElimination_NoPivoting() {
        return GaussJordanElimination_NoPivoting(this.adapter, this.A, this.B);
    }

    public TType[] GaussJordanElimination_PartialPivoting() {
        return GaussJordanElimination_PartialPivoting(this.adapter, this.A, this.B);
    }

    public TType[] GaussJordanElimination_FullPivoting() {
        return GaussJordanElimination_FullPivoting(this.adapter, this.A, this.B);
    }
}
