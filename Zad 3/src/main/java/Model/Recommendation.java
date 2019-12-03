package Model;

public class Recommendation {
    private Integer userId;
    private Integer productId;
    private Double rating;

    public Recommendation(Integer userId, Integer productId, Double rating) {
        this.userId = userId;
        this.productId = productId;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return this.userId + " | " + this.productId + " | " + this.rating;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}
