package com.sagnik.ProductService.exception;

import lombok.Data;

@Data
public class ProductServiceCustomException extends RuntimeException{
    private String errcode;
    public  ProductServiceCustomException(String message,String errcode){
        super(message);
        this.errcode=errcode;
    }

}
