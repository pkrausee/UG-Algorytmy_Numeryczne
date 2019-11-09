package Adapters;

import Models.Fraction;

public class FractionAdapter implements INumberAdapter<Fraction> {
    public final static Fraction ZERO = new Fraction(0);
    public final static Fraction ONE = new Fraction(1);

    @Override
    public Fraction add(Fraction n1, Fraction n2) {
        return n1.add(n2);
    }

    @Override
    public Fraction subtract(Fraction n1, Fraction n2) {
        return n1.subtract(n2);
    }

    @Override
    public Fraction multiply(Fraction n1, Fraction n2) {
        return n1.multiply(n2);
    }

    @Override
    public Fraction divide(Fraction n1, Fraction n2) {
        return n1.divide(n2);
    }

    @Override
    public Fraction pow(Fraction n, int e) {
        return n.pow(e);
    }

    @Override
    public Fraction sqrt(Fraction n) {
        return null;
    }

    @Override
    public Fraction ZERO() {
        return ZERO;
    }

    @Override
    public Fraction ONE() {
        return ONE;
    }

    @Override
    public Fraction abs(Fraction n) {
        return n.abs();
    }

    @Override
    public boolean isZero(Fraction f) {
        return f.compareTo(ZERO) == 0;
    }

    @Override
    public int compareTo(Fraction n1, Fraction n2) {
        return n1.compareTo(n2);
    }

    public String toString(Fraction f) {
        return f.toString();
    }

    @Override
    public Fraction[] copy(Fraction[] a) {
        Fraction[] copy = new Fraction[a.length];
        System.arraycopy( a, 0, copy, 0, a.length );

        return copy;
    }

    @Override
    public Fraction[][] copy(Fraction[][] a) {
        Fraction[][] copy = new Fraction[a.length][a[0].length];

        for(int i=0; i<a.length; i++){
            System.arraycopy(a[i], 0, copy[i], 0, a[i].length);
        }

        return copy;
    }

    @Override
    public Fraction getInstance() {
        return new Fraction(0);
    }

    @Override
    public Fraction[] getArrInstance(int size) {
        return new Fraction[size];
    }

    @Override
    public Fraction[][] getMatrixInstance(int size) {
        return new Fraction[size][size];
    }
}
