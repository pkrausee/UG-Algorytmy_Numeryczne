package Matrix;

import Models.Fraction;
import Models.PairResult;

import Adapters.INumberAdapter;

import Utilities.Parser;


public class MyMatrix
{
    private INumberAdapter[][] A;
    private INumberAdapter[] B;
    private char[] X;

    public MyMatrix(INumberAdapter[][] A, INumberAdapter[] B, char[] X)
    {
        this.A = A;
        this.B = B;
        this.X = X;
    }

    public MyMatrix (Fraction[][] A, Fraction[] B, char[] X)
    {
        this(Parser.parse(A), Parser.parse(B), X);
    }

    public MyMatrix (Float[][] A, Float[] B, char[] X)
    {
        this(Parser.parse(A), Parser.parse(B), X);
    }

    public MyMatrix (Double[][] A, Double[] B, char[] X)
    {
        this(Parser.parse(A), Parser.parse(B), X);
    }

    public MyMatrix (Integer[][] A, Integer[] B, char[] X)
    {
        this(Parser.parse(A), Parser.parse(B), X);
    }

    public static <TType extends Number> PairResult GaussJordanElimination (TType[][] A, TType[] B, char[] X)
    {
        // This method always uses DoubleAdapter type to parse given matrix and FullPivoting method

        return GaussJordanElimination_FullPivoting(Parser.parseGen(A), Parser.parseGen(B), X);
    }

    public PairResult GaussJordanElimination()
    {
        return GaussJordanElimination_FullPivoting(this.A, this.B, this.X);
    }

    public PairResult GaussJordanElimination_NoPivoting()
    {
        return GaussJordanElimination_NoPivoting(this.A, this.B, this.X);
    }

    public static PairResult GaussJordanElimination_NoPivoting(INumberAdapter[][] A, INumberAdapter[] B, char[] X)
    {
        INumberAdapter ZERO = A[0][0].ZERO();
        INumberAdapter ONE = A[0][0].ONE();

        for(int pos = 0; pos < A.length; pos++)
        {
            if(A[pos][pos].isZero())
            {
                int destRow = pos;
                while(destRow < A.length && A[destRow][pos].isZero())
                {
                    destRow++;
                }

                if(destRow < A.length && destRow != pos)
                {
                    swapRows(B, pos, destRow);
                    swapRows(A, pos, destRow);
                }

                int destCol = pos;
                while(destCol < A.length && A[pos][destCol].isZero())
                {
                    destCol++;
                }

                if(destCol < A.length && destCol != pos)
                {
                    swapCols(A, pos, destCol);
                }
            }

            eliminate(A, B, ZERO, ONE, X, pos);
        }

        return new PairResult<INumberAdapter[][], INumberAdapter[]>(A, B);
    }

    public PairResult GaussJordanElimination_PartialPivoting()
    {
        return GaussJordanElimination_PartialPivoting(this.A, this.B, this.X);
    }

    public static PairResult GaussJordanElimination_PartialPivoting(INumberAdapter[][] A, INumberAdapter[] B, char[] X)
    {
        INumberAdapter ZERO = A[0][0].ZERO();
        INumberAdapter ONE = A[0][0].ONE();

        for(int pos = 0; pos < A.length; pos++)
        {
            int max = pos;

            for(int i = pos; i < A.length; i++)
            {
                if(A[max][pos].compareTo(A[i][pos]) < 0)
                {
                    max = i;
                }
            }

            swapRows(B, pos, max);
            swapRows(A, pos, max);

            eliminate(A, B, ZERO, ONE, X, pos);
        }

        return new PairResult<INumberAdapter[][], INumberAdapter[]>(A, B);
    }

    public PairResult GaussJordanElimination_FullPivoting()
    {
        return GaussJordanElimination_FullPivoting(this.A, this.B, this.X);
    }

    public static PairResult GaussJordanElimination_FullPivoting(INumberAdapter[][] A, INumberAdapter[] B, char[] X)
    {
        INumberAdapter ZERO = A[0][0].ZERO();
        INumberAdapter ONE = A[0][0].ONE();

        for(int pos = 0; pos < A.length; pos++)
        {
            int maxR = pos;
            int maxC = pos;

            for(int i = pos; i < A.length; i++)
            {
                if(A[maxR][pos].compareTo(A[i][pos]) < 0)
                {
                    maxR = i;
                }

                if(A[pos][maxC].compareTo(A[pos][i]) < 0)
                {
                    maxC = i;
                }
            }

            if(A[maxR][pos].compareTo(A[pos][maxC]) < 0)
            {
                swapCols(A, pos, maxC);
            }
            else
            {
                swapRows(B, pos, maxR);
                swapRows(A, pos, maxR);
            }

            eliminate(A, B, ZERO, ONE, X, pos);
        }

        return new PairResult<INumberAdapter[][], INumberAdapter[]>(A, B);
    }

    private static void eliminate(
            INumberAdapter[][] A,
            INumberAdapter[] B,
            INumberAdapter ZERO,
            INumberAdapter ONE,
            char[] X,
            int pos)
    {
        if(!A[pos][pos].isZero())
        {
            for(int i = pos + 1; i < A[0].length + 1; i++)
            {
                if(i < A[0].length)
                {
                    A[pos][i] = A[pos][i].divide(A[pos][pos]);
                }
                else
                {
                    B[pos] = B[pos].divide(A[pos][pos]);
                }
            }

            A[pos][pos] = ONE;

            for(int i = 0; i < A.length; i++)
            {
                if(i != pos)
                {
                    INumberAdapter count = A[i][pos].multiply(A[pos][pos]);

                    A[i][pos] = ZERO;

                    for(int j = pos + 1; j < A[i].length + 1; j++)
                    {
                        if(j < A[i].length)
                        {
                            A[i][j] = A[i][j].subtract(A[pos][j].multiply(count));
                        }
                        else
                        {
                            B[i] = B[i].subtract(B[pos].multiply(count));
                        }
                    }
                }
            }
        }
    }

    private static void swapRows(INumberAdapter[] A, int src, int dest)
    {
        INumberAdapter temp = A[src];
        A[src] = A[dest];
        A[dest] = temp;
    }

    private static void swapRows(INumberAdapter[][] A, int src, int dest)
    {
        INumberAdapter[] temp = A[src];
        A[src] = A[dest];
        A[dest] = temp;
    }

    private static void swapCols(INumberAdapter[][] A, int src, int dest)
    {
        for(int i = 0; i < A.length; i++)
        {
            INumberAdapter temp = A[i][src];
            A[i][src] = A[i][dest];
            A[i][dest] = temp;
        }
    }
}
















