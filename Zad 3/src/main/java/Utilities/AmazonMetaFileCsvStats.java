package Utilities;

import java.io.*;
import java.util.*;

public class AmazonMetaFileCsvStats {
    public static void getStats (String filename) {
        HashSet<String> userIds = new HashSet<>();
        HashSet<String> productIds = new HashSet<>();
        HashMap<String, Integer> groups = new HashMap<>();

        int groupsCount = 0;
        int recommendations = 0;

        try {
            InputStream inputStream = new FileInputStream(filename);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            bufferedReader.readLine(); // Ignore headers
            String line = bufferedReader.readLine();
            while(line != null) {
                String[] recordSplit = line.split(", ");

                String userId = recordSplit[0];
                String productId = recordSplit[1];
                String group = recordSplit[3];

                userIds.add(userId);
                productIds.add(productId);

                if(!groups.containsKey(group)){
                    groups.put(group, 0);
                    groupsCount++;
                } else {
                    groups.put(group, groups.get(group) + 1);
                }

                recommendations++;

                line = bufferedReader.readLine();
            }

            bufferedReader.close();
            inputStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Number of recommendations: " + recommendations);
        System.out.println("Users: " + userIds.size());
        System.out.println("Products: " + productIds.size());
        System.out.println("Groups: " + groups.size());

        for(String g : groups.keySet()) {
            System.out.println("\t" + g + ": " + groups.get(g));
        }

    }
}
