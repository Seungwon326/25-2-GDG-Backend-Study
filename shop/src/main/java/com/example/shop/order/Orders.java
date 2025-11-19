package com.example.shop.order;

import com.example.shop.member.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Orders {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_id")
    private Long orderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "orderDate")
    private LocalDateTime orderDate;

    @Column(name = "total_price")
    private int totalPrice;

    @Column(name ="status",length = 20)
    private String status;

    @Column(name = "point_used")
    private int pointUsed;

    @Column(name = "cash_amount")
    private int cashAmount;

    public Orders(LocalDateTime orderDate, int totalPrice, String status, int pointUsed, int cashAmount) {

        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
        this.status = status;
        this.pointUsed = pointUsed;
        this.cashAmount = cashAmount;
    }

}