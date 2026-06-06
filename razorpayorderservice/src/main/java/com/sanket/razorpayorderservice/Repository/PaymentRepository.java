package com.sanket.razorpayorderservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sanket.razorpayorderservice.Entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, String> {
    
}
