package org.example.model.dto;

public class ShopDTO {
    private int no;
    private String name;
    private int score;
    private String review;

    public ShopDTO() {}

    public ShopDTO(String name, int score, String review) {
        this.name = name;
        this.score = score;
        this.review = review;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    @Override
    public String toString() {
        return "shopDTO [no=" + no + ", name=" + name + ", score=" + score + ", review=" + review  + "]";
    }
}
