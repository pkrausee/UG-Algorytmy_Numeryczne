import Adapters.DoubleAdapter;
import Adapters.FloatAdapter;
import Adapters.FractionAdapter;
import Matrix.MatrixGenerator;
import Matrix.MatrixUtilities;
import Matrix.MyMatrix;
import Models.Fraction;
import Utilities.CollectionUtilities;


public class MainTests {

    public static void main(String[] args) {

        int i = 4;

        Fraction[] X = new Fraction[i];
        Fraction[][] A = new Fraction[i][i];

        MatrixGenerator.generateValues(X);
        MatrixGenerator.generateValues(A);

        Fraction[] B = MatrixUtilities.multiplyByVector(Fraction.class, new FractionAdapter(), A, X);

        Fraction[][] Acopy = new FractionAdapter().copy(A);
        Fraction[] Bcopy = new FractionAdapter().copy(B);

        Fraction[] Xp = MyMatrix.GaussJordanElimination_NoPivoting(new FractionAdapter(), Acopy, Bcopy);

        double NPFail = MatrixUtilities.avg(MatrixUtilities.subtract(Fraction.class, new FractionAdapter(), X, Xp));

        CollectionUtilities.show(A, B);
        CollectionUtilities.show(Acopy, Bcopy);

        System.out.println(NPFail);
    }
}
