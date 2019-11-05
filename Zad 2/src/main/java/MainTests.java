import Adapters.DoubleAdapter;
import Matrix.MatrixGenerator;
import Matrix.MatrixUtilities;
import Matrix.MyMatrix;
import Utilities.CollectionUtilities;

public class MainTests {

    public static void main(String[] args) {

        DoubleAdapter adapter = new DoubleAdapter();
        int i = 2;

        Double[][] A = new Double[i][i];
        Double[] X = new Double[i];

        MatrixGenerator.generateValues(A, 1, 5);
        MatrixGenerator.generateValues(X, 1, 5);

        CollectionUtilities.show(A, X);

        Double[] B = MatrixUtilities.multiplyByVector(Double.class, adapter, A, X);

        CollectionUtilities.show(B);

        Double[] result = MyMatrix.GaussJordanElimination_NoPivoting(adapter, A, B);

        CollectionUtilities.show(X);
        CollectionUtilities.show(result);
    }

}
