// Paweł Krause - 253983 - Algorytmy numeryczne - Zadanie 1

package Utilities;

import Models.QTestResult;
import Models.TestResult;

import java.util.List;
import java.io.PrintWriter;


public class FileUtils
{
    private static TestResult sumTestResults(List<TestResult> content)
    {
        TestResult result = new TestResult();

        // Add up the results
        for (TestResult h: content)
        {
            result.setCurrentPow(result.getCurrentPow() + h.getCurrentPow());
            result.setCurrentExp(result.getCurrentExp() + h.getCurrentExp());
            result.setExp_s(result.getExp_s() + h.getExp_s());
            result.setExp_s_l(result.getExp_s_l() + h.getExp_s_l());
            result.setExp_p(result.getExp_p() + h.getExp_p());
            result.setExp_p_l(result.getExp_p_l() + h.getExp_p_l());
        }

        // Average the results
        result.setCurrentPow(result.getCurrentPow() / content.size());
        result.setCurrentExp(result.getCurrentExp() / content.size());
        result.setExp_s(result.getExp_s() / content.size());
        result.setExp_s_l(result.getExp_s_l() / content.size());
        result.setExp_p(result.getExp_p() / content.size());
        result.setExp_p_l(result.getExp_p_l() / content.size());

        return result;
    }

    private static QTestResult sumQTestResults(List<QTestResult> content)
    {
        QTestResult result = new QTestResult();

        // Add up the results
        for (QTestResult h: content)
        {
            result.setCurrentPow(result.getCurrentPow() + h.getCurrentPow());
            result.setCurrentExp(result.getCurrentExp() + h.getCurrentExp());
            result.setTest1(result.getTest1() + h.getTest1());
            result.setTest2(result.getTest2() + h.getTest2());
        }

        // Average the results
        result.setCurrentPow(result.getCurrentPow() / content.size());
        result.setCurrentExp(result.getCurrentExp() / content.size());
        result.setTest1(result.getTest1() / content.size());
        result.setTest2(result.getTest2() / content.size());

        return result;
    }

    public static void writeTestResult(PrintWriter writer, List<TestResult> content)
    {
        TestResult result = sumTestResults(content);

        // Calculate the failure
        String resultString =
                result.getCurrentPow()
                + "; " + result.getCurrentExp()
                + "; " + (Math.abs(result.getCurrentExp() - result.getExp_s())) / result.getCurrentExp()
                + "; " + (Math.abs(result.getCurrentExp() - result.getExp_s_l())) / result.getCurrentExp()
                + "; " + (Math.abs(result.getCurrentExp() - result.getExp_p())) / result.getCurrentExp()
                + "; " + (Math.abs(result.getCurrentExp() - result.getExp_p_l())) / result.getCurrentExp()
                + System.lineSeparator();

        writer.write(resultString.replace(".", ","));

        writer.flush();
    }

    public static void writeQTestResult(PrintWriter writer, List<QTestResult> content)
    {
        QTestResult result = sumQTestResults(content);

        // Calculate the failure
        String resultString =
                result.getCurrentPow()
                + "; " + result.getCurrentExp()
                + "; " + result.getTest1()
                + "; " + result.getTest2()
                + System.lineSeparator();

        writer.write(resultString.replace(".", ","));

        writer.flush();
    }
}
