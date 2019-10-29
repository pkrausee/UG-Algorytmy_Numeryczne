package Adapters;

public class FloatAdapter implements INumberAdapter<Float>
{
    private final Float value;

    public final static FloatAdapter ZERO = new FloatAdapter(0f);
    public final static FloatAdapter ONE = new FloatAdapter(1f);

    public FloatAdapter(Float value)
    {
        this.value = value;
    }

    public INumberAdapter add(INumberAdapter n)
    {
        return new FloatAdapter(this.value + ((Float) n.getValue()));
    }

    public INumberAdapter subtract(INumberAdapter n)
    {
        return new FloatAdapter(this.value - ((Float) n.getValue()));
    }

    public INumberAdapter multiply(INumberAdapter n)
    {
        return new FloatAdapter(this.value * ((Float) n.getValue()));
    }

    public INumberAdapter divide(INumberAdapter n)
    {
        return new FloatAdapter(this.value / ((Float) n.getValue()));
    }

    public FloatAdapter ZERO()
    {
        return ZERO;
    }

    public FloatAdapter ONE()
    {
        return ONE;
    }

    public boolean isZero()
    {
        return this.value.equals(ZERO.getValue());
    }

    public int compareTo(Object o)
    {
        if(!(o instanceof FloatAdapter))
        {
            return 0;
        }
        else
        {
            FloatAdapter f = (FloatAdapter) o;

            return this.value.compareTo(f.getValue());
        }
    }

    @Override
    public String toString()
    {
        return String.format("%1.2f", this.value);
    }

    public Float getValue()
    {
        return value;
    }
}
