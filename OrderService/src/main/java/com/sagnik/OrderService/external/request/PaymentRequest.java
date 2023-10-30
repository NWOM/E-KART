package com.sagnik.OrderService.external.request;

import com.sagnik.OrderService.model.PaymentMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentRequest {
    //REQUEST CLASS
    private long orderId;
    private long amount;
    private String referenceno;
    private PaymentMode paymentMode;


}
