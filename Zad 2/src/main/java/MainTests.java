import Adapters.FloatAdapter;
import Matrix.MyMatrix;
import Utilities.CollectionUtilities;


public class MainTests {

    public static void main(String[] args) {

        int i = 4;

        FloatAdapter adapter = new FloatAdapter();

        Float[][] A = new Float[][]{
                {1f, 2f, 3f},
                {4f, 5f, 6f},
                {7f, 8f, 9f}
        };

        Float[] X = new Float[]{11f, 12f, 13f};

//        Double[] Xp1 = MyMatrix.GaussJordanElimination_NoPivoting(adapter, A, X);
//        Double[] Xp2 = MyMatrix.GaussJordanElimination_PartialPivoting(adapter, A, X);
        Float[] Xp3 = MyMatrix.GaussJordanElimination_FullPivoting(adapter, A, X);

        CollectionUtilities.show(A, X);

    }
}
