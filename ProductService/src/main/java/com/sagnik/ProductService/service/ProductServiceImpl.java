package com.sagnik.ProductService.service;

import com.sagnik.ProductService.entity.Product;
import com.sagnik.ProductService.exception.ProductServiceCustomException;
import com.sagnik.ProductService.model.ProductRequest;
import com.sagnik.ProductService.model.ProductRespponse;
import com.sagnik.ProductService.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service //to denote its a service class
@Log4j2
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;

    @Override
    public long addProduct(ProductRequest productRequest) {
        log.info("Adding product..");
        Product product= Product.builder().productName(productRequest.getName())
                .quantity(productRequest.getQuantity())
                .price(productRequest.getPrice())
                .build();
       product=productRepository.save(product);
        log.info("product created");
        return product.getProductId();
    }

    @Override
    public ProductRespponse getProductById(long ProductId) {
        log.info("Get the product for productId{} ",ProductId);
        Product product=productRepository.findById(ProductId)
                .orElseThrow(() ->  new ProductServiceCustomException("Product with given id not found","PRODUCT_NOT_FOUND"));
        ProductRespponse productRespponse=new ProductRespponse();
        BeanUtils.copyProperties(product,productRespponse);


        return productRespponse;
    }

    @Override
    public void reduceQuantity(long productId, long quantity) {
        log.info("REDUCE QUANTITY{} for Id: {} ",quantity,productId);
        Product product=productRepository.findById(productId)
                .orElseThrow(()->new ProductServiceCustomException("Product with given id not found","PRODUCT_NOT_FOUND"));
        if(product.getQuantity()<quantity){
            throw new ProductServiceCustomException("Proudct does not have sufficient quantity","INSUFFICIENT_QUANTITY");
        }
        product.setQuantity(product.getQuantity()-quantity);
        productRepository.save(product);
        log.info("PRODUCT QUANTITY UPDATED SUCESFFULLY");

    }
}
