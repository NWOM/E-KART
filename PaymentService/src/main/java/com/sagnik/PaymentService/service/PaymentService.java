package com.sagnik.PaymentService.service;

import com.sagnik.PaymentService.model.PaymentRequest;
import com.sagnik.PaymentService.model.PaymentResponse;

public interface PaymentService {
    long doPayment(PaymentRequest paymentRequest);

    PaymentResponse getPaymentDetailsByOrderId(String orderId);
}
