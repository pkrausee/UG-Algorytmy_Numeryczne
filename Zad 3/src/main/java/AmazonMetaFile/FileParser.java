package AmazonMetaFile;

import Model.Recommendation;

import java.io.*;
import java.util.*;

public class FileParser {
    public static HashSet<Recommendation> parseAmazonMetaFile() {
        HashSet<Recommendation> recommendations = new HashSet<>();

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
                } else if (line.matches("(.*)cutomer:(.*)rating:(.*)")) {
                    // Get review and cut userId and rating out of it.
                    String[] recommendationSplit = line.split("\\p{javaSpaceChar}+");

                    String userId = recommendationSplit[3];
                    double rating = Double.parseDouble(recommendationSplit[5]);

                    Recommendation recommendation = new Recommendation(currentProductId, userId, rating, currentGroup);

                    recommendations.add(recommendation);
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

        return recommendations;
    }
}
