package com.sanket.razorpayorderservice.DTO;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

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
    PaymentsCollectionDTO payments;
    String offerId;
    Map<String, String> notes;
    TokenDTO token;
    
}
