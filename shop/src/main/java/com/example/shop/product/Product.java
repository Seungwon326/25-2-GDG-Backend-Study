package com.example.shop.product;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name ="products")
@Getter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="product_id")
    private Long Id;

    @Column(name = "productName")
    private String productName;

    public Product(String productName) {
        this.productName = productName;
    }


    public void updateInfo (String productName) {
        this.productName = productName;
    }

}
