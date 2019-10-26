// Paweł Krause - 253983 - Algorytmy numeryczne - Zadanie 1

package Main;

import Models.TestResult;

import Utilities.FileUtils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;


public class Test_Hypothesis_1_and_2
{
    public static void main (String[] args)
    {
        int tests = 1000000;

        // Precision of elements in Taylor series
        double precision = 0.0000000000000001;

        var results = new ArrayList<TestResult>();

        try(PrintWriter writer = new PrintWriter(new FileOutputStream("H1_H2_Results.csv")))
        {
            for(int currentTest = -(tests / 2); currentTest <= (tests / 2); currentTest++)
            {
                if((currentTest + (tests / 2)) % 10000 == 0)
                {
                    System.out.println("Progress: [" + (currentTest + (tests / 2)) / 10000 + " / 100]");
                }

                // Define current X
                double currentPow = (double) currentTest / 25000;

                // Create Taylor series
                var taylorSeries = new ArrayList<Double>();
                Exp_P.exp_s(currentPow, precision, taylorSeries);

                var taylorSeriesDependingOnPrevious = new ArrayList<Double>();
                Exp_P.exp_p(currentPow, precision, taylorSeriesDependingOnPrevious);

                // Create result
                var result = new TestResult(
                        currentPow,
                        Math.exp(currentPow),
                        Utilities.CollectionUtils.sum(taylorSeries),
                        Utilities.CollectionUtils.sum_l(taylorSeries),
                        Utilities.CollectionUtils.sum(taylorSeriesDependingOnPrevious),
                        Utilities.CollectionUtils.sum_l(taylorSeriesDependingOnPrevious));

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
