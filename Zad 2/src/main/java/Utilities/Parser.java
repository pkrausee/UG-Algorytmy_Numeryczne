package Utilities;

import Adapters.DoubleAdapter;
import Adapters.FloatAdapter;
import Adapters.FractionAdapter;
import Adapters.IntegerAdapter;

import Models.Fraction;

public class Parser
{
    public static IntegerAdapter[] parse (Integer[] A)
    {
        System.out.println("Models.IntegerAdapter[]");

        IntegerAdapter[] f = new IntegerAdapter[A.length];

        for(int i = 0; i < A.length; i++)
        {
            f[i] = new IntegerAdapter(A[i]);
        }

        return f;
    }

    public static FloatAdapter[] parse (Float[] A)
    {
        System.out.println("Models.FloatAdapter[]");

        FloatAdapter[] f = new FloatAdapter[A.length];

        for(int i = 0; i < A.length; i++)
        {
            f[i] = new FloatAdapter(A[i]);
        }

        return f;
    }

    public static DoubleAdapter[] parse (Double[] A)
    {
        System.out.println("Models.DoubleAdapter[]");

        DoubleAdapter[] d = new DoubleAdapter[A.length];

        for(int i = 0; i < A.length; i++)
        {
            d[i] = new DoubleAdapter(A[i]);
        }

        return d;
    }

    public static FractionAdapter[] parse (Fraction[] A)
    {
        System.out.println("Models.FractionAdapter[]");

        FractionAdapter[] f = new FractionAdapter[A.length];

        for(int i = 0; i < A.length; i++)
        {
            f[i] = new FractionAdapter(A[i]);
        }

        return f;
    }

    public static IntegerAdapter[][] parse (Integer[][] A)
    {
        System.out.println("Models.IntegerAdapter[][]");

        IntegerAdapter[][] f = new IntegerAdapter[A.length][A[0].length];

        for(int i = 0; i < A.length; i++)
        {
            for(int j = 0; j < A[i].length; j++)
            {
                f[i][j] = new IntegerAdapter(A[i][j]);
            }
        }

        return f;
    }

    public static FloatAdapter[][] parse (Float[][] A)
    {
        System.out.println("Models.FloatAdapter[][]");

        FloatAdapter[][] f = new FloatAdapter[A.length][A[0].length];

        for(int i = 0; i < A.length; i++)
        {
            for(int j = 0; j < A[i].length; j++)
            {
                f[i][j] = new FloatAdapter(A[i][j]);
            }
        }

        return f;
    }

    public static DoubleAdapter[][] parse (Double[][] A)
    {
        System.out.println("Models.DoubleAdapter[][]");

        DoubleAdapter[][] d = new DoubleAdapter[A.length][A[0].length];

        for(int i = 0; i < A.length; i++)
        {
            for(int j = 0; j < A[i].length; j++)
            {
                d[i][j] = new DoubleAdapter(A[i][j]);
            }
        }

        return d;
    }

    public static FractionAdapter[][] parse (Fraction[][] A)
    {
        System.out.println("Models.FractionAdapter[][]");

        FractionAdapter[][] d = new FractionAdapter[A.length][A[0].length];

        for(int i = 0; i < A.length; i++)
        {
            for(int j = 0; j < A[i].length; j++)
            {
                d[i][j] = new FractionAdapter(A[i][j]);
            }
        }

        return d;
    }
}
