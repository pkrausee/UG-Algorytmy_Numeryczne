// Paweł Krause - 253983 - Algorytmy numeryczne - Zadanie 1

package Main;

import Models.QTestResult;

import Utilities.FileUtils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;


public class Generate_answer_Q2
{
    public static void main (String[] args)
    {
        int tests = 1000000;

        // Precision of elements in Taylor series
        double precision = 0.000001;

        var results = new ArrayList<QTestResult>();

        try(PrintWriter writer = new PrintWriter(new FileOutputStream("Q2_Result.csv")))
        {
            for(int currentTest = -(tests / 2); currentTest <= (tests / 2); currentTest++)
            {
                if((currentTest + (tests / 2)) % 10000 == 0)
                {
                    System.out.println("Progress: [" + (currentTest + (tests / 2)) / 10000 + " / 100]");
                }

                // Define current X
                double currentPow = (double) currentTest / 25000;

                // Create result
                var result = new QTestResult(
                        currentPow,
                        Math.exp(currentPow),
                        Exp_P.exp_s(currentPow, precision),
                        Exp_P.exp_p(currentPow, precision));

                results.add(result);

                // Save packed results to file
                if(results.size() == 100)
                {
                    FileUtils.writeQTestResult(writer, results);

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
