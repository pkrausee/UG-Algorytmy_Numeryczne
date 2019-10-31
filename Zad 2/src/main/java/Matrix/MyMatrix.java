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
//            System.out.println("Models.Fraction");

            result = calculate(Parser.parse((Fraction[][]) A), Parser.parse((Fraction[]) B));
        }
        else if(A.getClass().equals(Float[][].class))
        {
//            System.out.println("Float");

            result = calculate(Parser.parse((Float[][]) A), Parser.parse((Float[]) B));
        }
        else if(A.getClass().equals(Double[][].class))
        {
//            System.out.println("Double");

            result = calculate(Parser.parse((Double[][]) A), Parser.parse((Double[]) B));
        }
        else if(A.getClass().equals(Integer[][].class))
        {
//            System.out.println("Integer");

            result = calculate(Parser.parse((Integer[][]) A), Parser.parse((Integer[]) B));
        }
        else
        {
//            System.out.println("Other");

            result = calculate(Parser.parseGen(A), Parser.parseGen(B));
        }

        return result;
    }

    private static Pair calculate (INumberAdapter[][] A, INumberAdapter[] B)
    {
        INumberAdapter ZERO = A[0][0].ZERO();
        INumberAdapter ONE = A[0][0].ONE();

        for(int x = 0; x < A.length; x++)
        {
            if(!A[x][x].isZero())
            {
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
        }

        return new Pair<INumberAdapter[][], INumberAdapter[]>(A, B);
    }

    private static void gauss_NoPivoting(INumberAdapter[][] A, INumberAdapter[] B, int pos)
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
    }

    private static void gauss_PartialPivoting(INumberAdapter[][] A, INumberAdapter[] B, int pos)
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
    }

    private static void gauss_FullPivoting(INumberAdapter[][] A, INumberAdapter[] B, int pos)
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
















