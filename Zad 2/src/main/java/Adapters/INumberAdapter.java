package Adapters;

public interface INumberAdapter <TType extends Number> extends Comparable
{
    INumberAdapter add(INumberAdapter n);
    INumberAdapter subtract(INumberAdapter n);
    INumberAdapter multiply(INumberAdapter n);
    INumberAdapter divide(INumberAdapter n);

    INumberAdapter ZERO();
    INumberAdapter ONE();

    boolean isZero();

    String toString();

    TType getValue();
}

