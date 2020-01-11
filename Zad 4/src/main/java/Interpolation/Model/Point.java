package Interpolation.Model;

public class Point {
    private double elevation;
    private Location location;
    private double resolution;

    public Point() {
    }

    public Point(double elevation, Location location, double resolution) {
        this.elevation = elevation;
        this.location = location;
        this.resolution = resolution;
    }

    public double getElevation() {
        return elevation;
    }

    public void setElevation(double elevation) {
        this.elevation = elevation;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public double getResolution() {
        return resolution;
    }

    public void setResolution(double resolution) {
        this.resolution = resolution;
    }
}
