package com.travel.travel.dto;

public class AddReviewRequest {
    private int exploiterId;
    private int tourId;
    private int star;
    private String review;

    public int getExploiterId() {
        return exploiterId;
    }

    public void setExploiterId(int exploiterId) {
        this.exploiterId = exploiterId;
    }

    public int getTourId() {
        return tourId;
    }

    public void setTourId(int tourId) {
        this.tourId = tourId;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
    @Override
    public String toString() {
        return "AddReviewRequest [exploiterId=" + exploiterId + ", tourId=" + tourId + ", star=" + star + ", review="
                + review + "]";
    }
}
