package com.ecommerce.orderService.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "inventory-service")
public interface InventoryClient {
	
	@PutMapping("/update/{productId}")
	boolean updateInventory(@PathVariable String productId,int qty);
}
