package Utilities;

import Model.Recommendation;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Parser {

    public static List<Recommendation> parseAmazonMetaFile(String filename) {
        List<Recommendation> recommendations = new ArrayList<Recommendation>();

        try {
            InputStream inputStream = new FileInputStream(filename);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            int currentProductId = 0;
            String line = bufferedReader.readLine();
            while(line != null) {

                if(line.matches("Id:\\p{javaSpaceChar}{3}(.*)")) {
                    // Get id and parse it to Integer.

                    String[] idSplit = line.split("\\p{javaSpaceChar}{3}");
                    currentProductId = Integer.parseInt(idSplit[1]);

                } else if (line.matches("(.*)cutomer:(.*)rating:(.*)")) {
                    // Get rating and cut userId and rating out of it.

                    String[] recommendationSplit = line.split("\\p{javaSpaceChar}+");

                    String userId = recommendationSplit[3];
                    double rating = Double.parseDouble(recommendationSplit[5]);

                    recommendations.add(new Recommendation(currentProductId, userId, rating));
                }

                line = bufferedReader.readLine();
            }

            bufferedReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return recommendations;
    }

}
