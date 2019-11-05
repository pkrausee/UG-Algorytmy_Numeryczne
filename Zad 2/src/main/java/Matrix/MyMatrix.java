package Matrix;

import Adapters.INumberAdapter;

public class MyMatrix <TType extends Number> {
    private INumberAdapter<TType> adapter;
    private TType[][] A;
    private TType[] B;

    public MyMatrix(INumberAdapter<TType> adapter, TType[][] A, TType[] B) {
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
            TType[][] A,
            TType[] B) {
        for (int pos = 0; pos < A.length; pos++) {
            if (adapter.isZero(A[pos][pos])) {
                int destRow = pos;
                while (destRow < A.length && adapter.isZero(A[destRow][pos])) {
                    destRow++;
                }

                if (destRow < A.length && destRow != pos) {
                    MatrixUtilities.swapRows(B, pos, destRow);
                    MatrixUtilities.swapRows(A, pos, destRow);
                }
            }

            eliminate(adapter, A, B, pos);
        }

        return B;
    }

    public static <TType extends Number> TType[] GaussJordanElimination_PartialPivoting(
            INumberAdapter<TType> adapter,
            TType[][] A,
            TType[] B) {
        for (int pos = 0; pos < A.length; pos++) {
            int max = pos;

            for (int i = pos; i < A.length; i++) {
                if (adapter.compareTo(A[max][pos], A[i][pos]) < 0) {
                    max = i;
                }
            }

            MatrixUtilities.swapRows(B, pos, max);
            MatrixUtilities.swapRows(A, pos, max);

            eliminate(adapter, A, B, pos);
        }

        return B;
    }

    public static <TType extends Number> TType[] GaussJordanElimination_FullPivoting(
            INumberAdapter<TType> adapter,
            TType[][] A,
            TType[] B) {
        for (int pos = 0; pos < A.length; pos++) {
            int maxR = pos;
            int maxC = pos;

            for (int i = pos; i < A.length; i++) {
                if (adapter.compareTo(A[maxR][pos], A[i][pos]) < 0) {
                    maxR = i;
                }

                if (adapter.compareTo(A[pos][maxC], A[pos][i]) < 0) {
                    maxC = i;
                }
            }

            if (adapter.compareTo(A[maxR][pos], A[pos][maxC]) < 0) {
                MatrixUtilities.swapCols(A, pos, maxC);
            } else {
                MatrixUtilities.swapRows(B, pos, maxR);
                MatrixUtilities.swapRows(A, pos, maxR);
            }

            eliminate(adapter, A, B, pos);
        }

        return B;
    }

    private static <TType extends Number> void eliminate(
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

                    A[i][pos] = adapter.ZERO();

                    for (int j = pos + 1; j < A[i].length + 1; j++) {
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
















