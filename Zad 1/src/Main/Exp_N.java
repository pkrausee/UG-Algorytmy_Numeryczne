// Paweł Krause - 253983 - Algorytmy numeryczne - Zadanie 1

package Main;

import Utilities.MathUtils;
import java.util.List;


public class Exp_N
{
    public static void exp_s(double x, int N, List<Double> results)
    {
        // compute every number separately

        results.clear();
        results.add(1.0);

        for(int n = 1; n < N; n++)
        {
            results.add(MathUtils.power(x, n) / MathUtils.factorial(n));
        }
    }

    public static void exp_p(double x, int N, List<Double> results)
    {
        // compute every number using previous number in series

        results.clear();
        results.add(1.0);

        double lastNumb = 1.0;
        for(int n = 0; n < N; n++)
        {
            lastNumb = lastNumb * (x / (n + 1));

            results.add(lastNumb);
        }
    }
}
