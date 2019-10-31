import Adapters.DoubleAdapter;
import Adapters.FractionAdapter;
import Matrix.MyMatrix;
import Models.Fraction;
import Models.Pair;
import Utilities.CollectionUtilities;
import Utilities.Parser;


public class Main
{
    public static void testDouble(String[] args)
    {
        Double[][] A =
                {
                        {1d, 2d, 3d},
                        {4d, 5d, 6d},
                        {7d, 8d, 9d}
                };

        char[] X = {'x', 'y', 'z'};

        Double[] B = {10.0, 11.0, 12.0};

        Pair result = MyMatrix.gauss(A, X, B);

        DoubleAdapter[][] AResult = (DoubleAdapter[][]) result.First;
        DoubleAdapter[] BResult = (DoubleAdapter[]) result.Second;

        CollectionUtilities.show(AResult, BResult);

        System.out.println(BResult[0].getValue() + " == " + new DoubleAdapter(-28d / 3).getValue() + " " + BResult[0].equals(new DoubleAdapter(-28d / 3)));
    }

    public static void main(String[] args)
    {
        Fraction[][] A =
                {
                        {new Fraction(1), new Fraction(2), new Fraction(3)},
                        {new Fraction(4), new Fraction(5), new Fraction(6)},
                        {new Fraction(7), new Fraction(8), new Fraction(9)}
                };

        char[] X = {'x', 'y', 'z'};

        Fraction[] B = {new Fraction(10), new Fraction(11), new Fraction(12)};

        CollectionUtilities.show(A, B);

        Pair result = MyMatrix.gauss(A, X, B);

        FractionAdapter[][] AResult = (FractionAdapter[][]) result.First;
        FractionAdapter[] BResult = (FractionAdapter[]) result.Second;

        CollectionUtilities.show(AResult, BResult);
    }
}
