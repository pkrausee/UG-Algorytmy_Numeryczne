package Interpolation;

import Utilities.CollectionUtils;

public class Interpolation {
    public static double getResult(double[] xs, double[] ys, double[] vector, int point) {
        int diff = getDiff(xs, point);

        vector = CollectionUtils.addOnStart(vector, 0);
        vector = CollectionUtils.addOnEnd(vector, 0);

        double r1 = (Math.pow(point - xs[diff + 1], 3) / (xs[diff] - xs[diff + 1])
                - (point - xs[diff + 1]) * (xs[diff] - xs[diff + 1]))
                * vector[diff] / 6;

        double r2 = ((Math.pow(point - xs[diff], 3)) / (xs[diff] - xs[diff + 1])
                - (point - xs[diff]) * (xs[diff] - xs[diff + 1]))
                * vector[diff + 1] / 6;

        double r3 = (ys[diff] * (point - xs[diff + 1])
                - ys[diff + 1] * (point - xs[diff]))
                / (xs[diff] - xs[diff + 1]);

        return r1 - r2 + r3;
    }

    private static int getDiff(double[] xs, int point) {
        for (int i = 0; i < xs.length; i++) {
            if (point >= xs[i] && point <= xs[i + 1]) {
                return i;
            }
        }

        throw new IllegalArgumentException("Something went wrong :(");
    }
}
