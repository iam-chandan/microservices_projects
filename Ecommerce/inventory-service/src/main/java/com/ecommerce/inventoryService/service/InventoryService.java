package com.ecommerce.inventoryService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.inventoryService.dto.InventoryRequest;
import com.ecommerce.inventoryService.entity.InventoryEntity;
import com.ecommerce.inventoryService.repository.InventoryRepository;


@Service
public class InventoryService {
	
	@Autowired
	private InventoryRepository inventoryRepository;
	
	@Transactional
	public int addInventory(InventoryRequest inventoryRequest) {
		inventoryRepository.findByProductId(inventoryRequest.getProductId())
						.ifPresent(inv -> {
							throw new RuntimeException("Inventory already exist for this product");
						});
		
		InventoryEntity invEntity = InventoryEntity.builder()
						.productId(inventoryRequest.getProductId())
						.quentity(inventoryRequest.getQuentity())
						.status(inventoryRequest.getQuentity() > 0 ? "IN_STOCK" : "OUT_OF_STOCK")
						.build();
		return inventoryRepository.save(invEntity).getId();
	}

	public boolean updateInventory(String productId, int qty) {
		InventoryEntity inventory = inventoryRepository.findByProductId(productId)
				.orElseThrow(() -> new RuntimeException("Inventory is not part of product : " + productId));
		
		int quentity = inventory.getQuentity() + qty;
		
		if(quentity < 0) {
			throw new RuntimeException("Insufficient stock for product : " + productId);
		}
		
		inventory.setQuentity(quentity);
		inventory.setStatus(quentity > 0 ? "IN_STOCK" : "OUT_OF_STOCK");
		InventoryEntity inveEntity = inventoryRepository.save(inventory);
		return inveEntity.getId() != 0;
	}

}
