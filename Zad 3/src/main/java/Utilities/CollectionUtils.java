package Utilities;

public abstract class CollectionUtils {
    public static void showRecommendationMatrix(double[][] A) {
        String format = "| %1$-4s ";

        for (double[] row : A) {
            for (double value : row) {
                System.out.format(format, value);
            }
            System.out.print("|\n");
        }

        System.out.println();
    }

    public static void swapRows(double[] A, int src, int dest) {
        double temp = A[src];
        A[src] = A[dest];
        A[dest] = temp;
    }

    public static void swapRows(Integer[] A, int src, int dest) {
        int temp = A[src];
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

    public static double[] getCol(double[][] src, int srcCol) {
        double[] col = new double[src.length];

        for (int i = 0; i < src.length; i++) {
            col[i] = src[i][srcCol];
        }

        return col;
    }

    public static double[] getRow(double[][] src, int srcRow) {
        double[] row = new double[src.length];

        System.arraycopy(src[srcRow], 0, row, 0, src.length);

        return row;
    }

    public static double[][] copy(double[][] src) {
        double[][] copy = new double[src.length][src[0].length];

        for (int i = 0; i < src.length; i++) {
            System.arraycopy(src[i], 0, copy[i], 0, src[i].length);
        }

        return copy;
    }

    public static void copy(double[][] src, double[][] dest, int srcCol, int destCol) {
        if (src.length != dest.length) {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < src.length; i++) {
            dest[i][destCol] = src[i][srcCol];
        }
    }

    public static void paste(double[] src, double[][] dest, int destCol) {
        for (int i = 0; i < src.length; i++) {
            dest[i][destCol] = src[i];
        }
    }

    public static Integer[] getIntArr(int size) {
        Integer[] arr = new Integer[size];

        for (int i = 0; i < size; i++) {
            arr[i] = i;
        }

        return arr;
    }
}
