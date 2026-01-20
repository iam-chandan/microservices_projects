package com.ecommerce.inventoryService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.inventoryService.dto.InventoryRequest;
import com.ecommerce.inventoryService.service.InventoryService;


@RestController
@RequestMapping("inventory")
public class InventoryController {
	
	@Autowired
	private InventoryService inventoryService;
	
	@PostMapping
	public ResponseEntity<String> addInventory(@RequestBody InventoryRequest inventoryRequest ) {
		int id = inventoryService.addInventory(inventoryRequest);
		return ResponseEntity.ok("Inventory saved successfully with id : " + id);
	}
	
	@PutMapping("/update/{productId}")
	public ResponseEntity<String> updateInventory(@PathVariable String productId,int qty){
		inventoryService.updateInventory(productId,qty);
		return ResponseEntity.ok("Inventory updated successfully for product : " + productId);
	}
	
}
