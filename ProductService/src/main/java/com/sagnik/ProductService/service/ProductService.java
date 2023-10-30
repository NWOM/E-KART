package com.sagnik.ProductService.service;

import com.sagnik.ProductService.model.ProductRequest;
import com.sagnik.ProductService.model.ProductRespponse;

public interface ProductService {   long addProduct(ProductRequest productRequest);

    ProductRespponse getProductById(long ProductId);

    void reduceQuantity(long productId, long quantity);
}
