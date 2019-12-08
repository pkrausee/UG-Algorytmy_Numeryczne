import Recommendation.Recommend;

import Test.Test_Convergence;
import Test.Test_D;

public class Main {
    public static void main(String[] args) {
        Recommend.check_Implementation();

        Test_Convergence.testParameters();
        Test_D.test_fUP();
        Test_D.test_timings();

    }
}
