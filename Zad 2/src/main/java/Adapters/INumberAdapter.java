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

    TType[] copy (TType[] a);
    TType[][] copy (TType[][] a);
}

