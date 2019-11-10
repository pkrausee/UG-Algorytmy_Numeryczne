import Adapters.DoubleAdapter;
import Adapters.FloatAdapter;
import Adapters.FractionAdapter;

import Matrix.MatrixGenerator;
import Matrix.MyMatrixV2;

import Models.Fraction;

public class TimingsTest {

    public static void main(String[] args) {
        int i = 500;
        int j = 100;

        DoubleAdapter doubleAdapter = new DoubleAdapter();
        FloatAdapter floatAdapter = new FloatAdapter();
        FractionAdapter fractionAdapter = new FractionAdapter();

        Double[][] doubleArr = new Double[i][i];
        Double[] doubleVec = new Double[i];

        MatrixGenerator.generateValues(doubleArr);
        MatrixGenerator.generateValues(doubleVec);

        Float[][] floatArr = new Float[i][i];
        Float[] floatVec = new Float[i];

        MatrixGenerator.generateValues(floatArr);
        MatrixGenerator.generateValues(floatVec);

        Fraction[][] fractionArr = new Fraction[j][j];
        Fraction[][] fraction2Arr = new Fraction[j + 10][j + 10];
        Fraction[][] fraction3Arr = new Fraction[j + 20][j + 20];
        Fraction[] fractionVec = new Fraction[j];
        Fraction[] fraction2Vec = new Fraction[j + 20];
        Fraction[] fraction3Vec = new Fraction[j + 20];

        MatrixGenerator.generateValues(fractionArr);
        MatrixGenerator.generateValues(fraction2Arr);
        MatrixGenerator.generateValues(fraction3Arr);
        MatrixGenerator.generateValues(fractionVec);
        MatrixGenerator.generateValues(fraction2Vec);
        MatrixGenerator.generateValues(fraction3Vec);

        double startTime, NPTime, PPTime, FPTime;

        startTime = System.nanoTime();
        MyMatrixV2.GaussJordanElimination_NoPivoting(doubleAdapter, doubleArr, doubleVec);
        NPTime = (System.nanoTime() - startTime) / 1000000000.0;

        startTime = System.nanoTime();
        MyMatrixV2.GaussJordanElimination_PartialPivoting(doubleAdapter, doubleArr, doubleVec);
        PPTime = (System.nanoTime() - startTime) / 1000000000.0;

        startTime = System.nanoTime();
        MyMatrixV2.GaussJordanElimination_FullPivoting(doubleAdapter, doubleArr, doubleVec);
        FPTime = (System.nanoTime() - startTime) / 1000000000.0;

        System.out.println("Double " + NPTime + " " + PPTime + " " + FPTime);

        startTime = System.nanoTime();
        MyMatrixV2.GaussJordanElimination_NoPivoting(floatAdapter, floatArr, floatVec);
        NPTime = (System.nanoTime() - startTime) / 1000000000.0;

        startTime = System.nanoTime();
        MyMatrixV2.GaussJordanElimination_PartialPivoting(floatAdapter, floatArr, floatVec);
        PPTime = (System.nanoTime() - startTime) / 1000000000.0;

        startTime = System.nanoTime();
        MyMatrixV2.GaussJordanElimination_FullPivoting(floatAdapter, floatArr, floatVec);
        FPTime = (System.nanoTime() - startTime) / 1000000000.0;

        System.out.println("Float " + NPTime + " " + PPTime + " " + FPTime);

        startTime = System.nanoTime();
        MyMatrixV2.GaussJordanElimination_NoPivoting(fractionAdapter, fractionArr, fractionVec);
        NPTime = (System.nanoTime() - startTime) / 1000000000.0;

        startTime = System.nanoTime();
        MyMatrixV2.GaussJordanElimination_PartialPivoting(fractionAdapter, fractionArr, fractionVec);
        PPTime = (System.nanoTime() - startTime) / 1000000000.0;

        startTime = System.nanoTime();
        MyMatrixV2.GaussJordanElimination_FullPivoting(fractionAdapter, fractionArr, fractionVec);
        FPTime = (System.nanoTime() - startTime) / 1000000000.0;

        System.out.println("Fraction (100) " + NPTime + " " + PPTime + " " + FPTime);

        startTime = System.nanoTime();
        MyMatrixV2.GaussJordanElimination_NoPivoting(fractionAdapter, fraction2Arr, fraction2Vec);
        NPTime = (System.nanoTime() - startTime) / 1000000000.0;

        startTime = System.nanoTime();
        MyMatrixV2.GaussJordanElimination_PartialPivoting(fractionAdapter, fraction2Arr, fraction2Vec);
        PPTime = (System.nanoTime() - startTime) / 1000000000.0;

        startTime = System.nanoTime();
        MyMatrixV2.GaussJordanElimination_FullPivoting(fractionAdapter, fraction2Arr, fraction2Vec);
        FPTime = (System.nanoTime() - startTime) / 1000000000.0;

        System.out.println("Fraction (110) " + NPTime + " " + PPTime + " " + FPTime);

        startTime = System.nanoTime();
        MyMatrixV2.GaussJordanElimination_NoPivoting(fractionAdapter, fraction3Arr, fraction3Vec);
        NPTime = (System.nanoTime() - startTime) / 1000000000.0;

        startTime = System.nanoTime();
        MyMatrixV2.GaussJordanElimination_PartialPivoting(fractionAdapter, fraction3Arr, fraction3Vec);
        PPTime = (System.nanoTime() - startTime) / 1000000000.0;

        startTime = System.nanoTime();
        MyMatrixV2.GaussJordanElimination_FullPivoting(fractionAdapter, fraction3Arr, fraction3Vec);
        FPTime = (System.nanoTime() - startTime) / 1000000000.0;

        System.out.println("Fraction (120) " + NPTime + " " + PPTime + " " + FPTime);
    }
}
