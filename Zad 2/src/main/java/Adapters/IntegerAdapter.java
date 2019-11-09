package Adapters;

public class IntegerAdapter implements INumberAdapter<Integer> {
    public final static Integer ZERO = 0;
    public final static Integer ONE = 1;

    @Override
    public Integer add(Integer n1, Integer n2) {
        return n1 + n2;
    }

    @Override
    public Integer subtract(Integer n1, Integer n2) {
        return n1 - n2;
    }

    @Override
    public Integer multiply(Integer n1, Integer n2) {
        return n1 * n2;
    }

    @Override
    public Integer divide(Integer n1, Integer n2) {
        return n1 / n2;
    }

    @Override
    public Integer pow(Integer n, int e) {
        return (int) Math.pow(n, e);
    }

    @Override
    public Integer sqrt(Integer n) {
        return (int) Math.sqrt(n);
    }

    @Override
    public Integer ZERO() {
        return ZERO;
    }

    @Override
    public Integer ONE() {
        return ONE;
    }

    @Override
    public Integer abs(Integer n) {
        return Math.abs(n);
    }

    @Override
    public boolean isZero(Integer i) {
        return i.compareTo(ZERO) == 0;
    }

    @Override
    public int compareTo(Integer n1, Integer n2) {
        return n1.compareTo(n2);
    }

    public String toString(Integer i) {
        return i.toString();
    }

    @Override
    public Integer[] copy(Integer[] a) {
        Integer[] copy = new Integer[a.length];
        System.arraycopy( a, 0, copy, 0, a.length );

        return copy;
    }

    @Override
    public Integer[][] copy(Integer[][] a) {
        Integer[][] copy = new Integer[a.length][a[0].length];

        for(int i=0; i<a.length; i++){
            System.arraycopy(a[i], 0, copy[i], 0, a[i].length);
        }

        return copy;
    }

    @Override
    public Integer getInstance() {
        return 0;
    }

    @Override
    public Integer[] getArrInstance(int size) {
        return new Integer[0];
    }

    @Override
    public Integer[][] getMatrixInstance(int size) {
        return new Integer[size][size];
    }
}
