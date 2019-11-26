package Models;

import java.math.BigInteger;

public class Fraction extends Number implements Comparable<Fraction> {
    public final static Fraction ZERO = new Fraction(BigInteger.ZERO, BigInteger.ZERO, false);
    public final static Fraction ONE = new Fraction(BigInteger.ONE, BigInteger.ONE, false);
    private final BigInteger numerator;
    private final BigInteger denominator;

    public Fraction(BigInteger numerator, BigInteger denominator, boolean reduce) {
        if (denominator.signum() < 0) {
            numerator = numerator.negate();
            denominator = denominator.negate();
        }

        if (reduce) {
            BigInteger gcd = numerator.gcd(denominator);

            this.numerator = numerator.divide(gcd);
            this.denominator = denominator.divide(gcd);
        } else {
            this.numerator = numerator;
            this.denominator = denominator;
        }
    }

    public Fraction(long numerator) {
        this(new BigInteger("" + numerator), new BigInteger("" + 1), false);
    }

    public Fraction(long numerator, long denominator) {
        this(new BigInteger("" + numerator), new BigInteger("" + denominator), false);
    }

    public Fraction(BigInteger numerator, BigInteger denominator) {
        this(numerator, denominator, false);
    }

    public Fraction(long numerator, long denominator, boolean reduce) {
        this(new BigInteger("" + numerator), new BigInteger("" + denominator), reduce);
    }

    public Fraction add(Fraction f) {
        BigInteger thisNumerator = this.numerator.multiply(f.getDenominator());
        BigInteger aNumerator = f.getNumerator().multiply(this.denominator);

        BigInteger newNumerator = thisNumerator.add(aNumerator);
        BigInteger newDenominator = this.denominator.multiply(f.getDenominator());

        return new Fraction(newNumerator, newDenominator, true);
    }

    public Fraction subtract(Fraction f) {
        BigInteger thisNumerator = this.numerator.multiply(f.getDenominator());
        BigInteger aNumerator = f.getNumerator().multiply(this.denominator);

        BigInteger newNumerator = thisNumerator.subtract(aNumerator);
        BigInteger newDenominator = this.denominator.multiply(f.getDenominator());

        return new Fraction(newNumerator, newDenominator, true);
    }

    public Fraction multiply(Fraction f) {
        BigInteger newNumerator = this.numerator.multiply(f.getNumerator());
        BigInteger newDenominator = this.denominator.multiply(f.getDenominator());

        return new Fraction(newNumerator, newDenominator, true);
    }

    public Fraction divide(Fraction f) {
        BigInteger newNumerator = this.numerator.multiply(f.getDenominator());
        BigInteger newDenominator = this.denominator.multiply(f.getNumerator());

        return new Fraction(newNumerator, newDenominator, true);
    }

    public Fraction pow (int e) {
        return new Fraction(this.numerator.pow(e), this.denominator.pow(e));
    }

    @Override
    public int intValue() {
        return this.numerator.divide(this.denominator).intValue();
    }

    @Override
    public long longValue() {
        return this.numerator.divide(this.denominator).longValue();
    }

    @Override
    public float floatValue() {
        return this.numerator.divide(this.denominator).floatValue();
    }

    @Override
    public double doubleValue() {
        return this.numerator.divide(this.denominator).doubleValue();
    }

    public boolean isZero() {
        return this.numerator.equals(ZERO.getNumerator());
    }

    public int signum()
    {
        return this.numerator.signum();
    }

    public Fraction abs()
    {
        return new Fraction(this.numerator.abs(), this.denominator.abs());
    }

    public int compareTo(Fraction f)
    {
        if(f == null)
            throw new IllegalArgumentException("Null argument");

        if(signum() != f.signum()){
            return signum() - f.signum();
        }

        if(this.denominator.equals(f.getDenominator())){
            return this.numerator.compareTo(f.getNumerator());
        }

        return this.numerator.multiply(f.getDenominator()).compareTo(this.denominator.multiply(f.getNumerator()));
    }

    @Override
    public String toString() {
        if (this.isZero()) {
            return " 0 ";
        }

        return " " + this.numerator + "/" + this.denominator + " ";
    }

    public BigInteger getNumerator() {
        return numerator;
    }

    public BigInteger getDenominator() {
        return denominator;
    }
}
