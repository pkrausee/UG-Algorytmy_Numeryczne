public class FloatAdapter extends Number implements IUsableNumber<FloatAdapter>
{
    private final Float value;

    public final static FloatAdapter ZERO = new FloatAdapter(0f);
    public final static FloatAdapter ONE = new FloatAdapter(1f);

    public FloatAdapter(Float value)
    {
        this.value = value;
    }

    public FloatAdapter add(FloatAdapter n) {
        return new FloatAdapter(this.value + n.value);
    }

    public FloatAdapter subtract(FloatAdapter n) {
        return new FloatAdapter(this.value - n.value);
    }

    public FloatAdapter multiply(FloatAdapter n) {
        return new FloatAdapter(this.value * n.value);
    }

    public FloatAdapter divide(FloatAdapter n) {
        return new FloatAdapter(this.value / n.value);
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
