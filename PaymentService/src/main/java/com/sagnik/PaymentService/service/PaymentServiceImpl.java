package com.sagnik.PaymentService.service;

import com.sagnik.PaymentService.entity.TransactionDetails;
import com.sagnik.PaymentService.model.PaymentMode;
import com.sagnik.PaymentService.model.PaymentRequest;
import com.sagnik.PaymentService.model.PaymentResponse;
import com.sagnik.PaymentService.repository.TransactionDetailsRepo;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service //this class is a service layer
@Log4j2
public class PaymentServiceImpl implements  PaymentService{
    //here we need object of the repository layer
    @Autowired
    private TransactionDetailsRepo transactionDetailsRepo;
    @Override
    public long doPayment(PaymentRequest paymentRequest) {
        log.info("Recording Payment Details: {}",paymentRequest);
        //we need to create a transaction detail from the paymentRequest we get to save in DATABASE
        TransactionDetails transactionDetails=
                TransactionDetails.builder()
                        .paymentdate(Instant.now())
                        .paymentmode(paymentRequest.getPaymentMode().name())
                        .paymentstatus("SUCESS")
                        .orderid(paymentRequest.getOrderId())
                        .referenceno(paymentRequest.getReferenceno())
                        .amount(paymentRequest.getAmount())
                        .build();
        transactionDetailsRepo.save(transactionDetails);
        log.info("TRANSACTION COMPLETED WITH ID: {}",transactionDetails.getId());
        return transactionDetails.getId();
    }

    @Override
    public PaymentResponse getPaymentDetailsByOrderId(String orderId) {
        log.info("Getting payment details ",orderId);
        TransactionDetails transactionDetails=transactionDetailsRepo.findByOrderId(Long.valueOf(orderId));
        PaymentResponse paymentResponse=PaymentResponse.builder()
                .paymentId(transactionDetails.getId())
                .paymentDate(transactionDetails.getPaymentdate())
                .paymentMode(PaymentMode.valueOf(transactionDetails.getPaymentmode()))
                .orderId(transactionDetails.getId())
                .amount(transactionDetails.getAmount())

                .build();

        return paymentResponse;
    }
    //here we r gonna store the transaction details
}
