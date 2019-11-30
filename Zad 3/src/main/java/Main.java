import Matrix.MatrixUtilities;
import Utilities.CollectionUtilities;

public class Main {
    public static void main(String[] args) {
        Double[][] A = new Double[][]{
                {1d, 0d, 2d},
                {-1d, 3d, 1d}
        };

        Double[][] B = new Double[][]{
                {3d, 1d},
                {2d, 1d},
                {1d, 0d}
        };

        Double[][] multiResult = MatrixUtilities.multiply(A, B);

        CollectionUtilities.show(multiResult);
    }
}
