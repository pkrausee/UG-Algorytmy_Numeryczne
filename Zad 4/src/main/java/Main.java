import Interpolation.ExternalInterpolationLib;
import Interpolation.Model.SparseLocation;
import Interpolation.Utils.MatrixUtils;
import org.javatuples.Pair;

import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {
        double[] xs = new double[]{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        double[] ys = new double[]{ 90.7404, 89.244, 87.685, 86.79, 85.52964, 83.140, 82.04270, 80.478057, 78.7, 77.01 };
        double[] hs = MatrixUtils.getHs(xs);

        Pair<Map<SparseLocation, Double>, double[]> getMatrixResult = MatrixUtils.getMatrixForSparse(xs, hs, ys);
        Map<SparseLocation, Double> locationDict = getMatrixResult.getValue0();
        double[] vector = getMatrixResult.getValue1();

//        double[] sparseResult = Sparse.calculate(locationDict, vector, 20);
//        System.out.println(Interpolation.getResult(xs, ys, sparseResult, 3));

        System.out.println(ExternalInterpolationLib.getResult(xs, ys,3));

//        String Chojnice = "53.694413,17.556895";
//        String Sierakowice = "54.348372,17.894768";
//        String Ilawa = "53.595996,19.568440";
//
//        JsonUtils.save("Ilawa-Chojnice.json", ValueProvider.getGoogleApiData(Chojnice, Ilawa, 200));
    }
}
