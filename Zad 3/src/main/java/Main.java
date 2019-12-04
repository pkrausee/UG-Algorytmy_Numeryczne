import MatrixOperations.Recommend;
import Utilities.CollectionUtils;

public class Main {
    public static void main(String[] args) {
        CollectionUtils.showRecommendationMatrix(Recommend.calculate_ALS(0.1, 3, 10));
    }
}
