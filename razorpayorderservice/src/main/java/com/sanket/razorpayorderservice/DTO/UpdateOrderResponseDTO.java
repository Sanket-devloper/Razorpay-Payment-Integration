package com.sanket.razorpayorderservice.DTO;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateOrderResponseDTO {
    String id;

    String entity;

    Integer amount;

    Integer amountPaid;

    Integer amountDue;

    String currency;

    String receipt;

    String offerId;

    String status;

    Integer attempts;

    Map<String, String> notes;

    Long createdAt;

}
