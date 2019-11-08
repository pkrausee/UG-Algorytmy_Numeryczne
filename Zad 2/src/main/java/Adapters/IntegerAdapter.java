package Adapters;

public class IntegerAdapter implements INumberAdapter<Integer> {
    public final static Integer ZERO = 0;
    public final static Integer ONE = 1;

    public Integer add(Integer n1, Integer n2) {
        return n1 + n2;
    }

    public Integer subtract(Integer n1, Integer n2) {
        return n1 - n2;
    }

    public Integer multiply(Integer n1, Integer n2) {
        return n1 * n2;
    }

    public Integer divide(Integer n1, Integer n2) {
        return n1 / n2;
    }

    public Integer pow(Integer n, int e) {
        return (int) Math.pow(n, e);
    }

    public Integer sqrt(Integer n) {
        return (int) Math.sqrt(n);
    }

    public Integer ZERO() {
        return ZERO;
    }

    public Integer ONE() {
        return ONE;
    }

    public Integer abs(Integer n) {
        return Math.abs(n);
    }

    public boolean isZero(Integer i) {
        return i.compareTo(ZERO) == 0;
    }

    public int compareTo(Integer n1, Integer n2) {
        return n1.compareTo(n2);
    }

    public String toString(Integer i) {
        return i.toString();
    }

    public Integer[] copy(Integer[] a) {
        Integer[] copy = new Integer[a.length];
        System.arraycopy( a, 0, copy, 0, a.length );

        return copy;
    }

    public Integer[][] copy(Integer[][] a) {
        Integer[][] copy = new Integer[a.length][a[0].length];

        for(int i=0; i<a.length; i++){
            System.arraycopy(a[i], 0, copy[i], 0, a[i].length);
        }

        return copy;
    }
}
