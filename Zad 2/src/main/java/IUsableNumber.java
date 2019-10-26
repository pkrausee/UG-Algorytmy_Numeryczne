interface IUsableNumber <TType extends IUsableNumber<TType>> extends Comparable
{
    TType add(TType n);
    TType subtract(TType n);
    TType multiply(TType n);
    TType divide(TType n);

    TType ZERO();
    TType ONE();

    boolean isZero();
}

