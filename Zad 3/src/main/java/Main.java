import MatrixOperations.Recommend;

public class Main {
    public static void main(String[] args) {
        Recommend.calculate_ALS(0.1, 3, 10);

//        Double[][] P_I1 = new Double[][] {
//                {3.5, 3.0},
//                {1.5, 2.0},
//                {5.0, 4.0}
//        };
//        Double[][] P_I1_T = MatrixUtilities.transpose(P_I1);
//
//        Double[][] P_I1_multi_P_I1_T = MatrixUtilities.multiply(P_I1, P_I1_T);
//
//        Double[][] unit = MatrixUtilities.multiply(0.1, MatrixGenerator.unitMatrix(3));
//
//        Double[][] A1 = MatrixUtilities.add(P_I1_multi_P_I1_T, unit);
//
//        Double[] V1 = new Double[] {22.75, 10.5, 31.0};
//
//        Double[] U1 = Gauss.GaussElimination_FullPivoting(A1, V1);
//
//        CollectionUtilities.show(U1);
    }
}
