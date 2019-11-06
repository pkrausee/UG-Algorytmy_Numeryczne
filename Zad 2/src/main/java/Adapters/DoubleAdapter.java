package Adapters;

public class DoubleAdapter implements INumberAdapter<Double> {
    public final static Double ZERO = 0d;
    public final static Double ONE = 1d;

    public Double add(Double n1, Double n2) {
        return n1 + n2;
    }

    public Double subtract(Double n1, Double n2) {
        return n1 - n2;
    }

    public Double multiply(Double n1, Double n2) {
        return n1 * n2;
    }

    public Double divide(Double n1, Double n2) {
        return n1 / n2;
    }

    public Double pow(Double n, int e) {
        return Math.pow(n, e);
    }

    public Double sqrt(Double n) {
        return Math.sqrt(n);
    }

    public Double ZERO() {
        return ZERO;
    }

    public Double ONE() {
        return ONE;
    }

    public boolean isZero(Double d) {
        return d.compareTo(ZERO) == 0;
    }

    public int compareTo(Double n1, Double n2) {
        return n1.compareTo(n2);
    }

    public String toString(Double d) {
        return String.format("%1.2f", d);
    }

    public Double[] copy(Double[] a) {
        Double[] copy = new Double[a.length];
        System.arraycopy( a, 0, copy, 0, a.length );

        return copy;
    }

    public Double[][] copy(Double[][] a) {
        Double[][] copy = new Double[a.length][a[0].length];

        for(int i=0; i<a.length; i++){
            System.arraycopy(a[i], 0, copy[i], 0, a[i].length);
        }

        return copy;
    }
}
