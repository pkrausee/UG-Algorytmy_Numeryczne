package Recommendation;

import MatrixOperations.Gauss;
import Model.Recommendation;
import Model.Triple;
import Utilities.CollectionUtils;
import Utilities.Generator;
import MatrixOperations.MathUtils;

import java.io.*;
import java.util.*;

public class Recommend {
    public static double[][] calculate_ALS(String group, Integer values, double lambda, int vectorSize, int iterations) {
        if(values == null) {
            values = 100;
        }

//        double[][] R = new double[][]{
//                {2,    3,    5,    5,    3,    5,    5,    5,    5,    5},
//                {4,    4,    4,    4,    4,    4,    4,    4,    4,    4},
//                {0,    2,    1,    3,    2,    1,    0,    0,    3,    2},
//                {0,    1,    5,    3,    5,    5,    0,    0,    5,    5},
//                {0,    5,    5,    5,    5,    5,    0,    0,    5,    5},
//                {5,    4,    4,    4,    4,    4,    5,    5,    4,    4},
//                {5,    5,    5,    5,    4,    5,    2,    5,    5,    5},
//                {0,    5,    5,    5,    5,    5,    0,    0,    5,    5},
//                {0,    3,    4,    4,    5,    1,    0,    0,    4,    4},
//                {4,    5,    5,    5,    5,    5,    4,    4,    5,    5}
//        };

        double R[][] = createR(group);
        CollectionUtils.showRecommendationMatrix(R);

        double[][] U = Generator.generateMatrix(0.01, 0.99, vectorSize, R.length);
        double[][] P = Generator.generateMatrix(0.01, 0.99, vectorSize, R[0].length);
//        CollectionUtils.showRecommendationMatrix(U);
//        CollectionUtils.showRecommendationMatrix(P);

        double[][] lambda_E = MathUtils.multiply(lambda, Generator.unitMatrix(vectorSize));

        for (int i = 0; i < iterations; i++) {

            for (int u = 0; u < U.length; u++) {
                List<Integer> Iu = get_Iu(R, u);

                double[][] P_Iu = get_P_Iu(P, Iu);
                double[][] P_Iu_T = MathUtils.transpose(P_Iu);

                double[][] Au = MathUtils.add(MathUtils.multiply(P_Iu, P_Iu_T), lambda_E);
                double[] Vu = get_Vu(R, P, u, Iu);

                double[] gaussResult = Gauss.FullPivoting(Au, Vu);

                CollectionUtils.paste(gaussResult, U, u);
            }

            for (int p = 0; p < P[0].length; p++) {
                List<Integer> Ip = get_Ip(R, p);

                double[][] U_Ip = get_U_Ip(U, Ip);
                double[][] U_Ip_T = MathUtils.transpose(U_Ip);

                double[][] Bp = MathUtils.add(MathUtils.multiply(U_Ip, U_Ip_T), lambda_E);
                double[] Wp = get_Wp(R, U, p, Ip);

                double[] gaussResult = Gauss.FullPivoting(Bp, Wp);

                CollectionUtils.paste(gaussResult, P, p);
            }

        }

        return MathUtils.multiply(MathUtils.transpose(U), P);
    }

    private static List<Integer> get_Iu(double[][] R, Integer u) {
        List<Integer> Iu = new ArrayList<>();

        for (int i = 0; i < R[0].length; i++) {
            if (R[u][i] != 0d) {
                Iu.add(i);
            }
        }

        return Iu;
    }

    private static List<Integer> get_Ip(double[][] R, Integer p) {
        List<Integer> Ip = new ArrayList<>();

        for (int i = 0; i < R.length; i++) {
            if (R[i][p] != 0d) {
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

    public static double f_U_P(double[][] R, double[][] P, double[][] U, double lambda) {
        // Objective function
        double result = 0;
        double tempResult = 0;

        for (int u = 0; u < U[0].length; u++) {
            for (int p = 0; p < P[0].length; p++) {
                if (R[u][p] != 0) {
                    double UT_multi_P = MathUtils.multiply(CollectionUtils.getCol(U, u), CollectionUtils.getCol(P, p));
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

        return result + tempResult * lambda;
    }

    private static Triple<List<Recommendation>, Integer, Integer> getRecommendations (String group) {
        Map<String, Integer> userIds = new HashMap<>();
        Map<String, Integer> productIds = new HashMap<>();
        List<Recommendation> recommendations = new ArrayList<>();

        int users = 0;
        int products = 0;

        try {
            InputStream inputStream = new FileInputStream("amazon-meta.csv");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            bufferedReader.readLine(); // Ignore headers
            String line = bufferedReader.readLine();
            while(line != null) {
                String[] recordSplit = line.split(", ");

                String userId = recordSplit[0];
                String productId = recordSplit[1];
                String rating = recordSplit[2];
                String currentGroup = recordSplit[3];

                if(group.equals(currentGroup)) {

                    // Generate Integer userId
                    if (!userIds.containsKey(userId)) {
                        userIds.put(userId, users);
                        users++;
                    }

                    // Generate productId
                    if (!productIds.containsKey(productId)) {
                        productIds.put(productId, products);
                        products++;
                    }

                    recommendations.add(new Recommendation(
                            productIds.get(productId),
                            String.valueOf(userIds.get(userId)),
                            Double.parseDouble(rating),
                            group));
                }

                line = bufferedReader.readLine();
            }

            bufferedReader.close();
            inputStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Generating recommendations for: ");
        System.out.println("Category: " + group);
        System.out.println(users + " users");
        System.out.println(productIds.size() + " products");
        System.out.println(recommendations.size() + " recommendations");

        return new Triple<>(recommendations, users, productIds.size());
    }

    private static double[][] createR (String group) {
        Triple<List<Recommendation>, Integer, Integer> getRecommendationsResult = getRecommendations(group);

        int users = getRecommendationsResult.getSecond();
        int products = getRecommendationsResult.getThird();
        List<Recommendation> recommendations = getRecommendationsResult.getFirst();

        double[][] R = Generator.createMatrix(users, products);

        for(Recommendation r : recommendations) {
            R[Integer.parseInt(r.getUserId())][r.getProductId()] = r.getRating();
        }

        return R;
    }
}
