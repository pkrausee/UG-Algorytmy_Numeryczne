package Main;

import Models.TestResult;

import Utilities.CollectionUtils;
import Utilities.FileUtils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.ArrayList;


public class Test_Hypothesis_3
{
    public static void main (String[] args)
    {
        // Elements in Taylor series
        int elements = 50;

        List<TestResult> results = new ArrayList<>();

        try(PrintWriter writer = new PrintWriter(new FileOutputStream("H3_Results.csv")))
        {
            var testPowers = CollectionUtils.generateRanges(-15, 50, 200000, 800000);

            for(Double currentPow : testPowers)
            {
                // Create Taylor series
                var taylorSeries = new ArrayList<Double>();
                Exp_N.exp_s(currentPow, elements, taylorSeries);

                var taylorSeriesDependingOnPrevious = new ArrayList<Double>();
                Exp_N.exp_p(currentPow, elements, taylorSeriesDependingOnPrevious);


                // Create result
                var result = new TestResult(
                        currentPow,
                        Math.exp(currentPow),
                        Utilities.CollectionUtils.sum(taylorSeries),
                        Utilities.CollectionUtils.sum_l(taylorSeries),
                        Utilities.CollectionUtils.sum(taylorSeries),
                        Utilities.CollectionUtils.sum_l(taylorSeries));

                results.add(result);

                // Save packed results to file
                if(results.size() == 100)
                {
                    FileUtils.writeTestResult(writer, results);

                    results.clear();
                }
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}
