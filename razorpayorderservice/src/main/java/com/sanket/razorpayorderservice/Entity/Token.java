package com.sanket.razorpayorderservice.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "Token")
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "auth_type")
    String authType;
    @Column(name = "expire_at")
    Integer expireAt;
    @Column(name = "failure_reason")
    String failureReason;
    @Column(name = "first_payment_amount")
    Integer firstPaymentAmount;
    @Column(name = "frequency")
    String frequency;
    @Column(name = "max_amount")
    Integer maxAmount;
    @Column(name = "recurring_status")
    String recurringStatus;
}
