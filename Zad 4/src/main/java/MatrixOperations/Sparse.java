package MatrixOperations;

import Interpolation.Model.SparseLocation;
import Utilities.CollectionUtils;
import Utilities.Generator;

import java.util.Map;

public class Sparse {
    private Map<SparseLocation, Double> A;
    private double[] B;

    public Sparse(Map<SparseLocation, Double> A, double[] B) {
        this.A = A;
        this.B = B;
    }

    public static double[] calculate(Map<SparseLocation, Double> locationDict, double[] vector, int iter) {
        double[] B = CollectionUtils.copy(vector);

        int len = locationDict.size();
        double[] result = Generator.createDoubleVector(len);

        for (int i = 0; i < iter; i++) {
            double[] prevResult = CollectionUtils.copy(result);
            result = CollectionUtils.copy(B);

            for(Map.Entry<SparseLocation, Double> key : locationDict.entrySet()) {
                int lat = key.getKey().getLat();
                int lng = key.getKey().getLng();

                if (lat != lng) {
                    result[lat] -= key.getValue() * prevResult[lng];
                }
            }

            for(int j = 0; j < result.length; j++) {
                result[j] /= locationDict.getOrDefault(new SparseLocation(j, j), 1d);
            }
        }

        return result;
    }

    public double[] calculate(int iter) {
        return calculate(this.A, this.B, iter);
    }
}
