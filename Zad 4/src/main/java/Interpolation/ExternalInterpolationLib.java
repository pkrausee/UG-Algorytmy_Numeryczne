package Interpolation;

import org.apache.commons.math3.analysis.interpolation.LinearInterpolator;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;

public class ExternalInterpolationLib {
    public static double getResult(double[] xs, double[] ys, int point) {
        LinearInterpolator interpolator = new LinearInterpolator();
        PolynomialSplineFunction estimateFunc = interpolator.interpolate(xs, ys);

        return estimateFunc.value(point);
    }
}
