import Adapters.DoubleAdapter;
import Adapters.FloatAdapter;
import Adapters.FractionAdapter;
import Matrix.MatrixGenerator;
import Matrix.MatrixUtilities;
import Matrix.MyMatrix;
import Models.Fraction;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class Tests {
    private final int numberOfTests = 1000;

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

        try (PrintWriter writer = new PrintWriter(new FileOutputStream("H2_Double.csv", true))) {
            for (int i = 10; i < numberOfTests; i += 10) {

                if (i % 10 == 0) {
                    System.out.println("D " + i + "/" + numberOfTests);
                }

                Double[][] A = new Double[i][i];
                Double[] X = new Double[i];

                MatrixGenerator.generateValues(A);
                MatrixGenerator.generateValues(X);

                Double[] B = MatrixUtilities.multiplyByVector(Double.class, adapter, A, X);

                Double[][] Acopy;
                Double[] Bcopy;

                Double[] Xp;

                long startTime, NPTime, PPTime, FPTime;
                double NPFail, PPFail, FPFail;

                Acopy = adapter.copy(A);
                Bcopy = adapter.copy(B);
                startTime = System.nanoTime();
                Xp = MyMatrix.GaussJordanElimination_NoPivoting(adapter, Acopy, Bcopy);
                NPTime = System.nanoTime() - startTime;

                NPFail = MatrixUtilities.avg(MatrixUtilities.subtract(Double.class, adapter, X, Xp));

                Acopy = adapter.copy(A);
                Bcopy = adapter.copy(B);
                startTime = System.nanoTime();
                Xp = MyMatrix.GaussJordanElimination_PartialPivoting(adapter, Acopy, Bcopy);
                PPTime = System.nanoTime() - startTime;

                PPFail = MatrixUtilities.avg(MatrixUtilities.subtract(Double.class, adapter, X, Xp));

                Acopy = adapter.copy(A);
                Bcopy = adapter.copy(B);
                startTime = System.nanoTime();
                Xp = MyMatrix.GaussJordanElimination_FullPivoting(adapter, Acopy, Bcopy);
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

        try (PrintWriter writer = new PrintWriter(new FileOutputStream("H2_Float.csv", true))) {
            for (int i = 10; i < numberOfTests; i += 10) {
                if (i % 10 == 0) {
                    System.out.println("F " + i + "/" + numberOfTests);
                }

                Float[][] A = new Float[i][i];
                Float[] X = new Float[i];

                MatrixGenerator.generateValues(A);
                MatrixGenerator.generateValues(X);

                Float[] B = MatrixUtilities.multiplyByVector(Float.class, adapter, A, X);

                Float[][] Acopy;
                Float[] Bcopy;

                Float[] Xp;

                long startTime, NPTime, PPTime, FPTime;
                double NPFail, PPFail, FPFail;

                Acopy = adapter.copy(A);
                Bcopy = adapter.copy(B);
                startTime = System.nanoTime();
                Xp = MyMatrix.GaussJordanElimination_NoPivoting(adapter, Acopy, Bcopy);
                NPTime = System.nanoTime() - startTime;

                NPFail = MatrixUtilities.avg(MatrixUtilities.subtract(Float.class, adapter, X, Xp));

                Acopy = adapter.copy(A);
                Bcopy = adapter.copy(B);
                startTime = System.nanoTime();
                Xp = MyMatrix.GaussJordanElimination_PartialPivoting(adapter, Acopy, Bcopy);
                PPTime = System.nanoTime() - startTime;

                PPFail = MatrixUtilities.avg(MatrixUtilities.subtract(Float.class, adapter, X, Xp));

                Acopy = adapter.copy(A);
                Bcopy = adapter.copy(B);
                startTime = System.nanoTime();
                Xp = MyMatrix.GaussJordanElimination_FullPivoting(adapter, Acopy, Bcopy);
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

        try (PrintWriter writer = new PrintWriter(new FileOutputStream("H2_Fraction.csv", true))) {
            for (int i = 10; i < numberOfTests; i += 10) {
                if (i % 10 == 0) {
                    System.out.println("Fr " + i + "/" + numberOfTests);
                }

                Fraction[][] A = new Fraction[i][i];
                Fraction[] X = new Fraction[i];

                MatrixGenerator.generateValues(A);
                MatrixGenerator.generateValues(X);

                Fraction[] B = MatrixUtilities.multiplyByVector(Fraction.class, adapter, A, X);

                Fraction[][] Acopy;
                Fraction[] Bcopy;

                Fraction[] Xp;

                long startTime, NPTime, PPTime, FPTime;
                double NPFail, PPFail, FPFail;

                Acopy = adapter.copy(A);
                Bcopy = adapter.copy(B);
                startTime = System.nanoTime();
                Xp = MyMatrix.GaussJordanElimination_NoPivoting(adapter, Acopy, Bcopy);
                NPTime = System.nanoTime() - startTime;

                NPFail = MatrixUtilities.avg(MatrixUtilities.subtract(Fraction.class, adapter, X, Xp));

                Acopy = adapter.copy(A);
                Bcopy = adapter.copy(B);
                startTime = System.nanoTime();
                Xp = MyMatrix.GaussJordanElimination_PartialPivoting(adapter, Acopy, Bcopy);
                PPTime = System.nanoTime() - startTime;

                PPFail = MatrixUtilities.avg(MatrixUtilities.subtract(Fraction.class, adapter, X, Xp));

                Acopy = adapter.copy(A);
                Bcopy = adapter.copy(B);
                startTime = System.nanoTime();
                Xp = MyMatrix.GaussJordanElimination_FullPivoting(adapter, Acopy, Bcopy);
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
