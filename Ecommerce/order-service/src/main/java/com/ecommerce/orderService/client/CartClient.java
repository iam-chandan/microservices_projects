package com.ecommerce.orderService.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ecommerce.orderService.dto.CartResponse;

@FeignClient(name = "cart-service")
public interface CartClient {

    @GetMapping("/cart/{userId}")
    CartResponse getCart(@PathVariable String userId);

    @DeleteMapping("/cart/clear/{userId}")
    void clearCart(@PathVariable String userId);
}

