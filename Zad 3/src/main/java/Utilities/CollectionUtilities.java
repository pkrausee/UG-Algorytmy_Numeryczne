package Utilities;

import java.util.List;

public abstract class CollectionUtilities {
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
        System.out.println("-----------------------------");
    }

    public static <TType> void show(TType[] A) {
        for (TType tType : A) {
            System.out.print(tType + " ");
        }
        System.out.println();
    }

    public static <TType> void show(TType[][] A) {
        for (TType[] tType : A) {
            for (TType t : tType) {
                System.out.print(t + " ");
            }
            System.out.print(System.lineSeparator());
        }
        System.out.println();
    }

    public static <TType> void show(List<TType> A) {
        for (TType tType : A) {
            System.out.print(tType + " ");
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

    public static <TType extends Comparable<TType>> boolean compare(TType[] A, TType[] B) {

        if (A.length != B.length) {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < A.length; i++) {
            if (A[i].compareTo(B[i]) != 0) {
                return false;
            }
        }

        return true;
    }
}
