// Paweł Krause - 253983 - Algorytmy numeryczne - Zadanie 1

package Models;

public class QTestResult
{
    private double currentPow;
    private double currentExp;
    private int test1;
    private int test2;

    public QTestResult() {}

    public QTestResult(
            double currentPow,
            double currentExp,
            int test1,
            int test2)
    {
        this.currentPow = currentPow;
        this.currentExp = currentExp;
        this.test1 = test1;
        this.test2 = test2;
    }

    public double getCurrentPow() {
        return currentPow;
    }

    public void setCurrentPow(double currentPow) {
        this.currentPow = currentPow;
    }

    public double getCurrentExp() {
        return currentExp;
    }

    public void setCurrentExp(double currentExp) {
        this.currentExp = currentExp;
    }

    public int getTest1() {
        return test1;
    }

    public void setTest1(int test1) {
        this.test1 = test1;
    }

    public int getTest2() {
        return test2;
    }

    public void setTest2(int test2) {
        this.test2 = test2;
    }
}
