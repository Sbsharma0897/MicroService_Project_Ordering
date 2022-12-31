package com.orderservice.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orderservice.Model.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}