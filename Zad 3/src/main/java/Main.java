import Adapters.DoubleAdapter;
import Adapters.INumberAdapter;
import Matrix.MatrixUtilities;
import Utilities.CollectionUtilities;

public class Main {
    public static void main(String[] args) {
        INumberAdapter<Double> adapter = new DoubleAdapter();

        Double[][] matrix = new Double[][]{
                {1d, 2d, 3d},
                {4d, 5d, 6d},
                {7d, 8d, 9d}
        };

        matrix = MatrixUtilities.transpose(adapter, matrix);

        CollectionUtilities.show(matrix);
    }
}
