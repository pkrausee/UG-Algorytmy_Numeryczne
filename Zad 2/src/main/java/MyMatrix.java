public class MyMatrix
{
    public static  <TTyp extends Number> void gauss (TTyp[][] A, char[] X, TTyp[] B)
    {
        Double[][] mergedMatrix = new Double[A.length][A.length + 1];

        //merge
        for(int i = 0; i < mergedMatrix.length; i++)
        {
            for(int j = 0; j < mergedMatrix[i].length; j++)
            {
                mergedMatrix[i][j] = (j < A[i].length) ? A[i][j].doubleValue() : B[i].doubleValue();
            }
        }

        show(mergedMatrix);

        for(int x = 0; x < mergedMatrix.length; x++)
        {
            if(mergedMatrix[x][x] == 0)
            {
                System.out.println("Brak rozwiazania");

                break;
            }

            //podziel wiersz przez obecny element
            for(int i = x + 1; i < mergedMatrix[0].length; i++)
            {
                mergedMatrix[x][i] = mergedMatrix[x][i] / mergedMatrix[x][x];
            }

            mergedMatrix[x][x] = 1.0;

            //wyzeruj wiersz ponizej
            for(int i = 0; i < mergedMatrix.length; i++)
            {
                if(i != x)
                {
                    double count = mergedMatrix[i][x] * mergedMatrix[x][x];
                    mergedMatrix[i][x] = 0.0;

                    for(int j = x + 1; j < mergedMatrix[i].length; j++)
                    {
                        mergedMatrix[i][j] -= mergedMatrix[x][j] * count;
                    }

                    show(mergedMatrix);
                }
            }
        }

        show(mergedMatrix);
    }

    private static void show(Double[][] matrix)
    {
        for (Double[] row : matrix)
        {
            for (Double value : row)
            {
                System.out.print(String.format("%.1f", value) + " ");
            }
            System.out.println();
        }
        System.out.println("----------------");
    }
}


