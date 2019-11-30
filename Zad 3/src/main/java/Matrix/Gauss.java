package Matrix;

import Utilities.CollectionUtilities;

public class Gauss {
    private Double[][] A;
    private Double[] B;

    public Gauss(Double[][] A, Double[] B) {
        this.A = A;
        this.B = B;
    }

    public static Double[] GaussElimination(
            Double[][] A,
            Double[] B) {
        return GaussElimination_FullPivoting(A, B);
    }

    public static Double[] GaussElimination_NoPivoting(
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

        return CalculateResults(A, B);
    }

    public static Double[] GaussElimination_PartialPivoting(
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

        return CalculateResults(A, B);
    }

    public static Double[] GaussElimination_FullPivoting(
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

        Double[] result = CalculateResults(A, B);
        Double[] swappedResult = new Double[result.length];

        for (int i = 0; i < result.length; i++) {
            swappedResult[ColumnChanges[i]] = result[i];
        }

        return swappedResult;
    }

    private static void Eliminate(
            Double[][] A,
            Double[] B,
            int pos) {

        if (!(A[pos][pos] == 0)) {

            for (int i = pos + 1; i < A.length; i++) {

                Double counter = A[i][pos] / A[pos][pos];

                A[i][pos] = 0d;

                for (int j = pos + 1; j <= A[i].length; j++) {
                    if (j < A[i].length) {
                        A[i][j] = A[i][j] - (A[pos][j] * counter);
                    } else {
                        B[i] = B[i] - (B[pos] * counter);
                    }
                }
            }

        }
    }

    private static Double[] CalculateResults(
            Double[][] A,
            Double[] B) {

        Double[] results = new Double[B.length];

        Double currentResult, currentX;

        for (int i = A.length - 1; i >= 0; i--) {
            currentResult = B[i];

            for (int j = A[i].length - 1; j >= i + 1; j--) {
                currentX = results[j] * A[i][j];
                currentResult = currentResult - currentX;
            }

            results[i] = currentResult / A[i][i];
        }

        return results;
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

    public Double[] GaussElimination() {
        return GaussElimination_FullPivoting(this.A, this.B);
    }

    public Double[] GaussElimination_NoPivoting() {
        return GaussElimination_NoPivoting(this.A, this.B);
    }

    public Double[] GaussElimination_PartialPivoting() {
        return GaussElimination_PartialPivoting(this.A, this.B);
    }

    public Double[] GaussElimination_FullPivoting() {
        return GaussElimination_FullPivoting(this.A, this.B);
    }
}
