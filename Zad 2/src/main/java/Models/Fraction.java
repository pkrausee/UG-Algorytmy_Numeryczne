package Models;

import java.math.BigInteger;

public class Fraction extends Number implements INumberAdapter<Fraction>
{
    private final BigInteger numerator;
    private final BigInteger denominator;

    public final static Fraction ZERO= new Fraction(BigInteger.ZERO, BigInteger.ZERO, false);
    public final static Fraction ONE = new Fraction(BigInteger.ONE, BigInteger.ONE, false);

    public Fraction(BigInteger numerator, BigInteger denominator, boolean reduce)
    {
        if(denominator.signum() < 0)
        {
            numerator = numerator.negate();
            denominator = denominator.negate();
        }

        if(reduce)
        {
            BigInteger gcd = numerator.gcd(denominator);
            this.numerator = numerator.divide(gcd);
            this.denominator = denominator.divide(gcd);
        }
        else
        {
            this.numerator = numerator;
            this.denominator = denominator;
        }
    }

    public Fraction(BigInteger numerator, BigInteger denominator)
    {
        this(numerator, denominator, false);
    }

    public Fraction (long numerator, long denominator, boolean reduce)
    {
        this(new BigInteger("" + numerator), new BigInteger("" + denominator), reduce);
    }

    public Fraction (long numerator, long denominator)
    {
        this(new BigInteger("" + numerator), new BigInteger("" + denominator), false);
    }

    public INumberAdapter add (INumberAdapter f)
    {
        BigInteger thisNumerator = this.numerator.multiply(((Fraction) f).getDenominator());
        BigInteger aNumerator = ((Fraction) f).getNumerator().multiply(this.denominator);

        BigInteger newNumerator = thisNumerator.add(aNumerator);
        BigInteger newDenominator = this.denominator.multiply(((Fraction) f).getDenominator());

        return new Fraction(newNumerator, newDenominator, true);
    }

    public INumberAdapter subtract (INumberAdapter f)
    {
        BigInteger thisNumerator = this.numerator.multiply(((Fraction) f).getDenominator());
        BigInteger aNumerator = ((Fraction) f).getNumerator().multiply(this.denominator);

        BigInteger newNumerator = thisNumerator.subtract(aNumerator);
        BigInteger newDenominator = this.denominator.multiply(((Fraction) f).getDenominator());

        return new Fraction(newNumerator, newDenominator, true);
    }

    public INumberAdapter multiply (INumberAdapter f)
    {
        BigInteger newNumerator = this.numerator.multiply(((Fraction) f).getNumerator());
        BigInteger newDenominator = this.denominator.multiply(((Fraction) f).getDenominator());

        return new Fraction(newNumerator, newDenominator, true);
    }

    public INumberAdapter divide (INumberAdapter f)
    {
        BigInteger newNumerator = this.numerator.multiply(((Fraction) f).getDenominator());
        BigInteger newDenominator = this.denominator.multiply(((Fraction) f).getNumerator());

        return new Fraction(newNumerator, newDenominator, true);
    }

    public Fraction ZERO()
    {
        return ZERO;
    }

    public Fraction ONE()
    {
        return ONE;
    }

    public Fraction getValue() {
        return null;
    }

    public boolean isZero() {
        return this.equals(ZERO);
    }

    public int intValue() {
        return this.numerator.divide(this.denominator).intValue();
    }

    public long longValue() {
        return this.numerator.divide(this.denominator).longValue();
    }

    public float floatValue() {
        return this.numerator.divide(this.denominator).floatValue();
    }

    public double doubleValue() {
        return this.numerator.divide(this.denominator).doubleValue();
    }

    public int compareTo(Object o)
    {
        if(!(o instanceof Fraction))
        {
            return 0;
        }
        else
        {
            Fraction f = (Fraction) o;

            int numeratorDiff = this.numerator.compareTo(f.getNumerator());
            int denominatorDiff = this.denominator.compareTo(f.getDenominator());

            return numeratorDiff + denominatorDiff;
        }
    }

    @Override
    public String toString() {
        return this.numerator + " / " + this.denominator;
    }

    public BigInteger getNumerator() {
        return numerator;
    }

    public BigInteger getDenominator() {
        return denominator;
    }
}
