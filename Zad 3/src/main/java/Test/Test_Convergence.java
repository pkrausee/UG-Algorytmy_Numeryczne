package Test;

import Recommendation.Recommend;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.List;

public class Test_Convergence {

    public static void testParameters() {
        int[] matrixSizes = new int[] {50, 500, 1000};

        for (int matrixSize : matrixSizes) {
            double[][] data = Recommend.createR_FromAmazonMetaFile("sorted_data_u5_p" + matrixSize + ".csv");

            // Lambda 0.3, d {5, 25, 50}
            List<Double> small_03 = Recommend.ALS(data, 0.3, 5, 100).getSecond();
            List<Double> medium_03 = Recommend.ALS(data, 0.3, 25, 100).getSecond();
            List<Double> big_03 = Recommend.ALS(data, 0.3, 50, 100).getSecond();

            // Lambda 0.6, d {5, 25, 50}
            List<Double> small_06 = Recommend.ALS(data, 0.6, 5, 100).getSecond();
            List<Double> medium_06 = Recommend.ALS(data, 0.6, 25, 100).getSecond();
            List<Double> big_06 = Recommend.ALS(data, 0.6, 50, 100).getSecond();

            // Lambda 0.9, d {5, 25, 50}
            List<Double> small_09 = Recommend.ALS(data, 0.9, 5, 100).getSecond();
            List<Double> medium_09 = Recommend.ALS(data, 0.9, 25, 100).getSecond();
            List<Double> big_09 = Recommend.ALS(data, 0.9, 50, 100).getSecond();


            try (PrintWriter writer = new PrintWriter(new FileOutputStream("testParameters_5x" + matrixSize + ".csv"))) {
                StringBuilder builder = new StringBuilder();

                for (int i = 0; i < 100; i++) {
                    builder.append(i).append(";");
                    builder.append(small_03.get(i)).append(";");
                    builder.append(medium_03.get(i)).append(";");
                    builder.append(big_03.get(i)).append(";");

                    builder.append(small_06.get(i)).append(";");
                    builder.append(medium_06.get(i)).append(";");
                    builder.append(big_06.get(i)).append(";");

                    builder.append(small_09.get(i)).append(";");
                    builder.append(medium_09.get(i)).append(";");
                    builder.append(big_09.get(i)).append("\n");

                    writer.print(builder.toString().replace('.', ','));

                    builder = new StringBuilder();
                }

                writer.flush();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
