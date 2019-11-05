import Adapters.IntegerAdapter;
import Matrix.MatrixGenerator;
import Matrix.MatrixUtilities;

public class MainTests {

    public static void main(String[] args) {

        IntegerAdapter adapter = new IntegerAdapter();
        int i = 3;

        Integer[][] A = new Integer[i][i];
        Integer[] X = new Integer[i];

        MatrixGenerator.generateValues(A, 1, 5);
        MatrixGenerator.generateValues(X, 1, 5);

        Integer[][] AT = MatrixUtilities.transpose(Integer.class, A);

    }

}
