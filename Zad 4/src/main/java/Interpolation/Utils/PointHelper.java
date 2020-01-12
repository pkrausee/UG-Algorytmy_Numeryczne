package Interpolation.Utils;

import Interpolation.Model.Point;
import java.util.List;

public class PointHelper {

    public static double[] extractElevations(List<Point> content) {
        double[] elevations = new double[content.size()];

        int i = 0;
        for (Point point : content) {
            elevations[i] = point.getElevation();
            i++;
        }

        return elevations;
    }

    public static double[] createIndexes(List<Point> content) {
        double[] indexes = new double[content.size()];

        for (int i = 0; i < content.size(); i++) {
            indexes[i] = i;
        }

        return indexes;
    }

}
