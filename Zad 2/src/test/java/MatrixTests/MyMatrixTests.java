package MatrixTests;

import Adapters.DoubleAdapter;
import Matrix.MyMatrix;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MyMatrixTests {

    @InjectMocks
    private MyMatrix<Double> myMatrix;

    private final static DoubleAdapter doubleAdapter = new DoubleAdapter();

    @org.junit.Test
    public void eliminateTest_ForDoubleType() {

        Double[][] A = new Double[][] {
                { 1d, 2d, 3d},
                { 4d, 5d, 6d},
                { 7d, 8d, 9d}
        };

        Double[] B = new Double[] { 11d, 12d, 13d};

        Double[][] expectedA = new Double[][] {
                { 1d, 2d, 3d},
                { 0d, -3d, -6d},
                { 0d, -6d, -12d}
        };

        Double[] expectedB = new Double[] { 11d, -32d, -64d };

        MyMatrix.eliminate(doubleAdapter, A, B, 0);

        assertThat(A, is(expectedA));
        assertThat(B, is(expectedB));

    }

    @org.junit.Test
    public void GJE_NoPivoting_Test_ForDoubleType() {

        Double[][] A = new Double[][] {
                { 1d, 2d, 3d},
                { 4d, 5d, 6d},
                { 7d, 8d, 9d}
        };

        Double[] B = new Double[] { 11d, 12d, 13d};

        myMatrix.setAdapter(doubleAdapter);
        myMatrix.setA(A);
        myMatrix.setB(B);

        // tutaj jakis mock eliminate ale to gowno nie pozwala...

        Double[][] expectedA = new Double[][] {
                { 1d, 2d, 3d},
                { 4d, 5d, 6d},
                { 7d, 8d, 9d}
        };

        Double[] expectedB = new Double[] { 11d, 12d, 13d };

        myMatrix.GaussJordanElimination_NoPivoting();

        assertThat(A, is(expectedA));
        assertThat(B, is(expectedB));

    }








}
