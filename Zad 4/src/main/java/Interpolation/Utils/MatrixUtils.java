package Interpolation.Utils;

import Utilities.CollectionUtils;
import org.javatuples.Pair;

public class MatrixUtils {
    /*

        xs - resolution / indeksy
        ys - elevation / wysokosc
        hs - odleglosci pomiedzy punktami

     */

    public static double[] getHs(double[] xs) {
        double[] result = new double[xs.length];

        double prev = 0;
        for (int i = 0; i < xs.length; i++) {
            result[i] = xs[i] - prev;
            prev = xs[i];
        }

        return result;
    }

    public static Pair<double[][], double[]> getMatrix(double[] xs, double[] hs, double[] ys) {
        int matrixSize = xs.length - 2;

        double[][] matrix = new double[matrixSize][matrixSize];
        double[] vector = new double[0];

        for (int i = 1, j = 0; i < xs.length - 1; i++, j++) {
            double[] Ms = getMs(hs, ys, i);

            vector = CollectionUtils.addOnEnd(vector, Ms[3], 1);
            CollectionUtils.setRow(matrix, getRowValues(i, matrixSize, Ms), j, 1, matrixSize);
        }

        return new Pair<>(matrix, vector);
    }

    private static double[] getRowValues(int i, int matrixSize, double[] Ms) {
        double[] row = new double[0];

        row = CollectionUtils.addOnStart(row, 0, i - 1);

        row = CollectionUtils.addOnEnd(row, Ms[0], 1);
        row = CollectionUtils.addOnEnd(row, Ms[1], 1);
        row = CollectionUtils.addOnEnd(row, Ms[2], 1);

        row = CollectionUtils.addOnEnd(row, 0, matrixSize - i);

        return row;
    }

    private static double[] getMs(double[] hs, double[] ys, int i) {
        double Mj0 = hs[i] / (hs[i] + hs[i + 1]);
        double Mj1 = 2;
        double Mj2 = hs[i + 1] / (hs[i] + hs[i + 1]);

        double b = (ys[i + 1] - ys[i]) / hs[i + 1];

        b -= (ys[i] - ys[i - 1]) / hs[i];
        b *= 6 / (hs[i] + hs[i + 1]);

        return new double[]{Mj0, Mj1, Mj2, b};
    }
}
