package Utilities;

import java.util.List;

public abstract class CollectionUtils {
    public static <TType> void show(TType[] A) {
        for (TType tType : A) {
            if(tType == null) {
                System.out.print("  ");
            } else {
                System.out.print(tType + " ");
            }
        }
        System.out.println();
    }

    public static <TType> void show(TType[][] A) {
        for (TType[] tType : A) {
            for (TType t : tType) {
                if(t == null) {
                    System.out.print("  ");
                } else {
                    System.out.print(t + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public static <TType> void show(List<TType> A) {
        for (TType tType : A) {
            if(tType == null) {
                System.out.print("  ");
            } else {
                System.out.print(tType + " ");
            }
        }
        System.out.println();
    }

    public static <TType> void show(TType[][] A, TType[] B) {
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length + 1; j++) {
                if (j < A[i].length) {
                    System.out.print(A[i][j] + " ");
                } else {
                    System.out.print("| " + B[i]);
                }
            }

            System.out.println();
        }
    }

    public static void showRecommendationMatrix(Double[][] A) {
        String format = "| %1$-4s ";

        for (Double[] row : A) {
            for (Double value : row) {
                    System.out.format(format, value);
            }
            System.out.print("|\n");
        }

        System.out.println();
    }

    public static Integer[] getIntArr(int size) {
        Integer[] arr = new Integer[size];

        for (int i = 0; i < size; i++) {
            arr[i] = i;
        }

        return arr;
    }

    public static <TType> void swapRows(TType[] A, int src, int dest) {
        TType temp = A[src];
        A[src] = A[dest];
        A[dest] = temp;
    }

    public static <TType> void swapRows(TType[][] A, int src, int dest) {
        for (int i = 0; i < A[src].length; i++) {
            TType temp = A[src][i];
            A[src][i] = A[dest][i];
            A[dest][i] = temp;
        }
    }

    public static <TType> void swapCols(TType[][] A, int src, int dest) {
        for (int i = 0; i < A.length; i++) {
            TType temp = A[i][src];
            A[i][src] = A[i][dest];
            A[i][dest] = temp;
        }
    }

    public static Double[] getCol(Double[][] src, int srcCol) {
        Double[] col = new Double[src.length];

        for(int i = 0; i < src.length; i++) {
            col[i] = src[i][srcCol];
        }

        return col;
    }

    public static Double[][] copy(Double[][] src) {
        Double[][] copy = new Double[src.length][src[0].length];

        for(int i = 0; i < src.length; i++) {
            System.arraycopy(src[i], 0, copy[i], 0, src[i].length);
        }

        return copy;
    }

    public static <TType> void copy(TType[][] src, TType[][] dest, int srcCol, int destCol) {
        if(src.length != dest.length) {
            throw new IllegalArgumentException();
        }

        for(int i = 0; i < src.length; i++) {
            dest[i][destCol] = src[i][srcCol];
        }
    }

    public static <TType> void paste(TType[] src, TType[][] dest, int destCol) {
        for(int i = 0; i < src.length; i++) {
            dest[i][destCol] = src[i];
        }
    }
}
