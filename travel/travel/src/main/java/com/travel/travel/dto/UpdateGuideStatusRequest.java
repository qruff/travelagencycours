package com.travel.travel.dto;

public class UpdateGuideStatusRequest {
    private String orderId;
    private String guideStatus;
    private String guideTime;
    private String guideDate;
    private int excId;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getGuideStatus() {
        return guideStatus;
    }

    public void setGuideStatus(String guideStatus) {
        this.guideStatus = guideStatus;
    }

    public String getGuideTime() {
        return guideTime;
    }

    public void setGuideTime(String guideTime) {
        this.guideTime = guideTime;
    }

    public String getGuideDate() {
        return guideDate;
    }

    public void setGuideDate(String guideDate) {
        this.guideDate = guideDate;
    }

    public int getExcId() {
        return excId;
    }

    public void setExcId(int excId) {
        this.excId = excId;
    }
    @Override
    public String toString() {
        return "UpdateDeliveryStatusRequest [orderId=" + orderId + ", guideStatus=" + guideStatus
                + ", guideTime=" + guideTime + ", guideDate=" + guideDate + "]";
    }
}
