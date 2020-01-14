package Interpolation;

import Interpolation.Model.Point;
import Interpolation.Model.SparseLocation;
import Interpolation.Utils.JsonUtils;
import Interpolation.Utils.MatrixUtils;
import Interpolation.Utils.PointHelper;
import MatrixOperations.Gauss;
import MatrixOperations.GaussSeidel;
import MatrixOperations.Jacobi;
import MatrixOperations.Sparse;
import Utilities.CollectionUtils;
import org.javatuples.Pair;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RunWith(JUnit4.class)
public class TestPerformance {

    @Test
    public void testE1() {
        List<Point> points = JsonUtils.read("Sierakowice-Chojnice.json");

        long start, end;

        StringBuilder builder = new StringBuilder();
        for (int i = 3; i < points.size(); i++) {
            List<Point> currentPoint = CollectionUtils.copy(points, i);

            double[] xs = PointHelper.createIndexes(currentPoint);
            double[] ys = PointHelper.extractElevations(currentPoint);
            double[] hs = MatrixUtils.getHs(xs);

            Pair<double[][], double[]> getMatrixResult = MatrixUtils.getMatrix(xs, hs, ys);
            double[][] matrix = getMatrixResult.getValue0();
            double[] vector = getMatrixResult.getValue1();

            Pair<Map<SparseLocation, Double>, double[]> sparseMatrixResult = MatrixUtils.getMatrixForSparse(xs, hs, ys);
            Map<SparseLocation, Double> sparseMatrix = sparseMatrixResult.getValue0();

            builder.append(i).append(";");

            start = System.nanoTime();
            Interpolation.getResult(xs, ys, Gauss.PartialPivoting(matrix, vector), 1);
            end = System.nanoTime();

            builder.append(end - start).append(";");

            start = System.nanoTime();
            Interpolation.getResult(xs, ys, GaussSeidel.calculate(matrix, vector, 20), 1);
            end = System.nanoTime();

            builder.append(end - start).append(";");

            start = System.nanoTime();
            Interpolation.getResult(xs, ys, Jacobi.calculate(matrix, vector, 20), 1);
            end = System.nanoTime();

            builder.append(end - start).append(";");

            start = System.nanoTime();
            Interpolation.getResult(xs, ys, Sparse.calculate(sparseMatrix, vector, 20), 1);
            end = System.nanoTime();

            builder.append(end - start).append(";");

            start = System.nanoTime();
            ExternalInterpolationLib.getResult(xs, ys, 1);
            end = System.nanoTime();

            builder.append(end - start).append(System.lineSeparator());
        }

        JsonUtils.save("E1.csv", builder.toString().replace(".", ","));
    }

    @Test
    public void testE2() {
        long start, end;

        List<Point> points = new ArrayList<>();

        for (int j = 0; j < 50; j++) {
            points.addAll(JsonUtils.read("Sierakowice-Chojnice.json"));
        }
        System.out.println("Size: " + points.size() + "\n");

        double[] xs = PointHelper.createIndexes(points);
        double[] ys = PointHelper.extractElevations(points);
        double[] hs = MatrixUtils.getHs(xs);

        Pair<double[][], double[]> getMatrixResult = MatrixUtils.getMatrix(xs, hs, ys);
        double[][] matrix = getMatrixResult.getValue0();
        double[] vector = getMatrixResult.getValue1();

        Pair<Map<SparseLocation, Double>, double[]> sparseMatrixResult = MatrixUtils.getMatrixForSparse(xs, hs, ys);
        Map<SparseLocation, Double> sparseMatrix = sparseMatrixResult.getValue0();
        double[] sparseVector = sparseMatrixResult.getValue1();

//        start = System.nanoTime();
//        Gauss.PartialPivoting(matrix, vector);
//        end = System.nanoTime();
//        System.out.println("Gauss: " + (double) (end - start) / 1000000000 + " sec\n");

//        start = System.nanoTime();
//        Interpolation.getResult(xs, ys, GaussSeidel.calculate(matrix, vector, 20), 1);
//        end = System.nanoTime();
//        System.out.println("Gauss-Seidel: " + (double) (end - start) / 1000000000 + " sec");
//
//        start = System.nanoTime();
//        Interpolation.getResult(xs, ys, Jacobi.calculate(matrix, vector, 20), 1);
//        end = System.nanoTime();
//        System.out.println("Jacobi: " + (double) (end - start) / 1000000000 + " sec");

        start = System.nanoTime();
        Interpolation.getResult(xs, ys, Sparse.calculate(sparseMatrix, sparseVector, 20), 1);
        end = System.nanoTime();
        System.out.println("Sparse: " + (double) (end - start) / 1000000000 + " sec");

        start = System.nanoTime();
        ExternalInterpolationLib.getResult(xs, ys, 1);
        end = System.nanoTime();
        System.out.println("External Lib: " + (double) (end - start) / 1000000000 + " sec");
    }

    @Test
    public void testE3() {
        List<Point> points = JsonUtils.read("Sierakowice-Chojnice.json");

        long start, end;

        double[] xs = PointHelper.createIndexes(points);
        double[] ys = PointHelper.extractElevations(points);
        double[] hs = MatrixUtils.getHs(xs);

        Pair<double[][], double[]> getMatrixResult = MatrixUtils.getMatrix(xs, hs, ys);
        double[][] matrix = getMatrixResult.getValue0();
        double[] vector = getMatrixResult.getValue1();

        Pair<Map<SparseLocation, Double>, double[]> sparseMatrixResult = MatrixUtils.getMatrixForSparse(xs, hs, ys);
        Map<SparseLocation, Double> sparseMatrix = sparseMatrixResult.getValue0();

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i <= 50; i += 1) {
            builder.append(i).append(";");

            start = System.nanoTime();
            GaussSeidel.calculate(matrix, vector, i);
            end = System.nanoTime();

            builder.append(end - start).append(";");

            start = System.nanoTime();
            Jacobi.calculate(matrix, vector, i);
            end = System.nanoTime();

            builder.append(end - start).append(";");

            start = System.nanoTime();
            Sparse.calculate(sparseMatrix, vector, i);
            end = System.nanoTime();

            builder.append(end - start).append(System.lineSeparator());
        }

        JsonUtils.save("E3.csv", builder.toString().replace(".", ","));
    }

    @Test
    public void testE3_2() {
        List<Point> points = JsonUtils.read("Ilawa-Chojnice.json");

        double[] xs = PointHelper.createIndexes(points);
        double[] ys = PointHelper.extractElevations(points);
        double[] hs = MatrixUtils.getHs(xs);

        Pair<double[][], double[]> getMatrixResult = MatrixUtils.getMatrix(xs, hs, ys);
        double[][] matrix = getMatrixResult.getValue0();
        double[] vector = getMatrixResult.getValue1();

        Pair<Map<SparseLocation, Double>, double[]> sparseMatrixResult = MatrixUtils.getMatrixForSparse(xs, hs, ys);
        Map<SparseLocation, Double> sparseMatrix = sparseMatrixResult.getValue0();

        int trainSize = (points.size()) / 5 * 4;
        double[] trainXs = new double[trainSize];
        double[] trainYs = new double[trainSize];

        for (int i = 1, j = 0; i < points.size(); i++) {
            if (i % 5 != 0) {
                trainXs[j] = xs[i];
                trainYs[j] = ys[i];

                j++;
            }
        }

        double[] trainHs = MatrixUtils.getHs(trainXs);

        Pair<double[][], double[]> trainGetMatrixResult = MatrixUtils.getMatrix(trainXs, trainHs, trainYs);
        double[][] trainMatrix = trainGetMatrixResult.getValue0();
        double[] trainVector = trainGetMatrixResult.getValue1();

        Pair<Map<SparseLocation, Double>, double[]> trainSparseMatrixResult = MatrixUtils.getMatrixForSparse(trainXs, trainHs, trainYs);
        Map<SparseLocation, Double> trainSparseMatrix = trainSparseMatrixResult.getValue0();

        double[] gaussSeidelResult = GaussSeidel.calculate(matrix, vector, 20);
        double[] jacobiResult = Jacobi.calculate(matrix, vector, 20);
        double[] sparseResult = Sparse.calculate(sparseMatrix, vector, 20);

        double[] gaussSeidelKnownValues = new double[points.size()];
        double[] jacobiKnownValues = new double[points.size()];
        double[] sparseKnownValues = new double[points.size()];

        double[] gaussSeidelUnknownValues = new double[points.size()];
        double[] jacobiUnknownValues = new double[points.size()];
        double[] sparseUnknownValues = new double[points.size()];

        double[] gaussSeidelUnknownValuesVector = new double[points.size() - trainSize];
        double[] jacobiUnknownValuesVector = new double[points.size() - trainSize];
        double[] sparseUnknownValuesVector = new double[points.size() - trainSize];

        StringBuilder builder = new StringBuilder();
        for (int i = 1; i <= 20; i += 1) {

            for(int k = 1, j = 0; k < points.size(); k++) {
                double[] trainGaussSeidelResult = GaussSeidel.calculate(trainMatrix, trainVector, i);
                double[] trainJacobiResult = Jacobi.calculate(trainMatrix, trainVector, i);
                double[] trainSparseResult = Sparse.calculate(trainSparseMatrix, trainVector, i);

                jacobiKnownValues[k] = Interpolation.getResult(xs, ys, gaussSeidelResult, k);
                gaussSeidelKnownValues[k] = Interpolation.getResult(xs, ys, jacobiResult, k);
                sparseKnownValues[k] = Interpolation.getResult(xs, ys, sparseResult, k);

                gaussSeidelUnknownValues[k] = Interpolation.getResult(trainXs, trainYs, trainGaussSeidelResult, k);
                jacobiUnknownValues[k] = Interpolation.getResult(trainXs, trainYs, trainJacobiResult, k);
                sparseUnknownValues[k] = Interpolation.getResult(trainXs, trainYs, trainSparseResult, k);

                double gaussSeidelDiff = Math.abs(gaussSeidelKnownValues[k] - gaussSeidelUnknownValues[k]);
                double jacobiDiff = Math.abs(jacobiKnownValues[k] - jacobiUnknownValues[k]);
                double sparseDiff = Math.abs(sparseKnownValues[k] - sparseUnknownValues[k]);

                if (k % 5 == 0) {
                    gaussSeidelUnknownValuesVector[j] = gaussSeidelDiff;
                    jacobiUnknownValuesVector[j] = jacobiDiff;
                    sparseUnknownValuesVector[j] = sparseDiff;

                    j++;
                }
            }

            builder.append(i)
                    .append(";")
                    .append(MatrixUtils.norm(gaussSeidelUnknownValuesVector))
                    .append(";")
                    .append(MatrixUtils.norm(jacobiUnknownValuesVector))
                    .append(";")
                    .append(MatrixUtils.norm(sparseUnknownValuesVector))
                    .append(System.lineSeparator());
        }

        JsonUtils.save("E3_2.csv", builder.toString().replace(".", ","));
    }

}
