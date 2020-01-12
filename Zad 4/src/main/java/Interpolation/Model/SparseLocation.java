package Interpolation.Model;

import java.util.Objects;

public class SparseLocation {
    private int lat;
    private int lng;

    public SparseLocation() {
    }

    public SparseLocation(int lat, int lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public int getLat() {
        return lat;
    }

    public void setLat(int lat) {
        this.lat = lat;
    }

    public int getLng() {
        return lng;
    }

    public void setLng(int lng) {
        this.lng = lng;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SparseLocation that = (SparseLocation) o;
        return lat == that.lat &&
                lng == that.lng;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lat, lng);
    }
}
