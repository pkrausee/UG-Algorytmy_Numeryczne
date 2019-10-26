public class MyMatrix
{
    public static <TType extends Number> void gauss (TType[][] A, char[] X, TType[] B)
    {
        if(A.getClass().equals(Fraction[][].class))
        {
            System.out.println("Fraction");

            calculate((Fraction[][]) A, (Fraction[]) B);
        }
        else if(A.getClass().equals(Float[][].class))
        {
            System.out.println("Float");

            calculate(CollectionUtilities.parse((Float[][]) A), CollectionUtilities.parse((Float[]) B));
        }
        else if(A.getClass().equals(Double[][].class))
        {
            System.out.println("Double");

            calculate(CollectionUtilities.parse((Double[][]) A), CollectionUtilities.parse((Double[]) B));
        }
        else if(A.getClass().equals(Integer[][].class))
        {
            System.out.println("Integer");

            calculate(CollectionUtilities.parse((Integer[][]) A), CollectionUtilities.parse((Integer[]) B));
        }
    }

    private static  <TType extends IUsableNumber<TType>> void calculate (TType[][] A, TType[] B)
    {
        CollectionUtilities.show(A, B);

        TType ZERO = A[0][0].ZERO();
        TType ONE = A[0][0].ONE();

        for(int x = 0; x < A.length; x++)
        {
            if(A[x][x].isZero())
            {
                System.out.println("Brak rozwiazania");

                break;
            }

            for(int i = x + 1; i < A[0].length + 1; i++)
            {
                if(i < A[0].length)
                {
                    A[x][i] = A[x][i].divide(A[x][x]);
                }
                else
                {
                    System.out.println("oasdasdasdasd");

                    B[x] = B[x].divide(A[x][x]);
                }
            }

            A[x][x] = ONE;

            for(int i = 0; i < A.length; i++)
            {
                if(i != x)
                {
                    TType count = A[i][x].multiply(A[x][x]);

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

        CollectionUtilities.show(A, B);
    }
}
