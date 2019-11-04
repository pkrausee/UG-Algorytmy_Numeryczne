import Matrix.MatrixGenerator;
import Models.Fraction;
import Utilities.CollectionUtilities;

public class Main
{
    public static void main (String[] args)
    {
        Fraction[] results = new Fraction[2];
        Fraction[][] matrix = new Fraction[2][2];

        MatrixGenerator.generateValues(results, 2, 4);
        MatrixGenerator.generateValues(matrix, 2, 4);

        CollectionUtilities.show(matrix, results);
    }
}
