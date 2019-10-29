package Adapters;

import Models.Fraction;

public class FractionAdapter implements INumberAdapter<Fraction>
{
    private final Fraction value;

    public final static FractionAdapter ZERO = new FractionAdapter(new Fraction(0));
    public final static FractionAdapter ONE = new FractionAdapter(new Fraction(1));

    public FractionAdapter(Fraction value)
    {
        this.value = value;
    }

    public INumberAdapter add(INumberAdapter n)
    {
        return new FractionAdapter(value.add((Fraction) n.getValue()));
    }

    public INumberAdapter subtract(INumberAdapter n)
    {
        return new FractionAdapter(value.subtract((Fraction) n.getValue()));
    }

    public INumberAdapter multiply(INumberAdapter n)
    {
        return new FractionAdapter(value.multiply((Fraction) n.getValue()));
    }

    public INumberAdapter divide(INumberAdapter n)
    {
        return new FractionAdapter(value.divide((Fraction) n.getValue()));
    }

    public FractionAdapter ZERO()
    {
        return ZERO;
    }

    public FractionAdapter ONE()
    {
        return ONE;
    }

    public boolean isZero()
    {
        return this.value.isZero();
    }

    public int compareTo(Object o)
    {
        if(!(o instanceof FractionAdapter))
        {
            return 0;
        }
        else
        {
            FractionAdapter f = (FractionAdapter) o;

            return this.value.compareTo(f.getValue());
        }
    }

    @Override
    public String toString()
    {
        return "" + this.value;
    }

    public Fraction getValue()
    {

        return value;
    }
}
