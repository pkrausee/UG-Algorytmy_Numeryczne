package Utilities;

import Model.Recommendation;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AmazonMetaFileParser {
    public static List<Recommendation> parseAmazonMetaFile(String group) {
        List<Recommendation> recommendations = new ArrayList<>();

        long startTime = System.nanoTime();

        // Parse input file into List<Recommendation>
        try {
            InputStream inputStream = new FileInputStream("amazon-meta.txt");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            int currentProductId = 0;
            String currentGroup = "";

            String line = bufferedReader.readLine();
            while(line != null) {

                if(line.matches("(.*)group:(.*)")) {
                    // Get current group
                    currentGroup = line.substring(9);
                } else if(line.matches("Id(.*)")) {
                    // Get current productId and parse it to Integer.
                    currentProductId = Integer.parseInt(line.substring(6));
                } else if ((group.equals("") || group.equals(currentGroup)) && line.matches("(.*)cutomer:(.*)rating:(.*)")) {
                    // Get review and cut userId and rating out of it.
                    String[] recommendationSplit = line.split("\\p{javaSpaceChar}+");

                    String userId = recommendationSplit[3];
                    double rating = Double.parseDouble(recommendationSplit[5]);

                    recommendations.add(new Recommendation(currentProductId, userId, rating, currentGroup));
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

        System.out.println("File parsed into list of recommendations: " + (System.nanoTime() - startTime) / 1000000000.0 + " sec");

        if(group.equals("")) {
            System.out.println("Recommendations: " + recommendations.size());
        } else {
            System.out.println("Recommendations in group \"" + group + "\": " + recommendations.size());
        }

        return recommendations;
    }

    public static void saveToCsv(List<Recommendation> recommendations) {
        long startTime = System.nanoTime();

        try (PrintWriter writer = new PrintWriter(new FileOutputStream("amazon-meta.csv"))) {

            writer.println("UserId, ProductId, Rating, Category");

            for (Recommendation r : recommendations) {
                writer.println(r.getUserId() + ", " + r.getProductId() + ", " + r.getRating() + ", " + r.getCategory());
            }

            writer.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("Csv created: " + (System.nanoTime() - startTime) / 1000000000.0 + " sec");
    }
}
