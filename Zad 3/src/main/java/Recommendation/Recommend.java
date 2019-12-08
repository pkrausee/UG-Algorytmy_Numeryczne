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
    public static double[][] calculate_ALS(String group, double lambda, int d, int iterations) {
        double[][] R = new double[][] {
                {5,    5,    5,    5,    5,    5,    5,    5,    5,    5},
                {4,    4,    4,    4,    4,    4,    4,    4,    4,    4},
                {0,    5,    5,    5,    5,    5,    0,    0,    5,    5},
                {0,    5,    5,    5,    5,    5,    0,    0,    5,    5},
                {0,    5,    5,    5,    5,    5,    0,    0,    5,    5},
                {5,    4,    4,    4,    4,    4,    5,    5,    4,    4},
                {5,    5,    5,    5,    4,    5,    5,    5,    5,    5},
                {0,    5,    5,    5,    5,    5,    0,    0,    5,    5},
                {0,    4,    4,    4,    5,    4,    0,    0,    4,    4},
                {4,    5,    5,    5,    5,    5,    4,    4,    5,    5}
        };

//        double R[][] = createR(group);

        CollectionUtils.showRecommendationMatrix(R);

        double[][] U = new double[][] {
                { 0.363406838274688, 0.9194400360855306, 0.3929807877076159, 0.9833093957253916, 0.69412359913744, 0.7783694289967863, 0.1580762728203221, 0.7625434210212357, 0.6053319677003883, 0.8360838058689247 },
                { 0.8031560348336361, 0.323570360408282, 0.9233057787879122, 0.8176024315242311, 0.3243067685334238, 0.8844590618617174, 0.00978299781883396, 0.505218360548414, 0.8031973145042771, 0.7690454974667934 },
                { 0.14915555112509504, 0.6040036591659396, 0.1997552814584872, 0.9862166153485551, 0.42380114623622955, 0.145164853428026, 0.047812494544514306, 0.5918009133377043, 0.577435333196127, 0.8829837006577045}
        };

        double[][] P = new double[][]{
                {0.5586916105144832, 0.410310717506706, 0.3286242286461395, 0.23939987074398283, 0.6518464492552102, 0.2539610218394863, 0.27405287537710965, 0.1941769403111726, 0.4889346100875034, 0.9084758052549194 },
                {0.5085769786932072, 0.23088682062228616, 0.6318799858310207, 0.16899531321576833, 0.13220728739993381, 0.7718779794066072, 0.9069114455191851, 0.8225502025932803, 0.6076375999210056, 0.0884740790628028 },
                {0.09055934459116588, 0.61691592963748, 0.9335127640326034, 0.010143462381482782, 0.05772204021281924, 0.553089001553943, 0.0519261647835767, 0.65622565566207, 0.733775770698509, 0.4026164936892014 }
        };

//        double[][] U = Generator.generateMatrix(0, 1, d, R.length);
//        double[][] P = Generator.generateMatrix(0, 1, d, R[0].length);

        CollectionUtils.showRecommendationMatrix(U);
        CollectionUtils.showRecommendationMatrix(P);

        double[][] lambda_E = MathUtils.multiply(lambda, Generator.unitMatrix(d));

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

            System.out.println(f_U_P(R, P, U, lambda));
        }

        return MathUtils.multiply(MathUtils.transpose(U), P);
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

    public static double f_U_P(double[][] R, double[][] P, double[][] U, double lambda) {
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

    private static Triple<HashSet<Recommendation>, Integer, Integer> getRecommendations (String group) {
        Map<String, Integer> userIds = new HashMap<>();
        Map<String, Integer> productIds = new HashMap<>();
        HashSet<Recommendation> recommendations = new HashSet<>();

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

        return new Triple<>(recommendations, users, products);
    }

    private static double[][] createR (String group) {
        Triple<HashSet<Recommendation>, Integer, Integer> getRecommendationsResult = getRecommendations(group);

        int users = getRecommendationsResult.getSecond();
        int products = getRecommendationsResult.getThird();
        HashSet<Recommendation> recommendations = getRecommendationsResult.getFirst();

        double[][] R = Generator.createMatrix(users, products);

        for(Recommendation r : recommendations) {
            R[Integer.parseInt(r.getUserId())][r.getProductId()] = r.getRating();
        }

        return R;
    }
}
