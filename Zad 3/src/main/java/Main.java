import Model.Recommendation;
import Utilities.Parser;

import java.util.List;

public class Main {
    public static void main(String[] args) {
//        CollectionUtils.showRecommendationMatrix(Recommend.calculate_ALS(0.1, 3, 10));

        long start = System.nanoTime();
        List<Recommendation> recommendationList = Parser.parseAmazonMetaFile("amazon-meta.txt");
        System.out.println("Time: " + (System.nanoTime() - start) / 1000000000.0);

        System.out.println(recommendationList.size());
    }
}
