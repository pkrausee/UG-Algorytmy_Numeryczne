package Adapters;

public class DoubleAdapter implements INumberAdapter<Double>
{
    private final Double value;

    public final static DoubleAdapter ZERO = new DoubleAdapter(0d);
    public final static DoubleAdapter ONE = new DoubleAdapter(1d);

    public DoubleAdapter(Double value)
    {
        this.value = value;
    }

    public INumberAdapter add(INumberAdapter n)
    {
        return new DoubleAdapter(this.value + ((Double) n.getValue()));
    }

    public INumberAdapter subtract(INumberAdapter n)
    {
        return new DoubleAdapter(this.value - ((Double) n.getValue()));
    }

    public INumberAdapter multiply(INumberAdapter n)
    {
        return new DoubleAdapter(this.value * ((Double) n.getValue()));
    }

    public INumberAdapter divide(INumberAdapter n)
    {
        return new DoubleAdapter(this.value / ((Double) n.getValue()));
    }

    public DoubleAdapter ZERO()
    {
        return ZERO;
    }

    public DoubleAdapter ONE()
    {
        return ONE;
    }

    public boolean isZero() {
        return this.value.equals(ZERO.getValue());
    }

    public int compareTo(Object o) {
        if(!(o instanceof DoubleAdapter))
        {
            return 0;
        }
        else
        {
            DoubleAdapter d = (DoubleAdapter) o;

            return this.value.compareTo(d.getValue());
        }
    }

    @Override
    public String toString() {
        return String.format("%1.2f", this.value);
    }

    public Double getValue() {
        return value;
    }
}
