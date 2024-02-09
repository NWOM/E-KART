package com.sagnik.OrderService.service;

import com.sagnik.OrderService.entity.OrderEntity;
import com.sagnik.OrderService.exception.CustomException;
import com.sagnik.OrderService.external.client.PaymentService;
import com.sagnik.OrderService.external.client.ProductService;
import com.sagnik.OrderService.external.request.PaymentRequest;
import com.sagnik.OrderService.external.response.PaymentResponse;
import com.sagnik.OrderService.external.response.ProductRespponse;
import com.sagnik.OrderService.model.OrderRequest;
import com.sagnik.OrderService.model.OrderResponse;
import com.sagnik.OrderService.repository.OrderRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;

//This class will act as a service layer
@Service
@Log4j2
public class OrderServiceIml implements OrderService{

    @Autowired //to inject it
    private OrderRepository orderRepository;
    @Autowired
    private ProductService productService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private RestTemplate restTemplate;
    @Override
    public long placeOrder(OrderRequest orderRequest) {
        //this placeOrder() API needs to connect to the repository layer cuz the particular object or orderEntity objectwe create
        //needs to store to DATABASE
        //Order Entity -> Save the data with Status Order created
        //Product Service -> Block Products(Reduce the Quantity)
        //Payment Service -> Payments -> Sucess -> COMPLETE ,Else
        //CANCELLED
        log.info("Placing Order Request:{}", orderRequest);
        productService.reduceQuantity(orderRequest.getProductId(),orderRequest.getQuantity());
        log.info("Creating Order with status Order Created ");
        OrderEntity orderEntity = OrderEntity.builder()
                .amount(orderRequest.getTotalAmount())
                .productId(orderRequest.getProductId())
                .orderStatus("CREATED")
                .orderDate(Instant.now())
                .quantity(orderRequest.getQuantity())
                .build();

         orderEntity= orderRepository.save(orderEntity);

         //ONCE WE REDUCED THE QUANTITY AND PUT THE ORDER WE CAN CALL THE PAYMENT SERVICE
         log.info("CALLING PAYMENT SERVICE TO COMPPLETE THE PAYMENT ");
        PaymentRequest paymentRequest=PaymentRequest.builder()
                .orderId(orderEntity.getId())
                .paymentMode(orderRequest.getPaymentMode())
                .amount(orderRequest.getTotalAmount())
                .build();
        String orderstatus=null;
        try {
            paymentService.doPayment(paymentRequest);
            log.info("PAYMENT DONE SUCESS CHANGING THE ORDER STATUS TO PLACED");
            orderstatus="PLACED";

        }catch (Exception e){
            log.error("ERROR occured in the payment. Changing Order Status to payment failed :) ");
            orderstatus="FAILED";
        }
        orderEntity.setOrderStatus(orderstatus);
        orderRepository.save(orderEntity);
         log.info("ORDER CREATED WITH ORDER ID {}",orderEntity.getId());
          return orderEntity.getId();
    }

    @Override
    public OrderResponse getOrderDetails(long orderId) {
        log.info("GET ORDER DETAILS FOR ORDER ID :{} ",orderId);
        OrderEntity orderEntity=orderRepository.findById(orderId)
                .orElseThrow(()->new CustomException("Order not found for OrderId"+orderId,"NOT_FOUND",404));
        ProductRespponse productRespponse=restTemplate.getForObject("http://PRODUCT-SERVICE/product/"+orderEntity.getProductId(),ProductRespponse.class);
        log.info("Invoking product service to fetch the product for id:{}",orderEntity.getProductId());
        OrderResponse.ProductDetail productDetail=OrderResponse.ProductDetail.builder()
                .productName(productRespponse.getProductName())
                .productId(productRespponse.getProductId())
                .build();
        
        log.info("GETTING PAYMENT INFO IN THE PAYMENT SERVICE");
        PaymentResponse paymentResponse=restTemplate.getForObject("http://PAYMENT-SERVICE/payment/order/"+orderEntity.getProductId(),PaymentResponse.class);
        OrderResponse.PaymentDetails paymentDetails=OrderResponse.PaymentDetails.builder()
                .paymentId(paymentResponse.getPaymentId())
                .paymentDate(paymentResponse.getPaymentDate())
                .paymentMode(paymentResponse.getPaymentMode())
                .paymentStatus(paymentResponse.getStatus())

                .build();
        OrderResponse orderResponse=OrderResponse.builder()
                .orderId(orderEntity.getId())
                .orderStatus(orderEntity.getOrderStatus())
                .amount(orderEntity.getAmount())
                .orderDate(orderEntity.getOrderDate())
                .productDetail(productDetail)
                .build();
        return orderResponse;
    }
}
