package com.ProductService.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ProductService.Model.Product;

public interface ProductRepo extends JpaRepository<Product, Integer>{

}
