package Matrix;

import Models.PairResult;

import Adapters.INumberAdapter;
import Utilities.CollectionUtilities;


public class MyMatrix<TType extends Number>
{
    private INumberAdapter<TType> adapter;

    private TType[][] A;
    private TType[] B;
    private Character[] X;

    public MyMatrix(INumberAdapter<TType> adapter, TType[][] A, TType[] B, Character[] X)
    {
        this.adapter = adapter;

        this.A = A;
        this.B = B;
        this.X = X;
    }

    public static <TType extends Number> PairResult<TType[][], TType[]> GaussJordanElimination (
            INumberAdapter<TType> adapter,
            TType[][] A,
            TType[] B,
            Character[] X)
    {
        // This method always uses DoubleAdapter type to parse given matrix and FullPivoting method

        return GaussJordanElimination_FullPivoting(adapter, A, B, X);
    }

    public PairResult<TType[][], TType[]> GaussJordanElimination()
    {
        return GaussJordanElimination_FullPivoting(this.adapter, this.A, this.B, this.X);
    }

    public PairResult<TType[][], TType[]> GaussJordanElimination_NoPivoting()
    {
        return GaussJordanElimination_NoPivoting(this.adapter, this.A, this.B, this.X);
    }

    public static <TType extends Number> PairResult<TType[][], TType[]> GaussJordanElimination_NoPivoting(
            INumberAdapter<TType> adapter,
            TType[][] A,
            TType[] B,
            Character[] X)
    {
        for(int pos = 0; pos < A.length; pos++)
        {
            if(adapter.isZero(A[pos][pos]))
            {
                int destRow = pos;
                while(destRow < A.length && adapter.isZero(A[destRow][pos]))
                {
                    destRow++;
                }

                if(destRow < A.length && destRow != pos)
                {
                    swapRows(B, pos, destRow);
                    swapRows(A, pos, destRow);
                }

                int destCol = pos;
                while(destCol < A.length && adapter.isZero(A[pos][destCol]))
                {
                    destCol++;
                }

                if(destCol < A.length && destCol != pos)
                {
                    swapCols(A, pos, destCol);
                    swapRows(X, pos, destCol);
                }
            }

            eliminate(adapter, A, B, pos);
        }

        return new PairResult<TType[][], TType[]>(A, B);
    }

    public PairResult<TType[][], TType[]> GaussJordanElimination_PartialPivoting()
    {
        return GaussJordanElimination_PartialPivoting(this.adapter, this.A, this.B);
    }

    public static <TType extends Number> PairResult<TType[][], TType[]> GaussJordanElimination_PartialPivoting(
            INumberAdapter<TType> adapter,
            TType[][] A,
            TType[] B)
    {
        for(int pos = 0; pos < A.length; pos++)
        {
            int max = pos;

            for(int i = pos; i < A.length; i++)
            {
                if(adapter.compareTo(A[max][pos], A[i][pos]) < 0)
                {
                    max = i;
                }
            }

            swapRows(B, pos, max);
            swapRows(A, pos, max);

            eliminate(adapter, A, B, pos);
        }

        return new PairResult<TType[][], TType[]>(A, B);
    }

    public PairResult<TType[][], TType[]> GaussJordanElimination_FullPivoting()
    {
        return GaussJordanElimination_FullPivoting(this.adapter, this.A, this.B, this.X);
    }

    public static <TType extends Number> PairResult<TType[][], TType[]> GaussJordanElimination_FullPivoting(
            INumberAdapter<TType> adapter,
            TType[][] A,
            TType[] B,
            Character[] X)
    {
        for(int pos = 0; pos < A.length; pos++)
        {
            int maxR = pos;
            int maxC = pos;

            for(int i = pos; i < A.length; i++)
            {
                if(adapter.compareTo(A[maxR][pos], A[i][pos]) < 0)
                {
                    maxR = i;
                }

                if(adapter.compareTo(A[pos][maxC], A[pos][i]) < 0)
                {
                    maxC = i;
                }
            }

            if(adapter.compareTo(A[maxR][pos], A[pos][maxC]) < 0)
            {
                swapCols(A, pos, maxC);
                swapRows(X, pos, maxC);
            }
            else
            {
                swapRows(B, pos, maxR);
                swapRows(A, pos, maxR);
            }

            eliminate(adapter, A, B, pos);
        }

        return new PairResult<TType[][], TType[]>(A, B);
    }

    private static <TType extends Number> void eliminate(
            INumberAdapter<TType> adapter,
            TType[][] A,
            TType[] B,
            int pos)
    {
        if(!adapter.isZero(A[pos][pos]))
        {
            CollectionUtilities.show(A, B);

            for(int i = pos + 1; i < A[0].length + 1; i++)
            {
                if(i < A[0].length)
                {
                    A[pos][i] = adapter.divide(A[pos][i], A[pos][pos]);
                }
                else
                {
                    B[pos] = adapter.divide(B[pos], A[pos][pos]);
                }
            }

            A[pos][pos] = adapter.ONE();

            for(int i = 0; i < A.length; i++)
            {
                if(i != pos)
                {
                    TType count = adapter.multiply(A[i][pos], A[pos][pos]);

                    A[i][pos] = adapter.ZERO();

                    for(int j = pos + 1; j < A[i].length + 1; j++)
                    {
                        if(j < A[i].length)
                        {
                            A[i][j] = adapter.subtract(A[i][j], adapter.multiply(A[pos][j], count));
                        }
                        else
                        {
                            B[i] = adapter.subtract(B[i], adapter.multiply(B[pos], count));
                        }
                    }
                }
            }
        }
    }

    private static <TType> void swapRows(TType[] A, int src, int dest)
    {
        TType temp = A[src];
        A[src] = A[dest];
        A[dest] = temp;
    }

    private static <TType> void swapRows(TType[][] A, int src, int dest)
    {
        TType[] temp = A[src];
        A[src] = A[dest];
        A[dest] = temp;
    }

    private static <TType> void swapCols(TType[][] A, int src, int dest)
    {
        for(int i = 0; i < A.length; i++)
        {
            TType temp = A[i][src];
            A[i][src] = A[i][dest];
            A[i][dest] = temp;
        }
    }
}
















