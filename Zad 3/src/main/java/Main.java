import Recommendation.Recommend;
import Utilities.CollectionUtils;

public class Main {
    public static void main(String[] args) {
//        AmazonMetaFileCsvStats.getStats("amazon-meta.csv");

        double[][] R = Recommend.calculate_ALS("DVD", 0.1, 3, 100);

    }
}
