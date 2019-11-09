import Adapters.DoubleAdapter;
import Matrix.MatrixGenerator;
import Matrix.MatrixUtilities;
import Matrix.MyMatrixV2;
import Utilities.CollectionUtilities;


public class MainTests {

    public static void main(String[] args) {

        int i = 5;

        DoubleAdapter adapter = new DoubleAdapter();

        Double[][] A = new Double[][]{
                { 0.947296142578125, -0.3624114990234375, 0.386505126953125 },
                { -0.47930908203125, -0.723846435546875, 0.598419189453125 },
                { 0.4524688720703125, -0.8554534912109375, 0.650390625 }
        };

        Double[] X = new Double[]{ -0.48309326171875, 0.4238433837890625, -0.83563232421875 };

//        MatrixGenerator.generateValues(A);
//        MatrixGenerator.generateValues(X);

        Double[] B = MatrixUtilities.multiplyByVector(adapter, A, X);

        CollectionUtilities.show(A, X);
        CollectionUtilities.show(A, B);

        Double[] XpNP = MyMatrixV2.GaussJordanElimination_NoPivoting(adapter, A, B);
        Double[] XpPP = MyMatrixV2.GaussJordanElimination_PartialPivoting(adapter, A, B);
        Double[] XpFP = MyMatrixV2.GaussJordanElimination_FullPivoting(adapter, A, B);

        Double[] XpNP_ErrorArr = MatrixUtilities.subtractAbs(adapter, X, XpNP);
        Double[] XpPP_ErrorArr = MatrixUtilities.subtractAbs(adapter, X, XpPP);
        Double[] XpFP_ErrorArr = MatrixUtilities.subtractAbs(adapter, X, XpFP);

        CollectionUtilities.show(X);
        CollectionUtilities.show(XpNP_ErrorArr);
        CollectionUtilities.show(XpPP_ErrorArr);
        CollectionUtilities.show(XpFP_ErrorArr);

        Double XpNP_Er = MatrixUtilities.avg(adapter, XpNP_ErrorArr);
        Double XpPP_Er = MatrixUtilities.avg(adapter, XpPP_ErrorArr);
        Double XpFP_Er = MatrixUtilities.avg(adapter, XpFP_ErrorArr);

        System.out.println("Error " + XpNP_Er);
        System.out.println("Error " + XpPP_Er);
        System.out.println("Error " + XpFP_Er);

    }
}
