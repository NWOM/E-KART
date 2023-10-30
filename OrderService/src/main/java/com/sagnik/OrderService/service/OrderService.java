package com.sagnik.OrderService.service;

import com.sagnik.OrderService.model.OrderRequest;
import com.sagnik.OrderService.model.OrderResponse;

public interface OrderService {

    long placeOrder(OrderRequest orderRequest);

    OrderResponse getOrderDetails(long orderId);
}
