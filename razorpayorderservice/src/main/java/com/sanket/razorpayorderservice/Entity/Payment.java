package com.sanket.razorpayorderservice.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "payments")
public class Payment {
    
    @Id
    @Column(name = "payment_id")
    String id;

    String entity;

    Integer amount;

    String currency;

    String status;

    String method;

    @Column(name = "order_id")
    String orderId;

    String description;

    Boolean international;

    @Column(name = "refund_status")
    String refundStatus;

    @Column(name = "amount_refunded")
    Integer amountRefunded;

    Boolean captured;

    String email;

    String contact;

    Integer fee;

    Integer tax;

    @Column(name = "error_code")
    String errorCode;

    @Column(name = "error_description")
    String errorDescription;

    @Column(name = "error_source")
    String errorSource;

    @Column(name = "error_step")
    String errorStep;

    @Column(name = "error_reason")
    String errorReason;

    @Column(name = "created_at")
    Long createdAt;

    @Column(name = "card_id")
    String cardId;

    String wallet;

    String bank;

    //AcquirerData
    @Column(name = "rrn")
    String rrn;

    @Column(name = "authentication_reference_number")
    String authenticationReferenceNumber;

    @Column(name = "bank_transaction_id")
    String bankTransactionId;

    //UPI
    @Column(name = "payer_account_type")
    String payerAccountType;

    @Column(name = "vpa")
    String vpa;

}
