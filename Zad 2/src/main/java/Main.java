public class Main
{
    public static void main(String[] args)
    {
        Integer[][] A =
        {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        char[] X = {'x', 'y', 'z'};

        Integer[] B = {10, 11, 12};

        MyMatrix.gauss(A, X, B);
    }


}
