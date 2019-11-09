package Adapters;

public class DoubleAdapter implements INumberAdapter<Double> {
    public final static Double ZERO = 0d;
    public final static Double ONE = 1d;

    @Override
    public Double add(Double n1, Double n2) {
        return n1 + n2;
    }

    @Override
    public Double subtract(Double n1, Double n2) {
        return n1 - n2;
    }

    @Override
    public Double multiply(Double n1, Double n2) {
        return n1 * n2;
    }

    @Override
    public Double divide(Double n1, Double n2) {
        return n1 / n2;
    }

    @Override
    public Double pow(Double n, int e) {
        return Math.pow(n, e);
    }

    @Override
    public Double sqrt(Double n) {
        return Math.sqrt(n);
    }

    @Override
    public Double ZERO() {
        return ZERO;
    }

    @Override
    public Double ONE() {
        return ONE;
    }

    @Override
    public Double abs(Double n) {
        return Math.abs(n);
    }

    @Override
    public boolean isZero(Double d) {
        return d == 0d;
    }

    @Override
    public int compareTo(Double n1, Double n2) {
        return n1.compareTo(n2);
    }

    public String toString(Double d) {
        return String.format("%1.2f", d);
    }

    @Override
    public Double[] copy(Double[] a) {
        Double[] copy = new Double[a.length];
        System.arraycopy( a, 0, copy, 0, a.length );

        return copy;
    }

    @Override
    public Double[][] copy(Double[][] a) {
        Double[][] copy = new Double[a.length][a[0].length];

        for(int i=0; i<a.length; i++){
            System.arraycopy(a[i], 0, copy[i], 0, a[i].length);
        }

        return copy;
    }

    @Override
    public Double getInstance() {
        return 0d;
    }

    @Override
    public Double[] getArrInstance(int size) {
        return new Double[size];
    }

    @Override
    public Double[][] getMatrixInstance(int size) {
        return new Double[size][size];
    }
}
