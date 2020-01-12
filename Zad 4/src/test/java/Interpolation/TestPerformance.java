package Interpolation;

import Interpolation.Model.Point;
import Interpolation.Utils.JsonUtils;
import Interpolation.Utils.MatrixUtils;
import Interpolation.Utils.PointHelper;
import MatrixOperations.Gauss;
import MatrixOperations.GaussSeidel;
import MatrixOperations.Jacobi;
import Utilities.CollectionUtils;
import org.javatuples.Pair;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

@RunWith(JUnit4.class)
public class TestPerformance {

    @Test
    public void testE1() {
        List<Point> points = JsonUtils.read("data_500_samples.json");

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

            builder.append(i).append(";");

            start = System.nanoTime();
            Gauss.PartialPivoting(matrix, vector);
            end = System.nanoTime();

            builder.append(end - start).append(";");

            start = System.nanoTime();
            GaussSeidel.calculate(matrix, vector, 20);
            end = System.nanoTime();

            builder.append(end - start).append(";");

            start = System.nanoTime();
            Jacobi.calculate(matrix, vector, 20);
            end = System.nanoTime();

            builder.append(end - start).append(System.lineSeparator());
        }

        JsonUtils.save("E1.csv", builder.toString().replace(".", ","));
    }

    @Test
    public void testE2() {
        List<Point> points = new ArrayList<>();

        for (int i = 0; i < 32; i++) {
            points.addAll(JsonUtils.read("data_500_samples.json"));
        }

        long start, end;

        double[] xs = PointHelper.createIndexes(points);
        double[] ys = PointHelper.extractElevations(points);
        double[] hs = MatrixUtils.getHs(xs);

        Pair<double[][], double[]> getMatrixResult = MatrixUtils.getMatrix(xs, hs, ys);
        double[][] matrix = getMatrixResult.getValue0();
        double[] vector = getMatrixResult.getValue1();

        System.out.println("Size: " + points.size() + "\n");

        start = System.nanoTime();
        Gauss.PartialPivoting(matrix, vector);
        end = System.nanoTime();
        System.out.println("Gauss: " + (double) (end - start) / 1000000000 + " sec\n");

        start = System.nanoTime();
        GaussSeidel.calculate(matrix, vector, 20);
        end = System.nanoTime();
        System.out.println("Gauss-Seidel: " + (double) (end - start) / 1000000000 + " sec\n");

        start = System.nanoTime();
        Jacobi.calculate(matrix, vector, 20);
        end = System.nanoTime();
        System.out.println("Jacobi: " + (double) (end - start) / 1000000000 + " sec");

    }

    @Test
    public void testE3() {
        List<Point> points = JsonUtils.read("data_500_samples.json");

        long start, end;

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i <= 50; i += 1) {
            double[] xs = PointHelper.createIndexes(points);
            double[] ys = PointHelper.extractElevations(points);
            double[] hs = MatrixUtils.getHs(xs);

            Pair<double[][], double[]> getMatrixResult = MatrixUtils.getMatrix(xs, hs, ys);
            double[][] matrix = getMatrixResult.getValue0();
            double[] vector = getMatrixResult.getValue1();

            builder.append(i).append(";");

            start = System.nanoTime();
            GaussSeidel.calculate(matrix, vector, i);
            end = System.nanoTime();

            builder.append(end - start).append(";");

            start = System.nanoTime();
            Jacobi.calculate(matrix, vector, i);
            end = System.nanoTime();

            builder.append(end - start).append(System.lineSeparator());
        }

        JsonUtils.save("E3.csv", builder.toString().replace(".", ","));
    }


}
