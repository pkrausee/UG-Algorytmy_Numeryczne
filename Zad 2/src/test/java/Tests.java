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

public class Tests {
    private final int numberOfTests = 1000;
    private final int jump = 5;

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

    @org.junit.Test
    public void TestMatrixForDoubleType() {
        DoubleAdapter adapter = new DoubleAdapter();

        double accuracy = 0.000000000000000000000000001d;

        try (PrintWriter writer = new PrintWriter(new FileOutputStream("H2_Double.csv", true))) {
            for (int i = jump; i <= numberOfTests; i += jump) {

                if (i % jump == 0) {
                    System.out.println("D " + i + "/" + numberOfTests);
                }

                Double[][] A = new Double[i][i];
                Double[] X = new Double[i];

                MatrixGenerator.generateValues(A);
                MatrixGenerator.generateValues(X);

                Double[] B = MatrixUtilities.multiplyByVector(Double.class, adapter, A, X);

                Double[] Xp;

                long startTime, NPTime, PPTime, FPTime;
                double NPFail, PPFail, FPFail;

                startTime = System.nanoTime();
                Xp = MyMatrixV1.GaussJordanElimination_NoPivoting(adapter, A, B, accuracy);
                NPTime = System.nanoTime() - startTime;

                NPFail = MatrixUtilities.avg(MatrixUtilities.subtract(Double.class, adapter, X, Xp));

                startTime = System.nanoTime();
                Xp = MyMatrixV1.GaussJordanElimination_PartialPivoting(adapter, A, B, accuracy);
                PPTime = System.nanoTime() - startTime;

                PPFail = MatrixUtilities.avg(MatrixUtilities.subtract(Double.class, adapter, X, Xp));

                startTime = System.nanoTime();
                Xp = MyMatrixV1.GaussJordanElimination_FullPivoting(adapter, A, B, accuracy);
                FPTime = System.nanoTime() - startTime;

                FPFail = MatrixUtilities.avg(MatrixUtilities.subtract(Double.class, adapter, X, Xp));

                Double[] result = new Double[]{
                        (double) i,
                        (double) NPTime, NPFail,
                        (double) PPTime, PPFail,
                        (double) FPTime, FPFail
                };

                writer.write(buildString(result).replace(".", ","));

                writer.flush();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void TestMatrixForFloatType() {
        FloatAdapter adapter = new FloatAdapter();

        float accuracy = 0.000000000000000000000000001f;

        try (PrintWriter writer = new PrintWriter(new FileOutputStream("H2_Float.csv", true))) {
            for (int i = jump; i <= numberOfTests; i += jump) {

                if (i % jump == 0) {
                    System.out.println("F " + i + "/" + numberOfTests);
                }

                Float[][] A = new Float[i][i];
                Float[] X = new Float[i];

                MatrixGenerator.generateValues(A);
                MatrixGenerator.generateValues(X);

                Float[] B = MatrixUtilities.multiplyByVector(Float.class, adapter, A, X);

                Float[] Xp;

                long startTime, NPTime, PPTime, FPTime;
                double NPFail, PPFail, FPFail;

                startTime = System.nanoTime();
                Xp = MyMatrixV1.GaussJordanElimination_NoPivoting(adapter, A, B, accuracy);
                NPTime = System.nanoTime() - startTime;

                NPFail = MatrixUtilities.avg(MatrixUtilities.subtract(Float.class, adapter, X, Xp));

                startTime = System.nanoTime();
                Xp = MyMatrixV1.GaussJordanElimination_PartialPivoting(adapter, A, B, accuracy);
                PPTime = System.nanoTime() - startTime;

                PPFail = MatrixUtilities.avg(MatrixUtilities.subtract(Float.class, adapter, X, Xp));

                startTime = System.nanoTime();
                Xp = MyMatrixV1.GaussJordanElimination_FullPivoting(adapter, A, B, accuracy);
                FPTime = System.nanoTime() - startTime;

                FPFail = MatrixUtilities.avg(MatrixUtilities.subtract(Float.class, adapter, X, Xp));

                Double[] result = new Double[]{
                        (double) i,
                        (double) NPTime, NPFail,
                        (double) PPTime, PPFail,
                        (double) FPTime, FPFail
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

        Fraction accuracy = new Fraction(0);

        try (PrintWriter writer = new PrintWriter(new FileOutputStream("H2_Fraction.csv", true))) {
            for (int i = jump; i <= numberOfTests; i += jump) {

                if (i % jump == 0) {
                    System.out.println("Fr " + i + "/" + numberOfTests);
                }

                Fraction[][] A = new Fraction[i][i];
                Fraction[] X = new Fraction[i];

                MatrixGenerator.generateValues(A);
                MatrixGenerator.generateValues(X);

                Fraction[] B = MatrixUtilities.multiplyByVector(Fraction.class, adapter, A, X);

                Fraction[] Xp;

                long startTime, NPTime, PPTime, FPTime;
                double NPFail, PPFail, FPFail;

                startTime = System.nanoTime();
                Xp = MyMatrixV1.GaussJordanElimination_NoPivoting(adapter, A, B, accuracy);
                NPTime = System.nanoTime() - startTime;

                NPFail = MatrixUtilities.avg(MatrixUtilities.subtract(Fraction.class, adapter, X, Xp));

                startTime = System.nanoTime();
                Xp = MyMatrixV1.GaussJordanElimination_PartialPivoting(adapter, A, B, accuracy);
                PPTime = System.nanoTime() - startTime;

                PPFail = MatrixUtilities.avg(MatrixUtilities.subtract(Fraction.class, adapter, X, Xp));

                startTime = System.nanoTime();
                Xp = MyMatrixV1.GaussJordanElimination_FullPivoting(adapter, A, B, accuracy);
                FPTime = System.nanoTime() - startTime;

                FPFail = MatrixUtilities.avg(MatrixUtilities.subtract(Fraction.class, adapter, X, Xp));

                Double[] result = new Double[]{
                        (double) i,
                        (double) NPTime, NPFail,
                        (double) PPTime, PPFail,
                        (double) FPTime, FPFail
                };

                writer.write(buildString(result).replace(".", ","));

                writer.flush();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
