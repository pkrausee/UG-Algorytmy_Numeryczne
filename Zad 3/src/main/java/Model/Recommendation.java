package Model;

public class Recommendation {
    private Integer productId;
    private String userId;
    private Double rating;

    public Recommendation(Integer productId, String userId, Double rating) {
        this.productId = productId;
        this.userId = userId;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return this.productId + " | " + this.userId + " | " + this.rating;
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
}
