package Matrix;

import Utilities.CollectionUtilities;

public class GaussJordan {
    private Double[][] A;
    private Double[] B;

    public GaussJordan(Double[][] A, Double[] B) {
        this.A = A;
        this.B = B;
    }

    public static Double[] GaussJordanElimination(
            Double[][] A,
            Double[] B) {
        return GaussJordanElimination_FullPivoting(A, B);
    }

    public static Double[] GaussJordanElimination_NoPivoting(
            Double[][] matrix,
            Double[] vector) {

        Double[][] A = copy(matrix);
        Double[] B = copy(vector);

        for (int pos = 0; pos < A.length; pos++) {

            if (A[pos][pos] == 0) {
                int destRow = pos;

                while (destRow < A.length && A[destRow][pos] == 0) {
                    destRow++;
                }

                if (destRow < A.length && destRow != pos) {
                    MatrixUtilities.swapRows(A, pos, destRow);
                    MatrixUtilities.swapRows(B, pos, destRow);
                }
            }

            Eliminate(A, B, pos);
        }

//        CollectionUtilities.show(A, B);

        return B;
    }

    public static Double[] GaussJordanElimination_PartialPivoting(
            Double[][] matrix,
            Double[] vector) {

        Double[][] A = copy(matrix);
        Double[] B = copy(vector);

        for (int pos = 0; pos < A.length; pos++) {

            int destRow = pos;

            for (int i = pos; i < A.length; i++) {
                if (A[destRow][pos].compareTo(A[i][pos]) < 0) {
                    destRow = i;
                }
            }

            if (destRow != pos) {
                MatrixUtilities.swapRows(A, pos, destRow);
                MatrixUtilities.swapRows(B, pos, destRow);
            }

            Eliminate(A, B, pos);
        }

//        CollectionUtilities.show(A, B);

        return B;
    }

    public static Double[] GaussJordanElimination_FullPivoting(
            Double[][] matrix,
            Double[] vector) {

        Double[][] A = copy(matrix);
        Double[] B = copy(vector);

        Integer[] ColumnChanges = CollectionUtilities.getIntArr(vector.length);

        for (int pos = 0; pos < A.length; pos++) {
            int destR = pos;
            int destC = pos;

            for (int i = pos; i < A.length; i++) {
                for (int j = pos; j < A[0].length; j++) {
                    if (A[destR][destC].compareTo(A[i][j]) < 0) {
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

            Eliminate(A, B, pos);
        }

//        CollectionUtilities.show(A, B);

        Double[] swappedResult = new Double[B.length];

        for (int i = 0; i < B.length; i++) {
            swappedResult[ColumnChanges[i]] = B[i];
        }

        return swappedResult;
    }

    private static void Eliminate(
            Double[][] A,
            Double[] B,
            int pos) {
        if (!(A[pos][pos] == 0)) {

            for (int i = pos + 1; i < A[0].length + 1; i++) {
                if (i < A[0].length) {
                    A[pos][i] = A[pos][i] / A[pos][pos];
                } else {
                    B[pos] = B[pos] / A[pos][pos];
                }
            }

            A[pos][pos] = 0d;

            for (int i = 0; i < A.length; i++) {
                if (i != pos) {
                    Double count = A[i][pos] * A[pos][pos];

                    for (int j = pos; j < A[i].length + 1; j++) {
                        if (j < A[i].length) {
                            A[i][j] = A[i][j] - (A[pos][j] * count);
                        } else {
                            B[i] = B[i] - (B[pos] * count);
                        }
                    }
                }
            }

        }
    }

    private static Double[] copy(Double[] a) {
        Double[] copy = new Double[a.length];
        System.arraycopy( a, 0, copy, 0, a.length );

        return copy;
    }

    private static Double[][] copy(Double[][] a) {
        Double[][] copy = new Double[a.length][a[0].length];

        for(int i=0; i<a.length; i++){
            System.arraycopy(a[i], 0, copy[i], 0, a[i].length);
        }

        return copy;
    }

    public Double[] GaussJordanElimination() {
        return GaussJordanElimination_FullPivoting(this.A, this.B);
    }

    public Double[] GaussJordanElimination_NoPivoting() {
        return GaussJordanElimination_NoPivoting(this.A, this.B);
    }

    public Double[] GaussJordanElimination_PartialPivoting() {
        return GaussJordanElimination_PartialPivoting(this.A, this.B);
    }

    public Double[] GaussJordanElimination_FullPivoting() {
        return GaussJordanElimination_FullPivoting(this.A, this.B);
    }
}
