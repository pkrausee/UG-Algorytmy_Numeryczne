public class DoubleAdapter extends Number implements IUsableNumber<DoubleAdapter>
{
    private final Double value;

    public final static DoubleAdapter ZERO = new DoubleAdapter(0d);
    public final static DoubleAdapter ONE = new DoubleAdapter(1d);

    public DoubleAdapter(Double value)
    {
        this.value = value;
    }

    public DoubleAdapter add(DoubleAdapter n)
    {
        return new DoubleAdapter(this.value + n.value);
    }

    public DoubleAdapter subtract(DoubleAdapter n)
    {
        return new DoubleAdapter(this.value - n.value);
    }

    public DoubleAdapter multiply(DoubleAdapter n)
    {
        return new DoubleAdapter(this.value * n.value);
    }

    public DoubleAdapter divide(DoubleAdapter n)
    {
        return new DoubleAdapter(this.value / n.value);
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

    public int intValue() {
        return this.value.intValue();
    }

    public long longValue() {
        return this.value.longValue();
    }

    public float floatValue() {
        return this.value.floatValue();
    }

    public double doubleValue() {
        return this.value;
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
        return String.format("%1.1f", this.value);
    }

    public Double getValue() {
        return value;
    }
}
