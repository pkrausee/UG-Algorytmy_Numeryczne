package Matrix;

import Models.Fraction;

import java.math.BigInteger;
import java.util.Random;

public class MatrixGenerator {
    private final static Random random = new Random();
    private final static int pow = (int) Math.pow(2, 16);
    private final static int min = (int) (-pow);
    private final static int max = pow - 1;

    public static void generateValues(Double[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int r = min + (int)(Math.random() * ((max - min) + 1));

            arr[i] = (double) r / pow;
        }
    }

    public static void generateValues(Float[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int r = min + (int)(Math.random() * ((max - min) + 1));

            arr[i] = (float) r / pow;
        }
    }

    public static void generateValues(Fraction[] arr) {
        for (int i = 0; i < arr.length; i++) {
            BigInteger r = randomBigInteger();

            arr[i] = new Fraction(r, BigInteger.valueOf(pow));
        }
    }

    public static void generateValues(Double[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                int r = min + (int)(Math.random() * ((max - min) + 1));

                matrix[i][j] = (double) r / pow;
            }
        }
    }

    public static void generateValues(Float[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                int r = min + (int)(Math.random() * ((max - min) + 1));

                matrix[i][j] = (float) r / pow;
            }
        }
    }

    public static void generateValues(Fraction[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                BigInteger r = randomBigInteger();

                matrix[i][j] = new Fraction(r, BigInteger.valueOf(pow));
            }
        }
    }

    private static BigInteger randomBigInteger () {
        BigInteger minVal = BigInteger.valueOf(min);
        BigInteger maxVal = BigInteger.valueOf(max);

        BigInteger bigInteger = maxVal.subtract(minVal);

        int len = maxVal.bitLength();
        BigInteger res = new BigInteger(len, random);

        if (res.compareTo(minVal) < 0) {
            res = res.add(minVal);
        }

        if (res.compareTo(bigInteger) >= 0){
            res = res.mod(bigInteger).add(minVal);
        }

        return res;
    }
}
