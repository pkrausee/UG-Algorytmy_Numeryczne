package Utilities;

import Interpolation.Model.Location;
import Interpolation.Model.SparseLocation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class CollectionUtils {
    public static void show(double[] matrix) {
        for (double value : matrix) {
            System.out.format(value + " ");
        }
        System.out.println();
    }

    public static void show(double[][] matrix) {
        for (double[] row : matrix) {
            for (double value : row) {
                System.out.format(value + " ");
            }
            System.out.print("\n");
        }
    }

    public static void show(Map<SparseLocation, Double> map) {
        for(Map.Entry<SparseLocation, Double> entry : map.entrySet()) {
            System.out.print(entry.getKey().getLat() + " " + entry.getKey().getLng());
            System.out.print(" " + entry.getValue() + "\n");
        }
    }

    public static void swapRows(int[] A, int src, int dest) {
        int temp = A[src];
        A[src] = A[dest];
        A[dest] = temp;
    }

    public static void swapRows(double[] A, int src, int dest) {
        double temp = A[src];
        A[src] = A[dest];
        A[dest] = temp;
    }

    public static void swapRows(double[][] A, int src, int dest) {
        for (int i = 0; i < A[src].length; i++) {
            double temp = A[src][i];
            A[src][i] = A[dest][i];
            A[dest][i] = temp;
        }
    }

    public static void swapCols(double[][] A, int src, int dest) {
        for (int i = 0; i < A.length; i++) {
            double temp = A[i][src];
            A[i][src] = A[i][dest];
            A[i][dest] = temp;
        }
    }

    public static void setRow(double[][] matrix, double[] row, int index, int start, int end) {
        for (int i = 0; i < end; i++) {
            matrix[index][i] = row[start];
            start++;
        }
    }

    public static double[] addOnStart(double[] vector, double value) {
        double[] result = new double[vector.length + 1];

        result[0] = value;
        System.arraycopy(vector, 0, result, 1, vector.length);

        return result;
    }

    public static double[] addOnEnd(double[] vector, double value) {
        double[] result = new double[vector.length + 1];

        System.arraycopy(vector, 0, result, 0, vector.length);
        result[result.length - 1] = value;

        return result;
    }

    public static double[] addOnStart(double[] vector, double value, int quantity) {
        double[] result = new double[vector.length + quantity];

        for (int i = 0; i < vector.length; i++) {
            result[i] = value;
        }

        System.arraycopy(vector, 0, result, vector.length, vector.length);

        return result;
    }

    public static double[] addOnEnd(double[] vector, double value, int quantity) {
        if (quantity > 0) {
            double[] result = new double[vector.length + quantity];

            System.arraycopy(vector, 0, result, 0, vector.length);

            for (int i = vector.length; i < vector.length + quantity; i++) {
                result[i] = value;
            }

            return result;
        }

        return vector;
    }

    public static double[] copy(double[] vector) {
        double[] copy = new double[vector.length];

        System.arraycopy(vector, 0, copy, 0, vector.length);

        return copy;
    }

    public static double[][] copy(double[][] a) {
        double[][] copy = new double[a.length][a[0].length];

        for (int i = 0; i < a.length; i++) {
            System.arraycopy(a[i], 0, copy[i], 0, a[i].length);
        }

        return copy;
    }

    public static <T> List<T> copy(List<T> list, int quantity) {
        List<T> copy = new ArrayList<>();

        for (int i = 0; i < quantity; i++) {
            copy.add(list.get(i));
        }

        return copy;
    }
}
