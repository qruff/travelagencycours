package com.travel.travel.dto;

public class MyOrderResponse {
    private String orderId;
    private int tourId;
    private int exploiterId;
    private String userName;
    private String exploiterPhone;
    private String tourName;
    private String tourDescription;
    private String tourImage;
    private int quantity;
    private String totalPrice;
    private String orderDate;
    private String guideDate;
    private String guideStatus;
    private String guidePersonName;
    private String guidePersonContact;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getTourId() {
        return tourId;
    }

    public void setTourId(int tourId) {
        this.tourId = tourId;
    }

    public int getExploiterId() {
        return exploiterId;
    }

    public void setExploiterId(int exploiterId) {
        this.exploiterId = exploiterId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getExploiterPhone() {
        return exploiterPhone;
    }

    public void setExploiterPhone(String exploiterPhone) {
        this.exploiterPhone = exploiterPhone;
    }

    public String getTourName() {
        return tourName;
    }

    public void setTourName(String tourName) {
        this.tourName = tourName;
    }

    public String getTourDescription() {
        return tourDescription;
    }

    public void setTourDescription(String tourDescription) {
        this.tourDescription = tourDescription;
    }

    public String getTourImage() {
        return tourImage;
    }

    public void setTourImage(String tourImage) {
        this.tourImage = tourImage;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getGuideDate() {
        return guideDate;
    }

    public void setGuideDate(String guideDate) {
        this.guideDate = guideDate;
    }

    public String getGuideStatus() {
        return guideStatus;
    }

    public void setGuideStatus(String guideStatus) {
        this.guideStatus = guideStatus;
    }

    public String getGuidePersonName() {
        return guidePersonName;
    }

    public void setGuidePersonName(String guidePersonName) {
        this.guidePersonName = guidePersonName;
    }

    public String getGuidePersonContact() {
        return guidePersonContact;
    }

    public void setGuidePersonContact(String guidePersonContact) {
        this.guidePersonContact = guidePersonContact;
    }
}
