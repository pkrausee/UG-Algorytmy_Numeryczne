import Adapters.DoubleAdapter;

import Adapters.FloatAdapter;
import Adapters.FractionAdapter;

import Matrix.MatrixGenerator;
import Matrix.MatrixUtilities;
import Matrix.MyMatrixV1;

import Models.Fraction;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class MatrixV1Tests {
    private final int numberOfTests = 200;
    private final int jump = 10;

    private static <TType extends Number> String buildString(TType[] results) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < results.length; i++) {
            builder.append(results[i]);

            if (i < results.length - 1) {
                builder.append("; ");
            }
        }

        builder.append(System.lineSeparator());

        return builder.toString();
    }

//    @org.junit.Test
    public void TestMatrixForDoubleType() {
        DoubleAdapter adapter = new DoubleAdapter();

        try (PrintWriter writer = new PrintWriter(new FileOutputStream("H2_Double_MatrixV1.csv"))) {
            for (int i = jump; i <= numberOfTests; i += jump) {

                if (i % jump == 0) {
                    System.out.println("D " + i + "/" + numberOfTests);
                }

                Double[][] A = new Double[i][i];
                Double[] X = new Double[i];

                MatrixGenerator.generateValues(A);
                MatrixGenerator.generateValues(X);

                Double[] B = MatrixUtilities.multiplyByVector(adapter, A, X);

                long startTime, NPTime, PPTime, FPTime;

                startTime = System.nanoTime();
                Double[] XpNP = MyMatrixV1.GaussJordanElimination_NoPivoting(adapter, A, B);
                NPTime = System.nanoTime() - startTime;

                startTime = System.nanoTime();
                Double[] XpPP = MyMatrixV1.GaussJordanElimination_PartialPivoting(adapter, A, B);
                PPTime = System.nanoTime() - startTime;

                startTime = System.nanoTime();
                Double[] XpFP = MyMatrixV1.GaussJordanElimination_FullPivoting(adapter, A, B);
                FPTime = System.nanoTime() - startTime;

                Double[] XpNP_ErrorArr = MatrixUtilities.subtract(adapter, X, XpNP);
                Double[] XpPP_ErrorArr = MatrixUtilities.subtract(adapter, X, XpPP);
                Double[] XpFP_ErrorArr = MatrixUtilities.subtract(adapter, X, XpFP);

                Double XpNP_Er = MatrixUtilities.avg(adapter, XpNP_ErrorArr);
                Double XpPP_Er = MatrixUtilities.avg(adapter, XpPP_ErrorArr);
                Double XpFP_Er = MatrixUtilities.avg(adapter, XpFP_ErrorArr);

                Double[] result = new Double[]{
                        (double) i,
                        (double) NPTime, XpNP_Er,
                        (double) PPTime, XpPP_Er,
                        (double) FPTime, XpFP_Er
                };

                writer.write(buildString(result).replace(".", ","));

                writer.flush();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

//    @org.junit.Test
    public void TestMatrixForFloatType() {
        FloatAdapter adapter = new FloatAdapter();

        try (PrintWriter writer = new PrintWriter(new FileOutputStream("H2_Float_MatrixV1.csv"))) {
            for (int i = jump; i <= numberOfTests; i += jump) {

                if (i % jump == 0) {
                    System.out.println("F " + i + "/" + numberOfTests);
                }

                Float[][] A = new Float[i][i];
                Float[] X = new Float[i];

                MatrixGenerator.generateValues(A);
                MatrixGenerator.generateValues(X);

                Float[] B = MatrixUtilities.multiplyByVector(adapter, A, X);

                long startTime, NPTime, PPTime, FPTime;

                startTime = System.nanoTime();
                Float[] XpNP = MyMatrixV1.GaussJordanElimination_NoPivoting(adapter, A, B);
                NPTime = System.nanoTime() - startTime;

                startTime = System.nanoTime();
                Float[] XpPP = MyMatrixV1.GaussJordanElimination_PartialPivoting(adapter, A, B);
                PPTime = System.nanoTime() - startTime;

                startTime = System.nanoTime();
                Float[] XpFP = MyMatrixV1.GaussJordanElimination_FullPivoting(adapter, A, B);
                FPTime = System.nanoTime() - startTime;

                Float[] XpNP_ErrorArr = MatrixUtilities.subtract(adapter, X, XpNP);
                Float[] XpPP_ErrorArr = MatrixUtilities.subtract(adapter, X, XpPP);
                Float[] XpFP_ErrorArr = MatrixUtilities.subtract(adapter, X, XpFP);

                Float XpNP_Er = MatrixUtilities.avg(adapter, XpNP_ErrorArr);
                Float XpPP_Er = MatrixUtilities.avg(adapter, XpPP_ErrorArr);
                Float XpFP_Er = MatrixUtilities.avg(adapter, XpFP_ErrorArr);

                Float[] result = new Float[]{
                        (float) i,
                        (float) NPTime, XpNP_Er,
                        (float) PPTime, XpPP_Er,
                        (float) FPTime, XpFP_Er
                };

                writer.write(buildString(result).replace(".", ","));

                writer.flush();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void TestMatrixForFractionType() {
        FractionAdapter adapter = new FractionAdapter();

        try (PrintWriter writer = new PrintWriter(new FileOutputStream("H2_Fraction_MatrixV1.csv"))) {
            for (int i = jump; i <= numberOfTests; i += jump) {

                if (i % jump == 0) {
                    System.out.println("Fr " + i + "/" + numberOfTests);
                }

                Fraction[][] A = new Fraction[i][i];
                Fraction[] X = new Fraction[i];

                MatrixGenerator.generateValues(A);
                MatrixGenerator.generateValues(X);

                Fraction[] B = MatrixUtilities.multiplyByVector(adapter, A, X);

                long startTime, NPTime, PPTime, FPTime;

                startTime = System.nanoTime();
                Fraction[] XpNP = MyMatrixV1.GaussJordanElimination_NoPivoting(adapter, A, B);
                NPTime = System.nanoTime() - startTime;

                startTime = System.nanoTime();
                Fraction[] XpPP = MyMatrixV1.GaussJordanElimination_PartialPivoting(adapter, A, B);
                PPTime = System.nanoTime() - startTime;

                startTime = System.nanoTime();
                Fraction[] XpFP = MyMatrixV1.GaussJordanElimination_FullPivoting(adapter, A, B);
                FPTime = System.nanoTime() - startTime;

                Fraction[] XpNP_ErrorArr = MatrixUtilities.subtract(adapter, X, XpNP);
                Fraction[] XpPP_ErrorArr = MatrixUtilities.subtract(adapter, X, XpPP);
                Fraction[] XpFP_ErrorArr = MatrixUtilities.subtract(adapter, X, XpFP);

                Fraction XpNP_Er = MatrixUtilities.norm(adapter, XpNP_ErrorArr);
                Fraction XpPP_Er = MatrixUtilities.norm(adapter, XpPP_ErrorArr);
                Fraction XpFP_Er = MatrixUtilities.norm(adapter, XpFP_ErrorArr);

                Fraction[] result = new Fraction[]{
                        new Fraction(i),
                        new Fraction(NPTime), XpNP_Er,
                        new Fraction(PPTime), XpPP_Er,
                        new Fraction(FPTime), XpFP_Er
                };

                writer.write(buildString(result).replace(".", ","));

                writer.flush();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
