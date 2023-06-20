package com.travel.travel.dto;

public class AddToCartRequest {
    private int tourId;
    private int quantity;
    private int exploiterId;

    public int getTourId() {
        return tourId;
    }

    public void setTourId(int tourId) {
        this.tourId = tourId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getExploiterId() {
        return exploiterId;
    }

    public void setExploiterId(int exploiterId) {
        this.exploiterId = exploiterId;
    }
    @Override
    public String toString() {
        return "AddToCartRequest [tourId=" + tourId + ", quantity=" + quantity + ", exploiterId=" + exploiterId + "]";
    }
}
