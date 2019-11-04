import Adapters.FractionAdapter;

import Matrix.MyMatrix;

import Models.Fraction;
import Models.PairResult;

import Utilities.CollectionUtilities;


public class FractionTest
{
    public static void main(String[] args)
    {
        Fraction[][] A =
                {
                        {new Fraction(1), new Fraction(2), new Fraction(3)},
                        {new Fraction(4), new Fraction(5), new Fraction(6)},
                        {new Fraction(7), new Fraction(8), new Fraction(9)}
                };

        Fraction[] B = { new Fraction(10), new Fraction(11), new Fraction(12)};

        Character[] X = { 'X', 'Y', 'Z' };

        MyMatrix myMatrix = new MyMatrix<Fraction>(new FractionAdapter(), A, B, X);

        CollectionUtilities.show(A, B);

        PairResult result = myMatrix.GaussJordanElimination_FullPivoting();

        Fraction[][] AResult = (Fraction[][]) result.First;
        Fraction[] BResult = (Fraction[]) result.Second;

        CollectionUtilities.show(AResult, BResult);
    }
}
