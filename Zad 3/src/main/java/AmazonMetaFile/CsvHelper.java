package AmazonMetaFile;

import Model.Recommendation;
import Model.Triple;

import java.io.*;
import java.util.*;

public class CsvHelper {
    public static String saveToCsv(List<Recommendation> recommendations, String group) {
        String filename = (group == null || group.equals("")) ? "amazon-meta.csv" : "amazon-meta-" + group + ".csv";

        try (PrintWriter writer = new PrintWriter(new FileOutputStream(filename))) {

            writer.println("UserId, ProductId, Rating, Group");

            for (Recommendation r : recommendations) {
                writer.println(r.getUserId() + "," + r.getProductId() + "," + r.getRating() + "," + r.getGroup());
            }

            writer.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return filename;
    }

    public static Triple<List<Recommendation>, Integer, Integer> readFromCsv(String filename) {
        Map<String, Integer> userIds = new HashMap<>();
        Map<String, Integer> productIds = new HashMap<>();
        List<Recommendation> recommendations = new ArrayList<>();

        int users = 0;
        int products = 0;

        try {
            InputStream inputStream = new FileInputStream(filename);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            bufferedReader.readLine(); // Ignore headers
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] recordSplit = line.split(",");

                String userId = recordSplit[0];
                String productId = recordSplit[1];
                String rating = recordSplit[2];
                String currentGroup = recordSplit[3];

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
                        currentGroup));

                line = bufferedReader.readLine();
            }

            bufferedReader.close();
            inputStream.close();

            System.out.println("File parsed");
            System.out.println("Recommendations: " + recommendations.size());
            System.out.println("Users: " + users);
            System.out.println("Products: " + products);

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Triple<>(recommendations, users, products);
    }
}
