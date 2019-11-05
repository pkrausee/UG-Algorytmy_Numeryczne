package Adapters;

public class FloatAdapter implements INumberAdapter<Float> {
    public final static Float ZERO = 0f;
    public final static Float ONE = 1f;

    public Float add(Float n1, Float n2) {
        return n1 + n2;
    }

    public Float subtract(Float n1, Float n2) {
        return n1 - n2;
    }

    public Float multiply(Float n1, Float n2) {
        return n1 * n2;
    }

    public Float divide(Float n1, Float n2) {
        return n1 / n2;
    }

    public Float pow(Float n, int e) {
        return (float) Math.pow(n, e);
    }

    public Float sqrt(Float n) {
        return (float) Math.sqrt(n);
    }

    public Float ZERO() {
        return ZERO;
    }

    public Float ONE() {
        return ONE;
    }

    public boolean isZero(Float f) {
        return f.compareTo(ZERO) == 0;
    }

    public int compareTo(Float n1, Float n2) {
        return n1.compareTo(n2);
    }

    public String toString(Float f) {
        return String.format("%1.2f", f);
    }
}
