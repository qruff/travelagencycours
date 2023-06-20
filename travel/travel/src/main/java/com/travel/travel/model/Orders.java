package com.travel.travel.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CollectionId;

@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "orderId")
    private String orderId;

    @OneToOne
    @JoinColumn(name = "tour_id")
    private Tour tour;

    @OneToOne
    @JoinColumn(name = "exploiter_id")
    private Exploiter exploiter;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "orderDate")
    private String orderDate;

    @Column(name = "guideStatus")
    private String guideStatus;

    @Column(name = "guideDate")
    private String guideDate;

    @Column(name = "guideTime")
    private String guideTime;

    @Column(name = "guideAssigned")
    private String guideAssigned;

    @Column(name = "guidePersonId")
    private int guidePersonId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public Exploiter getExploiter() {
        return exploiter;
    }

    public void setExploiter(Exploiter exploiter) {
        this.exploiter = exploiter;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getGuideStatus() {
        return guideStatus;
    }

    public void setGuideStatus(String guideStatus) {
        this.guideStatus = guideStatus;
    }

    public String getGuideDate() {
        return guideDate;
    }

    public void setGuideDate(String guideDate) {
        this.guideDate = guideDate;
    }

    public String getGuideTime() {
        return guideTime;
    }

    public void setGuideTime(String guideTime) {
        this.guideTime = guideTime;
    }

    public String getGuideAssigned() {
        return guideAssigned;
    }

    public void setGuideAssigned(String guideAssigned) {
        this.guideAssigned = guideAssigned;
    }

    public int getGuidePersonId() {
        return guidePersonId;
    }

    public void setGuidePersonId(int guidePersonId) {
        this.guidePersonId = guidePersonId;
    }
}
