package com.sanket.razorpayorderservice.DTO;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FetchPaymentsResponseDTO {
    
    String id;

    String entity;

    Integer amount;

    String currency;

    String status;

    String orderId;

    String invoiceId;

    Boolean international;

    String method;

    Integer amountRefunded;

    String refundStatus;

    Boolean captured;

    String description;

    String cardId;

    String bank;

    String wallet;

    String vpa;

    String email;

    String contact;

    Map<String, String> notes;

    Integer fee;

    Integer tax;

    String errorCode;

    String errorDescription;

    String errorSource;

    String errorStep;

    String errorReason;

    AcquirerDataDTO acquirerData;

    Long createdAt;

    UpiDTO upi;
}
