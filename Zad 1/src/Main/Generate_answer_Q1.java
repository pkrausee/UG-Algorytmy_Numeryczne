// Paweł Krause - 253983 - Algorytmy numeryczne - Zadanie 1

package Main;

import Models.TestResult;

import Utilities.FileUtils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;


public class Generate_answer_Q1
{
    public static void main (String[] args)
    {
        int tests = 1000000;

        var results = new ArrayList<TestResult>();

        try(PrintWriter writer = new PrintWriter(new FileOutputStream("Q1_Results.csv")))
        {
            for(int currentTest = -(tests / 2); currentTest <= (tests / 2); currentTest++)
            {
                if((currentTest + (tests / 2)) % 10000 == 0)
                {
                    System.out.println("Progress: [" + (currentTest + (tests / 2)) / 10000 + " / 100]");
                }

                // Define current X
                double currentPow = (double) currentTest / ((double)tests / 2) * 10;

                // Create Taylor series for 4 tests cases
                var taylorSeries5 = new ArrayList<Double>();
                Exp_N.exp_s(currentPow, 5, taylorSeries5);

                var taylorSeries25 = new ArrayList<Double>();
                Exp_N.exp_s(currentPow, 25, taylorSeries25);

                var taylorSeries50 = new ArrayList<Double>();
                Exp_N.exp_s(currentPow, 50, taylorSeries50);

                var taylorSeries100 = new ArrayList<Double>();
                Exp_N.exp_s(currentPow, 100, taylorSeries100);

                // Create result
                var result = new TestResult(
                        currentPow,
                        Math.exp(currentPow),
                        Utilities.CollectionUtils.sum(taylorSeries5),
                        Utilities.CollectionUtils.sum(taylorSeries25),
                        Utilities.CollectionUtils.sum(taylorSeries50),
                        Utilities.CollectionUtils.sum(taylorSeries100));

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
