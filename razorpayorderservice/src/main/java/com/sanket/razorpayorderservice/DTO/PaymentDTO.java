package com.sanket.razorpayorderservice.DTO;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {
    String id;
    String entity;
    Integer amount;
    String currency;
    String status;
    String method;
    String orderId;
    String description;

    Boolean international;

    String refundStatus;
    Integer amountRefunded;

    Boolean captured;

    String email;
    String contact;

    Integer fee;
    Integer tax;

    String errorCode;
    String errorDescription;
    String errorSource;
    String errorStep;
    String errorReason;

    Map<String, String> notes;

    Long createdAt;

    String cardId;

    String wallet;

    String bank;


    AcquirerDataDTO acquirerData;

    UpiDTO upi;

    String vpa;
}
