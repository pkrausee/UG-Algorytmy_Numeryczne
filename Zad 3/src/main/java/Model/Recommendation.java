package Model;

public class Recommendation implements Comparable<Recommendation> {
    private Integer productId;
    private String userId;
    private Double rating;
    private String group;

    public Recommendation(Integer productId, String userId, Double rating, String group) {
        this.productId = productId;
        this.userId = userId;
        this.rating = rating;
        this.group = group;
    }

    @Override
    public String toString() {
        return this.productId + " | " + this.userId + " | " + this.rating;
    }

    @Override
    public int compareTo(Recommendation r2) {
        return this.userId.compareTo(r2.getUserId());
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
