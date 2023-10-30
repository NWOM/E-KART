package com.sagnik.OrderService.config;

import com.sagnik.OrderService.external.decoder.CustomErrorDecoder;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {
    @Bean //so now whenever the error decoder is needed it will pass the customerror decoder
    ErrorDecoder errorDecoder(){
        return new CustomErrorDecoder();
    }
}
