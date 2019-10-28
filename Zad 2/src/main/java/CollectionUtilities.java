import Models.DoubleAdapter;
import Models.FloatAdapter;
import Models.IntegerAdapter;

public abstract class CollectionUtilities
{
    public static <TType> void show (TType[][] A, TType[] B)
    {
        for(int i = 0; i < A.length; i++)
        {
            for(int j = 0; j < A[i].length + 1; j++)
            {
                if(j < A[i].length)
                {
                    System.out.print(A[i][j] + " ");
                }
                else
                {
                    System.out.print(B[i] + " ");
                }
            }

            System.out.println();
        }
        System.out.println("-----------------------------");
    }

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
}
