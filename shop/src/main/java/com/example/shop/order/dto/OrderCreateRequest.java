package com.example.shop.order.dto;


import jakarta.persistence.Column;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter

public class OrderCreateRequest {


    private LocalDateTime orderDate;

    private int totalPrice;

    private String status;

    private int pointUsed;

    private int cashAmount;

    public OrderCreateRequest(LocalDateTime orderDate, int totalPrice, String status, int pointUsed, int cashAmount) {
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
        this.status = status;
        this.pointUsed = pointUsed;
        this.cashAmount = cashAmount;
    }
}