package Models;

public class FloatAdapter extends Number implements INumberAdapter<Float>
{
    private final Float value;

    public final static FloatAdapter ZERO = new FloatAdapter(0f);
    public final static FloatAdapter ONE = new FloatAdapter(1f);

    public FloatAdapter(Float value)
    {
        this.value = value;
    }

    public INumberAdapter add(INumberAdapter n) {
        return new FloatAdapter(this.value + ((Float) n.getValue()));
    }

    public INumberAdapter subtract(INumberAdapter n) {
        return new FloatAdapter(this.value - ((Float) n.getValue()));
    }

    public INumberAdapter multiply(INumberAdapter n) {
        return new FloatAdapter(this.value * ((Float) n.getValue()));
    }

    public INumberAdapter divide(INumberAdapter n) {
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

    public boolean isZero() {
        return this.equals(ZERO);
    }

    public int intValue() {
        return this.value.intValue();
    }

    public long longValue() {
        return this.value.longValue();
    }

    public float floatValue() {
        return this.value;
    }

    public double doubleValue() {
        return this.value.doubleValue();
    }

    public int compareTo(Object o) {
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
    public String toString() {
        return "" + this.value;
    }

    public Float getValue() {
        return value;
    }
}
