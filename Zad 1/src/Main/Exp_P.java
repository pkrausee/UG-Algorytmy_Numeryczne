package Main;

import Utilities.MathUtils;
import java.util.List;

public class Exp_P
{
    public static int exp_s(double x, double precision)
    {
        // compute every number separately

        int n = 1;
        double currentElement = MathUtils.power(x, n) / MathUtils.factorial(n);

        while(Math.abs(currentElement) > precision)
        {
            n++;
            currentElement = MathUtils.power(x, n) / MathUtils.factorial(n);
        }

        return n;
    }

    public static void exp_s(double x, double precision, List<Double> results)
    {
        // compute every number separately

        results.clear();
        results.add(1.0);

        int n = 1;
        double currentElement = MathUtils.power(x, n) / MathUtils.factorial(n);

        while(Math.abs(currentElement) > precision)
        {
            results.add(currentElement);

            n++;
            currentElement = MathUtils.power(x, n) / MathUtils.factorial(n);
        }
    }

    public static int exp_p(double x, double precision)
    {
        // compute every number using previous number in series

        int n = 0;
        double lastNumb = 1.0 * (x / (n + 1));

        while(Math.abs(lastNumb) > precision)
        {
            n++;
            lastNumb = lastNumb * (x / (n + 1));
        }

        return n;
    }

    public static void exp_p(double x, double precision, List<Double> results)
    {
        // compute every number using previous number in series

        results.clear();
        results.add(1.0);

        int n = 0;
        double lastNumb = 1.0 * (x / (n + 1));

        while(Math.abs(lastNumb) > precision)
        {
            results.add(lastNumb);

            n++;
            lastNumb = lastNumb * (x / (n + 1));
        }
    }
}
