package com.sanket.razorpayorderservice.DTO;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderResponseDTO {
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
    String offerId;
    Map<String, String> notes;

}
