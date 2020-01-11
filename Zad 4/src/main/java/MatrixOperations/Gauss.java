package MatrixOperations;

import Utilities.CollectionUtils;
import Utilities.Generator;

public class Gauss {
    private double[][] A;
    private double[] B;

    public Gauss(double[][] A, double[] B) {
        this.A = A;
        this.B = B;
    }

    public static double[] NoPivoting(double[][] matrix, double[] vector) {
        double[][] A = CollectionUtils.copy(matrix);
        double[] B = CollectionUtils.copy(vector);

        for (int pos = 0; pos < A.length; pos++) {

            if (A[pos][pos] == 0) {
                int destRow = pos;

                while (destRow < A.length && A[destRow][pos] == 0) {
                    destRow++;
                }

                if (destRow < A.length && destRow != pos) {
                    CollectionUtils.swapRows(A, pos, destRow);
                    CollectionUtils.swapRows(B, pos, destRow);
                }
            }

            Eliminate(A, B, pos);
        }

        return CalculateResults(A, B);
    }

    public static double[] PartialPivoting(double[][] matrix, double[] vector) {
        double[][] A = CollectionUtils.copy(matrix);
        double[] B = CollectionUtils.copy(vector);

        for (int pos = 0; pos < A.length; pos++) {

            int destRow = pos;

            for (int i = pos; i < A.length; i++) {
                if (A[destRow][pos] < A[i][pos]) {
                    destRow = i;
                }
            }

            if (destRow != pos) {
                CollectionUtils.swapRows(A, pos, destRow);
                CollectionUtils.swapRows(B, pos, destRow);
            }

            Eliminate(A, B, pos);
        }

        return CalculateResults(A, B);
    }

    public static double[] FullPivoting(double[][] matrix, double[] vector) {
        double[][] A = CollectionUtils.copy(matrix);
        double[] B = CollectionUtils.copy(vector);

        int[] ColumnChanges = Generator.createIntVector(vector.length);

        for (int pos = 0; pos < A.length; pos++) {
            int destR = pos;
            int destC = pos;

            for (int i = pos; i < A.length; i++) {
                for (int j = pos; j < A[0].length; j++) {
                    if (A[destR][destC] < A[i][j]) {
                        destR = i;
                        destC = j;
                    }
                }
            }

            if (destR != pos) {
                CollectionUtils.swapRows(A, pos, destR);
                CollectionUtils.swapRows(B, pos, destR);
            }

            if (destC != pos) {
                CollectionUtils.swapCols(A, pos, destC);
                CollectionUtils.swapRows(ColumnChanges, pos, destC);
            }

            Eliminate(A, B, pos);
        }

        double[] result = CalculateResults(A, B);
        double[] swappedResult = new double[result.length];

        for (int i = 0; i < result.length; i++) {
            swappedResult[ColumnChanges[i]] = result[i];
        }

        return swappedResult;
    }

    private static void Eliminate(double[][] A, double[] B, int pos) {
        if (!(A[pos][pos] == 0)) {

            for (int i = pos + 1; i < A.length; i++) {
                double counter = A[i][pos] / A[pos][pos];

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

    private static double[] CalculateResults(double[][] A, double[] B) {
        double[] results = new double[B.length];

        double currentResult, currentX;

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

    public double[] NoPivoting() {
        return NoPivoting(this.A, this.B);
    }

    public double[] PartialPivoting() {
        return PartialPivoting(this.A, this.B);
    }

    public double[] FullPivoting() {
        return FullPivoting(this.A, this.B);
    }
}
