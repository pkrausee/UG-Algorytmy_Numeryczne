// Paweł Krause - 253983 - Algorytmy numeryczne - Zadanie 1

package Utilities;

public class MathUtils
{
    public static double factorial(int number)
    {
        double result = 1.0;

        while(number > 0)
        {
            result *= number;

            number--;
        }

        return result;
    }

    public static double factorial_rec(int number)
    {
        if(number <= 1.0)
        {
            return 1.0;
        }

        return number * factorial_rec(number - 1);
    }

    public static double power(double number, int power)
    {
        double result = 1;

        while(power > 0)
        {
            result *= number;

            power--;
        }

        return result;
    }
}
