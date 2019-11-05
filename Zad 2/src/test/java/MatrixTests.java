import Adapters.DoubleAdapter;
import Adapters.FloatAdapter;
import Matrix.MatrixGenerator;
import Matrix.MatrixUtilities;
import Matrix.MyMatrix;
import Models.ResultType;

import java.util.ArrayList;

public class MatrixTests {

    private final int numberOfTests = 1000;

    @org.junit.Test
    public void TestMatrixForDoubleType() {
        ResultType<Double> results = new ResultType<>(new ArrayList<Double[]>());

        DoubleAdapter adapter = new DoubleAdapter();

        for (int i = 10; i < numberOfTests; i += 10) {
            Double[][] A = new Double[i][i];
            Double[] X = new Double[i];

            MatrixGenerator.generateValues(A, -1, 1);
            MatrixGenerator.generateValues(X, -1, 1);

            Double[] B = MatrixUtilities.multiplyByVector(Double.class, adapter, A, X);
            Double[] Xp = MyMatrix.GaussJordanElimination_NoPivoting(adapter, A, B);

            Double[] failures = MatrixUtilities.subtract(Double.class, adapter, X, Xp);
            Double fail = MatrixUtilities.norm(Double.class, adapter, failures);

            results.results.add(new Double[]{ (double) i, fail });
        }

        System.out.println(results.toString());
    }

    @org.junit.Test
    public void TestMatrixForFloatType() {
        ResultType<Float> results = new ResultType<>(new ArrayList<Float[]>());

        FloatAdapter adapter = new FloatAdapter();

        for (int i = 10; i < numberOfTests; i += 10) {
            Float[][] A = new Float[i][i];
            Float[] X = new Float[i];

            MatrixGenerator.generateValues(A, -1, 1);
            MatrixGenerator.generateValues(X, -1, 1);

            Float[] B = MatrixUtilities.multiplyByVector(Float.class, adapter, A, X);
            Float[] Xp = MyMatrix.GaussJordanElimination_NoPivoting(adapter, A, B);

            Float[] failures = MatrixUtilities.subtract(Float.class, adapter, X, Xp);
            Float fail = MatrixUtilities.norm(Float.class, adapter, failures);

            results.results.add(new Float[]{ (float) i, fail });
        }

        System.out.println(results.toString());
    }

}
