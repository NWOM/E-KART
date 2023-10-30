package com.sagnik.ProductService.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRespponse {
    private String productName;
    private long productId;
    private long quantity;
    private long price;

}
