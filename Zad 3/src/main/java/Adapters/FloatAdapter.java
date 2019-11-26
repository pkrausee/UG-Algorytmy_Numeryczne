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

    public Float abs(Float n) {
        return Math.abs(n);
    }

    public boolean isZero(Float f) {
        return f == 0f;
    }

    public int compareTo(Float n1, Float n2) {
        return n1.compareTo(n2);
    }

    public String toString(Float f) {
        return String.format("%1.2f", f);
    }

    public Float[] copy(Float[] a) {
        Float[] copy = new Float[a.length];
        System.arraycopy(a, 0, copy, 0, a.length);

        return copy;
    }

    public Float[][] copy(Float[][] a) {
        Float[][] copy = new Float[a.length][a[0].length];

        for (int i = 0; i < a.length; i++) {
            System.arraycopy(a[i], 0, copy[i], 0, a[i].length);
        }

        return copy;
    }

    public Float getInstance() {
        return 0f;
    }

    public Float[] getArrInstance(int size) {
        return new Float[size];
    }

    public Float[][] getMatrixInstance(int size) {
        return new Float[size][size];
    }
}
