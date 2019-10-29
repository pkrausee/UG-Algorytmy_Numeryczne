package Matrix;

import Models.Fraction;
import Models.Pair;

import Adapters.INumberAdapter;

import Utilities.Parser;


public class MyMatrix
{
    public static <TType extends Number> Pair gauss (TType[][] A, char[] X, TType[] B)
    {
        Pair result = new Pair<INumberAdapter[][], INumberAdapter[]>();

        if(A.getClass().equals(Fraction[][].class))
        {
            System.out.println("Models.Fraction");

            result = calculate(Parser.parse((Fraction[][]) A), Parser.parse((Fraction[]) B));
        }
        else if(A.getClass().equals(Float[][].class))
        {
            System.out.println("Float");

            result = calculate(Parser.parse((Float[][]) A), Parser.parse((Float[]) B));
        }
        else if(A.getClass().equals(Double[][].class))
        {
            System.out.println("Double");

            result = calculate(Parser.parse((Double[][]) A), Parser.parse((Double[]) B));
        }
        else if(A.getClass().equals(Integer[][].class))
        {
            System.out.println("Integer");

            result = calculate(Parser.parse((Integer[][]) A), Parser.parse((Integer[]) B));
        }

        return result;
    }

    private static Pair calculate (INumberAdapter[][] A, INumberAdapter[] B)
    {
        INumberAdapter ZERO = A[0][0].ZERO();
        INumberAdapter ONE = A[0][0].ONE();

        for(int x = 0; x < A.length; x++)
        {
            int destRow = x;
            while(destRow < A.length && A[destRow][x].isZero())
            {
                destRow++;
            }

            if(destRow != x)
            {
                swapRows(B, x, destRow);
                swapRows(A, x, destRow);
            }

            for(int i = x + 1; i < A[0].length + 1; i++)
            {
                if(i < A[0].length)
                {
                    A[x][i] = A[x][i].divide(A[x][x]);
                }
                else
                {
                    B[x] = B[x].divide(A[x][x]);
                }
            }

            A[x][x] = ONE;

            for(int i = 0; i < A.length; i++)
            {
                if(i != x)
                {
                    INumberAdapter count = A[i][x].multiply(A[x][x]);

                    A[i][x] = ZERO;

                    for(int j = x + 1; j < A[i].length + 1; j++)
                    {
                        if(j < A[i].length)
                        {
                            A[i][j] = A[i][j].subtract(A[x][j].multiply(count));
                        }
                        else
                        {
                            B[i] = B[i].subtract(B[x].multiply(count));
                        }
                    }
                }
            }
        }

        return new Pair<INumberAdapter[][], INumberAdapter[]>(A, B);
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
}
