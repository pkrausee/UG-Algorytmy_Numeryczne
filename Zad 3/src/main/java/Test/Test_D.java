package Test;


import Recommendation.Recommend;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.List;

public class Test_D {
    public static void test_fUP() {
        double[][] small_data = Recommend.createR_FromAmazonMetaFile("sorted_data_u5_p50.csv");
        double[][] medium_data = Recommend.createR_FromAmazonMetaFile("sorted_data_u5_p500.csv");
        double[][] big_data = Recommend.createR_FromAmazonMetaFile("sorted_data_u5_p1000.csv");

        try (PrintWriter writer = new PrintWriter(new FileOutputStream("testFupForParameterD.csv"))) {
            StringBuilder builder = new StringBuilder();

            for (int d = 3; d <= 25; d += 1) {
                System.out.println(d);

                List<Double> small_data_fUP = Recommend.ALS(small_data, 0.1, d, 100).getSecond();
                List<Double> medium_data_fUP = Recommend.ALS(medium_data, 0.1, d, 100).getSecond();
                List<Double> big_data_fUP = Recommend.ALS(big_data, 0.1, d, 100).getSecond();

                builder.append(d).append(";");
                builder.append(small_data_fUP.get(small_data_fUP.size() - 1)).append(";");
                builder.append(medium_data_fUP.get(medium_data_fUP.size() - 1)).append(";");
                builder.append(big_data_fUP.get(big_data_fUP.size() - 1)).append("\n");

                writer.print(builder.toString().replace('.', ','));

                builder = new StringBuilder();
            }

            writer.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void test_timings() {
        double[][] small_data = Recommend.createR_FromAmazonMetaFile("sorted_data_u5_p50.csv");
        double[][] medium_data = Recommend.createR_FromAmazonMetaFile("sorted_data_u5_p500.csv");
        double[][] big_data = Recommend.createR_FromAmazonMetaFile("sorted_data_u5_p1000.csv");

        try (PrintWriter writer = new PrintWriter(new FileOutputStream("testTimingsForParameterD.csv"))) {
            StringBuilder builder = new StringBuilder();

            for (int d = 10; d <= 100; d += 5) {

                System.out.println(d);

                Long small_data_time = Recommend.ALS(small_data, 0.1, d, 100).getThird();
                Long medium_data_time = Recommend.ALS(medium_data, 0.1, d, 100).getThird();
                Long big_data_time = Recommend.ALS(big_data, 0.1, d, 100).getThird();

                builder.append(d).append(";");
                builder.append(small_data_time).append(";");
                builder.append(medium_data_time).append(";");
                builder.append(big_data_time).append("\n");

                writer.print(builder.toString().replace('.', ','));

                builder = new StringBuilder();
            }

            writer.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
