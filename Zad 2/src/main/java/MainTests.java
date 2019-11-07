import Adapters.DoubleAdapter;
import Adapters.FloatAdapter;
import Adapters.FractionAdapter;
import Matrix.MyMatrixV1;
import Matrix.MyMatrixV2;
import Models.Fraction;
import Utilities.CollectionUtilities;


public class MainTests {

    public static void main(String[] args) {

        int i = 4;

        DoubleAdapter adapter = new DoubleAdapter();

        Double[][] A = new Double[][]{
                { 1d, 2d, 3d },
                { 4d, 5d, 6d },
                { 7d, 8d, 9d }
        };

        Double[] B = new Double[]{ 11d, 12d, 13d };

//        FractionAdapter adapter = new FractionAdapter();
//
//        Fraction[][] A = new Fraction[][]{
//                { new Fraction(1), new Fraction(2), new Fraction(3) },
//                { new Fraction(4), new Fraction(5), new Fraction(6) },
//                { new Fraction(7), new Fraction(8), new Fraction(9) }
//        };
//
//        Fraction[] B = new Fraction[]{ new Fraction(11), new Fraction(12), new Fraction(13) };

        MyMatrixV2.GaussJordanElimination_NoPivoting(adapter, A, B);
        MyMatrixV2.GaussJordanElimination_PartialPivoting(adapter, A, B);
        MyMatrixV2.GaussJordanElimination_FullPivoting(adapter, A, B);

        CollectionUtilities.show(A, B);

    }
}
