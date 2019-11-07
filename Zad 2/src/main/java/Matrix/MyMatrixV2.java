package Matrix;

import Adapters.INumberAdapter;
import Utilities.CollectionUtilities;

public class MyMatrixV2<TType extends Number> {
    private INumberAdapter<TType> adapter;
    private TType[][] A;
    private TType[] B;

    public MyMatrixV2(INumberAdapter<TType> adapter, TType[][] A, TType[] B) {
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
                    System.out.println("Swap rows " + pos + " " + destRow);

                    MatrixUtilities.swapRows(A, pos, destRow);
                    MatrixUtilities.swapRows(B, pos, destRow);
                }
            }

            eliminate(adapter, A, B, pos);
        }

        CollectionUtilities.show(A, B);

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
                System.out.println("Swap rows " + pos + " " + destRow);

                MatrixUtilities.swapRows(A, pos, destRow);
                MatrixUtilities.swapRows(B, pos, destRow);
            }

            eliminate(adapter, A, B, pos);

        }

        CollectionUtilities.show(A, B);

        return B;
    }

    public static <TType extends Number> TType[] GaussJordanElimination_FullPivoting(
            INumberAdapter<TType> adapter,
            TType[][] matrix,
            TType[] vector) {

        TType[][] A = adapter.copy(matrix);
        TType[] B = adapter.copy(vector);

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

            System.out.println(pos + " " + maxR + " " + maxC);

            if(maxR != pos || maxC != pos) {
                if (adapter.compareTo(A[maxR][pos], A[pos][maxC]) <= 0) {
                    System.out.println("Swap cols " + pos + " " + maxC);

                    MatrixUtilities.swapCols(A, pos, maxC);
                } else {
                    System.out.println("Swap rows " + pos + " " + maxR);

                    MatrixUtilities.swapRows(B, pos, maxR);
                    MatrixUtilities.swapRows(A, pos, maxR);
                }
            }

            eliminate(adapter, A, B, pos);

        }

        CollectionUtilities.show(A, B);

        return B;
    }

    public static <TType extends Number> void eliminate(
            INumberAdapter<TType> adapter,
            TType[][] A,
            TType[] B,
            int pos) {

        if (!adapter.isZero(A[pos][pos])) {

            for(int i = pos + 1; i < A.length; i++){

                TType counter = adapter.divide(A[i][pos], A[pos][pos]);

                for(int j = pos; j <= A[i].length; j++) {
                    if(j < A[i].length){
                        A[i][j] = adapter.subtract(A[i][j], adapter.multiply(A[pos][j], counter));
                    } else {
                        B[i] = adapter.subtract(B[i], adapter.multiply(B[pos], counter));
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

    public INumberAdapter<TType> getAdapter() {
        return adapter;
    }

    public void setAdapter(INumberAdapter<TType> adapter) {
        this.adapter = adapter;
    }

    public TType[][] getA() {
        return A;
    }

    public void setA(TType[][] a) {
        A = a;
    }

    public TType[] getB() {
        return B;
    }

    public void setB(TType[] b) {
        B = b;
    }
}
