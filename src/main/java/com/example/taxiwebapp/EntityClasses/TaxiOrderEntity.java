package com.example.taxiwebapp.EntityClasses;


import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Timestamp;

@Table("taxiorder")
public class TaxiOrderEntity {
    @Id
    private Long id;
    private Long statusId;
    private Double orderLatitude;
    private Double orderLongitude;
    private Double arrivalLatitude;
    private Double arrivalLongitude;
    private Long recipientId;
    private Long senderId;
    private Double price;
    private Timestamp orderDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    public Double getOrderLatitude() {
        return orderLatitude;
    }

    public void setOrderLatitude(Double orderLatitude) {
        this.orderLatitude = orderLatitude;
    }

    public Double getOrderLongitude() {
        return orderLongitude;
    }

    public void setOrderLongitude(Double orderLongitude) {
        this.orderLongitude = orderLongitude;
    }

    public Double getArrivalLatitude() {
        return arrivalLatitude;
    }

    public void setArrivalLatitude(Double arrivalLatitude) {
        this.arrivalLatitude = arrivalLatitude;
    }

    public Double getArrivalLongitude() {
        return arrivalLongitude;
    }

    public void setArrivalLongitude(Double arrivalLongitude) {
        this.arrivalLongitude = arrivalLongitude;
    }

    public Long getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(Long recipientId) {
        this.recipientId = recipientId;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }
}