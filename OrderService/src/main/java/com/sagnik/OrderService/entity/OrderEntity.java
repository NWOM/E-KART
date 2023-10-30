package com.sagnik.OrderService.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name="ORDER_DETAILS")
@Data
@AllArgsConstructor
@NoArgsConstructor //for default constructor
@Builder
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name="PRODUCT_ID")
    private long productId;
    @Column(name="QUANTITY")
    private long quantity;
    @Column(name="ORDER_DATE")
    private Instant orderDate;
    @Column(name="STATUS")
    private String orderStatus;
    @Column(name="TOTAL_AMOUNT")
    private long amount;

}
