package Utilities;

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
}
