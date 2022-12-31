package com.inventoryservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import com.inventoryservice.Model.Inventory;
import com.inventoryservice.Repo.InventoryRepository;

@SpringBootApplication
@EnableEurekaClient
public class InventoryserviceApplication implements CommandLineRunner{
	
	@Autowired
	private InventoryRepository inventoryRepository;

	public static void main(String[] args) {
		SpringApplication.run(InventoryserviceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		 Inventory inventory = new Inventory();
		 inventory.setId((long)1);
         inventory.setSkuCode("iphone_13");
         inventory.setQuantity(100);

         Inventory inventory1 = new Inventory();
         inventory.setId((long)2);
         inventory1.setSkuCode("iphone_13_red");
         inventory1.setQuantity(0);

         inventoryRepository.save(inventory);
         inventoryRepository.save(inventory1);
	}

}
