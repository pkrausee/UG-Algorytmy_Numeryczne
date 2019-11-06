import Adapters.DoubleAdapter;
import Adapters.FloatAdapter;
import Adapters.FractionAdapter;
import Matrix.MatrixGenerator;
import Matrix.MatrixUtilities;
import Matrix.MyMatrix;
import Models.Fraction;
import Models.ResultType;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Tests {

    private final int numberOfTests = 1000;

    @org.junit.Test
    public void TestMatrixForDoubleType() {
        DoubleAdapter adapter = new DoubleAdapter();
        ResultType<Double> results = new ResultType<>(new ArrayList<Double[]>());

        for (int i = 10; i < numberOfTests; i += 10) {

            if(i % 10 == 0)
            {
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

            results.results.add(new Double[]{
                    (double) i,
                    (double) NPTime, NPFail,
                    (double) PPTime, PPFail,
                    (double) FPTime, FPFail
            });
        }

        saveResults(results, "H2_Double");
    }

    @org.junit.Test
    public void TestMatrixForFloatType() {
        FloatAdapter adapter = new FloatAdapter();
        ResultType<Double> results = new ResultType<>(new ArrayList<Double[]>());

        for (int i = 10; i < numberOfTests; i += 10) {
            if(i % 10 == 0)
            {
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

            results.results.add(new Double[]{
                    (double) i,
                    (double) NPTime, NPFail,
                    (double) PPTime, PPFail,
                    (double) FPTime, FPFail
            });
        }

        saveResults(results, "H2_Float");
    }

    @org.junit.Test
    public void TestMatrixForFractionType() {
        FractionAdapter adapter = new FractionAdapter();
        ResultType<Double> results = new ResultType<>(new ArrayList<Double[]>());

        for (int i = 10; i < numberOfTests; i += 10) {
            if(i % 10 == 0)
            {
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

            results.results.add(new Double[]{
                    (double) i,
                    (double) NPTime, NPFail,
                    (double) PPTime, PPFail,
                    (double) FPTime, FPFail
            });
        }

        saveResults(results, "H2_Fraction");
    }

    private static <TType extends Number> void saveResults(ResultType<TType> results, String filename) {
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(filename + ".csv"))) {

            StringBuilder builder = new StringBuilder();
            for (TType[] result : results.results){
                for(TType r : result)
                {
                    builder.append(r);
                    builder.append("; ");
                }
                builder.append(System.lineSeparator());
            }

            writer.write(builder.toString().replace(".", ","));

            writer.flush();

            results.reset(new ArrayList<TType[]>());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
