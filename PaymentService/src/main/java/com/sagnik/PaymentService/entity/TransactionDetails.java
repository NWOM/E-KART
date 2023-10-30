package com.sagnik.PaymentService.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
@Entity
@Table(name="TRANSACTION_DETAILS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "ORDER_ID")
    private long orderid;
    @Column(name = "PAYMENT_MODE")
    private String paymentmode;
    @Column(name = "REFERENCE_NO")
    private String referenceno;
    @Column(name = "DATE")
    private Instant paymentdate;
    @Column(name = "STATUS")
    private String paymentstatus;
    @Column(name = "AMT")
    private long amount;

}
