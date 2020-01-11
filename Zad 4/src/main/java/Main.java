import Interpolation.Interpolation;
import Interpolation.MatrixUtils;
import MatrixOperations.Jacobi;
import Utilities.CollectionUtils;
import org.javatuples.Pair;

public class Main {
    public static void main(String[] args) {
        double[] xs = new double[]{0, 1, 2, 4, 5, 6, 7, 8, 9};
        double[] ys = new double[]{90.7404, 89.244, 87.685, 85.52964, 83.140, 82.04270, 80.478057, 78.7, 77.01};

        double[] hs = MatrixUtils.getHs(xs);

        Pair<double[][], double[]> getMatrixResult = MatrixUtils.getMatrix(xs, hs, ys);

        CollectionUtils.show(getMatrixResult.getValue0());
        CollectionUtils.show(getMatrixResult.getValue1());

        System.out.println(Interpolation.getResult(xs,
                ys,
                Jacobi.calculate(getMatrixResult.getValue0(), getMatrixResult.getValue1(), 20),
                9));

    }
}
//86.79,
