import Recommendation.Recommend;
import Utilities.AmazonMetaFileCsvStats;
import Utilities.CollectionUtils;

public class Main {
    public static void main(String[] args) {
//        AmazonMetaFileCsvStats.getStats("amazon-meta.csv");

        CollectionUtils.showRecommendationMatrix(Recommend.calculate_ALS("DVD", 0.1, 3, 100));
    }
}
