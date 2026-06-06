package com.sanket.razorpayorderservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sanket.razorpayorderservice.Entity.Order;

public interface OrderRepository extends JpaRepository<Order, String> {
    
}
