// Paweł Krause - 253983 - Algorytmy numeryczne - Zadanie 1

package Models;

public class TestResult
{
    private double currentPow;
    private double currentExp;
    private double exp_s;
    private double exp_s_l;
    private double exp_p;
    private double exp_p_l;

    public TestResult() {}

    public TestResult(
            double currentPow,
            double currentExp,
            double exp_s,
            double exp_s_l,
            double exp_p,
            double exp_p_l)
    {
        this.currentPow = currentPow;
        this.currentExp = currentExp;
        this.exp_s = exp_s;
        this.exp_s_l = exp_s_l;
        this.exp_p = exp_p;
        this.exp_p_l = exp_p_l;
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

    public double getExp_s() {
        return exp_s;
    }

    public void setExp_s(double exp_s) {
        this.exp_s = exp_s;
    }

    public double getExp_s_l() {
        return exp_s_l;
    }

    public void setExp_s_l(double exp_s_l) {
        this.exp_s_l = exp_s_l;
    }

    public double getExp_p() {
        return exp_p;
    }

    public void setExp_p(double exp_p) {
        this.exp_p = exp_p;
    }

    public double getExp_p_l() {
        return exp_p_l;
    }

    public void setExp_p_l(double exp_p_l) {
        this.exp_p_l = exp_p_l;
    }
}
