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
import org.javatuples.Pair;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;
import java.util.Map;

@RunWith(JUnit4.class)
public class TestCorrectness {

    @Test
    public void testC1() {
        List<Point> points = JsonUtils.read("Ilawa-Chojnice.json");

        double[] xs = PointHelper.createIndexes(points);
        double[] ys = PointHelper.extractElevations(points);
        double[] hs = MatrixUtils.getHs(xs);

        Pair<double[][], double[]> getMatrixResult = MatrixUtils.getMatrix(xs, hs, ys);
        double[][] matrix = getMatrixResult.getValue0();
        double[] vector = getMatrixResult.getValue1();

        Pair<Map<SparseLocation, Double>, double[]> sparseMatrixResult = MatrixUtils.getMatrixForSparse(xs, hs, ys);
        Map<SparseLocation, Double> sparseMatrix = sparseMatrixResult.getValue0();
        double[] sparseVector = sparseMatrixResult.getValue1();

        double[] gaussResult = Gauss.PartialPivoting(matrix, vector);
        double[] gaussSeidelResult = GaussSeidel.calculate(matrix, vector, 20);
        double[] jacobiResult = Jacobi.calculate(matrix, vector, 20);
        double[] sparseResult = Sparse.calculate(sparseMatrix, sparseVector, 20);

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

        double[] trainGaussResult = Gauss.PartialPivoting(trainMatrix, trainVector);
        double[] trainGaussSeidelResult = GaussSeidel.calculate(trainMatrix, trainVector, 20);
        double[] trainJacobiResult = Jacobi.calculate(trainMatrix, trainVector, 20);
        double[] trainSparseResult = Sparse.calculate(trainSparseMatrix, trainVector, 20);

        double[] gaussKnownValues = new double[points.size()];
        double[] gaussSeidelKnownValues = new double[points.size()];
        double[] jacobiKnownValues = new double[points.size()];
        double[] sparseKnownValues = new double[points.size()];
        double[] externalKnownValues = new double[points.size()];

        double[] gaussUnknownValues = new double[points.size()];
        double[] gaussSeidelUnknownValues = new double[points.size()];
        double[] jacobiUnknownValues = new double[points.size()];
        double[] sparseUnknownValues = new double[points.size()];
        double[] externalUnknownValues = new double[points.size()];

        double[] gaussUnknownValuesVector = new double[points.size() - trainSize];
        double[] gaussSeidelUnknownValuesVector = new double[points.size() - trainSize];
        double[] jacobiUnknownValuesVector = new double[points.size() - trainSize];
        double[] sparseUnknownValuesVector = new double[points.size() - trainSize];
        double[] externalUnknownValuesVector = new double[points.size() - trainSize];

        StringBuilder result = new StringBuilder();
        for (int i = 1, j = 0; i < points.size(); i++) {
            gaussKnownValues[i] = Interpolation.getResult(xs, ys, gaussResult, i);
            jacobiKnownValues[i] = Interpolation.getResult(xs, ys, gaussSeidelResult, i);
            gaussSeidelKnownValues[i] = Interpolation.getResult(xs, ys, jacobiResult, i);
            sparseKnownValues[i] = Interpolation.getResult(xs, ys, sparseResult, i);
            externalKnownValues[i] = ExternalInterpolationLib.getResult(xs, ys, i);

            gaussUnknownValues[i] = Interpolation.getResult(trainXs, trainYs, trainGaussResult, i);
            gaussSeidelUnknownValues[i] = Interpolation.getResult(trainXs, trainYs, trainGaussSeidelResult, i);
            jacobiUnknownValues[i] = Interpolation.getResult(trainXs, trainYs, trainJacobiResult, i);
            sparseUnknownValues[i] = Interpolation.getResult(trainXs, trainYs, trainSparseResult, i);
            externalUnknownValues[i] = ExternalInterpolationLib.getResult(trainXs, trainYs, i);

            double gaussDiff = Math.abs(gaussKnownValues[i] - gaussUnknownValues[i]);
            double gaussSeidelDiff = Math.abs(gaussSeidelKnownValues[i] - gaussSeidelUnknownValues[i]);
            double jacobiDiff = Math.abs(jacobiKnownValues[i] - jacobiUnknownValues[i]);
            double sparseDiff = Math.abs(sparseKnownValues[i] - sparseUnknownValues[i]);
            double externalDiff = Math.abs(externalKnownValues[i] - externalUnknownValues[i]);

            if (i % 5 == 0) {
                gaussUnknownValuesVector[j] = gaussDiff;
                gaussSeidelUnknownValuesVector[j] = gaussSeidelDiff;
                jacobiUnknownValuesVector[j] = jacobiDiff;
                sparseUnknownValuesVector[j] = sparseDiff;
                externalUnknownValuesVector[j] = externalDiff;

                j++;
            }

            result.append(i)
                    .append(";")
                    .append(Interpolation.getResult(xs, ys, gaussResult, i))
                    .append(";")
                    .append(Interpolation.getResult(trainXs, trainYs, trainGaussResult, i))
                    .append(System.lineSeparator());
        }

        JsonUtils.save("C1.csv", result.toString().replace(".", ","));

        System.out.println("Gauss: " + MatrixUtils.norm(gaussUnknownValuesVector));
        System.out.println("Gauss-Seidel: " + MatrixUtils.norm(gaussSeidelUnknownValuesVector));
        System.out.println("Jacobi: " + MatrixUtils.norm(jacobiUnknownValuesVector));
        System.out.println("Sparse: " + MatrixUtils.norm(sparseUnknownValuesVector));
        System.out.println("External: " + MatrixUtils.norm(externalUnknownValuesVector));
    }

    @Test
    public void testC2() {
        List<Point> points = JsonUtils.read("Ilawa-Chojnice.json");

        double[] xs = PointHelper.createIndexes(points);
        double[] ys = PointHelper.extractElevations(points);
        double[] hs = MatrixUtils.getHs(xs);

        Pair<double[][], double[]> getMatrixResult = MatrixUtils.getMatrix(xs, hs, ys);
        double[][] matrix = getMatrixResult.getValue0();
        double[] vector = getMatrixResult.getValue1();

        Pair<Map<SparseLocation, Double>, double[]> sparseMatrixResult = MatrixUtils.getMatrixForSparse(xs, hs, ys);
        Map<SparseLocation, Double> sparseMatrix = sparseMatrixResult.getValue0();

        double[] gaussResult = Gauss.PartialPivoting(matrix, vector);
        double[] gaussSeidelResult = new double[0];
        double[] jacobiResult = new double[0];
        double[] sparseResult = new double[0];

        StringBuilder result = new StringBuilder();
        for (int i = 1; i <= 30; i++) {
            gaussSeidelResult = GaussSeidel.calculate(matrix, vector, i);
            jacobiResult = Jacobi.calculate(matrix, vector, i);
            sparseResult = Sparse.calculate(sparseMatrix, vector, i);

            result.append(i)
                    .append(";")
                    .append(MatrixUtils.norm(gaussSeidelResult))
                    .append(";")
                    .append(MatrixUtils.norm(jacobiResult))
                    .append(";")
                    .append(MatrixUtils.norm(sparseResult))
                    .append(System.lineSeparator());
        }

        JsonUtils.save("C2.csv", result.toString().replace(".", ","));

        System.out.println("Gauss: " + MatrixUtils.norm(gaussResult));
        System.out.println("Gauss-Seidel: " + MatrixUtils.norm(gaussSeidelResult));
        System.out.println("Jacobi: " + MatrixUtils.norm(jacobiResult));
        System.out.println("Sparse: " + MatrixUtils.norm(sparseResult));
    }
}
