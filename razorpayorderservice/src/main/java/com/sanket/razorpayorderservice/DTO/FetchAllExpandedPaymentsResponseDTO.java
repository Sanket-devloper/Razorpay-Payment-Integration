package com.sanket.razorpayorderservice.DTO;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FetchAllExpandedPaymentsResponseDTO {
    String id;
    Integer amount;
    String entity;
    Integer amountPaid;
    Integer amountDue;
    String currency;
    String receipt;
    String status;
    Integer attempts;
    Long createdAt;
    PaymentDTO payment;
    String offerId;
    Map<String, String> notes;
    TokenDTO token;
    
}
