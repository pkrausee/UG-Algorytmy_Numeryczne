package Adapters;

public interface INumberAdapter<TType extends Number> {
    TType add(TType n1, TType n2);

    TType subtract(TType n1, TType n2);

    TType multiply(TType n1, TType n2);

    TType divide(TType n1, TType n2);

    TType pow(TType n, int e);

    TType sqrt(TType n);

    TType ZERO();

    TType ONE();

    TType abs(TType n);

    boolean isZero(TType n);

    int compareTo(TType n1, TType n2);

    String toString(TType n);

    TType[] copy(TType[] a);

    TType[][] copy(TType[][] a);

    TType getInstance();

    TType[] getArrInstance(int size);

    TType[][] getMatrixInstance(int rows, int cols);
}

