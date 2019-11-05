package Adapters;

public interface INumberAdapter<TType extends Number> {
    TType add(TType n1, TType n2);

    TType subtract(TType n1, TType n2);

    TType multiply(TType n1, TType n2);

    TType divide(TType n1, TType n2);

    TType ZERO();

    TType ONE();

    boolean isZero(TType n);

    int compareTo(TType n1, TType n2);
}

