package Adapters;

import Models.Fraction;

public class FractionAdapter implements INumberAdapter<Fraction> {
    public final static Fraction ZERO = new Fraction(0);
    public final static Fraction ONE = new Fraction(1);

    public Fraction add(Fraction n1, Fraction n2) {
        return n1.add(n2);
    }

    public Fraction subtract(Fraction n1, Fraction n2) {
        return n1.subtract(n2);
    }

    public Fraction multiply(Fraction n1, Fraction n2) {
        return n1.multiply(n2);
    }

    public Fraction divide(Fraction n1, Fraction n2) {
        return n1.divide(n2);
    }

    public Fraction pow(Fraction n, int e) {
        return n.pow(e);
    }

    public Fraction sqrt(Fraction n) {
        return null;
    }

    public Fraction ZERO() {
        return ZERO;
    }

    public Fraction ONE() {
        return ONE;
    }

    public boolean isZero(Fraction f) {
        return f.compareTo(ZERO) == 0;
    }

    public int compareTo(Fraction n1, Fraction n2) {
        return n1.compareTo(n2);
    }

    public String toString(Fraction f) {
        return f.toString();
    }
}
