package com.sagnik.PaymentService.controller;

import com.sagnik.PaymentService.model.PaymentRequest;
import com.sagnik.PaymentService.model.PaymentResponse;
import com.sagnik.PaymentService.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @PostMapping
    public ResponseEntity<Long> doPayment(@RequestBody PaymentRequest paymentRequest){
       return new ResponseEntity<>(
               paymentService.doPayment(paymentRequest),
               HttpStatus.OK
       );
    }
    //Fetching Payment details for getOrder  details API
    @GetMapping("/order/{orderId}")
    public ResponseEntity<PaymentResponse> getPaymentDetailsByOrderId(@PathVariable String orderId){
        return new ResponseEntity<>(paymentService.getPaymentDetailsByOrderId(orderId),
                HttpStatus.OK);
    }
}
