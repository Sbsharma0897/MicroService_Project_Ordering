package com.inventoryservice.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inventoryservice.Model.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    List<Inventory> findBySkuCodeIn(List<String> skuCode);
}