package MatrixOperations;

import Model.Recommendation;
import Utilities.CollectionUtils;
import Utilities.Generator;
import Utilities.MathUtils;
import Utilities.MockGenerator;

import java.util.ArrayList;
import java.util.List;

public class Recommend {
    private static final int users = 3; // TODO: This should be deleted - get user count from input matrix.
    private static final int products = 4; // TODO: Same here - get products count from input matrix.
    private static final Double[] ratings = new Double[] {1.0, 2.0, 3.0, 4.0, 5.0};

    public static void calculate_ALS (double lambda, int vectorSize, int iterations) {
//        List<Recommendation> recommendations = MockGenerator.mockRecommendations(ratings, users, products);

        // TODO: Try adding more info about ratings..
        Double[][] input = new Double[][] {
                {1d, 5d, 0d, 2d},
                {2d, 0d, 1d, 3d},
                {3d, 0d, 1d, 3d},
        };
        CollectionUtils.showRecommendationMatrix(input);

        Double[][] R = CollectionUtils.copy(input);

//        Double[][] U = Generator.generateMatrix(0, 1, vectorSize, users);
//        Double[][] P = Generator.generateMatrix(0, 1, vectorSize, products);

        Double[][] U = new Double[][] {
                {0.74, 0.6, 0.92},
                {0.04, 0.22, 0.05},
                {0.52, 0.03, 0.45},
        };

        Double[][] P = new Double[][] {
                {0.38, 0.6, 0.69, 0.45},
                {0.14, 0.76, 0.1, 0.07},
                {0.24, 0.95, 0.87, 0.37},
        };

        Double[][] lambda_E = MathUtils.multiply(lambda, Generator.unitMatrix(vectorSize));

        // TODO: It turns out that 100 iterations is the most optimal number here.
        for(int i = 0; i < iterations; i++) {
            for(int u = 0; u < U.length; u++) {
                List<Integer> Iu = get_Iu(R, u);

                Double[][] P_Iu = get_P_Iu(P, Iu);
                Double[][] P_Iu_T = MathUtils.transpose(P_Iu);

                Double[][] Au = MathUtils.add(MathUtils.multiply(P_Iu, P_Iu_T), lambda_E);
                Double[] Vu = get_Vu(R, P, u, Iu);

                Double[] gaussResult = Gauss.GaussElimination_FullPivoting(Au, Vu);

                CollectionUtils.paste(gaussResult, U, u);
            }

            for(int p = 0; p < P[0].length; p++) {
                List<Integer> Ip = get_Ip(R, p);

                Double[][] U_Ip = get_U_Ip(U, Ip);
                Double[][] U_Ip_T = MathUtils.transpose(U_Ip);

                Double[][] Bp = MathUtils.add(MathUtils.multiply(U_Ip, U_Ip_T), lambda_E);
                Double[] Wp = get_Wp(R, U, p, Ip);

                Double[] gaussResult = Gauss.GaussElimination_FullPivoting(Bp, Wp);

                CollectionUtils.paste(gaussResult, P, p);
            }
//        R = MathUtils.multiply(U, P);

//        CollectionUtils.show(U);
//        CollectionUtils.show(P);
        }
        
        R = MathUtils.multiply(U, P);

        CollectionUtils.showRecommendationMatrix(R);
    }

    private static Double[][] create_R (List<Recommendation> recommendations) {
        Double[][] R = new Double[products][users];

        for(Recommendation r : recommendations) {
            R[r.getProductId() - 1][r.getUserId() - 1] = r.getRating();
        }

        return R;
    }

    private static List<Integer> get_Iu(Double[][] R, Integer u) {
        List<Integer> Iu = new ArrayList<Integer>();

        for(int i = 0; i < R[u].length; i++) {
            if (R[u][i] != 0d) {
                Iu.add(i);
            }
        }

        return Iu;
    }

    private static List<Integer> get_Ip(Double[][] R, Integer p) {
        List<Integer> Ip = new ArrayList<Integer>();

        for(int i = 0; i < R.length; i++) {
            if (R[i][p] != 0d) {
                Ip.add(i);
            }
        }

        return Ip;
    }

    private static Double[][] get_P_Iu(Double[][] P, List<Integer> Iu) {
        Double[][] P_Iu = new Double[P.length][Iu.size()];

        for(int i = 0; i < Iu.size(); i++) {
            CollectionUtils.copy(P, P_Iu, Iu.get(i), i);
        }

        return P_Iu;
    }

    private static Double[][] get_U_Ip(Double[][] U, List<Integer> Ip) {
        Double[][] U_Ip = new Double[U.length][Ip.size()];

        for(int i = 0; i < Ip.size(); i++) {
            CollectionUtils.copy(U, U_Ip, Ip.get(i), i);
        }

        return U_Ip;
    }

    private static Double[] get_Vu(Double[][] R, Double[][] P, int u, List<Integer> Iu) {
        Double[] Vu = Generator.createVector(P.length);

        for(int i = 0; i < Iu.size(); i++) {
            Double[] Pi = CollectionUtils.getCol(P, i);
            Double rui = R[u][Iu.get(i)];

            Vu = MathUtils.add(Vu, MathUtils.multiply(rui, Pi));
        }

        return Vu;
    }

    private static Double[] get_Wp(Double[][] R, Double[][] U, int p, List<Integer> Ip) {
        Double[] Wp = Generator.createVector(U.length);

        for(int i = 0; i < Ip.size(); i++) {
            Double[] Ui = CollectionUtils.getCol(U, i);
            Double rip = R[Ip.get(i)][p];

            Wp = MathUtils.add(Wp, MathUtils.multiply(rip, Ui));
        }

        return Wp;
    }
}
