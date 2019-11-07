package MatrixTests;

import Adapters.DoubleAdapter;
import Adapters.IntegerAdapter;
import Matrix.MatrixUtilities;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MatrixUtilitiesTests {

    @org.junit.Test
    public void swapRowsTest_ForSingleDimension() {

        Integer[] arr = new Integer[] { 1,2,3,4 };

        Integer[] expected = new Integer[] { 1, 4, 3, 2 };

        MatrixUtilities.swapRows(arr, 1, 3);

        assertThat(arr, is(expected));

    }

    @org.junit.Test
    public void swapRowsTest_ForTwoDimension() {

        Integer[][] arr = new Integer[][] {
                { 1, 2, 3},
                { 4, 5, 6},
                { 7, 8, 9}
        };

        Integer[][] expected = new Integer[][] {
                { 7, 8, 9},
                { 4, 5, 6},
                { 1, 2, 3}
        };

        MatrixUtilities.swapRows(arr, 0, 2);

        assertThat(arr, is(expected));

    }

    @org.junit.Test
    public void swapColsTest() {

        Integer[][] arr = new Integer[][] {
                { 1, 2, 3},
                { 4, 5, 6},
                { 7, 8, 9}
        };

        Integer[][] expected = new Integer[][] {
                { 3, 2, 1},
                { 6, 5, 4},
                { 9, 8, 7}
        };

        MatrixUtilities.swapCols(arr, 0, 2);

        assertThat(arr, is(expected));

    }

    @org.junit.Test
    public void multiplyByVectorTest() {

        Integer[][] arr = new Integer[][] {
                { 1, 1, 1},
                { 2, 2, 2},
                { 3, 3, 3}
        };

        Integer[] vector = new Integer[] { 1, 2, 3 };

        Integer[] expected = new Integer[] { 6, 12, 18 };

        Integer[] result = MatrixUtilities.multiplyByVector(Integer.class, new IntegerAdapter(), arr, vector);

        assertThat(result, is(expected));

    }

    @org.junit.Test
    public void transposeTest() {

        Integer[][] arr = new Integer[][] {
                { 1, 1, 1},
                { 2, 2, 2},
                { 3, 3, 3}
        };

        Integer[][] expected = new Integer[][] {
                { 1, 2, 3},
                { 1, 2, 3},
                { 1, 2, 3}
        };

        Integer[][] actual = MatrixUtilities.transpose(Integer.class, arr);

        assertThat(actual, is(expected));

    }

    @org.junit.Test
    public void normTest() {

        Double[] vector = new Double[] { 2d, 2d, 2d, 2d };

        Double expected = 4d;

        Double result = MatrixUtilities.norm(Double.class, new DoubleAdapter(), vector);

        assertThat(result, is(expected));

    }

    @org.junit.Test
    public void avgTest() {

        Double[] vector = new Double[] { 2d, 2d, 2d, 2d };

        Double expected = 2d;

        Double result = MatrixUtilities.avg(vector);

        assertThat(result, is(expected));

    }

    @org.junit.Test
    public void subtractTest() {

        Integer[] arr1 = new Integer[] { 1, 2, 3, 4 };

        Integer[] arr2 = new Integer[] { 1, 4, 3, 2 };

        Integer[] expected = new Integer[] { 0, -2, 0, 2 };

        Integer[] result = MatrixUtilities.subtract(Integer.class, new IntegerAdapter(), arr1, arr2);

        assertThat(result, is(expected));

    }
}
