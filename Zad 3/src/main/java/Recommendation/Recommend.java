package Recommendation;

import AmazonMetaFile.CsvHelper;
import MatrixOperations.Gauss;
import MatrixOperations.MathUtils;
import Model.Recommendation;
import Model.Triple;
import Utilities.CollectionUtils;
import Utilities.Generator;

import java.util.ArrayList;
import java.util.List;

public class Recommend {
    public static void check_Implementation() {
        int[] testIndexes = new int[]{2, 3, 2, 4, 4, 9, 8, 5, 2, 1};

        double[][] train = new double[][]{
                {5, 5, 5, 5, 5, 5, 5, 5, 5, 5},
                {4, 4, 4, 4, 4, 0, 4, 4, 4, 4},
                {0, 5, 5, 5, 5, 5, 0, 5, 5, 5},
                {0, 5, 5, 5, 5, 5, 0, 0, 5, 5},
                {0, 5, 5, 5, 5, 5, 0, 0, 5, 5},
                {5, 4, 4, 4, 4, 4, 5, 5, 4, 4},
                {5, 5, 5, 5, 4, 5, 5, 5, 5, 5},
                {0, 5, 5, 5, 5, 5, 0, 0, 5, 5},
                {0, 1, 2, 3, 1, 2, 0, 0, 1, 2},
                {4, 5, 5, 5, 5, 5, 4, 4, 5, 5}
        };

        double[][] test = Generator.createMatrix(train.length, train[0].length);

        for (int i = 0; i < train.length; i++) {
            test[i][testIndexes[i]] = train[i][testIndexes[i]];
            train[i][testIndexes[i]] = 0;
        }

        double[][] result = ALS(train, 0.1, 3, 100).getFirst();

        double[] diff = new double[test.length];

        for (int i = 0; i < train.length; i++) {
            diff[i] = test[i][testIndexes[i]] - result[i][testIndexes[i]];
        }

        System.out.println("Input");
        CollectionUtils.showRecommendationMatrix(train);

        System.out.println("Output");
        CollectionUtils.showRecommendationMatrix(result);

        System.out.println("Difference on test indexes");
        CollectionUtils.show(diff);
    }

    public static Triple<double[][], List<Double>, Long> ALS(double[][] R, double lambda, int d, int iterations) {
        double[][] P = Generator.generateMatrix(0, 1, d, R[0].length);
        double[][] U = Generator.generateMatrix(0, 1, d, R.length);
        double[][] lambda_E = MathUtils.multiply(lambda, Generator.unitMatrix(d));

        long startTime = System.nanoTime();
        List<Double> f_UP_Changes = new ArrayList<>();

        for (int i = 0; i < iterations; i++) {

            for (int u = 0; u < R.length; u++) {
                List<Integer> Iu = get_Iu(R, u);

                double[][] P_Iu = get_P_Iu(P, Iu);
                double[][] P_Iu_T = MathUtils.transpose(P_Iu);

                double[][] Au = MathUtils.add(MathUtils.multiply(P_Iu, P_Iu_T), lambda_E);
                double[] Vu = get_Vu(R, P, u, Iu);

                double[] gaussResult = Gauss.PartialPivoting(Au, Vu);

                CollectionUtils.paste(gaussResult, U, u);
            }

            for (int p = 0; p < R[0].length; p++) {
                List<Integer> Ip = get_Ip(R, p);

                double[][] U_Ip = get_U_Ip(U, Ip);
                double[][] U_Ip_T = MathUtils.transpose(U_Ip);

                double[][] Bp = MathUtils.add(MathUtils.multiply(U_Ip, U_Ip_T), lambda_E);
                double[] Wp = get_Wp(R, U, p, Ip);

                double[] gaussResult = Gauss.PartialPivoting(Bp, Wp);

                CollectionUtils.paste(gaussResult, P, p);
            }

            f_UP_Changes.add(get_f_UP(R, P, U, lambda));
        }

        return new Triple<>(MathUtils.multiply(MathUtils.transpose(U), P), f_UP_Changes, System.nanoTime() - startTime);
    }

    public static double[][] createR_FromAmazonMetaFile(String filename) {
        Triple<List<Recommendation>, Integer, Integer> readFromCsvResult = CsvHelper.readFromCsv(filename);

        int users = readFromCsvResult.getSecond();
        int products = readFromCsvResult.getThird();
        List<Recommendation> recommendations = readFromCsvResult.getFirst();

        double[][] R = Generator.createMatrix(users, products);

        for (Recommendation r : recommendations) {
            R[Integer.parseInt(r.getUserId())][r.getProductId()] = r.getRating();
        }

        return R;
    }

    private static List<Integer> get_Iu(double[][] R, Integer u) {
        List<Integer> Iu = new ArrayList<>();

        for (int i = 0; i < R[0].length; i++) {
            if (R[u][i] != 0) {
                Iu.add(i);
            }
        }

        return Iu;
    }

    private static List<Integer> get_Ip(double[][] R, Integer p) {
        List<Integer> Ip = new ArrayList<>();

        for (int i = 0; i < R.length; i++) {
            if (R[i][p] != 0) {
                Ip.add(i);
            }
        }

        return Ip;
    }

    private static double[][] get_P_Iu(double[][] P, List<Integer> Iu) {
        double[][] P_Iu = new double[P.length][Iu.size()];

        for (int i = 0; i < Iu.size(); i++) {
            CollectionUtils.copy(P, P_Iu, Iu.get(i), i);
        }

        return P_Iu;
    }

    private static double[][] get_U_Ip(double[][] U, List<Integer> Ip) {
        double[][] U_Ip = new double[U.length][Ip.size()];

        for (int i = 0; i < Ip.size(); i++) {
            CollectionUtils.copy(U, U_Ip, Ip.get(i), i);
        }

        return U_Ip;
    }

    private static double[] get_Vu(double[][] R, double[][] P, int u, List<Integer> Iu) {
        double[] Vu = Generator.createVector(P.length);

        for (Integer integer : Iu) {
            double[] Pi = CollectionUtils.getCol(P, integer);
            double rui = R[u][integer];

            Vu = MathUtils.add(Vu, MathUtils.multiply(rui, Pi));
        }

        return Vu;
    }

    private static double[] get_Wp(double[][] R, double[][] U, int p, List<Integer> Ip) {
        double[] Wp = Generator.createVector(U.length);

        for (Integer integer : Ip) {
            double[] Ui = CollectionUtils.getCol(U, integer);
            double rip = R[integer][p];

            Wp = MathUtils.add(Wp, MathUtils.multiply(rip, Ui));
        }

        return Wp;
    }

    public static double get_f_UP(double[][] R, double[][] P, double[][] U, double lambda) {
        // Objective function
        double result = 0;
        double tempResult = 0;

        for (int u = 0; u < U[0].length; u++) {
            for (int p = 0; p < P[0].length; p++) {
                if (R[u][p] != 0) {
                    double[] U_col = CollectionUtils.getCol(U, u);
                    double[] P_col = CollectionUtils.getCol(P, p);

                    double UT_multi_P = MathUtils.multiply(U_col, P_col);
                    double rup = R[u][p];

                    result += Math.pow(rup - UT_multi_P, 2);
                }
            }
        }

        for (int u = 0; u < U[0].length; u++) {
            tempResult += Math.pow(MathUtils.norm(CollectionUtils.getCol(U, u)), 2);
        }

        for (int p = 0; p < P[0].length; p++) {
            tempResult += Math.pow(MathUtils.norm(CollectionUtils.getCol(P, p)), 2);
        }

        return result + (tempResult * lambda);
    }
}
