public class Main
{
    public static void main(String[] args)
    {
        Double[][] A =
        {
                {1.0, 2.0, 3.0},
                {4.0, 5.0, 6.0},
                {7.0, 8.0, 9.0}
        };

        char[] X = {'x', 'y', 'z'};

        Double[] B = {10.0, 11.0, 12.0};

        MyMatrix.gauss(A, X, B);
    }
}
