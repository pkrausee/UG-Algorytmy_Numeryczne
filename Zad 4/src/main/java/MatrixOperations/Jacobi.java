package MatrixOperations;

import Utilities.CollectionUtils;
import Utilities.Generator;

public class Jacobi {
    private double[][] A;
    private double[] B;

    public Jacobi(double[][] A, double[] B) {
        this.A = A;
        this.B = B;
    }

    public static double[] calculate(double[][] matrix, double[] vector, int iter) {
        double[][] A = CollectionUtils.copy(matrix);
        double[] B = CollectionUtils.copy(vector);

        int len = A.length;
        double[] result = Generator.createDoubleVector(len);

        for (int i = 0; i < iter; i++) {
            double[] prevResult = CollectionUtils.copy(result);

            for (int j = 0; j < len; j++) {
                double sum = B[j];

                for (int k = 0; k < len; k++) {
                    if (j != k) {
                        sum -= A[j][k] * prevResult[k];
                    }
                }

                result[j] = sum / A[j][j];
            }
        }

        return result;
    }

    public double[] calculate(int iter) {
        return calculate(this.A, this.B, iter);
    }
}
