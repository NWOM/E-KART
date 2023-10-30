package com.sagnik.ProductService.controller;

import com.sagnik.ProductService.model.ProductRequest;
import com.sagnik.ProductService.model.ProductRespponse;
import com.sagnik.ProductService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//WE NEED A CONTROLLER TO HANDLE OUR ALL RQST
//Create Product APi
//CONTROLLER LAYER NEEDS THE OBJECT OF FIRST SERVICE LAYER CUZ FROM CONTROLLER LAYER WE WOULD BE CALLING BUSINESS LAYER
@RestController
@RequestMapping("/product")    //to map a particular thing
public class ProductController {
    @Autowired //to inject it
    private ProductService productService;
    //creating a method that will handle addproduct for us
    @PostMapping
    public ResponseEntity<Long> addProduct(@RequestBody ProductRequest productRequest){
        //this addProduct() will take any of the request as object body
        long productId=productService.addProduct(productRequest);
        return new ResponseEntity<>(productId, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductRespponse> getProductById(@PathVariable("id") long ProductId){
      ProductRespponse productRespponse=
              productService.getProductById(ProductId);
      return  new ResponseEntity<>(productRespponse,HttpStatus.OK);
    }
    @PutMapping("/reduceQuantity/{id}")
    public ResponseEntity<Void> reduceQuantity(
            @PathVariable("id") long productId,
            @RequestParam long quantity){
            productService.reduceQuantity(productId,quantity);
            return new ResponseEntity<>(HttpStatus.OK);
    }

}
