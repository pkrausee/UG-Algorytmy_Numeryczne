package MatrixOperations;

import Model.Recommendation;
import Utilities.CollectionUtils;
import Utilities.Generator;
import Utilities.MathUtils;

import java.util.ArrayList;
import java.util.List;

public class Recommend {
    private static final Double[] ratings = new Double[] {1.0, 2.0, 3.0, 4.0, 5.0};

    public static Double[][] calculate_ALS (double lambda, int vectorSize, int iterations) {
        Double[][] R = new Double[][] {
                {1d, 5d, 0d, 2d},
                {2d, 3d, 1d, 3d},
                {3d, 0d, 1d, 3d},
        };
        CollectionUtils.showRecommendationMatrix(R);

        Double[][] U = Generator.generateMatrix(0.01, 0.99, vectorSize, R.length);
        Double[][] P = Generator.generateMatrix(0.01, 0.99, vectorSize, R[0].length);

        Double[][] lambda_E = MathUtils.multiply(lambda, Generator.unitMatrix(vectorSize));

        for(int i = 0; i < iterations; i++) {

            for(int u = 0; u < U.length; u++) {
                List<Integer> Iu = get_Iu(R, u);

                Double[][] P_Iu = get_P_Iu(P, Iu);
                Double[][] P_Iu_T = MathUtils.transpose(P_Iu);

                Double[][] Au = MathUtils.add(MathUtils.multiply(P_Iu, P_Iu_T), lambda_E);
                Double[] Vu = get_Vu(R, P, u, Iu);

                Double[] gaussResult = Gauss.FullPivoting(Au, Vu);

                CollectionUtils.paste(gaussResult, U, u);
            }

            for(int p = 0; p < P[0].length; p++) {
                List<Integer> Ip = get_Ip(R, p);

                Double[][] U_Ip = get_U_Ip(U, Ip);
                Double[][] U_Ip_T = MathUtils.transpose(U_Ip);

                Double[][] Bp = MathUtils.add(MathUtils.multiply(U_Ip, U_Ip_T), lambda_E);
                Double[] Wp = get_Wp(R, U, p, Ip);

                Double[] gaussResult = Gauss.FullPivoting(Bp, Wp);

                CollectionUtils.paste(gaussResult, P, p);
            }

        }

        return MathUtils.multiply(MathUtils.transpose(U), P);
    }

    private static Double[][] create_R (List<Recommendation> recommendations) {
        int users = 3;
        int products = 5;

        Double[][] R = new Double[users][products];

        for(Recommendation r : recommendations) {
            R[r.getProductId() - 1][r.getUserId() - 1] = r.getRating();
        }

        return R;
    }

    private static List<Integer> get_Iu(Double[][] R, Integer u) {
        List<Integer> Iu = new ArrayList<Integer>();

        for(int i = 0; i < R[0].length; i++) {
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

        for (Integer integer : Iu) {
            Double[] Pi = CollectionUtils.getCol(P, integer);
            Double rui = R[u][integer];

            Vu = MathUtils.add(Vu, MathUtils.multiply(rui, Pi));
        }

        return Vu;
    }

    private static Double[] get_Wp(Double[][] R, Double[][] U, int p, List<Integer> Ip) {
        Double[] Wp = Generator.createVector(U.length);

        for (Integer integer : Ip) {
            Double[] Ui = CollectionUtils.getCol(U, integer);
            Double rip = R[integer][p];

            Wp = MathUtils.add(Wp, MathUtils.multiply(rip, Ui));
        }

        return Wp;
    }
}
