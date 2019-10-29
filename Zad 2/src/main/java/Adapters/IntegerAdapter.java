package Adapters;

public class IntegerAdapter implements INumberAdapter<Integer>
{
    private final Integer value;

    public final static IntegerAdapter ZERO = new IntegerAdapter(0);
    public final static IntegerAdapter ONE = new IntegerAdapter(1);

    public IntegerAdapter(Integer value)
    {
        this.value = value;
    }

    public INumberAdapter add(INumberAdapter n)
    {
        return new IntegerAdapter(this.value + ((Integer) n.getValue()));
    }

    public INumberAdapter subtract(INumberAdapter n)
    {
        return new IntegerAdapter(this.value - ((Integer) n.getValue()));
    }

    public INumberAdapter multiply(INumberAdapter n)
    {
        return new IntegerAdapter(this.value * ((Integer) n.getValue()));
    }

    public INumberAdapter divide(INumberAdapter n)
    {
        return new IntegerAdapter(this.value / ((Integer) n.getValue()));
    }

    public IntegerAdapter ZERO()
    {
        return ZERO;
    }

    public IntegerAdapter ONE()
    {
        return ONE;
    }

    public boolean isZero()
    {
        return this.value.equals(ZERO.getValue());
    }

    public int compareTo(Object o)
    {
        if(!(o instanceof IntegerAdapter))
        {
            return 0;
        }
        else
        {
            IntegerAdapter d = (IntegerAdapter) o;

            return this.value.compareTo(d.getValue());
        }
    }

    @Override
    public String toString()
    {
        return "" + this.value;
    }

    public Integer getValue()
    {
        return value;
    }
}
