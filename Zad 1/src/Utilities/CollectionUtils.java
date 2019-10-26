// Paweł Krause - 253983 - Algorytmy numeryczne - Zadanie 1

package Utilities;

import java.util.ArrayList;
import java.util.List;


public class CollectionUtils
{
    public static double sum (List<Double> collection)
    {
        double result = 0.0;

        for (double n: collection)
        {
            result += n;
        }

        return result;
    }

    public static double sum_l (List<Double> collection)
    {
        double result = 0.0;

        for (int i = collection.size() - 1; i >= 0; i--)
        {
            result += collection.get(i);
        }

        return result;
    }

    public static List<Double> generateRanges(double min, double max, int negativeNumbers, int positiveNumbers)
    {
        double positiveInc = max/positiveNumbers;
        double negativeInc = Math.abs(min/negativeNumbers);

        var rangeList = new ArrayList<Double>();

        for (int i = -negativeNumbers; i <= 0; i++)
        {
            double e = i * negativeInc;
            rangeList.add(e);
        }

        for (int i = 0; i <= positiveNumbers; i++)
        {
            double e = i * positiveInc;
            rangeList.add(e);
        }

        return rangeList;
    }
}
